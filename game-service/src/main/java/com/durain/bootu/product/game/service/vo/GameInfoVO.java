package com.durain.bootu.product.game.service.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameInfoVO {

	@JsonProperty("id")
	private String gameId;

	@JsonProperty("name")
	private String gameName;

	@JsonProperty("price")
	private BigDecimal gamePrice;

	@JsonProperty("description")
	private String gameDescription;

	@JsonProperty("icon")
	private String gameIcon;

}
