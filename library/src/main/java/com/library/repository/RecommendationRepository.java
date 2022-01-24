package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Recommendation;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {

}
