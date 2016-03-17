package com.cx.wxs.po;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IReply2 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "i_reply2", catalog = "wxs")
public class IReply2 implements java.io.Serializable {

	// Fields

	private Integer ireply1Id;
	private UUser UUserByUserId;
	private IReply1 IReply1;
	private UUser UUserByCommentator;
	private String content;
	private String clientAngent;
	private String clientIp;
	private Short clientType;
	private Timestamp replyTime;
	private Timestamp replyId;
	private Short sortOrder;
	private Integer ext1;
	private Integer ext2;
	private String ext3;
	private String ext4;

	// Constructors

	/** default constructor */
	public IReply2() {
	}

	/** minimal constructor */
	public IReply2(UUser UUserByUserId, IReply1 IReply1,
			UUser UUserByCommentator, String content) {
		this.UUserByUserId = UUserByUserId;
		this.IReply1 = IReply1;
		this.UUserByCommentator = UUserByCommentator;
		this.content = content;
	}

	/** full constructor */
	public IReply2(UUser UUserByUserId, IReply1 IReply1,
			UUser UUserByCommentator, String content, String clientAngent,
			String clientIp, Short clientType, Timestamp replyTime,
			Timestamp replyId, Short sortOrder, Integer ext1, Integer ext2,
			String ext3, String ext4) {
		this.UUserByUserId = UUserByUserId;
		this.IReply1 = IReply1;
		this.UUserByCommentator = UUserByCommentator;
		this.content = content;
		this.clientAngent = clientAngent;
		this.clientIp = clientIp;
		this.clientType = clientType;
		this.replyTime = replyTime;
		this.replyId = replyId;
		this.sortOrder = sortOrder;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
		this.ext4 = ext4;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ireply1_id", unique = true, nullable = false)
	public Integer getIreply1Id() {
		return this.ireply1Id;
	}

	public void setIreply1Id(Integer ireply1Id) {
		this.ireply1Id = ireply1Id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UUser getUUserByUserId() {
		return this.UUserByUserId;
	}

	public void setUUserByUserId(UUser UUserByUserId) {
		this.UUserByUserId = UUserByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ireply_id", nullable = false)
	public IReply1 getIReply1() {
		return this.IReply1;
	}

	public void setIReply1(IReply1 IReply1) {
		this.IReply1 = IReply1;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commentator", nullable = false)
	public UUser getUUserByCommentator() {
		return this.UUserByCommentator;
	}

	public void setUUserByCommentator(UUser UUserByCommentator) {
		this.UUserByCommentator = UUserByCommentator;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "client_angent", length = 250)
	public String getClientAngent() {
		return this.clientAngent;
	}

	public void setClientAngent(String clientAngent) {
		this.clientAngent = clientAngent;
	}

	@Column(name = "client_ip", length = 50)
	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name = "client_type")
	public Short getClientType() {
		return this.clientType;
	}

	public void setClientType(Short clientType) {
		this.clientType = clientType;
	}

	@Column(name = "reply_time", length = 19)
	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	@Column(name = "reply_id", length = 19)
	public Timestamp getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Timestamp replyId) {
		this.replyId = replyId;
	}

	@Column(name = "sort_order")
	public Short getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name = "ext1")
	public Integer getExt1() {
		return this.ext1;
	}

	public void setExt1(Integer ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "ext2")
	public Integer getExt2() {
		return this.ext2;
	}

	public void setExt2(Integer ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "ext3", length = 1024)
	public String getExt3() {
		return this.ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "ext4", length = 1024)
	public String getExt4() {
		return this.ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

}