package com.cx.wxs.po;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UFriendGroup entity. @author MyEclipse Persistence Tools
 * 朋友分组
 */
@Entity
@Table(name = "u_friend_group", catalog = "wxs")
public class UFriendGroup implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private UUser UUser;
	private String name;
	private Integer groupType;
	private Integer groupCount;
	private Timestamp addTime;
	private Timestamp updateTime;
	private Short status;
	private List<UFriend> UFriends = new ArrayList<UFriend>(0);

	// Constructors

	/** default constructor */
	public UFriendGroup() {
	}

	/** minimal constructor */
	public UFriendGroup(UUser UUser, String name) {
		this.UUser = UUser;
		this.name = name;
	}

	/** full constructor */
	public UFriendGroup(UUser UUser, String name, Integer groupType,
			Integer groupCount, Timestamp addTime, Timestamp updateTime,
			Short status, List<UFriend> UFriends) {
		this.UUser = UUser;
		this.name = name;
		this.groupType = groupType;
		this.groupCount = groupCount;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.status = status;
		this.UFriends = UFriends;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "group_id", unique = true, nullable = false)
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UUser getUUser() {
		return this.UUser;
	}

	public void setUUser(UUser UUser) {
		this.UUser = UUser;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "group_type")
	public Integer getGroupType() {
		return this.groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	@Column(name = "group_count")
	public Integer getGroupCount() {
		return this.groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}
	
	@Column(name = "add_time", length = 19)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UFriendGroup")
	public List<UFriend> getUFriends() {
		return this.UFriends;
	}

	public void setUFriends(List<UFriend> UFriends) {
		this.UFriends = UFriends;
	}

}