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
	 * ===========������Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�������Ϣ��ҳ�б�
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selLockInfoList(LockInfo lockInfo,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�������Ϣ
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selLockInfoList(LockInfo lockInfo);
	
	/**
	 * ���������õ�������Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LockInfo selLockInfo(LockInfo lockInfo);
	
	/**
	 * ���������Ϣ
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public void addLockInfo(LockInfo lockInfo);
	
	/**
	 * ����������Ϣ
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public void updLockInfo(LockInfo lockInfo);
	
	/**
	 * ɾ��������Ϣ
	 * @param lockInfo
	 * @return
	 */
	public void delLockInfo(LockInfo lockInfo);
	
}