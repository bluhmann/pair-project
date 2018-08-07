package com.solstice.cloud.employeeservice.repositories;

import com.solstice.cloud.employeeservice.entities.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Employee findByNumber(int number) {
        return entityManager.find(Employee.class, number);
    }

}
