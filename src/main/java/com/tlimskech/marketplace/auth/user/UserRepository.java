package com.tlimskech.marketplace.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    User findByEmail(String email);
    Optional<User> findByEmailAndVerifiedIsTrue(String email);
    Optional<User> findByEmailAndStatusIsTrue(String email);
    Optional<User> findByEmailAndIdNot(String email, Long id);
    Optional<User> findByCode(String code);
}
