package com.solstice.cloud.nominationservice.repositories;

import com.solstice.cloud.nominationservice.entities.Nomination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
@Transactional
public class NominationRepository {

    @PersistenceContext
    EntityManager entityManager;


    // start = 6
    // end = 5, week = startWeek + 1
    public List<Nomination> nominationsByWeek(Date weekDate) {
        Calendar startCalendar = GregorianCalendar.getInstance();
        startCalendar.setTime(weekDate);
        Calendar endCalendar = GregorianCalendar.getInstance();
        endCalendar.setTime(weekDate);

        // If we're given a day before Thursday, set our week back 1
        if (startCalendar.get(Calendar.DAY_OF_WEEK) <= 5) {
            startCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        }
        startCalendar.set(Calendar.DAY_OF_WEEK, 6);

        // If we're being given Friday or Saturday, set the week forward 1
        if (endCalendar.get(Calendar.DAY_OF_WEEK) >= 6) {
            endCalendar.add(Calendar.WEEK_OF_YEAR, -1);
        }
        endCalendar.set(Calendar.DAY_OF_WEEK, 6);

        return nominationsForRange(startCalendar.getTime(), endCalendar.getTime());
    }

    public List<Nomination> nominationsForRange(Date startDate, Date endDate) {
        Query query = entityManager.createQuery("SELECT n from Nomination n WHERE n.date BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", startDate, TemporalType.DATE);
        query.setParameter("endDate", endDate, TemporalType.DATE);

        return query.getResultList();
    }

    public List<Nomination> nominationsForEmployee(Integer employeeNumber) {
        Query query = entityManager.createQuery("SELECT n FROM Nomination n WHERE n.nominatedEmployee=" + employeeNumber.toString());

        return query.getResultList();
    }

    public void addNomination(Nomination nomination) {
        entityManager.merge(nomination);
    }

}
