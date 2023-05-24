package com.muhammadusman92.criminalrecordservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CriminalStatusKey implements Serializable {
    @Column(name = "crime_id")
    int crimeId;
    @Column(name = "criminal_CNIC")
    String criminalCNIC;
}