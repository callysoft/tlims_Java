package com.tlimskech.marketplace.category.favorite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByPostIdAndCreatedBy(Long postId, String user);
    List<Favorite> findByCreatedBy(String user);
}
