package com.durain.bootu.product.game.service.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
	GAME_NOT_FOUND(1, "Game not found"),
	GAME_STOCK_ERROR(2, "Game stock error");

	private Integer code;

	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
