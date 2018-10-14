package com.durain.bootu.product.game.service.enums;

import lombok.Getter;

@Getter
public enum GameStatusEnum {
	IN_STOCK(0, "In Stock"), OUT_OF_STOCK(1, "Out Of Stock"),;

	private Integer code;

	private String message;

	GameStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
