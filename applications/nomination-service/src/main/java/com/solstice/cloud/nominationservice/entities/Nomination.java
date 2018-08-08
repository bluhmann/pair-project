package com.solstice.cloud.nominationservice.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Transactional
public class Nomination {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer nominatedEmployee;
    private Integer nominator;
    private Date date;
    private String principles;
    private String description;

    @Override
    public String toString() {
        return "employeeId: " + this.nominatedEmployee + "\nprinciples: " + this.principles;
    }
}
