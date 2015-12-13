package com.cx.wxs.dto;

import com.cx.wxs.base.BeanBase;
import java.sql.Timestamp;

import com.cx.wxs.dto.IImageDto;
import com.cx.wxs.dto.UUserDto;
/**
 * @author 陈义
 * @date 2015-12-09 22:36:38
 */
public class IAccessDto extends BeanBase{
    private Long iaccessId;
    private IImageDto IImageDto;
    private UUserDto UUserDto;
    private String clientAgent;
    private String clientIp;
    private Short clientType;
    private Timestamp time;

    /** default constructor */
    public IAccessDto(){

    }

    /** full constructor */
    public IAccessDto(Long iaccessId,IImageDto IImageDto,UUserDto UUserDto,String clientAgent,String clientIp,Short clientType,Timestamp time){super();
        this.iaccessId=iaccessId;
        this.IImageDto=IImageDto;
        this.UUserDto=UUserDto;
        this.clientAgent=clientAgent;
        this.clientIp=clientIp;
        this.clientType=clientType;
        this.time=time;
    }
    public Long getIaccessId(){
        return iaccessId;
    }
    public void setIaccessId(Long iaccessId){
        this.iaccessId=iaccessId;
    }

    public IImageDto getIImageDto(){
        return IImageDto;
    }
    public void setIImageDto(IImageDto IImageDto){
        this.IImageDto=IImageDto;
    }

    public UUserDto getUUserDto(){
        return UUserDto;
    }
    public void setUUserDto(UUserDto UUserDto){
        this.UUserDto=UUserDto;
    }

    public String getClientAgent(){
        return clientAgent;
    }
    public void setClientAgent(String clientAgent){
        this.clientAgent=clientAgent;
    }

    public String getClientIp(){
        return clientIp;
    }
    public void setClientIp(String clientIp){
        this.clientIp=clientIp;
    }

    public Short getClientType(){
        return clientType;
    }
    public void setClientType(Short clientType){
        this.clientType=clientType;
    }

    public Timestamp getTime(){
        return time;
    }
    public void setTime(Timestamp time){
        this.time=time;
    }


}