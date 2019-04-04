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
	 * @param Integer id (���ĺ��⣺id;�����ݴ洢���ͣ�INT)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNewsId() {
		return this.newsId;
	}

	/*
	 * @param Integer newsId (���ĺ��⣺����ͼ����ϢID;�����ݴ洢���ͣ�INT)
	 */
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	/*
	 * @param Integer orderNo (���ĺ��⣺����˳��;�����ݴ洢���ͣ�INT)
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	
}