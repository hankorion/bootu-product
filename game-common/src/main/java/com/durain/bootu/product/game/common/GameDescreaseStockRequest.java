package com.durain.bootu.product.game.common;

import lombok.Data;

@Data
public class GameDescreaseStockRequest {

	private String gameId;

	private Integer gameQuantity;

	public GameDescreaseStockRequest() {

	}

	public GameDescreaseStockRequest(String gameId, Integer gameQuantity) {
		super();
		this.gameId = gameId;
		this.gameQuantity = gameQuantity;
	}

}
