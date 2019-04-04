package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InTextMessage;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-23
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface InTextMessageDao{

	
	
	/*
	 * ===========�ı���Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ��ı���Ϣ��ҳ�б�
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public List selInTextMessageList(InTextMessage inTextMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ��ı���Ϣ
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public List selInTextMessageList(InTextMessage inTextMessage);
	
	/**
	 * ���������õ��ı���Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InTextMessage selInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * ����ı���Ϣ
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public void addInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * �����ı���Ϣ
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public void updInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * ɾ���ı���Ϣ
	 * @param inTextMessage
	 * @return
	 */
	public void delInTextMessage(InTextMessage inTextMessage);
	
}