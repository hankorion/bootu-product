package com.durain.bootu.product.game.common;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class GameInfoResponse {
	private String gameId;
	private String gameName;
	private BigDecimal gamePrice;
	private Integer gameStock;
	private String gameDescription;
	private String gameIcon;
	private Integer gameStatus;
	private Integer categoryType;
}
