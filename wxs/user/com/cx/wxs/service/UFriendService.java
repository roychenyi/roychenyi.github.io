package com.cx.wxs.service;

import java.util.List;

import com.cx.wxs.dto.UFriendDto;

/**
 * @author 陈义
 * @date   2015-12-3 下午2:29:54
 */
public interface UFriendService {

	/***
	 * 通过Ufriend的ID获取Ufriend
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:16:21
	 */
	public UFriendDto getUfriendByID(UFriendDto ufriendDto);
	/**
	 * 通过两个用户获取两个用户是否是朋友关系
	 * @param uFriendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-6上午10:26:04
	 */
	public UFriendDto getUFriend(UFriendDto uFriendDto);
	
	/***
	 * 通过用户group获取friend的列表
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:16:52
	 */
	public List<UFriendDto> getUfriendList(UFriendDto ufriendDto);

	/***
	 *通过用户和分类获取好友的数量
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-8下午9:07:44
	 */
	public Integer getFriendCountByGroup(UFriendDto ufriendDto);
	/***
	 * 获取好友的数量
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-8下午9:08:56
	 */
	public Integer getFriendCountByUser(UFriendDto ufriendDto);
	/***
	 * 
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:35:15
	 */
	public Integer addUfriend(UFriendDto ufriendDto);
	/***
	 * 更新用户的信息
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:18:15
	 */
	public Integer updateUfriend(UFriendDto ufriendDto);
	
	/***
	 * 删除用户的信息
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:18:32
	 */
	public Integer deleteUfriend(UFriendDto ufriendDto);
	/***
	 * 通过好友分组类型删除所有好友
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-8下午6:35:10
	 */
	public Integer deleteUfriendByGroupID(UFriendDto ufriendDto);
	/**
	 * 作废好友关系
	 * @param uFriendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-6上午11:41:25
	 */
	public Integer nullifyFriend(UFriendDto uFriendDto);
}
