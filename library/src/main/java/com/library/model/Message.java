package com.library.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.library.enums.MessageType;

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
public class Message extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private AppUser user;
	
	private LocalDateTime date;
	private MessageType type;
	@ElementCollection (fetch = FetchType.LAZY)
	@CollectionTable(name = "message_book_ids")
	private List<Long> ids;

}