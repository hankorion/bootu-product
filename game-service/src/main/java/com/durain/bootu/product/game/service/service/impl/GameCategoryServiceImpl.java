package com.durain.bootu.product.game.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durain.bootu.product.game.service.dataobject.GameCategory;
import com.durain.bootu.product.game.service.repository.GameCategoryRepository;
import com.durain.bootu.product.game.service.service.GameCategoryService;

@Service
public class GameCategoryServiceImpl implements GameCategoryService{

	@Autowired
	GameCategoryRepository gameCategoryRepository;
	
	
	@Override
	public List<GameCategory> findByGameCategoryTypeIn(List<Integer> categoryTypeList) {
		return gameCategoryRepository.findByCategoryTypeIn(categoryTypeList);
	}

}
