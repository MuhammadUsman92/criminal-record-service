package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatusKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CriminalStatusRepo extends JpaRepository<CriminalStatus, CriminalStatusKey> {
    Page<CriminalStatus> findByIdCrimeId(Integer crimeId, Pageable pageable);
    Page<CriminalStatus> findByIdCriminalCNIC(String criminalCNIC, Pageable pageable);
}
