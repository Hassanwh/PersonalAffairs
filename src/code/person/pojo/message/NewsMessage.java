package code.person.pojo.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-03-21
 * @author  ldh
 * @version  2.0
 */
public class NewsMessage implements Serializable
{

	// Fields
	private Integer id;
	private String name;
	private Date regDate;
	private String newsType;
	private String status;
	private String regBy;
	private List<ArticleInfo> articleInfos;
	private List<Article> articleList;
	
	
	// Constructors
	public NewsMessage() {
	}	

	// Property accessors	
	public Integer getId() {
		return this.id;
	}

	/*
	 * @param Integer id (���ĺ��⣺ID;�����ݴ洢���ͣ�INT)
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	/*
	 * @param String name (���ĺ��⣺ͼ������;�����ݴ洢���ͣ�VARCHAR(60))
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	/*
	 * @param Date regDate (���ĺ��⣺�Ǽ�����;�����ݴ洢���ͣ�DATE)
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getNewsType() {
		return this.newsType;
	}

	/*
	 * @param String newsType (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�CHAR(2))
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getStatus() {
		return this.status;
	}

	/*
	 * @param String status (���ĺ��⣺״̬;�����ݴ洢���ͣ�CHAR(1))
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegBy() {
		return regBy;
	}

	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}

	public List<ArticleInfo> getArticleInfos() {
		return articleInfos;
	}

	public void setArticleInfos(List<ArticleInfo> articleInfos) {
		this.articleInfos = articleInfos;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}


	
}