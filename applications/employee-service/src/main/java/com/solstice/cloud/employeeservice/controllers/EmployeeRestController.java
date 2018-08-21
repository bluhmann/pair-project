package com.solstice.cloud.employeeservice.controllers;

import com.solstice.cloud.employeeservice.entities.Employee;
import com.solstice.cloud.employeeservice.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

    private final String ID = "id";

    private EmployeeRepository repository;

    public EmployeeRestController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/employee", produces = "application/json; charset=UTF-8")
    public Employee getEmployee(@RequestParam("id") String id) {
        Employee employee = repository.getEmployeeForID(Integer.parseInt(id));
        System.out.println("EMPLOYEE_REST_CONTROLLER:\n" + employee);
        return employee;
    }

}
