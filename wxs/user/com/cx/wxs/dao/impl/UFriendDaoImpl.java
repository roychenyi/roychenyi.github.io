package com.cx.wxs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.cx.wxs.base.dao.BaseDaoImpl;
import com.cx.wxs.dao.UFriendDao;
import com.cx.wxs.dto.UFriendDto;
import com.cx.wxs.dto.UFriendGroupDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.enums.DbType;
import com.cx.wxs.po.UBook;
import com.cx.wxs.po.UFriend;
import com.cx.wxs.po.UUser;
import com.cx.wxs.utils.BeanToDto;
import com.cx.wxs.utils.StringUtils;

/**
 * @author 陈义
 * @date   2015-12-8 下午4:16:50
 */
@Repository("UFriendDao")
public  class UFriendDaoImpl extends BaseDaoImpl<UFriend,Integer> implements UFriendDao{


	private BeanToDto<UFriend, UFriendDto> beanToDto=new BeanToDto<UFriend,UFriendDto>();
	
	/**
	 * @return the beanToDto
	 */
	public BeanToDto<UFriend, UFriendDto> getBeanToDto() {
		return beanToDto;
	}

	/**
	 * @param beanToDto the beanToDto to set
	 */
	public void setBeanToDto(BeanToDto<UFriend, UFriendDto> beanToDto) {
		this.beanToDto = beanToDto;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#getUfriendByID(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public UFriendDto getUfriendByID(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null&&ufriendDto.getId()!=null&&ufriendDto.getId()>0){
			StringBuffer stringBuffer=new StringBuffer();
			stringBuffer.append("from "+UFriend.class.getName()+" a");
			stringBuffer.append(" where a.id=:id");
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("id",ufriendDto.getId());
			List<UFriend> list=this.find(stringBuffer.toString(),params);
			UFriendDto friendDto=new UFriendDto();
			if(list!=null&&list.size()>0){
				UFriend friend= list.get(0);
				friendDto= beanToDto.T1ToD1(friend, new UFriendDto());
				return friendDto;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#getUfriendList(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public List<UFriendDto> getUfriendList(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null){
			StringBuffer stringBuffer=new StringBuffer();
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where 1=1 ");
			if(ufriendDto.getUFriendGroupDto().getGroupId()!=null){
			stringBuffer.append(" and a.UFriendGroup.groupId =:groupId");
			params.put("groupId",ufriendDto.getUFriendGroupDto().getGroupId());
			}
			if(ufriendDto.getUUserDto().getUserId()!=null){
				stringBuffer.append(" and a.UUserDto.userId=:userid");
				params.put("userid",ufriendDto.getUUserDto().getUserId());
			}
			if(ufriendDto.getFriendDto().getUserId()!=null){
				stringBuffer.append(" and a.FriendDto.userId=:friendid");
				params.put("friendid",ufriendDto.getFriendDto().getUserId());
			}
			if(ufriendDto.getStatus()!=null){
				stringBuffer.append(" and a.status=:status");
				params.put("status",ufriendDto.getStatus());
			}else{
				stringBuffer.append(" and a.status = 1");
			}
			List<UFriendDto> list=new ArrayList<UFriendDto>();
			List<UFriend> list1=new ArrayList<UFriend>();
			if(ufriendDto.getPage()!=null&&ufriendDto.getRows()!=null){
				list1=this.find(stringBuffer.toString(),params,ufriendDto.getPage(),ufriendDto.getRows());
			}else{
				list1=this.find(stringBuffer.toString(),params,1,10);
			}
			for(int i=0;i<list1.size();i++){
				UFriend friend=list1.get(i);
				UFriendDto friendDto=new UFriendDto();
				friendDto=beanToDto.T1ToD1(friend, friendDto);
				if(ufriendDto.getRows()!=null&&ufriendDto.getPage()!=null){
					friendDto.setPage(ufriendDto.getPage());
					friendDto.setRow((ufriendDto.getPage()-1)*friendDto.getRows()+i+1);
				}else{
					friendDto.setPage(1);
					friendDto.setRow(i+1);
				}
				list.add(friendDto);
			}
			return list;
		}
		return null;
	}
	
	

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#addUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer addUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null&&ufriendDto.getUUserDto()!=null&&ufriendDto.getFriendDto()!=null&&ufriendDto.getUFriendGroupDto()!=null){
		     UFriend ufriend=beanToDto.D1ToT1(new UFriend(), ufriendDto);
			return this.save(ufriend);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#updateUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer updateUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null&ufriendDto.getUid()!=null){
		StringBuffer stringBuffer =new StringBuffer(DbType.UPDATE.toString());
		String[] fl = new String[]{"uid","page","rows","addTime"};//过滤掉的字段
		Map<String, Object> map = ufriendDto.createSetPropertiesVal(ufriendDto, "a", fl);
		Map<String, Object> params = (Map<String, Object>) map.get(StringUtils.PARAMS);		
		stringBuffer.append(" from "+UFriend.class.getName()+" a ");
		stringBuffer.append(map.get(StringUtils.SET_HQL));
		stringBuffer.append(" where a.id=:uid");
		params.put("uid",ufriendDto.getId());
		return this.executeHql(stringBuffer.toString(),params);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#deleteUfriend(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer deleteUfriend(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null&&ufriendDto.getUid()!=null){
			StringBuffer stringBuffer=new StringBuffer(DbType.DELETE.toString());
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where a.id=:uid");
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("uid",ufriendDto.getId());
			return this.executeHql(stringBuffer.toString(),params);
		}
		return 0;
	}
	
	public Integer deleteUfriendByGroupID(UFriendDto ufriendDto){
		if(ufriendDto!=null&&ufriendDto.getUFriendGroupDto().getGroupId()!=null){
			StringBuffer stringBuffer=new StringBuffer(DbType.DELETE.toString());
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where a.UFriendGroup.groupId=:groupId");
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("groupId",ufriendDto.getUFriendGroupDto().getGroupId());
			return this.executeHql(stringBuffer.toString(),params);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#getFriendCountByGroup(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer getFriendCountByGroup(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto.getUFriendGroupDto().getGroupId()!=null&&ufriendDto.getUUserDto().getUserId()!=null){
			StringBuffer stringBuffer=new StringBuffer();
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append("select count(*) from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where a.UUser.userId=:userid and a.UFriendGroup.groupId =:groupid");
			params.put("userid",ufriendDto.getUUserDto().getUserId());
			params.put("groupid",ufriendDto.getUFriendGroupDto().getGroupId());
			return this.count(stringBuffer.toString(), params);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UfriendDao#getFriendCountByUser(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer getFriendCountByUser(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto.getUUserDto().getUserId()!=null){
			StringBuffer stringBuffer=new StringBuffer();
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append("select count(*) from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where a.UUser.userId=:userid ");
			params.put("userid",ufriendDto.getUUserDto().getUserId());
			return this.count(stringBuffer.toString(), params);
		}
		return 0;
	}
	
	public UFriendDto ToUFriendDto(UFriend friend){
		UFriendDto friendDto=new UFriendDto();
		BeanUtils.copyProperties(friend, friendDto);
		if(friend.getUFriendGroup()!=null){
			UFriendGroupDto groupDto=new UFriendGroupDto();
			BeanUtils.copyProperties(friend.getUFriendGroup(), groupDto);
			friendDto.setUFriendGroupDto(groupDto);
		}
/*		if(friend.getUUserByUserId()!=null){
			UUserDto userDto=new UUserDto();
			BeanUtils.copyProperties(friend.getUUserByUserId(), userDto);
			friendDto.setUserDto(userDto);
		}
		if(friend.getUUserByFriendId()!=null){
			UUserDto ufriend=new UUserDto();
			BeanUtils.copyProperties(friend.getUUserByFriendId(), ufriend);
			friendDto.setFriendDto(ufriend);
		}*/
		return friendDto;
	}
	
	public UFriend ToUFriend(UFriendDto friendDto){
		UFriend friend=new UFriend();
		BeanUtils.copyProperties(friendDto, friend);
		if(friendDto.getUFriendGroupDto()!=null){
			
		}
		return friend;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UFriendDao#getUFriendCount(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public Integer getUFriendCount(UFriendDto ufriendDto) {
		// TODO Auto-generated method stub
		if(ufriendDto!=null){
			StringBuffer stringBuffer=new StringBuffer(DbType.SELECT+" count(*) ");
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where 1=1 ");
			if(ufriendDto.getUFriendGroupDto().getGroupId()!=null){
			stringBuffer.append(" and a.UFriendGroup.groupId =:groupId");
			params.put("groupId",ufriendDto.getUFriendGroupDto().getGroupId());
			}
			if(ufriendDto.getUUserDto().getUserId()!=null){
				stringBuffer.append(" and a.UUserDto.userId=:userid");
				params.put("userid",ufriendDto.getUUserDto().getUserId());
			}
			if(ufriendDto.getFriendDto().getUserId()!=null){
				stringBuffer.append(" and a.FriendDto.userId=:friendid");
				params.put("friendid",ufriendDto.getFriendDto().getUserId());
			}
			if(ufriendDto.getStatus()!=null){
				stringBuffer.append(" and a.status=:status");
				params.put("status",ufriendDto.getStatus());
			}else{
				stringBuffer.append(" and a.status = 1");
			}
			return this.count(stringBuffer.toString(), params);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.UFriendDao#getUFriendRow(com.cx.wxs.dto.UFriendDto)
	 */
	@Override
	public UFriendDto getUFriendRow(UFriendDto uFriendDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
