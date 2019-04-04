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
	 * ===========视频消息
	 */
	 
	/**
	 * 根据非空条件得到视频消息分页列表
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVideoMessageList(InVideoMessage inVideoMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到视频消息
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public List selInVideoMessageList(InVideoMessage inVideoMessage);
	
	/**
	 * 根据主键得到视频消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InVideoMessage selInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * 添加视频消息
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public void addInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * 更新视频消息
	 * @param inVideoMessage
	 * @return
	 * @throws Exception
	 */
	public void updInVideoMessage(InVideoMessage inVideoMessage);
	
	/**
	 * 删除视频消息
	 * @param inVideoMessage
	 * @return
	 */
	public void delInVideoMessage(InVideoMessage inVideoMessage);
	
}