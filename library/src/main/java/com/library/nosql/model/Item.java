package com.library.nosql.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public class Item {

	@Id
	private String id;

	private String name;
	private int quantity;
	private String category;
	private List<Subitem> subitems;;

	public Item(String id, String name, int quantity, String category) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.category = category;
	}
	
	public Item withSubitems(List<Subitem> subitems) {
		this.subitems = subitems;
		return this;
	}
	
	public Item withSubitem(Subitem subitem) {
		if (this.subitems == null) {this.subitems = new ArrayList();}
		this.subitems.add(subitem);
		return this;
	}
}