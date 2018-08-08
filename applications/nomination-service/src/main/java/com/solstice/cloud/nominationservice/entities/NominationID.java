package com.solstice.cloud.nominationservice.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class NominationID implements Serializable {

    private Integer nominatedEmployee;
    private Integer nominator;
    private Date date;

    public NominationID() { }

    public NominationID(Integer nominatedEmployee, Integer nominator, Date date) {
        this.nominatedEmployee = nominatedEmployee;
        this.nominator = nominator;
        this.date = date;
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

    @Override
    public int hashCode() {
        return Objects.hash(getNominatedEmployee(), getNominator(), getDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NominationID)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        NominationID that = (NominationID) obj;

        return Objects.equals(getNominatedEmployee(), that.getNominatedEmployee()) &&
                Objects.equals(getNominator(), that.getNominator()) &&
                Objects.equals(getDate(), that.getDate());
    }

}
