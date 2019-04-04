package code.person.dao.customer;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.customer.CusInfo;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-20
 * @author  ldh
 * @version  2.0
 */
public interface CusInfoDao{

	
	
	/*
	 * ===========�ͻ���Ϣ��
	 */
	 
	/**
	 * ���ݷǿ������õ��ͻ���Ϣ���ҳ�б�
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public List selCusInfoList(CusInfo cusInfo,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ��ͻ���Ϣ��
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public List selCusInfoList(CusInfo cusInfo);
	
	/**
	 * ���������õ��ͻ���Ϣ�����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CusInfo selCusInfo(CusInfo cusInfo);
	
	/**
	 * ��ӿͻ���Ϣ��
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public void addCusInfo(CusInfo cusInfo);
	
	/**
	 * ���¿ͻ���Ϣ��
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public void updCusInfo(CusInfo cusInfo);
	
	/**
	 * ɾ���ͻ���Ϣ��
	 * @param cusInfo
	 * @return
	 */
	public void delCusInfo(CusInfo cusInfo);
	
	public String getTestPer(String openId);
	
}