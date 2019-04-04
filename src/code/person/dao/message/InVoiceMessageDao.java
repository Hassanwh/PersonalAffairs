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
	 * ===========语音消息
	 */
	 
	/**
	 * 根据非空条件得到语音消息分页列表
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVoiceMessageList(InVoiceMessage inVoiceMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到语音消息
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVoiceMessageList(InVoiceMessage inVoiceMessage);
	
	/**
	 * 根据主键得到语音消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InVoiceMessage selInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * 添加语音消息
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public void addInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * 更新语音消息
	 * @param inVoiceMessage
	 * @return
	 * @throws Exception
	 */
	public void updInVoiceMessage(InVoiceMessage inVoiceMessage);
	
	/**
	 * 删除语音消息
	 * @param inVoiceMessage
	 * @return
	 */
	public void delInVoiceMessage(InVoiceMessage inVoiceMessage);
	
}