package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.NewsMessage;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-03-21
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface NewsMessageDao{

	
	
	/*
	 * ===========ͼ����Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�ͼ����Ϣ��ҳ�б�
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public List selNewsMessageList(NewsMessage newsMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�ͼ����Ϣ
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public List selNewsMessageList(NewsMessage newsMessage);
	
	/**
	 * ���������õ�ͼ����Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NewsMessage selNewsMessage(NewsMessage newsMessage);
	
	/**
	 * ���ͼ����Ϣ
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public void addNewsMessage(NewsMessage newsMessage);
	
	/**
	 * ����ͼ����Ϣ
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public void updNewsMessage(NewsMessage newsMessage);
	
	/**
	 * ɾ��ͼ����Ϣ
	 * @param newsMessage
	 * @return
	 */
	public void delNewsMessage(NewsMessage newsMessage);
	
}