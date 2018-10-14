package com.durain.bootu.product.game.service.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "game_info")
public class GameInfo {
	@Id
	private String gameId;
	private String gameName;
	private BigDecimal gamePrice;
	private Integer gameStock;
	private String gameDescription;
	private String gameIcon;
	private Integer gameStatus;
	private Integer categoryType;
	private Date createTime;
	private Date updateTime;
}
