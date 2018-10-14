package com.durain.bootu.product.game.service.service;

import java.util.List;

import com.durain.bootu.product.game.service.dataobject.GameCategory;

public interface GameCategoryService {
	List<GameCategory> findByGameCategoryTypeIn(List<Integer> categoryTypeList);
}
