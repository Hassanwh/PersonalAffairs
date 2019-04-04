package code.person.pojo.message;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-03-21
 * @author  ldh
 * @version  2.0
 */
public class ArticleInfo extends Article implements Serializable
{

	// Fields
	private Integer id;
	private Integer newsId;
	private Integer orderNo;
	
	
	// Constructors
	public ArticleInfo() {
	}	

	// Property accessors	
	public Integer getId() {
		return this.id;
	}

	/*
	 * @param Integer id (中文含意：id;　数据存储类型：INT)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNewsId() {
		return this.newsId;
	}

	/*
	 * @param Integer newsId (中文含意：所属图文消息ID;　数据存储类型：INT)
	 */
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	/*
	 * @param Integer orderNo (中文含意：排外顺序;　数据存储类型：INT)
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	
}