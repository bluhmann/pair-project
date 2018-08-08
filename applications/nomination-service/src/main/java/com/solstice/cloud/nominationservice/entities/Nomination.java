package com.solstice.cloud.nominationservice.entities;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
public class Nomination {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "nominatedEmployee", column = @Column(name="NOMINATED_EMPLOYEE")),
            @AttributeOverride(name = "nominator", column = @Column(name="NOMINATOR")),
            @AttributeOverride(name = "date", column = @Column(name="DATE"))
    })
    private NominationID nominationID;
    private String principles;
    private String description;



}
