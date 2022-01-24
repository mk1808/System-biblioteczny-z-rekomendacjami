package com.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Reservation;
import com.library.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID>{

}
