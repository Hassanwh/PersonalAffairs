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
 * <br/>Date:	2015-01-04
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface QuestionDatabaseDao{

	
	
	/*
	 * ===========֪ʶ��
	 */
	 
	/**
	 * ���ݷǿ������õ�֪ʶ���ҳ�б�
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public List selQuestionDatabaseList(QuestionDatabase questionDatabase,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�֪ʶ��
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public List selQuestionDatabaseList(QuestionDatabase questionDatabase);
	
	/**
	 * ���������õ�֪ʶ�����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QuestionDatabase selQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * ���֪ʶ��
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public void addQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * ����֪ʶ��
	 * @param questionDatabase
	 * @return
	 * @throws Exception
	 */
	public void updQuestionDatabase(QuestionDatabase questionDatabase);
	
	/**
	 * ɾ��֪ʶ��
	 * @param questionDatabase
	 * @return
	 */
	public void delQuestionDatabase(QuestionDatabase questionDatabase);
	
}