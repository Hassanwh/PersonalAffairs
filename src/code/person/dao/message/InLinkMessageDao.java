package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InLinkMessage;

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
public interface InLinkMessageDao{

	
	
	/*
	 * ===========������Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�������Ϣ��ҳ�б�
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLinkMessageList(InLinkMessage inLinkMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�������Ϣ
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLinkMessageList(InLinkMessage inLinkMessage);
	
	/**
	 * ���������õ�������Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InLinkMessage selInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * ���������Ϣ
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public void addInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * ����������Ϣ
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public void updInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * ɾ��������Ϣ
	 * @param inLinkMessage
	 * @return
	 */
	public void delInLinkMessage(InLinkMessage inLinkMessage);
	
}