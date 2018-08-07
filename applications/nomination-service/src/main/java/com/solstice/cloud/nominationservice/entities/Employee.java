package com.solstice.cloud.nominationservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Employee {

    private Integer employeeNumber;
    private String firstName;
    private String lastName;
    private String office;
    private String title;
    private String email;
    private String imageURL;

    public Employee() { }

    public Employee(Integer employeeNumber, String firstName, String lastName, String office, String title, String email, String imageURL) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.title = title;
        this.email = email;
        this.imageURL = imageURL;
    }

}
