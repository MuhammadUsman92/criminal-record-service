package com.muhammadusman92.criminalrecordservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE criminalStatus SET deleted = true WHERE CNIC=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class CriminalStatus {

    @EmbeddedId
    CriminalStatusKey id;

    @ManyToOne
    @MapsId("crimeId")
    @JoinColumn(name = "crime_id")
    Crime crime;

    @ManyToOne
    @MapsId("criminalCNIC")
    @JoinColumn(name = "criminal_CNIC")
    Criminal criminal;

    private Date arrestDate;
    private Date dischargeDate;
    private int numOfDayInJail;
    private boolean deleted = Boolean.FALSE;
}