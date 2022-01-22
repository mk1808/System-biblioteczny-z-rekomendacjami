package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.enums.ChangeProposalStatus;
import com.library.model.ChangeProposal;

@Repository
public interface ChangeProposalRepository extends JpaRepository<ChangeProposal, Long> {

	List<ChangeProposal> getChangeProposalsByBookId(Long bookId);

	List<ChangeProposal> getByStatus(ChangeProposalStatus waiting);

}
