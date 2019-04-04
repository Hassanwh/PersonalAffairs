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
	 * ===========接收消息表
	 */
	 
	/**
	 * 根据非空条件得到接收消息表分页列表
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public List selInMessageList(InMessage inMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到接收消息表
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public List selInMessageList(InMessage inMessage);
	
	/**
	 * 根据主键得到接收消息表对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InMessage selInMessage(InMessage inMessage);
	
	/**
	 * 添加接收消息表
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public void addInMessage(InMessage inMessage);
	
	/**
	 * 更新接收消息表
	 * @param inMessage
	 * @return
	 * @throws Exception
	 */
	public void updInMessage(InMessage inMessage);
	
	/**
	 * 删除接收消息表
	 * @param inMessage
	 * @return
	 */
	public void delInMessage(InMessage inMessage);
	
}