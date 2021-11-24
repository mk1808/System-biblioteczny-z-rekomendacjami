package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.ChangeProposal;

public interface ChangeProposalRepository extends JpaRepository<ChangeProposal, Long> {

}
