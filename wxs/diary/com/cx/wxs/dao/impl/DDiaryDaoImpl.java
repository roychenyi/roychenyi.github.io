package com.cx.wxs.dao.impl;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.cx.wxs.base.dao.BaseDaoImpl;
import com.cx.wxs.dao.DDiaryDao;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.enums.DbType;
import com.cx.wxs.po.DDiary;
import com.cx.wxs.utils.StringUtils;

import com.cx.wxs.utils.BeanToDto;

/**
 * @author 陈义
 * @date 2016-04-09 16:11:18
 */
@Repository("DDiaryDao")
public class DDiaryDaoImpl extends BaseDaoImpl<DDiary, Integer> implements DDiaryDao{

    private BeanToDto<DDiary, DDiaryDto> beanToDto=new BeanToDto<DDiary, DDiaryDto>();

    public BeanToDto<DDiary, DDiaryDto> getBeanToDto(){
        return beanToDto;
    }

    public void setBeanToDto(BeanToDto<DDiary, DDiaryDto> beanToDto) {
        this.beanToDto = beanToDto;
    }
    /**
    * 通过id获取DDiaryDto
    * @author 陈义
    * @date 2016-04-09 16:11:18
    */
    @Override
    public DDiaryDto getDDiaryByID(DDiaryDto dDiaryDto){
        // TODO Auto-generated method stub
        if(dDiaryDto!=null&&dDiaryDto.getDiaryId()!=null){
           StringBuffer stringBuffer=new StringBuffer();
           Map<String,Object> params=new HashMap<String, Object>();
           stringBuffer.append("from  "+DDiary.class.getName()+"  a where a.diaryId=:id");
           params.put("id",dDiaryDto.getDiaryId());
           List<DDiary> list=this.find(stringBuffer.toString(), params);
           if(list!=null&&list.size()>0){
            DDiary t1= list.get(0);
            DDiaryDto    dto=new    DDiaryDto();
            dto=beanToDto.T1ToD1(t1,dto);
            return dto;
            }
        }
        return null;
    }

    /**
    * 通过相关数据获取DDiaryDtoList
    * @author 陈义
    * @date 2016-04-09 16:11:18
    */
    @Override
    public List<DDiaryDto> getDDiaryList(DDiaryDto dDiaryDto){
        return null;
    }

    /**
    * 添加一个新的DDiary到数据库
    * @author 陈义
    * @date 2016-04-09 16:11:18
    */
    @Override
    public Integer addDDiary(DDiaryDto dDiaryDto){
         // TODO Auto-generated method stub
        if(dDiaryDto!=null){
           DDiary dDiary= new DDiary();
           dDiary=beanToDto.D1ToT1(dDiary, dDiaryDto);
           return this.save(dDiary);
        }
        return 0;
    }

    /**
    * 更新DDiary
    * @author 陈义
    * @date 2016-04-09 16:11:18
    */
    @Override
    public Integer updateDDiary(DDiaryDto dDiaryDto){
        // TODO Auto-generated method stub
        if(dDiaryDto!=null&&dDiaryDto.getDiaryId()!=null){
           StringBuffer stringBuffer =new StringBuffer(DbType.UPDATE.toString());
           String[] fl = new String[]{"uid"};//过滤掉的字段
           Map<String, Object> map = dDiaryDto.createSetPropertiesVal(dDiaryDto, "a", fl);
           Map<String, Object> params = (Map<String, Object>) map.get(StringUtils.PARAMS);
           stringBuffer.append(" form "+DDiary.class.getName()+" a");
           stringBuffer.append(map.get(StringUtils.SET_HQL));
           stringBuffer.append(" where a.diaryId=:uid");
           params.put("uid",dDiaryDto.getDiaryId());
           return this.executeHql(stringBuffer.toString(),params);
        }
        return 0;
    }

    /**
    * 删除DDiary
    * @author 陈义
    * @date 2016-04-09 16:11:18
    */
    @Override
    public Integer deleteDDiary(DDiaryDto dDiaryDto){
         // TODO Auto-generated method stub
        if(dDiaryDto!=null&&dDiaryDto.getDiaryId()!=null){
           StringBuffer stringBuffer=new StringBuffer(DbType.DELETE.toString());
           Map<String,Object> params=new HashMap<String,Object>();
           stringBuffer.append(" from "+DDiary.class.getName()+" a");
           stringBuffer.append(" where a.diaryId=:uid ");
           params.put("uid",dDiaryDto.getDiaryId());
           DDiary dDiary= new DDiary();
           BeanUtils.copyProperties(dDiaryDto, dDiary);
           return this.executeHql(stringBuffer.toString(),params);
        }
        return 0;
    }

}