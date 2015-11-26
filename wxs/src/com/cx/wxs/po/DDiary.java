package com.cx.wxs.po;

import java.sql.Timestamp;
import java.util.HashSet;
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
 * DDiary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "d_diary", catalog = "wxs")
public class DDiary implements java.io.Serializable {

	// Fields

	private Integer diaryId;
	private UUser UUser;
	private BSite BSite;
	private DCatalog DCatalog;
	private String title;
	private String content;
	private Integer drarySize;
	private Integer replyCount;
	private Integer viewCount;
	private Short clientType;
	private String clientIp;
	private String clientAgent;
	private Timestamp writeTime;
	private Timestamp lastReadTime;
	private Timestamp lastReplyTime;
	private Timestamp modifyTime;
	private Short replyNotify;
	private Short moodLevel;
	private Short locked;
	private Short contribute;
	private Short role;
	private String tag;
	private Integer annexId;
	private Integer ext1;
	private Integer ext2;
	private Integer ext3;
	private Integer ext4;
	private Set<DAnnex> DAnnexes = new HashSet<DAnnex>(0);
	private Set<DUpvote> DUpvotes = new HashSet<DUpvote>(0);
	private Set<DAccess> DAccesses = new HashSet<DAccess>(0);
	private Set<DReply1> DReply1s = new HashSet<DReply1>(0);
	private Set<BTag> BTags = new HashSet<BTag>(0);

	// Constructors

	/** default constructor */
	public DDiary() {
	}

	/** minimal constructor */
	public DDiary(UUser UUser, BSite BSite, DCatalog DCatalog, String title,
			String content) {
		this.UUser = UUser;
		this.BSite = BSite;
		this.DCatalog = DCatalog;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public DDiary(UUser UUser, BSite BSite, DCatalog DCatalog, String title,
			String content, Integer drarySize, Integer replyCount,
			Integer viewCount, Short clientType, String clientIp,
			String clientAgent, Timestamp writeTime, Timestamp lastReadTime,
			Timestamp lastReplyTime, Timestamp modifyTime, Short replyNotify,
			Short moodLevel, Short locked, Short contribute, Short role,
			String tag, Integer annexId, Integer ext1, Integer ext2,
			Integer ext3, Integer ext4, Set<DAnnex> DAnnexes,
			Set<DUpvote> DUpvotes, Set<DAccess> DAccesses,
			Set<DReply1> DReply1s, Set<BTag> BTags) {
		this.UUser = UUser;
		this.BSite = BSite;
		this.DCatalog = DCatalog;
		this.title = title;
		this.content = content;
		this.drarySize = drarySize;
		this.replyCount = replyCount;
		this.viewCount = viewCount;
		this.clientType = clientType;
		this.clientIp = clientIp;
		this.clientAgent = clientAgent;
		this.writeTime = writeTime;
		this.lastReadTime = lastReadTime;
		this.lastReplyTime = lastReplyTime;
		this.modifyTime = modifyTime;
		this.replyNotify = replyNotify;
		this.moodLevel = moodLevel;
		this.locked = locked;
		this.contribute = contribute;
		this.role = role;
		this.tag = tag;
		this.annexId = annexId;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
		this.ext4 = ext4;
		this.DAnnexes = DAnnexes;
		this.DUpvotes = DUpvotes;
		this.DAccesses = DAccesses;
		this.DReply1s = DReply1s;
		this.BTags = BTags;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "diary_id", unique = true, nullable = false)
	public Integer getDiaryId() {
		return this.diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UUser getUUser() {
		return this.UUser;
	}

	public void setUUser(UUser UUser) {
		this.UUser = UUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id", nullable = false)
	public BSite getBSite() {
		return this.BSite;
	}

	public void setBSite(BSite BSite) {
		this.BSite = BSite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id", nullable = false)
	public DCatalog getDCatalog() {
		return this.DCatalog;
	}

	public void setDCatalog(DCatalog DCatalog) {
		this.DCatalog = DCatalog;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "drary_size")
	public Integer getDrarySize() {
		return this.drarySize;
	}

	public void setDrarySize(Integer drarySize) {
		this.drarySize = drarySize;
	}

	@Column(name = "reply_count")
	public Integer getReplyCount() {
		return this.replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	@Column(name = "view_count")
	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name = "client_type")
	public Short getClientType() {
		return this.clientType;
	}

	public void setClientType(Short clientType) {
		this.clientType = clientType;
	}

	@Column(name = "client_ip", length = 50)
	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name = "client_agent", length = 250)
	public String getClientAgent() {
		return this.clientAgent;
	}

	public void setClientAgent(String clientAgent) {
		this.clientAgent = clientAgent;
	}

	@Column(name = "write_time", length = 0)
	public Timestamp getWriteTime() {
		return this.writeTime;
	}

	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}

	@Column(name = "last_read_time", length = 0)
	public Timestamp getLastReadTime() {
		return this.lastReadTime;
	}

	public void setLastReadTime(Timestamp lastReadTime) {
		this.lastReadTime = lastReadTime;
	}

	@Column(name = "last_reply_time", length = 0)
	public Timestamp getLastReplyTime() {
		return this.lastReplyTime;
	}

	public void setLastReplyTime(Timestamp lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	@Column(name = "modify_time", length = 0)
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "reply_notify")
	public Short getReplyNotify() {
		return this.replyNotify;
	}

	public void setReplyNotify(Short replyNotify) {
		this.replyNotify = replyNotify;
	}

	@Column(name = "mood_level")
	public Short getMoodLevel() {
		return this.moodLevel;
	}

	public void setMoodLevel(Short moodLevel) {
		this.moodLevel = moodLevel;
	}

	@Column(name = "locked")
	public Short getLocked() {
		return this.locked;
	}

	public void setLocked(Short locked) {
		this.locked = locked;
	}

	@Column(name = "contribute")
	public Short getContribute() {
		return this.contribute;
	}

	public void setContribute(Short contribute) {
		this.contribute = contribute;
	}

	@Column(name = "role")
	public Short getRole() {
		return this.role;
	}

	public void setRole(Short role) {
		this.role = role;
	}

	@Column(name = "tag", length = 100)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Column(name = "annex_id")
	public Integer getAnnexId() {
		return this.annexId;
	}

	public void setAnnexId(Integer annexId) {
		this.annexId = annexId;
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

	@Column(name = "ext3")
	public Integer getExt3() {
		return this.ext3;
	}

	public void setExt3(Integer ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "ext4")
	public Integer getExt4() {
		return this.ext4;
	}

	public void setExt4(Integer ext4) {
		this.ext4 = ext4;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DDiary")
	public Set<DAnnex> getDAnnexes() {
		return this.DAnnexes;
	}

	public void setDAnnexes(Set<DAnnex> DAnnexes) {
		this.DAnnexes = DAnnexes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DDiary")
	public Set<DUpvote> getDUpvotes() {
		return this.DUpvotes;
	}

	public void setDUpvotes(Set<DUpvote> DUpvotes) {
		this.DUpvotes = DUpvotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DDiary")
	public Set<DAccess> getDAccesses() {
		return this.DAccesses;
	}

	public void setDAccesses(Set<DAccess> DAccesses) {
		this.DAccesses = DAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DDiary")
	public Set<DReply1> getDReply1s() {
		return this.DReply1s;
	}

	public void setDReply1s(Set<DReply1> DReply1s) {
		this.DReply1s = DReply1s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "DDiary")
	public Set<BTag> getBTags() {
		return this.BTags;
	}

	public void setBTags(Set<BTag> BTags) {
		this.BTags = BTags;
	}

}