package com.durain.bootu.product.game.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durain.bootu.product.game.service.dataobject.GameCategory;

public interface GameCategoryRepository extends JpaRepository<GameCategory, Integer>{
	
	List<GameCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
