package com.cx.wxs.dto;

import com.cx.wxs.base.BeanBase;
import java.sql.Timestamp;

import com.cx.wxs.dto.UFriendGroupDto;
import com.cx.wxs.po.UUser;
/**
 * @author 陈义
 * @date 2015-12-09 22:36:38
 */
public class UFriendDto extends BeanBase{
    private Integer id;
    private UFriendGroupDto UFriendGroupDto;
    private UUserDto UUserDto;
    private UUserDto FriendDto;
    private Integer friendType;
    private Integer friendRole;
    private Timestamp addTime;
    private Timestamp updateTime;
	private Short status;
	private Short care;

    /** default constructor */
    public UFriendDto(){

    }

    /** full constructor */
    public UFriendDto(Integer id,UFriendGroupDto UFriendGroupDto,UUserDto UUserDto,UUserDto FriendDto,Integer friendType,Integer friendRole,Timestamp addTime
    		,Timestamp updateTime,Short status,Short care){super();
        this.id=id;
        this.UFriendGroupDto=UFriendGroupDto;
        this.UUserDto=UUserDto;
        this.FriendDto=FriendDto;
        this.friendType=friendType;
        this.friendRole=friendRole;
        this.addTime=addTime;
        this.updateTime=updateTime;
        this.status=status;
        this.care=care;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public UFriendGroupDto getUFriendGroupDto(){
        return UFriendGroupDto;
    }
    public void setUFriendGroupDto(UFriendGroupDto UFriendGroupDto){
        this.UFriendGroupDto=UFriendGroupDto;
    }


	public UUserDto getUUserDto() {
		return UUserDto;
	}

	public void setUUserDto(UUserDto uUserDto) {
		UUserDto = uUserDto;
	}

	public UUserDto getFriendDto() {
		return FriendDto;
	}

	public void setFriendDto(UUserDto friendDto) {
		FriendDto = friendDto;
	}

	public Integer getFriendType(){
        return friendType;
    }
    public void setFriendType(Integer friendType){
        this.friendType=friendType;
    }

    public Integer getFriendRole(){
        return friendRole;
    }
    public void setFriendRole(Integer friendRole){
        this.friendRole=friendRole;
    }

    public Timestamp getAddTime(){
        return addTime;
    }
    public void setAddTime(Timestamp addTime){
        this.addTime=addTime;
    }

    
	public Timestamp getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public Short getStatus(){
		return status;
	}
	public void setStatus(Short status){
		this.status=status;
	}

	public Short getCare() {
		return care;
	}


	public void setCare(Short care) {
		this.care = care;
	}



}