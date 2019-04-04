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
	 * ===========������Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ�������Ϣ��ҳ�б�
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selOnlineServiceList(OnlineService onlineService,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ�������Ϣ
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public List selOnlineServiceList(OnlineService onlineService);
	
	/**
	 * ���������õ�������Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OnlineService selOnlineService(OnlineService onlineService);
	
	public OnlineService selOnlineServiceLower(OnlineService onlineService);
	
	
	/**
	 * ���������Ϣ
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public void addOnlineService(OnlineService onlineService);
	
	/**
	 * ����������Ϣ
	 * @param OnlineService
	 * @return
	 * @throws Exception
	 */
	public void updOnlineService(OnlineService onlineService);
	
	/**
	 * ɾ��������Ϣ
	 * @param OnlineService
	 * @return
	 */
	public void delOnlineService(OnlineService onlineService);
	
}