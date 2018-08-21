package com.solstice.cloud.nominationservice.entities;

import java.io.Serializable;

public class Employee implements Serializable {

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

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOffice() {
        return office;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "firstName: " + this.firstName + "\nlastName: " + this.lastName + "\noffice: " + this.office + "\nemail: " + this.email + "\nimageURL: " + this.imageURL + "\n-----\n";
    }
}
