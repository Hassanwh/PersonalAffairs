package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InVideoMessage;

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
public interface InVideoMessageDao{

	
	
	/*
	 * ===========��Ƶ��Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ���Ƶ��Ϣ��ҳ�б�
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVideoMessageList(InVideoMessage inVideoMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ���Ƶ��Ϣ
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVideoMessageList(InVideoMessage inVideoMessage);
	
	/**
	 * ���������õ���Ƶ��Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InVideoMessage selInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * �����Ƶ��Ϣ
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public void addInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * ������Ƶ��Ϣ
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public void updInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * ɾ����Ƶ��Ϣ
	 * @param inVideoMessage
	 * @return
	 */
	public void delInVideoMessage(InVideoMessage inVideoMessage);
	
}