package com.muhammadusman92.criminalrecordservice.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FirDto {
    private int id;
    private String policeStationName;
    private String complainantName;
    private Date complainantDate;
    private String complainantCategory;
    private String assignedOfficerName;
    private String incidentReport;
    private String contactNum;
    private String officerCell;
    private String document_1;
    private String document_2;
    private CrimeDto crime;
}
