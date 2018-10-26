package com.durain.bootu.product.game.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.durain.bootu.product.game.client.config.ProtoFeignConfiguration;
import com.durain.bootu.product.game.grpc.lib.GamesListResponse;

@FeignClient(name = "DURAIN-GAMES", configuration = ProtoFeignConfiguration.class)
public interface GameServiceProtoClient {

	@RequestMapping(value = "/games/listall", method = RequestMethod.GET, consumes = "application/x-protobuf", produces = "application/x-protobuf")
	public GamesListResponse listAllProtobuf();

}
