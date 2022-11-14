package com.example.restfull.repositories;

import com.example.restfull.models.Like;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
}
