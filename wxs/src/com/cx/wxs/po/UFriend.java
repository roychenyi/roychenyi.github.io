package com.cx.wxs.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UFriend entity. @author MyEclipse Persistence Tools
 * 朋友表
 */
@Entity
@Table(name = "u_friend", catalog = "wxs")
public class UFriend implements java.io.Serializable {

	// Fields

	private Integer id;
	private UFriendGroup UFriendGroup;
	private UUser UUser;
	private UUser Friend;
	private Integer friendType;
	private Integer friendRole;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Short status;
	private Short care;

	// Constructors

	/** default constructor */
	public UFriend() {
	}

	/** minimal constructor */
	public UFriend(Integer id, UFriendGroup UFriendGroup, UUser UUser,
			UUser Friend, Integer friendType, Timestamp addTime) {
		this.id = id;
		this.UFriendGroup = UFriendGroup;
		this.UUser=UUser;
		this.Friend = Friend;
		this.friendType = friendType;
		this.addTime = addTime;
	}

	/** full constructor */
	public UFriend(Integer id, UFriendGroup UFriendGroup, UUser UUser,
			UUser Friend, Integer friendType, Integer friendRole,
			Timestamp addTime, Timestamp updateTime, Short status, Short care) {
		this.id = id;
		this.UFriendGroup = UFriendGroup;
		this.UUser=UUser;
		this.Friend = Friend;
		this.friendType = friendType;
		this.friendRole = friendRole;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.status = status;
		this.care = care;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	public UFriendGroup getUFriendGroup() {
		return this.UFriendGroup;
	}

	public void setUFriendGroup(UFriendGroup UFriendGroup) {
		this.UFriendGroup = UFriendGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UUser getUUser() {
		return UUser;
	}

	/**
	 * @param uUser the uUser to set
	 */
	public void setUUser(UUser uUser) {
		UUser = uUser;
	}

	/**
	 * @return the friend
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friend_id", nullable = false)
	public UUser getFriend() {
		return Friend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(UUser friend) {
		Friend = friend;
	}


	@Column(name = "friend_type", nullable = false)
	public Integer getFriendType() {
		return this.friendType;
	}

	public void setFriendType(Integer friendType) {
		this.friendType = friendType;
	}

	@Column(name = "friend_role")
	public Integer getFriendRole() {
		return this.friendRole;
	}

	public void setFriendRole(Integer friendRole) {
		this.friendRole = friendRole;
	}

	@Column(name = "add_time", nullable = false, length = 19)
	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "care")
	public Short getCare() {
		return this.care;
	}

	public void setCare(Short care) {
		this.care = care;
	}

}