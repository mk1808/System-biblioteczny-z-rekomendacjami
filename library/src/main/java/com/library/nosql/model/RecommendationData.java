package com.library.nosql.model;

import java.util.Date;

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
@Document("recommendations")
public class RecommendationData {

	@Id
	private String id;
	@Id
	private String userId;

	private String bookId;

	private Long rating;
	private Boolean shouldNotRecommend;
	private Boolean shouldNotRecommendType;
	private Date created;
	private Date deleted;

}
