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

    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam("id") String id) {
        return repository.getEmployeeForID(Integer.parseInt(id));
    }

}
