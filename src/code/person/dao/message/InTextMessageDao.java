package code.person.dao.message;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.message.InTextMessage;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	
 * @author  WH
 * @version  1.0
 */
@Repository
public interface InTextMessageDao{

	
	
	/*
	 * ===========文本信息
	 */
	 
	/**
	 * 根据非空条件得到文本信息分页列表
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public List selInTextMessageList(InTextMessage inTextMessage,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到文本信息
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public List selInTextMessageList(InTextMessage inTextMessage);
	
	/**
	 * 根据主键得到文本信息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InTextMessage selInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * 添加文本信息
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public void addInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * 更新文本信息
	 * @param inTextMessage
	 * @return
	 * @throws Exception
	 */
	public void updInTextMessage(InTextMessage inTextMessage);
	
	/**
	 * 删除文本信息
	 * @param inTextMessage
	 * @return
	 */
	public void delInTextMessage(InTextMessage inTextMessage);
	
}
