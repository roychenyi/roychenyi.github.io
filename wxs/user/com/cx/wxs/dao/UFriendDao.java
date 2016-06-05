package com.cx.wxs.dao;

import java.util.List;

import com.cx.wxs.base.dao.IBaseDao;
import com.cx.wxs.dto.UFriendDto;
import com.cx.wxs.po.UFriend;

/**
 * @author 陈义
 * @date   2015-12-3 下午2:25:48
 */
public interface UFriendDao extends IBaseDao<UFriend,Integer>{
	
	/***
	 * 通过Ufriend的ID获取Ufriend
	 * @param ufriendDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:16:21
	 */
	public UFriendDto getUfriendByID(UFriendDto ufriendDto);
	
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
	 * 获取好友数量
	 * @param uFriendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-5下午10:01:30
	 */
	public Integer getUFriendCount(UFriendDto uFriendDto);
	/**
	 * 获取该好友所在的行
	 * @param uFriendDto
	 * @return
	 * @author 陈义
	 * @date   2016-6-5下午10:02:29
	 */
	public UFriendDto getUFriendRow(UFriendDto uFriendDto);

}
