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
 * <br/>Date:	2014-10-21
 * @author  ldh
 * @version  2.0
 */
@Repository
public interface CusRelDao{

	
	
	/*
	 * ===========�ͻ���Ϣ������
	 */
	 
	/**
	 * ���ݷǿ������õ��ͻ���Ϣ�������ҳ�б�
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public List selCusRelList(CusRel cusRel,PageBounds pageBounds);
		
	/**
	 * ���ݷǿ������õ��ͻ���Ϣ������
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public List selCusRelList(CusRel cusRel);
	
	/**
	 * ���������õ��ͻ���Ϣ���������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CusRel selCusRel(CusRel cusRel);
	
	public CusRel selCusRelByIdAcct(CusRel cusRel);
	
	/**
	 * ��ӿͻ���Ϣ������
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public void addCusRel(CusRel cusRel);
	
	/**
	 * ���¿ͻ���Ϣ������
	 * @param cusRel
	 * @return
	 * @throws Exception
	 */
	public void updCusRel(CusRel cusRel);
	
	/**
	 * ɾ���ͻ���Ϣ������
	 * @param cusRel
	 * @return
	 */
	public void delCusRel(CusRel cusRel);
	
	public void delCusRelByIdAcct(CusRel cusRel);
	
	public List selCusRelListById(CusRel cusRel);
	
	public List selCusRelListByAccountNo(CusRel cusRel);
	
	public void delCusRelByOpenId(String openId);
	
}