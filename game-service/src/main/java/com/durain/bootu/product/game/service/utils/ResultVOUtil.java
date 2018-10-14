package com.durain.bootu.product.game.service.utils;

import com.durain.bootu.product.game.service.vo.ResultVO;

public class ResultVOUtil {

	public static ResultVO success(Object object) {
		ResultVO resultVO = new ResultVO();
		resultVO.setData(object);
		resultVO.setCode(0);
		resultVO.setMsg("success");
		return resultVO;
	}
}
