package com.cx.wxs.dao;

import java.util.List;

import com.cx.wxs.dto.UfriendGroupDto;
import com.cx.wxs.po.UFriendGroup;
import com.cy.wxs.base.dao.IBaseDao;

/**
 * @author 陈义
 * @date   2015-12-3 下午2:27:19
 */
public interface UfriendGroupDao extends IBaseDao<UFriendGroup,Integer>{
	
	/***
	 * 通过ID获取好友列表
	 * @param ufriendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:23:42
	 */
	public UfriendGroupDto getUfriendGroupByID(UfriendGroupDto ufriendGroupDto);
	/***
	 * 通过用户信息和名称信息获取好友分类的类型
	 * @param ufriendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:24:05
	 */
	public List<UfriendGroupDto> getUfriendGroupList(UfriendGroupDto ufriendGroupDto);
	
	/***
	 * 删除好友分组信息
	 * @param ufriendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午9:05:12
	 */
	public Integer addUfriendGroup(UfriendGroupDto ufriendGroupDto);
	/***
	 * 更新好友分类信息
	 * @param ufriendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:24:35
	 */
	public Integer updateUfriendGroup(UfriendGroupDto ufriendGroupDto);
	/***
	 * 删除好友分类
	 * @param ufriendGroupDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:24:51
	 */
	public Integer deleteUfriendByGroup(UfriendGroupDto ufriendGroupDto);

}
