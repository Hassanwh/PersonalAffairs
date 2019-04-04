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
	 * ===========链接消息
	 */
	 
	/**
	 * 根据非空条件得到链接消息分页列表
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLinkMessageList(InLinkMessage inLinkMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到链接消息
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public List selInLinkMessageList(InLinkMessage inLinkMessage);
	
	/**
	 * 根据主键得到链接消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InLinkMessage selInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * 添加链接消息
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public void addInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * 更新链接消息
	 * @param inLinkMessage
	 * @return
	 * @throws Exception
	 */
	public void updInLinkMessage(InLinkMessage inLinkMessage);
	
	/**
	 * 删除链接消息
	 * @param inLinkMessage
	 * @return
	 */
	public void delInLinkMessage(InLinkMessage inLinkMessage);
	
}