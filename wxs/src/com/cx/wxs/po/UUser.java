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
import javax.persistence.UniqueConstraint;

/**
 * UUser entity. @author MyEclipse Persistence Tools
 * 用户表
 */
@Entity
@Table(name = "u_user", catalog = "wxs", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private USign USign;
	private BSite BSite;
	private SysRank sysRank;
	private String username;
	private String password;
	private String nickname;
	private Integer roleId;
	private Short sex;
	private Timestamp birth;
	private String email;
	private String homepage;
	private String qq;
	private String blood;
	private String mobile;
	private Integer provinceId;
	private Integer cityId;
	private Integer schoolId;
	private Integer collegeId;
	private String class_;
	private String portrait;
	private Timestamp regtime;
	private Timestamp lastTime;
	private String lastIp;
	private Short keepDay;
	private Short onlineStatus;
	private Integer rank;
	private Integer articleCount;
	private Integer articleReplyCount;
	private Integer topicCount;
	private Integer topicReplyCount;
	private Integer imageCount;
	private Integer imageReplyCount;
	private Integer guestbookCount;
	private Integer tagCount;
	private Integer fans;
	private Integer follows;
	private Integer popedom;
	private Integer codeId;
	private List<WMenber> WMenbers = new ArrayList<WMenber>(0);
	private List<DDiary> DDiaries = new ArrayList<DDiary>(0);
	private List<VVote> VVotes = new ArrayList<VVote>(0);
	private List<DUpvote> DUpvotes = new ArrayList<DUpvote>(0);
	private List<DReply1> DReply1s = new ArrayList<DReply1>(0);
	private List<IReply2> IReply2sForUserId = new ArrayList<IReply2>(0);
	private List<SSound> SSounds = new ArrayList<SSound>(0);
	private List<USign> USigns = new ArrayList<USign>(0);
	private List<SReply2> SReply2sForCommentator = new ArrayList<SReply2>(0);
	private List<BAccess> BAccesses = new ArrayList<BAccess>(0);
	private List<VUpvote> VUpvotes = new ArrayList<VUpvote>(0);
	private List<IImage> IImages = new ArrayList<IImage>(0);
	private List<VReply1> VReply1s = new ArrayList<VReply1>(0);
	private List<DCatalog> DCatalogs = new ArrayList<DCatalog>(0);
	private List<MAffiche> MAffiches = new ArrayList<MAffiche>(0);
	private List<DReply2> DReply2sForCommentator = new ArrayList<DReply2>(0);
	private List<WAccess> WAccesses = new ArrayList<WAccess>(0);
	private List<BSite> BSites = new ArrayList<BSite>(0);
	private List<SUpvote> SUpvotes = new ArrayList<SUpvote>(0);
	private List<IReply1> IReply1s = new ArrayList<IReply1>(0);
	private List<IUpvote> IUpvotes = new ArrayList<IUpvote>(0);
	private List<SysLoginRecord> sysLoginRecords = new ArrayList<SysLoginRecord>(0);
	private List<SBox> SBoxes = new ArrayList<SBox>(0);
	private List<VReply2> VReply2sForCommentator = new ArrayList<VReply2>(0);
	private List<SReply2> SReply2sForUserId = new ArrayList<SReply2>(0);
	private List<UFriend> UFriendByUser=new ArrayList<UFriend>(0);
	private List<UFriend> UFriendByFriend=new ArrayList<UFriend>(0);
	private List<UFriendGroup> UFriendGroups = new ArrayList<UFriendGroup>(0);
	private List<MGuestbook> MGuestbooks = new ArrayList<MGuestbook>(0);
	private List<IAlbum> IAlbums = new ArrayList<IAlbum>(0);
	private List<SAccess> SAccesses = new ArrayList<SAccess>(0);
	private List<VReply2> VReply2sForUserId = new ArrayList<VReply2>(0);
	private List<VCount> VCounts = new ArrayList<VCount>(0);
	private List<DAnnex> DAnnexes = new ArrayList<DAnnex>(0);
	private List<UBook> UBooks = new ArrayList<UBook>(0);
	private List<IAccess> IAccesses = new ArrayList<IAccess>(0);
	private List<BBacklist> BBacklistsForUserId = new ArrayList<BBacklist>(0);
	private List<DReply2> DReply2sForUserId = new ArrayList<DReply2>(0);
	private List<MMessage> MMessagesForUserId = new ArrayList<MMessage>(0);
	private List<VAccess> VAccesses = new ArrayList<VAccess>(0);
	private List<SysInvitationCode> sysInvitationCodes = new ArrayList<SysInvitationCode>(
			0);
	private List<MMessage> MMessagesForReceiveId = new ArrayList<MMessage>(0);
	private List<MReply> MReplies = new ArrayList<MReply>(0);
	private List<IReply2> IReply2sForCommentator = new ArrayList<IReply2>(0);
	private List<SReply1> SReply1s = new ArrayList<SReply1>(0);
	private List<DAccess> DAccesses = new ArrayList<DAccess>(0);
	private List<BBacklist> BBacklistsForBackId = new ArrayList<BBacklist>(0);

	// Constructors

	/** default constructor */
	public UUser() {
	}

	/** minimal constructor */
	public UUser(String username, String password, String nickname,
			Integer roleId) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.roleId = roleId;
	}

	/** full constructor */
	public UUser(USign USign, BSite BSite, SysRank sysRank, String username,
			String password, String nickname, Integer roleId, Short sex,
			Timestamp birth, String email, String homepage, String qq,
			String blood, String mobile, Integer provinceId, Integer cityId,
			Integer schoolId, Integer collegeId, String class_,
			String portrait, Timestamp regtime, Timestamp lastTime,
			String lastIp, Short keepDay, Short onlineStatus, Integer rank,
			Integer articleCount, Integer articleReplyCount,
			Integer topicCount, Integer topicReplyCount, Integer imageCount,
			Integer imageReplyCount, Integer guestbookCount, Integer tagCount,
			Integer fans, Integer follows, Integer popedom, Integer codeId,
			List<WMenber> WMenbers, List<DDiary> DDiaries, List<VVote> VVotes, List<DUpvote> DUpvotes,
			List<DReply1> DReply1s, List<IReply2> IReply2sForUserId,
			List<SSound> SSounds, List<USign> USigns,
			List<SReply2> SReply2sForCommentator, List<BAccess> BAccesses,
			List<VUpvote> VUpvotes, List<IImage> IImages, List<VReply1> VReply1s,
			List<DCatalog> DCatalogs, List<MAffiche> MAffiches,
			List<DReply2> DReply2sForCommentator, List<WAccess> WAccesses,
			List<BSite> BSites, List<SUpvote> SUpvotes, List<IReply1> IReply1s,
			List<IUpvote> IUpvotes, List<SysLoginRecord> sysLoginRecords,
			List<SBox> SBoxes, List<VReply2> VReply2sForCommentator,
			List<SReply2> SReply2sForUserId,List<UFriend> UFriendByUser,List<UFriend> UFriendByFriend, List<UFriendGroup> UFriendGroups,
			List<MGuestbook> MGuestbooks, List<IAlbum> IAlbums,
			List<SAccess> SAccesses, List<VReply2> VReply2sForUserId,
			List<VCount> VCounts, List<DAnnex> DAnnexes, List<UBook> UBooks,
			List<IAccess> IAccesses, List<BBacklist> BBacklistsForUserId,
			List<DReply2> DReply2sForUserId, List<MMessage> MMessagesForUserId,
			List<VAccess> VAccesses, List<SysInvitationCode> sysInvitationCodes,
			List<MMessage> MMessagesForReceiveId, List<MReply> MReplies,
			List<IReply2> IReply2sForCommentator, List<SReply1> SReply1s,
			List<DAccess> DAccesses, List<BBacklist> BBacklistsForBackId) {
		this.USign = USign;
		this.BSite = BSite;
		this.sysRank = sysRank;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.roleId = roleId;
		this.sex = sex;
		this.birth = birth;
		this.email = email;
		this.homepage = homepage;
		this.qq = qq;
		this.blood = blood;
		this.mobile = mobile;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.schoolId = schoolId;
		this.collegeId = collegeId;
		this.class_ = class_;
		this.portrait = portrait;
		this.regtime = regtime;
		this.lastTime = lastTime;
		this.lastIp = lastIp;
		this.keepDay = keepDay;
		this.onlineStatus = onlineStatus;
		this.rank = rank;
		this.articleCount = articleCount;
		this.articleReplyCount = articleReplyCount;
		this.topicCount = topicCount;
		this.topicReplyCount = topicReplyCount;
		this.imageCount = imageCount;
		this.imageReplyCount = imageReplyCount;
		this.guestbookCount = guestbookCount;
		this.tagCount = tagCount;
		this.fans = fans;
		this.follows = follows;
		this.popedom = popedom;
		this.codeId = codeId;
		this.WMenbers=WMenbers;
		this.DDiaries = DDiaries;
		this.VVotes = VVotes;
		this.DUpvotes = DUpvotes;
		this.DReply1s = DReply1s;
		this.IReply2sForUserId = IReply2sForUserId;
		this.SSounds = SSounds;
		this.USigns = USigns;
		this.SReply2sForCommentator = SReply2sForCommentator;
		this.BAccesses = BAccesses;
		this.VUpvotes = VUpvotes;
		this.IImages = IImages;
		this.VReply1s = VReply1s;
		this.DCatalogs = DCatalogs;
		this.MAffiches = MAffiches;
		this.DReply2sForCommentator = DReply2sForCommentator;
		this.WAccesses = WAccesses;
		this.BSites = BSites;
		this.SUpvotes = SUpvotes;
		this.IReply1s = IReply1s;
		this.IUpvotes = IUpvotes;
		this.sysLoginRecords = sysLoginRecords;
		this.SBoxes = SBoxes;
		this.VReply2sForCommentator = VReply2sForCommentator;
		this.SReply2sForUserId = SReply2sForUserId;
		this.UFriendByUser=UFriendByUser;
		this.UFriendByFriend=UFriendByFriend;
		this.UFriendGroups = UFriendGroups;
		this.MGuestbooks = MGuestbooks;
		this.IAlbums = IAlbums;
		this.SAccesses = SAccesses;
		this.VReply2sForUserId = VReply2sForUserId;
		this.VCounts = VCounts;
		this.DAnnexes = DAnnexes;
		this.UBooks = UBooks;
		this.IAccesses = IAccesses;
		this.BBacklistsForUserId = BBacklistsForUserId;
		this.DReply2sForUserId = DReply2sForUserId;
		this.MMessagesForUserId = MMessagesForUserId;
		this.VAccesses = VAccesses;
		this.sysInvitationCodes = sysInvitationCodes;
		this.MMessagesForReceiveId = MMessagesForReceiveId;
		this.MReplies = MReplies;
		this.IReply2sForCommentator = IReply2sForCommentator;
		this.SReply1s = SReply1s;
		this.DAccesses = DAccesses;
		this.BBacklistsForBackId = BBacklistsForBackId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sign_id")
	public USign getUSign() {
		return this.USign;
	}

	public void setUSign(USign USign) {
		this.USign = USign;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	public BSite getBSite() {
		return this.BSite;
	}

	public void setBSite(BSite BSite) {
		this.BSite = BSite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rank_id")
	public SysRank getSysRank() {
		return this.sysRank;
	}

	public void setSysRank(SysRank sysRank) {
		this.sysRank = sysRank;
	}

	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "nickname", nullable = false, length = 20)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "sex")
	public Short getSex() {
		return this.sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	@Column(name = "birth", length = 19)
	public Timestamp getBirth() {
		return this.birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "homepage", length = 100)
	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Column(name = "qq", length = 16)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "blood", length = 5)
	public String getBlood() {
		return this.blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	@Column(name = "mobile", length = 16)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "province_id")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "school_id")
	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "college_id")
	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "class", length = 10)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Column(name = "portrait", length = 100)
	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	@Column(name = "regtime", length = 19)
	public Timestamp getRegtime() {
		return this.regtime;
	}

	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}

	@Column(name = "last_time", length = 19)
	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	@Column(name = "last_ip", length = 48)
	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	@Column(name = "keep_day")
	public Short getKeepDay() {
		return this.keepDay;
	}

	public void setKeepDay(Short keepDay) {
		this.keepDay = keepDay;
	}

	@Column(name = "online_status")
	public Short getOnlineStatus() {
		return this.onlineStatus;
	}

	public void setOnlineStatus(Short onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "article_count")
	public Integer getArticleCount() {
		return this.articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@Column(name = "article_reply_count")
	public Integer getArticleReplyCount() {
		return this.articleReplyCount;
	}

	public void setArticleReplyCount(Integer articleReplyCount) {
		this.articleReplyCount = articleReplyCount;
	}

	@Column(name = "topic_count")
	public Integer getTopicCount() {
		return this.topicCount;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}

	@Column(name = "topic_reply_count")
	public Integer getTopicReplyCount() {
		return this.topicReplyCount;
	}

	public void setTopicReplyCount(Integer topicReplyCount) {
		this.topicReplyCount = topicReplyCount;
	}

	@Column(name = "image_count")
	public Integer getImageCount() {
		return this.imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}

	@Column(name = "image_reply_count")
	public Integer getImageReplyCount() {
		return this.imageReplyCount;
	}

	public void setImageReplyCount(Integer imageReplyCount) {
		this.imageReplyCount = imageReplyCount;
	}

	@Column(name = "guestbook_count")
	public Integer getGuestbookCount() {
		return this.guestbookCount;
	}

	public void setGuestbookCount(Integer guestbookCount) {
		this.guestbookCount = guestbookCount;
	}

	@Column(name = "tag_count")
	public Integer getTagCount() {
		return this.tagCount;
	}

	public void setTagCount(Integer tagCount) {
		this.tagCount = tagCount;
	}

	@Column(name = "fans")
	public Integer getFans() {
		return this.fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}

	@Column(name = "follows")
	public Integer getFollows() {
		return this.follows;
	}

	public void setFollows(Integer follows) {
		this.follows = follows;
	}

	@Column(name = "popedom")
	public Integer getPopedom() {
		return this.popedom;
	}

	public void setPopedom(Integer popedom) {
		this.popedom = popedom;
	}

	@Column(name = "code_id")
	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	
	/**
	 * @return the wMenbers
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<WMenber> getWMenbers() {
		return WMenbers;
	}

	/**
	 * @param wMenbers the wMenbers to set
	 */
	public void setWMenbers(List<WMenber> wMenbers) {
		WMenbers = wMenbers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DDiary> getDDiaries() {
		return this.DDiaries;
	}

	public void setDDiaries(List<DDiary> DDiaries) {
		this.DDiaries = DDiaries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<VVote> getVVotes() {
		return this.VVotes;
	}

	public void setVVotes(List<VVote> VVotes) {
		this.VVotes = VVotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DUpvote> getDUpvotes() {
		return this.DUpvotes;
	}

	public void setDUpvotes(List<DUpvote> DUpvotes) {
		this.DUpvotes = DUpvotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DReply1> getDReply1s() {
		return this.DReply1s;
	}

	public void setDReply1s(List<DReply1> DReply1s) {
		this.DReply1s = DReply1s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<IReply2> getIReply2sForUserId() {
		return this.IReply2sForUserId;
	}

	public void setIReply2sForUserId(List<IReply2> IReply2sForUserId) {
		this.IReply2sForUserId = IReply2sForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SSound> getSSounds() {
		return this.SSounds;
	}

	public void setSSounds(List<SSound> SSounds) {
		this.SSounds = SSounds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<USign> getUSigns() {
		return this.USigns;
	}

	public void setUSigns(List<USign> USigns) {
		this.USigns = USigns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByCommentator")
	public List<SReply2> getSReply2sForCommentator() {
		return this.SReply2sForCommentator;
	}

	public void setSReply2sForCommentator(List<SReply2> SReply2sForCommentator) {
		this.SReply2sForCommentator = SReply2sForCommentator;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<BAccess> getBAccesses() {
		return this.BAccesses;
	}

	public void setBAccesses(List<BAccess> BAccesses) {
		this.BAccesses = BAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "VVote")
	public List<VUpvote> getVUpvotes() {
		return this.VUpvotes;
	}

	public void setVUpvotes(List<VUpvote> VUpvotes) {
		this.VUpvotes = VUpvotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<IImage> getIImages() {
		return this.IImages;
	}

	public void setIImages(List<IImage> IImages) {
		this.IImages = IImages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<VReply1> getVReply1s() {
		return this.VReply1s;
	}

	public void setVReply1s(List<VReply1> VReply1s) {
		this.VReply1s = VReply1s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DCatalog> getDCatalogs() {
		return this.DCatalogs;
	}

	public void setDCatalogs(List<DCatalog> DCatalogs) {
		this.DCatalogs = DCatalogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<MAffiche> getMAffiches() {
		return this.MAffiches;
	}

	public void setMAffiches(List<MAffiche> MAffiches) {
		this.MAffiches = MAffiches;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByCommentator")
	public List<DReply2> getDReply2sForCommentator() {
		return this.DReply2sForCommentator;
	}

	public void setDReply2sForCommentator(List<DReply2> DReply2sForCommentator) {
		this.DReply2sForCommentator = DReply2sForCommentator;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<WAccess> getWAccesses() {
		return this.WAccesses;
	}

	public void setWAccesses(List<WAccess> WAccesses) {
		this.WAccesses = WAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<BSite> getBSites() {
		return this.BSites;
	}

	public void setBSites(List<BSite> BSites) {
		this.BSites = BSites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SUpvote> getSUpvotes() {
		return this.SUpvotes;
	}

	public void setSUpvotes(List<SUpvote> SUpvotes) {
		this.SUpvotes = SUpvotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<IReply1> getIReply1s() {
		return this.IReply1s;
	}

	public void setIReply1s(List<IReply1> IReply1s) {
		this.IReply1s = IReply1s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<IUpvote> getIUpvotes() {
		return this.IUpvotes;
	}

	public void setIUpvotes(List<IUpvote> IUpvotes) {
		this.IUpvotes = IUpvotes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SysLoginRecord> getSysLoginRecords() {
		return this.sysLoginRecords;
	}

	public void setSysLoginRecords(List<SysLoginRecord> sysLoginRecords) {
		this.sysLoginRecords = sysLoginRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SBox> getSBoxes() {
		return this.SBoxes;
	}

	public void setSBoxes(List<SBox> SBoxes) {
		this.SBoxes = SBoxes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByCommentator")
	public List<VReply2> getVReply2sForCommentator() {
		return this.VReply2sForCommentator;
	}

	public void setVReply2sForCommentator(List<VReply2> VReply2sForCommentator) {
		this.VReply2sForCommentator = VReply2sForCommentator;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<SReply2> getSReply2sForUserId() {
		return this.SReply2sForUserId;
	}

	public void setSReply2sForUserId(List<SReply2> SReply2sForUserId) {
		this.SReply2sForUserId = SReply2sForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<UFriend> getUFriendByUser() {
		return UFriendByUser;
	}

	public void setUFriendByUser(List<UFriend> uFriendByUser) {
		UFriendByUser = uFriendByUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "FFriend")
	public List<UFriend> getUFriendByFriend() {
		return UFriendByFriend;
	}

	public void setUFriendByFriend(List<UFriend> uFriendByFriend) {
		UFriendByFriend = uFriendByFriend;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<UFriendGroup> getUFriendGroups() {
		return this.UFriendGroups;
	}


	public void setUFriendGroups(List<UFriendGroup> UFriendGroups) {
		this.UFriendGroups = UFriendGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<MGuestbook> getMGuestbooks() {
		return this.MGuestbooks;
	}

	public void setMGuestbooks(List<MGuestbook> MGuestbooks) {
		this.MGuestbooks = MGuestbooks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<IAlbum> getIAlbums() {
		return this.IAlbums;
	}

	public void setIAlbums(List<IAlbum> IAlbums) {
		this.IAlbums = IAlbums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SAccess> getSAccesses() {
		return this.SAccesses;
	}

	public void setSAccesses(List<SAccess> SAccesses) {
		this.SAccesses = SAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<VReply2> getVReply2sForUserId() {
		return this.VReply2sForUserId;
	}

	public void setVReply2sForUserId(List<VReply2> VReply2sForUserId) {
		this.VReply2sForUserId = VReply2sForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<VCount> getVCounts() {
		return this.VCounts;
	}

	public void setVCounts(List<VCount> VCounts) {
		this.VCounts = VCounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DAnnex> getDAnnexes() {
		return this.DAnnexes;
	}

	public void setDAnnexes(List<DAnnex> DAnnexes) {
		this.DAnnexes = DAnnexes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<UBook> getUBooks() {
		return this.UBooks;
	}

	public void setUBooks(List<UBook> UBooks) {
		this.UBooks = UBooks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<IAccess> getIAccesses() {
		return this.IAccesses;
	}

	public void setIAccesses(List<IAccess> IAccesses) {
		this.IAccesses = IAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<BBacklist> getBBacklistsForUserId() {
		return this.BBacklistsForUserId;
	}

	public void setBBacklistsForUserId(List<BBacklist> BBacklistsForUserId) {
		this.BBacklistsForUserId = BBacklistsForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<DReply2> getDReply2sForUserId() {
		return this.DReply2sForUserId;
	}

	public void setDReply2sForUserId(List<DReply2> DReply2sForUserId) {
		this.DReply2sForUserId = DReply2sForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByUserId")
	public List<MMessage> getMMessagesForUserId() {
		return this.MMessagesForUserId;
	}

	public void setMMessagesForUserId(List<MMessage> MMessagesForUserId) {
		this.MMessagesForUserId = MMessagesForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<VAccess> getVAccesses() {
		return this.VAccesses;
	}

	public void setVAccesses(List<VAccess> VAccesses) {
		this.VAccesses = VAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SysInvitationCode> getSysInvitationCodes() {
		return this.sysInvitationCodes;
	}

	public void setSysInvitationCodes(List<SysInvitationCode> sysInvitationCodes) {
		this.sysInvitationCodes = sysInvitationCodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByReceiveId")
	public List<MMessage> getMMessagesForReceiveId() {
		return this.MMessagesForReceiveId;
	}

	public void setMMessagesForReceiveId(List<MMessage> MMessagesForReceiveId) {
		this.MMessagesForReceiveId = MMessagesForReceiveId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<MReply> getMReplies() {
		return this.MReplies;
	}

	public void setMReplies(List<MReply> MReplies) {
		this.MReplies = MReplies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByCommentator")
	public List<IReply2> getIReply2sForCommentator() {
		return this.IReply2sForCommentator;
	}

	public void setIReply2sForCommentator(List<IReply2> IReply2sForCommentator) {
		this.IReply2sForCommentator = IReply2sForCommentator;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<SReply1> getSReply1s() {
		return this.SReply1s;
	}

	public void setSReply1s(List<SReply1> SReply1s) {
		this.SReply1s = SReply1s;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUser")
	public List<DAccess> getDAccesses() {
		return this.DAccesses;
	}

	public void setDAccesses(List<DAccess> DAccesses) {
		this.DAccesses = DAccesses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "UUserByBackId")
	public List<BBacklist> getBBacklistsForBackId() {
		return this.BBacklistsForBackId;
	}

	public void setBBacklistsForBackId(List<BBacklist> BBacklistsForBackId) {
		this.BBacklistsForBackId = BBacklistsForBackId;
	}

}