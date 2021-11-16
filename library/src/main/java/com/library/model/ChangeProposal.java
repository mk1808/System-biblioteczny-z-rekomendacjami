package com.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.enums.ChangeProposalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChangeProposal extends BaseEntity {
	
	private String value;
	private String type;
	private ChangeProposalStatus status;
	@ManyToOne(fetch = FetchType.LAZY)
	Book book;

	@ManyToOne(fetch = FetchType.LAZY)
	AppUser user;
	
}