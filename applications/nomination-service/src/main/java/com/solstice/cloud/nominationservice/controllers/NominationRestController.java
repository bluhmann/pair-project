package com.solstice.cloud.nominationservice.controllers;

import com.solstice.cloud.nominationservice.entities.Nomination;
import com.solstice.cloud.nominationservice.repositories.NominationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class NominationRestController {

    private final String ID = "id";
    private final String START_DATE = "startDate";
    private final String END_DATE = "endDate";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    private NominationRepository repository;

    public NominationRestController(NominationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/nominations")
    public List<Nomination> getNominations(@RequestParam() Map<String, String> parameters) {
        if (parameters.containsKey(ID)) {
            return getNominationsForEmployee(Integer.parseInt(parameters.get(ID)));
        } else if (parameters.containsKey(START_DATE) && parameters.containsKey(END_DATE)) {
            return getNominationsForDateRange(parameters.get(START_DATE), parameters.get(END_DATE));
        } else if (parameters.isEmpty()) {
            return getNominationsForCurrentWeek();
        }

        return null;
    }

    @GetMapping("/weeklyNominations")
    public List<Nomination> getNominationsForWeek(@RequestParam("date") String date) {
        try {
            return repository.nominationsByWeek(dateFormatter.parse(date));
        } catch (ParseException e) {
            logger.error(e.toString());
            return null;
        }
    }

    @PostMapping("/addNomination")
    public void addNomination(@RequestParam(name = "nominatedEmployeeId", required = true) String nominatedEmployeeId,
                              @RequestParam(name = "nominatingEmployeeId", required = true) String nominatingEmployeeId,
                              @RequestParam(name = "principles", required = true) String principles,
                              @RequestParam(name = "description", required = true) String description) {
        Nomination nomination = new Nomination(Integer.parseInt(nominatedEmployeeId), Integer.parseInt(nominatingEmployeeId), new Date(), principles, description);
        repository.addNomination(nomination);
    }

    // Private Helper Methods

    private List<Nomination> getNominationsForEmployee(Integer id) {
        logger.info("requesting employee number: " + id);
        return repository.nominationsForEmployee(id);
    }

    private List<Nomination> getNominationsForDateRange(String startDate, String endDate) {
        try {
            return repository.nominationsForRange(dateFormatter.parse(startDate), dateFormatter.parse(endDate));
        } catch (ParseException e) {
            logger.error(e.toString());
            return null;
        }
    }

    private List<Nomination> getNominationsForCurrentWeek() {
        return repository.nominationsByWeek(new Date());
    }

}
