package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InLocationMessage;

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
public interface InLocationMessageDao{

	
	
	/*
	 * ===========����λ����Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�����λ����Ϣ��ҳ�б�
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLocationMessageList(InLocationMessage inLocationMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�����λ����Ϣ
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLocationMessageList(InLocationMessage inLocationMessage);
	
	/**
	 * ���������õ�����λ����Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InLocationMessage selInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * ��ӵ���λ����Ϣ
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public void addInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * ���µ���λ����Ϣ
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public void updInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * ɾ������λ����Ϣ
	 * @param inLocationMessage
	 * @return
	 */
	public void delInLocationMessage(InLocationMessage inLocationMessage);
	
}