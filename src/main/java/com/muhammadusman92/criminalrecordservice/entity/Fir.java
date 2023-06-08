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
@SQLDelete(sql = "UPDATE fir SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Fir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150)
    private String policeStationName;
    @Column(length = 100)
    private String complainantName;
    private Date complainantDate;
    @Column(length = 100)
    private String complainantCategory;
    @Column(length = 100)
    private String assignedOfficerName;
    @Column(length = 100)
    private String incidentReport;
    @Column(length = 20)
    private String contactNum;
    @Column(length = 20)
    private String officerCell;
    private String document_1;
    private String document_2;
    @ManyToOne(fetch = FetchType.LAZY)
    private Crime crime;
    private boolean deleted = Boolean.FALSE;
}
