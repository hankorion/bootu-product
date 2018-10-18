package com.durain.bootu.product.game.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;

@FeignClient(name = "DURAIN-GAMES")
public interface GameServiceClient {

	@GetMapping("/games/msg")
	String gameMsg();
	
	@PostMapping("/games/listFromOrder")
	public List<GameInfoResponse> listFromOrder(@RequestBody List<String> productIdList);
	
	@PostMapping("/games/decreaseGameStock")
	public void decreaseGameStock(@RequestBody List<GameDescreaseStockRequest> cardDTOList);
}
