package com.durain.bootu.product.game.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameServerController {

	@GetMapping("/msg")
	public String msg() {
		return "GameServerController msg";
	}
}
