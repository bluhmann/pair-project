package com.solstice.cloud.nominationservice.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.solstice.cloud.nominationservice.entities.Employee;
import com.solstice.cloud.nominationservice.entities.Nomination;
import com.solstice.cloud.nominationservice.models.AwesomenessNomination;
import com.solstice.cloud.nominationservice.repositories.NominationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private EurekaClient discoveryClient;
    private RestTemplate restTemplate;

    // No @Autowired necessary if we only have one constructor
    public NominationRestController(NominationRepository repository, EurekaClient discoveryClient) {
        this.repository = repository;
        this.discoveryClient = discoveryClient;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/nominations")
    public List<AwesomenessNomination> getNominations(@RequestParam() Map<String, String> parameters) {
        if (parameters.containsKey(ID)) {
            Integer id = Integer.parseInt(parameters.get(ID));

            return getNominationsForEmployee(id);
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

    private List<AwesomenessNomination> getNominationsForEmployee(Integer id) {
        Employee nominatedEmployee = this.getEmployeeForId(id);
        List<Nomination> nominations = repository.nominationsForEmployee(id);
        List<AwesomenessNomination> awesomenessNominations = new ArrayList<>(nominations.size());

        for(Nomination nomination: nominations) {
            Employee nominatingEmployee = this.getEmployeeForId(nomination.getNominator());

            awesomenessNominations.add(new AwesomenessNomination(nomination, nominatedEmployee, nominatingEmployee));

        }

        return awesomenessNominations;
    }

    private List<AwesomenessNomination> getNominationsForDateRange(String startDate, String endDate) {
        try {
            List<Nomination> nominations = repository.nominationsForRange(dateFormatter.parse(startDate), dateFormatter.parse(endDate));

            return convertedNominations(nominations);
        } catch (ParseException e) {
            logger.error(e.toString());
            return null;
        }
    }

    private List<AwesomenessNomination> getNominationsForCurrentWeek() {
        List<Nomination> nominations = repository.nominationsByWeek(new Date());

        return convertedNominations(nominations);
    }

    private Employee getEmployeeForId(Integer id) {
        InstanceInfo instance = this.discoveryClient.getNextServerFromEureka("employee-service", false);
        String getEmployeeURL = instance.getHomePageUrl() + "/employee?id=" + id;

        return this.restTemplate.getForObject(getEmployeeURL, Employee.class);
    }

    private List<AwesomenessNomination> convertedNominations(List<Nomination> nominations) {
        List<AwesomenessNomination> awesomenessNominations = new ArrayList<>(nominations.size());

        for(Nomination nomination: nominations) {
            Employee nominatedEmployee = this.getEmployeeForId(nomination.getNominatedEmployee());
            Employee nominatingEmployee = this.getEmployeeForId(nomination.getNominator());

            awesomenessNominations.add(new AwesomenessNomination(nomination, nominatedEmployee, nominatingEmployee));
        }

        return awesomenessNominations;
    }

}
