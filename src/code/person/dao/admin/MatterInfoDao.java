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
	 * ===========素材信息
	 */
	 
	/**
	 * 根据非空条件得到素材信息分页列表
	 * @param lockInfo
	 * @return
	 * @throws Exception
	 */
	public List selMatterInfoList(MatterInfo matterInfo,PageBounds pageBounds);
		
	/**
	 * 根据非空条件得到素材信息
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public List selMatterInfoList(MatterInfo matterInfo);
	
	/**
	 * 根据主键得到素材信息对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MatterInfo selMatterInfo(MatterInfo matterInfo);
	
	/**
	 * 添加素材信息
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public void addMatterInfo(MatterInfo matterInfo);
	
	/**
	 * 更新素材信息
	 * @param MatterInfo
	 * @return
	 * @throws Exception
	 */
	public void updMatterInfo(MatterInfo matterInfo);
	
	/**
	 * 删除素材信息
	 * @param MatterInfo
	 * @return
	 */
	public void delMatterInfo(MatterInfo matterInfo);
	
}