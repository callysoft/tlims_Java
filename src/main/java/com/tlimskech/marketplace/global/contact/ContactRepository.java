package com.tlimskech.marketplace.global.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long>, QuerydslPredicateExecutor<Contact> {

    Optional<Contact> findByNameAndPhoneNumberOrEmail(String name, String phoneNumber, String email);
}
