package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

}
