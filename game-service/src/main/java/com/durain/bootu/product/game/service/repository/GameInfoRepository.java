package com.durain.bootu.product.game.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durain.bootu.product.game.service.dataobject.GameInfo;

public interface GameInfoRepository extends JpaRepository<GameInfo, String>{

	List<GameInfo> findByGameStatus(Integer gameStatus);
	
	List<GameInfo> findByGameIdIn(List<String> gameIdList);
}
