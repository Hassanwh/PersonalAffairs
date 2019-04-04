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
	 * ===========图片消息
	 */
	 
	/**
	 * 根据非空条件得到图片消息分页列表
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public List selInImageMessageList(InImageMessage inImageMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到图片消息
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public List selInImageMessageList(InImageMessage inImageMessage);
	
	/**
	 * 根据主键得到图片消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InImageMessage selInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * 添加图片消息
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public void addInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * 更新图片消息
	 * @param inImageMessage
	 * @return
	 * @throws Exception
	 */
	public void updInImageMessage(InImageMessage inImageMessage);
	
	/**
	 * 删除图片消息
	 * @param inImageMessage
	 * @return
	 */
	public void delInImageMessage(InImageMessage inImageMessage);
	
}