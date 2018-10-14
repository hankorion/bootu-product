package com.durain.bootu.product.game.service.service;

import java.util.List;

import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;
import com.durain.bootu.product.game.service.dataobject.GameInfo;

public interface GameService {
	
	/*
	 * Query all in stock games list
	 */

	List<GameInfo> findInStockGames();
	
	
	List<GameInfoResponse> findGamesByIds(List<String> gameIdList);
	
	void decreaseGameStock(List<GameDescreaseStockRequest> gameDescreaseStockRequestList);
}
