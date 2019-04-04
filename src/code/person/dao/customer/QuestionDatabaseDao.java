package code.person.dao.customer;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.customer.QuestionDatabase;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	
 * @author  WH
 * @version  1.0
 */
@Repository
public interface QuestionDatabaseDao{

	
	
	/*
	 * ===========知识库
	 */
	 
	/**
	 * 根据非空条件得到知识库分页列表
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public List selQuestionDatabaseList(QuestionDatabase questionDatabase,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到知识库
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public List selQuestionDatabaseList(QuestionDatabase questionDatabase);
	
	/**
	 * 根据主键得到知识库对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QuestionDatabase selQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * 添加知识库
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public void addQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * 更新知识库
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public void updQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * 删除知识库
	 * @param questionDatabase
	 * @return
	 */
	public void delQuestionDatabase(QuestionDatabase questionDatabase);
	
}
