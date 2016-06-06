package com.cx.wxs.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
	@Override
	public UFriendDto getUFriend(UFriendDto uFriendDto){
		if(uFriendDto!=null&& uFriendDto.getUUserDto()!=null&&uFriendDto.getFFriendDto()!=null){
			StringBuffer stringBuffer=new StringBuffer();
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" where 1=1 ");
			stringBuffer.append(" and a.UUser.userId=:userId ");
			stringBuffer.append(" and a.FFriend.userId=:friendId ");
			stringBuffer.append(" and status=1 ");
			params.put("userId",uFriendDto.getUUserDto().getUserId());
			params.put("friendId",uFriendDto.getFFriendDto().getUserId());
			List<UFriend> list=this.find(stringBuffer.toString(), params);
			if(list!=null&&list.size()>0){
				 UFriend	uFriend=list.get(0);
				 uFriendDto=beanToDto.T1ToD1(uFriend, uFriendDto);
				return uFriendDto;
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
			if(ufriendDto.getUFriendGroupDto()!=null){
				stringBuffer.append(" and a.UFriendGroup.groupId =:groupId");
				params.put("groupId",ufriendDto.getUFriendGroupDto().getGroupId());
			}
			if(ufriendDto.getUUserDto()!=null){
				stringBuffer.append(" and a.UUser.userId=:userid");
				params.put("userid",ufriendDto.getUUserDto().getUserId());
			}
			if(ufriendDto.getFFriendDto()!=null){
				stringBuffer.append(" and a.Friend.userId=:friendid");
				params.put("friendid",ufriendDto.getFFriendDto().getUserId());
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
		if(ufriendDto!=null&&ufriendDto.getUUserDto()!=null&&ufriendDto.getFFriendDto()!=null){
			UFriend ufriend=beanToDto.D1ToT1(new UFriend(), ufriendDto);
			Date date=new Date();
			ufriend.setAddTime(new Timestamp(date.getTime()));
			ufriend.setFriendType(0);
			ufriend.setStatus((short)1);
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

	@Override
	public Integer nullifyFriend(UFriendDto uFriendDto){
		// TODO Auto-generated method stub
		if(uFriendDto!=null&uFriendDto.getId()!=null){
			StringBuffer stringBuffer =new StringBuffer(DbType.UPDATE.toString());
			Map<String,Object> params=new HashMap<String,Object>();
			stringBuffer.append(" from "+UFriend.class.getName()+" a ");
			stringBuffer.append(" set a.updateTime=:time");
			stringBuffer.append(",a.status=0");
			stringBuffer.append(" where a.id=:id ");
			Date date=new Date();
			params.put("time",new Timestamp(date.getTime()));
			params.put("id",uFriendDto.getId());
			
			
			return this.executeHql(stringBuffer.toString(),params);
		}else{
			return 0;
		}
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
			if(ufriendDto.getUFriendGroupDto()!=null){
				stringBuffer.append(" and a.UFriendGroup.groupId =:groupId");
				params.put("groupId",ufriendDto.getUFriendGroupDto().getGroupId());
			}
			if(ufriendDto.getUUserDto()!=null){
				stringBuffer.append(" and a.UUser.userId=:userid");
				params.put("userid",ufriendDto.getUUserDto().getUserId());
			}
			if(ufriendDto.getFFriendDto()!=null){
				stringBuffer.append(" and a.Friend.userId=:friendid");
				params.put("friendid",ufriendDto.getFFriendDto().getUserId());
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
