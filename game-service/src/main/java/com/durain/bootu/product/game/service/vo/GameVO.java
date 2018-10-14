package com.durain.bootu.product.game.service.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameVO {

	@JsonProperty("name")
	private String categoryName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("games")
	List<GameInfoVO> gameInfoVOList;

}
