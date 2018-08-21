package com.solstice.cloud.nominationservice.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Transactional
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer nominatedEmployee;
    private Integer nominator;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String principles;
    private String description;

    public Nomination() { }

    public Nomination(Integer nominatedEmployee, Integer nominator, Date date, String principles, String description) {
        this.nominatedEmployee = nominatedEmployee;
        this.nominator = nominator;
        this.date = date;
        this.principles = principles;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nid: " + this.id + "\nnominatedEmployeeId: " + this.nominatedEmployee + "\nprinciples: " + this.principles + "\ndate: " + this.date + "\n";
    }

    public Integer getId() {
        return id;
    }

    public Integer getNominatedEmployee() {
        return nominatedEmployee;
    }

    public Integer getNominator() {
        return nominator;
    }

    public Date getDate() {
        return date;
    }

    public String getPrinciples() {
        return principles;
    }

    public String getDescription() {
        return description;
    }
}
