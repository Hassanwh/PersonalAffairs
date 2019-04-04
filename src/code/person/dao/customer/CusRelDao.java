package code.person.dao.customer;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.PageBounds;
import org.springframework.stereotype.Repository;

import code.person.pojo.customer.CusRel;
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
public interface CusRelDao{

	
	
	/*
	 * ===========客户信息关联表
	 */
	 
	/**
	 * 根据非空条件得到客户信息关联表分页列表
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public List selCusRelList(CusRel cusRel,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到客户信息关联表
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public List selCusRelList(CusRel cusRel);
	
	/**
	 * 根据主键得到客户信息关联表对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CusRel selCusRel(CusRel cusRel);
	
	public CusRel selCusRelByIdAcct(CusRel cusRel);
	
	/**
	 * 添加客户信息关联表
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public void addCusRel(CusRel cusRel);
	
	/**
	 * 更新客户信息关联表
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public void updCusRel(CusRel cusRel);
	
	/**
	 * 删除客户信息关联表
	 * @param cusRel
	 * @return
	 */
	public void delCusRel(CusRel cusRel);
	
	public void delCusRelByIdAcct(CusRel cusRel);
	
	public List selCusRelListById(CusRel cusRel);
	
	public List selCusRelListByAccountNo(CusRel cusRel);
	
	public void delCusRelByOpenId(String openId);
	
}
