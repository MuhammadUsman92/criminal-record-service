package com.muhammadusman92.criminalrecordservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE crime SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    private Location incidentLocation;
    private Date incidentDate;
    @Column(length = 100)
    private String type;
    @OneToMany(mappedBy = "crime",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Fir> firSet=new HashSet<>();
    @OneToMany(mappedBy = "crime")
    private Set<CriminalStatus> criminalStatuses = new HashSet<>();
    private boolean deleted = Boolean.FALSE;
}
