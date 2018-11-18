package com.tlimskech.marketplace.category.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RepairRepository extends JpaRepository<Repair, Long>, QuerydslPredicateExecutor<Repair> {
}
