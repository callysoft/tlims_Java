package com.tlimskech.marketplace.category.commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CommercialToolRepository extends JpaRepository<CommercialTool, Long>, QuerydslPredicateExecutor<CommercialTool> {
}
