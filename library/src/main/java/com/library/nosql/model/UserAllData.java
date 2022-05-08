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
import lombok.Singular;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("usersAllData")
public class UserAllData {
	
	@Id
	private String id;
	private String userId;
	@Singular
	private List<SingleActionData> actions;
	
	public UserAllData withSingleAction(SingleActionData action) {
		if (this.actions == null) {this.actions = new ArrayList();}
		this.actions.add(action);
		return this;
	}

	
}
	