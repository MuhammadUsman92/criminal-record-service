package com.muhammadusman92.criminalrecordservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE criminal SET deleted = true WHERE CNIC=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Criminal {
    @Id
    @Column(name = "CNIC",unique = true,nullable = false,length = 50)
    private String CNIC;
    @Column(length = 100)
    private String Name;
    private int Age;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Gender gender;
    @OneToOne(fetch = FetchType.LAZY)
    private Location location;
    @OneToMany(mappedBy = "criminal")
    private Set<CriminalStatus> criminalStatuses = new HashSet<>();
    private boolean deleted = Boolean.FALSE;
}
