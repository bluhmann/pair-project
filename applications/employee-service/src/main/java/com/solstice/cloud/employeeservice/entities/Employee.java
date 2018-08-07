package com.solstice.cloud.employeeservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer employeeNumber;
    private String firstName;
    private String lastName;
    private String office;
    private String title;
    private String email;
    @Column(name="IMAGE_URL")
    private String imageURL;

    public Employee() { }

    public Employee(String firstName, String lastName, String office, String title, String email, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.title = title;
        this.email = email;
        this.imageURL = imageURL;
    }

}
