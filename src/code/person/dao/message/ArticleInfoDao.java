package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;

import code.person.pojo.message.ArticleInfo;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-03-21
 * @author  ldh
 * @version  2.0
 */
public interface ArticleInfoDao{

	
	
	/*
	 * ===========图文详情
	 */
	 
	/**
	 * 根据非空条件得到图文详情分页列表
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public List selArticleInfoList(ArticleInfo articleInfo,PageBounds pageBounds);
	
	public List selArticleInfoListByNewsId(String newsId);
		
	/**
	 * 根据非空条件得到图文详情
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public List selArticleInfoList(ArticleInfo articleInfo);
	
	/**
	 * 根据主键得到图文详情对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArticleInfo selArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * 添加图文详情
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public void addArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * 更新图文详情
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public void updArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * 删除图文详情
	 * @param articleInfo
	 * @return
	 */
	public void delArticleInfo(ArticleInfo articleInfo);
	
}