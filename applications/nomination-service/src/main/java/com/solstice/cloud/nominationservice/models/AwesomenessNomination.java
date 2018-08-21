package com.solstice.cloud.nominationservice.models;

import com.solstice.cloud.nominationservice.entities.Employee;
import com.solstice.cloud.nominationservice.entities.Nomination;

import java.util.Date;

public class AwesomenessNomination {

    private String employeeFirstName;
    private String employeeLastName;
    private String nominatorFirstName;
    private String nominatorLastName;
    private String principles;
    private String description;
    private Date date;

    public AwesomenessNomination(Nomination nomination, Employee employee, Employee nominator) {
        this.employeeFirstName = employee.getFirstName();
        this.employeeLastName = employee.getLastName();
        this.nominatorFirstName = nominator.getFirstName();
        this.nominatorLastName = nominator.getLastName();
        this.principles = nomination.getPrinciples();
        this.description = nomination.getDescription();
        this.date = nomination.getDate();
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

    @Override
    public String toString() {
        return "firstName: " + this.employeeFirstName + "\nlastName: " + this.employeeLastName + "\nnominatorFirstName: " + this.nominatorFirstName + "\nnominatorLastName: " + this.nominatorLastName + "\nprinciples: " + this.principles + "\ndescription: " + this.description + "\ndate: " + this.date + "\n--------\n";
    }
}
