package com.durain.bootu.product.game.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;
import com.durain.bootu.product.game.service.dataobject.GameInfo;
import com.durain.bootu.product.game.service.enums.GameStatusEnum;
import com.durain.bootu.product.game.service.enums.ResultEnum;
import com.durain.bootu.product.game.service.exception.GameException;
import com.durain.bootu.product.game.service.repository.GameInfoRepository;
import com.durain.bootu.product.game.service.service.GameService;
import com.durain.bootu.product.game.service.utils.JsonUtil;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameInfoRepository gameInfoRepository;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public List<GameInfo> findInStockGames() {
		return gameInfoRepository.findByGameStatus(GameStatusEnum.IN_STOCK.getCode());
	}

	@Override
	public List<GameInfoResponse> findGamesByIds(List<String> gameIdList) {
		return gameInfoRepository.findByGameIdIn(gameIdList).stream().map(e -> {
			GameInfoResponse gameResp = new GameInfoResponse();
			BeanUtils.copyProperties(e, gameResp);
			return gameResp;
		}).collect(Collectors.toList());
	}

	@Override
	public void decreaseGameStock(List<GameDescreaseStockRequest> gameDescreaseStockRequestList) {

		List<GameInfo> gameInfoList = decreaseGameStockProcess(gameDescreaseStockRequestList);

		List<GameInfoResponse> gameInfoRespList = gameInfoList.stream().map(e -> {
			GameInfoResponse gameInfoResp = new GameInfoResponse();
			BeanUtils.copyProperties(e, gameInfoResp);
			return gameInfoResp;
		}).collect(Collectors.toList());

		// Send message to AMQ
		amqpTemplate.convertAndSend("DurainGameInfoQueue", JsonUtil.toJson(gameInfoRespList));
	}

	@Transactional
	public List<GameInfo> decreaseGameStockProcess(List<GameDescreaseStockRequest> gameDescreaseStockRequestList) {
		List<GameInfo> gameInfoList = new ArrayList<>();
		for (GameDescreaseStockRequest gameDescreaseStockRequest : gameDescreaseStockRequestList) {
			Optional<GameInfo> gameInfoOptional = gameInfoRepository.findById(gameDescreaseStockRequest.getGameId());
			// Check the game exists
			if (!gameInfoOptional.isPresent()) {
				throw new GameException(ResultEnum.GAME_NOT_FOUND);
			}
			GameInfo gameInfo = gameInfoOptional.get();
			Integer result = gameInfo.getGameStock() - gameDescreaseStockRequest.getGameQuantity();
			// Check game stock
			if (result < 0) {
				throw new GameException(ResultEnum.GAME_STOCK_ERROR);
			}
			gameInfo.setGameStock(result);
			gameInfoRepository.save(gameInfo);

			gameInfoList.add(gameInfo);
		}

		return gameInfoList;
	}

}
