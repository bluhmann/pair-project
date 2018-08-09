package com.solstice.cloud.nominationservice.models;

import java.util.Date;

public class AwesomenessNomination {

    private String employeeFirstName;
    private String employeeLastName;
    private String nominatorFirstName;
    private String nominatorLastName;
    private String principles;
    private String description;
    private Date date;

    public AwesomenessNomination(String employeeFirstName, String employeeLastName, String nominatorFirstName, String nominatorLastName, String principles, String description, Date date) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.nominatorFirstName = nominatorFirstName;
        this.nominatorLastName = nominatorLastName;
        this.principles = principles;
        this.description = description;
        this.date = date;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getNominatorFirstName() {
        return nominatorFirstName;
    }

    public String getNominatorLastName() {
        return nominatorLastName;
    }

    public String getPrinciples() {
        return principles;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
