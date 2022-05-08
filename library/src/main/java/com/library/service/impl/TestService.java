package com.library.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.nosql.model.Item;
import com.library.nosql.model.Subitem;
import com.library.nosql.model.RecommendationData;
import com.library.nosql.repository.ItemRepository;
import com.library.nosql.repository.RecommendationDataRepository;

@Service
public class TestService {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RecommendationDataRepository recommendationDataRepository;
	
	public Item save() {
		Item item = new Item("6", "name", 5, "catog");
		Subitem subitem1 = new Subitem("nName1");
		Subitem subitem2 = new Subitem("nName2");
		item.withSubitem(subitem1).withSubitem(subitem2);
		return itemRepository.save(item);
	}
	
	public RecommendationData saveRecommendation() {
		RecommendationData recommendation = new RecommendationData("8", "2", "3", 5L, false, false, new Date(), new Date());
		return recommendationDataRepository.save(recommendation);
	}
	
	public List<RecommendationData> getRecommendation() {
		//RecommendationData recommendation = new RecommendationData("8", "2", "3", 5L, false, false, new Date(), new Date());
		return recommendationDataRepository.findAll();
	}
}
