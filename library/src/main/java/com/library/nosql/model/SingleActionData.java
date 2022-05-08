package com.library.nosql.model;

import java.util.ArrayList;
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
@Document("singleActionData")
public class SingleActionData {
	
	@Id
	private String id;
	private String action;
	private String bookId;
	private String value;
	private Long date;

}
