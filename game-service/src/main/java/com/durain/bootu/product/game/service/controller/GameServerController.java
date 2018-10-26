package com.durain.bootu.product.game.service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durain.bootu.product.game.grpc.lib.GameInfoProto;
import com.durain.bootu.product.game.grpc.lib.GamesCategoryProto;
import com.durain.bootu.product.game.grpc.lib.GamesListResponse;
import com.durain.bootu.product.game.service.dataobject.GameCategory;
import com.durain.bootu.product.game.service.dataobject.GameInfo;
import com.durain.bootu.product.game.service.service.GameCategoryService;
import com.durain.bootu.product.game.service.service.GameService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/games")
@Slf4j
public class GameServerController {

	@Autowired
	GameService gameService;

	@Autowired
	GameCategoryService gameCategoryService;

	@GetMapping("/msg")
	public String msg() {
		return "GameServerController msg";
	}

	@GetMapping("/listall")
	public GamesListResponse gamesList() {

		GamesListResponse.Builder gameResBuilder = GamesListResponse.newBuilder();

		List<GameInfo> gameInfoList = gameService.findInStockGames();
		List<Integer> categoryTypeList = gameInfoList.stream().map(GameInfo::getCategoryType)
				.collect(Collectors.toList());

		List<GameCategory> gameCategoryList = gameCategoryService.findByGameCategoryTypeIn(categoryTypeList);
		for (GameCategory gameCategory : gameCategoryList) {
			GamesCategoryProto.Builder gamesBuilder = GamesCategoryProto.newBuilder();

			gamesBuilder.setCategoryName(gameCategory.getCategoryName());
			gamesBuilder.setCategoryType(gameCategory.getCategoryType());

			for (GameInfo gameInfo : gameInfoList) {
				if (gameInfo.getCategoryType().equals(gameCategory.getCategoryType())) {

					GameInfoProto.Builder gameInfoProtoBuilder = GameInfoProto.newBuilder();

					gameInfoProtoBuilder.setGameDescription(gameInfo.getGameDescription());
					gameInfoProtoBuilder.setGameIcon(gameInfo.getGameIcon() != null ? gameInfo.getGameIcon() : "");
					gameInfoProtoBuilder.setGameId(gameInfo.getGameId());
					gameInfoProtoBuilder.setGameName(gameInfo.getGameName());
					gameInfoProtoBuilder.setGamePrice(gameInfo.getGamePrice().doubleValue());
					gamesBuilder.addGameInfoProto(gameInfoProtoBuilder);

				}
			}

			gameResBuilder.addGamesCategoryProto(gamesBuilder);
		}

		log.info(" GamesListResponse protobuf server -->  {}", gameResBuilder.build());

		return gameResBuilder.build();
	}
}
