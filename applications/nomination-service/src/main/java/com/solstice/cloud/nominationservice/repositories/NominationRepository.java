package com.solstice.cloud.nominationservice.repositories;

import com.solstice.cloud.nominationservice.entities.Employee;
import com.solstice.cloud.nominationservice.entities.Nomination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class NominationRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Nomination[] nominationsByWeek() {
        return nominationsForRange(new Date(), new Date());
    }

    public Nomination[] nominationsForRange(Date startDate, Date endDate) {
        return new Nomination[] { new Nomination() };
    }

    public List<Nomination> nominationsForEmployee(Integer employeeNumber) {
        Query query = entityManager.createQuery("SELECT n FROM Nomination n WHERE n.nominatedEmployee=" + employeeNumber.toString());

        return query.getResultList();
    }

}
