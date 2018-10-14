package com.durain.bootu.product.game.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durain.bootu.product.game.common.GameDescreaseStockRequest;
import com.durain.bootu.product.game.common.GameInfoResponse;
import com.durain.bootu.product.game.service.dataobject.GameCategory;
import com.durain.bootu.product.game.service.dataobject.GameInfo;
import com.durain.bootu.product.game.service.service.GameCategoryService;
import com.durain.bootu.product.game.service.service.GameService;
import com.durain.bootu.product.game.service.utils.ResultVOUtil;
import com.durain.bootu.product.game.service.vo.GameInfoVO;
import com.durain.bootu.product.game.service.vo.GameVO;
import com.durain.bootu.product.game.service.vo.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/game")
@Api(description = "Game Services", tags = { "Game" })
public class GameController {

	@Autowired
	GameService gameService;

	@Autowired
	GameCategoryService gameCategoryService;

	@GetMapping(value = "/list")
	@ApiOperation(value = "Game List", notes = "In stock game list", tags = {
			"Game" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultVO<GameVO> list() {
		List<GameInfo> gameInfoList = gameService.findInStockGames();
		List<Integer> categoryTypeList = gameInfoList.stream().map(GameInfo::getCategoryType)
				.collect(Collectors.toList());
		List<GameCategory> gameCategoryList = gameCategoryService.findByGameCategoryTypeIn(categoryTypeList);
		List<GameVO> gameVOList = new ArrayList();
		for (GameCategory gameCategory : gameCategoryList) {
			GameVO gameVO = new GameVO();
			gameVO.setCategoryName(gameCategory.getCategoryName());
			gameVO.setCategoryType(gameCategory.getCategoryType());

			List<GameInfoVO> gameInfoVOList = new ArrayList();
			for (GameInfo gameInfo : gameInfoList) {
				if (gameInfo.getCategoryType().equals(gameCategory.getCategoryType())) {
					GameInfoVO gameInfoVO = new GameInfoVO();
					BeanUtils.copyProperties(gameInfo, gameInfoVO);
					gameInfoVOList.add(gameInfoVO);
				}
			}
			gameVO.setGameInfoVOList(gameInfoVOList);
			gameVOList.add(gameVO);
		}
		return ResultVOUtil.success(gameVOList);
	}

	@PostMapping("/listFromOrder")
	public List<GameInfoResponse> listFromOrder(@RequestBody List<String> productIdList) {
		return gameService.findGamesByIds(productIdList);
	}

	@PostMapping("/decreaseGameStock")
	public void decreaseGameStock(@RequestBody List<GameDescreaseStockRequest> gameDescreaseStockRequestList) {
		gameService.decreaseGameStock(gameDescreaseStockRequestList);
	}
}
