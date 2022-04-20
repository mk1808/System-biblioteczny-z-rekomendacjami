package com.library.nosql.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public class Item {

	@Id
	private String id;

	private String name;
	private int quantity;
	private String category;

	public Item(String id, String name, int quantity, String category) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.category = category;
	}
}