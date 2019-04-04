package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InImageMessage;

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
public interface InImageMessageDao{

	
	
	/*
	 * ===========ͼƬ��Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�ͼƬ��Ϣ��ҳ�б�
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public List selInImageMessageList(InImageMessage inImageMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�ͼƬ��Ϣ
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public List selInImageMessageList(InImageMessage inImageMessage);
	
	/**
	 * ���������õ�ͼƬ��Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InImageMessage selInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * ���ͼƬ��Ϣ
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public void addInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * ����ͼƬ��Ϣ
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public void updInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * ɾ��ͼƬ��Ϣ
	 * @param inImageMessage
	 * @return
	 */
	public void delInImageMessage(InImageMessage inImageMessage);
	
}