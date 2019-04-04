package code.person.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import code.person.util.AccessToken;

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
public interface AccessTokenDao{

	
	/**
	 * 根据主键得到令牌信息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AccessToken selAccessToken(AccessToken AccessToken);
	
	/**
	 * 添加令牌信息
	 * @param AccessToken
	 * @return
	 * @throws Exception
	 */
	public void addAccessToken(AccessToken AccessToken);
	
	/**
	 * 更新令牌信息
	 * @param AccessToken
	 * @return
	 * @throws Exception
	 */
	public void updAccessToken(AccessToken AccessToken);
	
	/**
	 * 删除锁定信息
	 * @param AccessToken
	 * @return
	 */
	public void delAccessToken(AccessToken AccessToken);
	
}
