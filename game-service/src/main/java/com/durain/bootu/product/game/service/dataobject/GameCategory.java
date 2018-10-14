package com.durain.bootu.product.game.service.dataobject;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "game_category")
public class GameCategory {

	@Id
	@GeneratedValue
	private Integer categoryId;
	private String categoryName;
	private Integer categoryType;
	private Date createTime;
	private Date updateTime;
	
}
