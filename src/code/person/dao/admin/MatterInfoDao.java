package code.person.dao.admin;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.admin.MatterInfo;

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
public interface MatterInfoDao{

	
	
	/*
	 * ===========�ز���Ϣ
	 */
	 
	/**
	 * ���ݷǿ������õ��ز���Ϣ��ҳ�б�
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selMatterInfoList(MatterInfo matterInfo,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ��ز���Ϣ
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public List selMatterInfoList(MatterInfo matterInfo);
	
	/**
	 * ���������õ��ز���Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MatterInfo selMatterInfo(MatterInfo matterInfo);
	
	/**
	 * ����ز���Ϣ
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public void addMatterInfo(MatterInfo matterInfo);
	
	/**
	 * �����ز���Ϣ
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public void updMatterInfo(MatterInfo matterInfo);
	
	/**
	 * ɾ���ز���Ϣ
	 * @param MatterInfo
	 * @return
	 */
	public void delMatterInfo(MatterInfo matterInfo);
	
}