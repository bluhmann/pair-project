package com.solstice.cloud.nominationservice.entities;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Transactional
public class Nomination {

    private Integer nominatedEmployeeNumber;
    private Integer nominatingEmployeeNumber;
    private Date date;
    private String principles;
    private String description;

}
