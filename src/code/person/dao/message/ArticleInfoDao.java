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
	 * ===========ͼ������
	 */
	 
	/**
	 * ���ݷǿ������õ�ͼ�������ҳ�б�
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public List selArticleInfoList(ArticleInfo articleInfo,PageBounds pageBounds);
	
	public List selArticleInfoListByNewsId(String newsId);
		
	/**
	 * ���ݷǿ������õ�ͼ������
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public List selArticleInfoList(ArticleInfo articleInfo);
	
	/**
	 * ���������õ�ͼ���������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArticleInfo selArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * ���ͼ������
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public void addArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * ����ͼ������
	 * @param articleInfo
	 * @return
	 * @throws Exception
	 */
	public void updArticleInfo(ArticleInfo articleInfo);
	
	/**
	 * ɾ��ͼ������
	 * @param articleInfo
	 * @return
	 */
	public void delArticleInfo(ArticleInfo articleInfo);
	
}