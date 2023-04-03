package com.sip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.entities.Actualite;

@Repository
public interface ActualiteRepository  extends JpaRepository<Actualite,Long> {


}