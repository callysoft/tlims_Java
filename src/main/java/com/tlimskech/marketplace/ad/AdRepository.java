package com.tlimskech.marketplace.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AdRepository extends JpaRepository<Ad, Long>, QuerydslPredicateExecutor<Ad> {
}
