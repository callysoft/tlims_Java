package com.tlimskech.marketplace.category.mobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MobileRepository extends JpaRepository<Mobile, Long>, QuerydslPredicateExecutor<Mobile> {
}
