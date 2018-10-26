package com.durain.bootu.product.game.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.durain.bootu.product.game.client.GameServiceClient.GameClientFallback;
import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;

@FeignClient(name = "DURAIN-GAMES", fallback = GameClientFallback.class)
public interface GameServiceClient {

	@PostMapping("/games/listFromOrder")
	public List<GameInfoResponse> listFromOrder(@RequestBody List<String> productIdList);

	@PostMapping("/games/decreaseGameStock")
	public void decreaseGameStock(@RequestBody List<GameDescreaseStockRequest> cardDTOList);

	@Component
	static class GameClientFallback implements GameServiceClient {

		@Override
		public List<GameInfoResponse> listFromOrder(List<String> productIdList) {
			return null;
		}

		@Override
		public void decreaseGameStock(List<GameDescreaseStockRequest> cardDTOList) {
			// TODO Auto-generated method stub
		}

	}
}
