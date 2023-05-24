package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalRepo extends JpaRepository<Criminal,String> {
}
