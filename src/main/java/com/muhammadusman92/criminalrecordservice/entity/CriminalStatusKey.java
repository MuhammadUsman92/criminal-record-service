package com.muhammadusman92.criminalrecordservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CriminalStatusKey implements Serializable {
    @Column(name = "crime_id")
    int crimeId;
    @Column(name = "criminal_CNIC")
    String criminalCNIC;
}