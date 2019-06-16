package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.DataGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long>, QuerydslPredicateExecutor<Ad> {

    @Query("select new com.tlimskech.marketplace.core.data.DataGroup(d.brand.code, d.brand.name," +
            "COUNT(d)) from Ad d where d.category.code = ?1 and d.brand.code is not null group by " +
            "d.brand.name having count(d) > 0")
    List<DataGroup> findBrandCount(String catCode);
}
