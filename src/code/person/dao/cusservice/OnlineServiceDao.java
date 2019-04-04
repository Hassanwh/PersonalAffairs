package code.person.dao.cusservice;

import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.cusservice.OnlineService;
/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-06-10
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface OnlineServiceDao{

	
	
	/*
	 * ===========锁定信息
	 */
	 
	/**
	 * 根据非空条件得到锁定信息分页列表
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selOnlineServiceList(OnlineService onlineService,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到锁定信息
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public List selOnlineServiceList(OnlineService onlineService);
	
	/**
	 * 根据主键得到锁定信息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OnlineService selOnlineService(OnlineService onlineService);
	
	public OnlineService selOnlineServiceLower(OnlineService onlineService);
	
	
	/**
	 * 添加锁定信息
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public void addOnlineService(OnlineService onlineService);
	
	/**
	 * 更新锁定信息
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public void updOnlineService(OnlineService onlineService);
	
	/**
	 * 删除锁定信息
	 * @param OnlineService
	 * @return
	 */
	public void delOnlineService(OnlineService onlineService);
	
}