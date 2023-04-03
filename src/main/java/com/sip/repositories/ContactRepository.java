package com.sip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sip.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {


}