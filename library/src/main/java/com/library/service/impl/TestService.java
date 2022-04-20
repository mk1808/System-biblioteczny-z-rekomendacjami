package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.nosql.model.Item;
import com.library.nosql.repository.ItemRepository;

@Service
public class TestService {
	@Autowired
	private ItemRepository itemRepository;
	public Item save() {
		Item item = new Item("6", "name", 5, "catog");
		return itemRepository.save(item);
	}
}
