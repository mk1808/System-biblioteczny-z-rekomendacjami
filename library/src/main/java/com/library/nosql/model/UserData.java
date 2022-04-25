package com.library.nosql.model;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("users")
public class UserData {
	
	@Id
	private String id;
	@Id
	private String userId;
	private String action;
	private String bookId;
	private String value;
	private Long date;
	

}
