package code.person.dao.admin;

import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.admin.LockInfo;
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
public interface LockInfoDao{

	
	
	/*
	 * ===========锁定信息
	 */
	 
	/**
	 * 根据非空条件得到锁定信息分页列表
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selLockInfoList(LockInfo lockInfo,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到锁定信息
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selLockInfoList(LockInfo lockInfo);
	
	/**
	 * 根据主键得到锁定信息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LockInfo selLockInfo(LockInfo lockInfo);
	
	/**
	 * 添加锁定信息
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public void addLockInfo(LockInfo lockInfo);
	
	/**
	 * 更新锁定信息
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public void updLockInfo(LockInfo lockInfo);
	
	/**
	 * 删除锁定信息
	 * @param lockInfo
	 * @return
	 */
	public void delLockInfo(LockInfo lockInfo);
	
}