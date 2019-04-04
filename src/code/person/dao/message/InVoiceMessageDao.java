package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InVoiceMessage;

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
public interface InVoiceMessageDao{

	
	
	/*
	 * ===========������Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�������Ϣ��ҳ�б�
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVoiceMessageList(InVoiceMessage inVoiceMessage,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�������Ϣ
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVoiceMessageList(InVoiceMessage inVoiceMessage);
	
	/**
	 * ���������õ�������Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InVoiceMessage selInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * ���������Ϣ
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public void addInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * ����������Ϣ
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public void updInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * ɾ��������Ϣ
	 * @param inVoiceMessage
	 * @return
	 */
	public void delInVoiceMessage(InVoiceMessage inVoiceMessage);
	
}