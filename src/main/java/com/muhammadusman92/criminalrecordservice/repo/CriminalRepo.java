package com.muhammadusman92.criminalrecordservice.repo;

import com.muhammadusman92.criminalrecordservice.entity.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CriminalRepo extends JpaRepository<Criminal,String> {
    @Query(value = "SELECT COUNT(CNIC) FROM Criminal WHERE CNIC =:cnic",nativeQuery=true)
    long existsCNIC(@Param("cnic") String cnic);
}
