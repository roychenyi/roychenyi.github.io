package com.cx.wxs.dto;

import com.cx.wxs.base.BeanBase;
import java.sql.Timestamp;

import com.cx.wxs.dto.USignDto;
import com.cx.wxs.dto.BSiteDto;
import com.cx.wxs.dto.SysRankDto;
/**
 * @author 陈义
 * @date 2016-04-05 21:16:31
 */
public class UUserDto extends BeanBase{
    private Integer userId;
    private USignDto USignDto;
    private BSiteDto BSiteDto;
    private SysRankDto sysRankDto;
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

    /** default constructor */
    public UUserDto(){

    }
    
    public UUserDto(Integer userId){
    	this.userId=userId;
    }

    /** full constructor */
    public UUserDto(Integer userId,USignDto USignDto,BSiteDto BSiteDto,SysRankDto sysRankDto,String username,String password,String nickname,Integer roleId,Short sex,Timestamp birth,String email,String homepage,String qq,String blood,String mobile,Integer provinceId,Integer cityId,Integer schoolId,Integer collegeId,String class_,String portrait,Timestamp regtime,Timestamp lastTime,String lastIp,Short keepDay,Short onlineStatus,Integer rank,Integer articleCount,Integer articleReplyCount,Integer topicCount,Integer topicReplyCount,Integer imageCount,Integer imageReplyCount,Integer guestbookCount,Integer tagCount,Integer fans,Integer follows,Integer popedom,Integer codeId){super();
        this.userId=userId;
        this.USignDto=USignDto;
        this.BSiteDto=BSiteDto;
        this.sysRankDto=sysRankDto;
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.roleId=roleId;
        this.sex=sex;
        this.birth=birth;
        this.email=email;
        this.homepage=homepage;
        this.qq=qq;
        this.blood=blood;
        this.mobile=mobile;
        this.provinceId=provinceId;
        this.cityId=cityId;
        this.schoolId=schoolId;
        this.collegeId=collegeId;
        this.class_=class_;
        this.portrait=portrait;
        this.regtime=regtime;
        this.lastTime=lastTime;
        this.lastIp=lastIp;
        this.keepDay=keepDay;
        this.onlineStatus=onlineStatus;
        this.rank=rank;
        this.articleCount=articleCount;
        this.articleReplyCount=articleReplyCount;
        this.topicCount=topicCount;
        this.topicReplyCount=topicReplyCount;
        this.imageCount=imageCount;
        this.imageReplyCount=imageReplyCount;
        this.guestbookCount=guestbookCount;
        this.tagCount=tagCount;
        this.fans=fans;
        this.follows=follows;
        this.popedom=popedom;
        this.codeId=codeId;
    }

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId=userId;
    }

    public USignDto getUSignDto(){
        return USignDto;
    }
    public void setUSignDto(USignDto USignDto){
        this.USignDto=USignDto;
    }

    public BSiteDto getBSiteDto(){
        return BSiteDto;
    }
    public void setBSiteDto(BSiteDto BSiteDto){
        this.BSiteDto=BSiteDto;
    }

    public SysRankDto getSysRankDto(){
        return sysRankDto;
    }
    public void setSysRankDto(SysRankDto sysRankDto){
        this.sysRankDto=sysRankDto;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getNickname(){
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public Integer getRoleId(){
        return roleId;
    }
    public void setRoleId(Integer roleId){
        this.roleId=roleId;
    }

    public Short getSex(){
        return sex;
    }
    public void setSex(Short sex){
        this.sex=sex;
    }

    public Timestamp getBirth(){
        return birth;
    }
    public void setBirth(Timestamp birth){
        this.birth=birth;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getHomepage(){
        return homepage;
    }
    public void setHomepage(String homepage){
        this.homepage=homepage;
    }

    public String getQq(){
        return qq;
    }
    public void setQq(String qq){
        this.qq=qq;
    }

    public String getBlood(){
        return blood;
    }
    public void setBlood(String blood){
        this.blood=blood;
    }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }

    public Integer getProvinceId(){
        return provinceId;
    }
    public void setProvinceId(Integer provinceId){
        this.provinceId=provinceId;
    }

    public Integer getCityId(){
        return cityId;
    }
    public void setCityId(Integer cityId){
        this.cityId=cityId;
    }

    public Integer getSchoolId(){
        return schoolId;
    }
    public void setSchoolId(Integer schoolId){
        this.schoolId=schoolId;
    }

    public Integer getCollegeId(){
        return collegeId;
    }
    public void setCollegeId(Integer collegeId){
        this.collegeId=collegeId;
    }

    public String getClass_(){
        return class_;
    }
    public void setClass_(String class_){
        this.class_=class_;
    }

    public String getPortrait(){
        return portrait;
    }
    public void setPortrait(String portrait){
        this.portrait=portrait;
    }

    public Timestamp getRegtime(){
        return regtime;
    }
    public void setRegtime(Timestamp regtime){
        this.regtime=regtime;
    }

    public Timestamp getLastTime(){
        return lastTime;
    }
    public void setLastTime(Timestamp lastTime){
        this.lastTime=lastTime;
    }

    public String getLastIp(){
        return lastIp;
    }
    public void setLastIp(String lastIp){
        this.lastIp=lastIp;
    }

    public Short getKeepDay(){
        return keepDay;
    }
    public void setKeepDay(Short keepDay){
        this.keepDay=keepDay;
    }

    public Short getOnlineStatus(){
        return onlineStatus;
    }
    public void setOnlineStatus(Short onlineStatus){
        this.onlineStatus=onlineStatus;
    }

    public Integer getRank(){
        return rank;
    }
    public void setRank(Integer rank){
        this.rank=rank;
    }

    public Integer getArticleCount(){
        return articleCount;
    }
    public void setArticleCount(Integer articleCount){
        this.articleCount=articleCount;
    }

    public Integer getArticleReplyCount(){
        return articleReplyCount;
    }
    public void setArticleReplyCount(Integer articleReplyCount){
        this.articleReplyCount=articleReplyCount;
    }

    public Integer getTopicCount(){
        return topicCount;
    }
    public void setTopicCount(Integer topicCount){
        this.topicCount=topicCount;
    }

    public Integer getTopicReplyCount(){
        return topicReplyCount;
    }
    public void setTopicReplyCount(Integer topicReplyCount){
        this.topicReplyCount=topicReplyCount;
    }

    public Integer getImageCount(){
        return imageCount;
    }
    public void setImageCount(Integer imageCount){
        this.imageCount=imageCount;
    }

    public Integer getImageReplyCount(){
        return imageReplyCount;
    }
    public void setImageReplyCount(Integer imageReplyCount){
        this.imageReplyCount=imageReplyCount;
    }

    public Integer getGuestbookCount(){
        return guestbookCount;
    }
    public void setGuestbookCount(Integer guestbookCount){
        this.guestbookCount=guestbookCount;
    }

    public Integer getTagCount(){
        return tagCount;
    }
    public void setTagCount(Integer tagCount){
        this.tagCount=tagCount;
    }

    public Integer getFans(){
        return fans;
    }
    public void setFans(Integer fans){
        this.fans=fans;
    }

    public Integer getFollows(){
        return follows;
    }
    public void setFollows(Integer follows){
        this.follows=follows;
    }

    public Integer getPopedom(){
        return popedom;
    }
    public void setPopedom(Integer popedom){
        this.popedom=popedom;
    }

    public Integer getCodeId(){
        return codeId;
    }
    public void setCodeId(Integer codeId){
        this.codeId=codeId;
    }


}