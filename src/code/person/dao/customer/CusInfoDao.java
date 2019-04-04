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
	 * ===========客户信息表
	 */
	 
	/**
	 * 根据非空条件得到客户信息表分页列表
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public List selCusInfoList(CusInfo cusInfo,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到客户信息表
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public List selCusInfoList(CusInfo cusInfo);
	
	/**
	 * 根据主键得到客户信息表对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CusInfo selCusInfo(CusInfo cusInfo);
	
	/**
	 * 添加客户信息表
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public void addCusInfo(CusInfo cusInfo);
	
	/**
	 * 更新客户信息表
	 * @param cusInfo
	 * @return
	 * @throws Exception
	 */
	public void updCusInfo(CusInfo cusInfo);
	
	/**
	 * 删除客户信息表
	 * @param cusInfo
	 * @return
	 */
	public void delCusInfo(CusInfo cusInfo);
	
	public String getTestPer(String openId);
	
}