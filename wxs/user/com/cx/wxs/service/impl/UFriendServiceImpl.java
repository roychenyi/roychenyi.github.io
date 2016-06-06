package com.cx.wxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.wxs.dao.UFriendDao;
import com.cx.wxs.dto.UFriendDto;
import com.cx.wxs.service.UFriendService;

/**
 * @author 陈义
 * @date   2016-5-29 下午9:56:55
 */
@Service("UFriendService")
public class UFriendServiceImpl implements UFriendService{

	@Autowired
	private UFriendDao uFriendDao;
	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#getUfriendByID(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public UFriendDto getUfriendByID(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.getUfriendByID(ufriendDto);
	}

	@Override
	public UFriendDto getUFriend(UFriendDto uFriendDto){
		UFriendDto friendDto=uFriendDao.getUFriend(uFriendDto);
		if(friendDto==null){
			uFriendDto.setStatusFlag("-1");
		}else{
			uFriendDto.setStatusFlag("1");
		}
		return uFriendDto;
	}
	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#getUfriendList(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public List<UFriendDto> getUfriendList(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		List<UFriendDto> list=uFriendDao.getUfriendList(ufriendDto);
		if(list!=null&&list.size()>0){
			Integer count=uFriendDao.getUFriendCount(ufriendDto);
			int pageCount=0;
			if(ufriendDto.getRows()!=null){
				pageCount=count/ufriendDto.getRows()+count%ufriendDto.getRows()>0?1:0;
			}else{
				pageCount=count/10+count%10>0?1:0;
			}
			list.get(0).setRows(count);
			list.get(0).setPageCount(pageCount);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#getFriendCountByGroup(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer getFriendCountByGroup(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.getFriendCountByGroup(ufriendDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#getFriendCountByUser(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer getFriendCountByUser(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.getFriendCountByUser(ufriendDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#addUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer addUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.addUfriend(ufriendDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#updateUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer updateUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.updateUfriend(ufriendDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#deleteUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer deleteUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.deleteUfriend(ufriendDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.UFriendService#deleteUfriendByGroupID(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer deleteUfriendByGroupID(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		return uFriendDao.deleteUfriendByGroupID(ufriendDto);
	}
	@Override
	public Integer nullifyFriend(UFriendDto uFriendDto){
		return uFriendDao.nullifyFriend(uFriendDto);
	}

}
