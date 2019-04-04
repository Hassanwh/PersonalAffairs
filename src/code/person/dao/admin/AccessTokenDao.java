package code.person.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import code.person.util.AccessToken;

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
public interface AccessTokenDao{

	
	/**
	 * ���������õ�������Ϣ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AccessToken selAccessToken(AccessToken AccessToken);
	
	/**
	 * ���������Ϣ
	 * @param AccessToken
	 * @return
	 * @throws Exception
	 */
	public void addAccessToken(AccessToken AccessToken);
	
	/**
	 * ����������Ϣ
	 * @param AccessToken
	 * @return
	 * @throws Exception
	 */
	public void updAccessToken(AccessToken AccessToken);
	
	/**
	 * ɾ��������Ϣ
	 * @param AccessToken
	 * @return
	 */
	public void delAccessToken(AccessToken AccessToken);
	
}