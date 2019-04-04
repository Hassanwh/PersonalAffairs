package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.NewsMessage;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-03-21
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface NewsMessageDao{

	
	
	/*
	 * ===========图文消息
	 */
	 
	/**
	 * 根据非空条件得到图文消息分页列表
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public List selNewsMessageList(NewsMessage newsMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到图文消息
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public List selNewsMessageList(NewsMessage newsMessage);
	
	/**
	 * 根据主键得到图文消息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NewsMessage selNewsMessage(NewsMessage newsMessage);
	
	/**
	 * 添加图文消息
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public void addNewsMessage(NewsMessage newsMessage);
	
	/**
	 * 更新图文消息
	 * @param newsMessage
	 * @return
	 * @throws Exception
	 */
	public void updNewsMessage(NewsMessage newsMessage);
	
	/**
	 * 删除图文消息
	 * @param newsMessage
	 * @return
	 */
	public void delNewsMessage(NewsMessage newsMessage);
	
}