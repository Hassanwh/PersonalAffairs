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
	 * ===========地理位置消息
	 */
	 
	/**
	 * 根据非空条件得到地理位置消息分页列表
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLocationMessageList(InLocationMessage inLocationMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到地理位置消息
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLocationMessageList(InLocationMessage inLocationMessage);
	
	/**
	 * 根据主键得到地理位置消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InLocationMessage selInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * 添加地理位置消息
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public void addInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * 更新地理位置消息
	 * @param inLocationMessage
	 * @return
	 * @throws Exception
	 */
	public void updInLocationMessage(InLocationMessage inLocationMessage);
	
	/**
	 * 删除地理位置消息
	 * @param inLocationMessage
	 * @return
	 */
	public void delInLocationMessage(InLocationMessage inLocationMessage);
	
}