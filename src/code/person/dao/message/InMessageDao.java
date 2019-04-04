package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InMessage;

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
public interface InMessageDao{

	
	
	/*
	 * ===========������Ϣ��
	 */
	 
	/**
	 * ���ݷǿ������õ�������Ϣ���ҳ�б�
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public List selInMessageList(InMessage inMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�������Ϣ��
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public List selInMessageList(InMessage inMessage);
	
	/**
	 * ���������õ�������Ϣ�����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InMessage selInMessage(InMessage inMessage);
	
	/**
	 * ��ӽ�����Ϣ��
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public void addInMessage(InMessage inMessage);
	
	/**
	 * ���½�����Ϣ��
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public void updInMessage(InMessage inMessage);
	
	/**
	 * ɾ��������Ϣ��
	 * @param inMessage
	 * @return
	 */
	public void delInMessage(InMessage inMessage);
	
}