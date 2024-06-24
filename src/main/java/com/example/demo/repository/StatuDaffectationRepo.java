package com.example.demo.repository;

import com.example.demo.entites.Session;
import com.example.demo.entites.StatuDaffectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatuDaffectationRepo extends JpaRepository<StatuDaffectation, Long> {

    @Query("SELECT s FROM StatuDaffectation s WHERE s.Nomstatutdaff = :nomstatutdaff")
    StatuDaffectation findByNomstatutdaff(@Param("nomstatutdaff") String nomstatutdaff);
}
