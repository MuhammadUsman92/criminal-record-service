package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.Crime;
import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import com.muhammadusman92.criminalrecordservice.entity.CriminalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CrimeRepo extends JpaRepository<Crime, Integer> {

}
