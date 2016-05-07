package com.cx.wxs.dao.impl;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.cx.wxs.base.dao.BaseDaoImpl;
import com.cx.wxs.dao.DAccessDao;
import com.cx.wxs.dto.DAccessDto;
import com.cx.wxs.enums.DbType;
import com.cx.wxs.po.DAccess;
import com.cx.wxs.utils.StringUtils;

import com.cx.wxs.utils.BeanToDto;

/**
 * @author 陈义
 * @date 2016-04-26 21:51:42
 */
@Repository("DAccessDao")
public class DAccessDaoImpl extends BaseDaoImpl<DAccess, Integer> implements DAccessDao{

    private BeanToDto<DAccess, DAccessDto> beanToDto=new BeanToDto<DAccess, DAccessDto>();

    public BeanToDto<DAccess, DAccessDto> getBeanToDto(){
        return beanToDto;
    }

    public void setBeanToDto(BeanToDto<DAccess, DAccessDto> beanToDto) {
        this.beanToDto = beanToDto;
    }
    /**
    * 通过id获取DAccessDto
    * @author 陈义
    * @date 2016-04-26 21:51:43
    */
    @Override
    public DAccessDto getDAccessByID(DAccessDto dAccessDto){
        // TODO Auto-generated method stub
        if(dAccessDto!=null&&dAccessDto.getDaccessId()!=null){
           StringBuffer stringBuffer=new StringBuffer();
           Map<String,Object> params=new HashMap<String, Object>();
           stringBuffer.append("from  "+DAccess.class.getName()+"  a where a.daccessId=:id");
           params.put("id",dAccessDto.getDaccessId());
           List<DAccess> list=this.find(stringBuffer.toString(), params);
           if(list!=null&&list.size()>0){
            DAccess t1= list.get(0);
            DAccessDto    dto=new    DAccessDto();
            dto=beanToDto.T1ToD1(t1,dto);
            return dto;
            }
        }
        return null;
    }

    /**
    * 通过相关数据获取DAccessDtoList
    * @author 陈义
    * @date 2016-04-26 21:51:43
    */
    @Override
    public List<DAccessDto> getDAccessList(DAccessDto dAccessDto){
        return null;
    }

    /**
    * 添加一个新的DAccess到数据库
    * @author 陈义
    * @date 2016-04-26 21:51:43
    */
    @Override
    public Integer addDAccess(DAccessDto dAccessDto){
         // TODO Auto-generated method stub
        if(dAccessDto!=null){
           DAccess dAccess= new DAccess();
           dAccess=beanToDto.D1ToT1(dAccess, dAccessDto);
           return this.save(dAccess);
        }
        return 0;
    }

    /**
    * 更新DAccess
    * @author 陈义
    * @date 2016-04-26 21:51:43
    */
    @Override
    public Integer updateDAccess(DAccessDto dAccessDto){
        // TODO Auto-generated method stub
        if(dAccessDto!=null&&dAccessDto.getDaccessId()!=null){
           StringBuffer stringBuffer =new StringBuffer(DbType.UPDATE.toString());
           String[] fl = new String[]{"uid"};//过滤掉的字段
           Map<String, Object> map = dAccessDto.createSetPropertiesVal(dAccessDto, "a", fl);
           Map<String, Object> params = (Map<String, Object>) map.get(StringUtils.PARAMS);
           stringBuffer.append(" from "+DAccess.class.getName()+" a");
           stringBuffer.append(map.get(StringUtils.SET_HQL));
           stringBuffer.append(" where a.daccessId=:uid");
           params.put("uid",dAccessDto.getDaccessId());
           return this.executeHql(stringBuffer.toString(),params);
        }
        return 0;
    }

    /**
    * 删除DAccess
    * @author 陈义
    * @date 2016-04-26 21:51:43
    */
    @Override
    public Integer deleteDAccess(DAccessDto dAccessDto){
         // TODO Auto-generated method stub
        if(dAccessDto!=null&&dAccessDto.getDaccessId()!=null){
           StringBuffer stringBuffer=new StringBuffer(DbType.DELETE.toString());
           Map<String,Object> params=new HashMap<String,Object>();
           stringBuffer.append("  "+DAccess.class.getName()+" a");
           stringBuffer.append(" where a.daccessId=:uid ");
           params.put("uid",dAccessDto.getDaccessId());
           return this.executeHql(stringBuffer.toString(),params);
        }
        return 0;
    }

	/* (non-Javadoc)
	 * @see com.cx.wxs.dao.DAccessDao#getDAccessCount(com.cx.wxs.dto.DAccessDto)
	 */
	@Override
	public Integer getDAccessCount(DAccessDto dAccessDto) {
		// TODO Auto-generated method stub
		if(dAccessDto!=null&&dAccessDto.getDDiaryDto()!=null){
			StringBuffer stringBuffer=new StringBuffer(DbType.SELECT+" count(*) ");
 			Map<String,Object> params=new HashMap<String, Object>();
 			stringBuffer.append(" from "+DAccess.class.getName()+" a where 1=1");
 			stringBuffer.append(" and a.DDiary.diaryId=:diaryId");
			params.put("diaryId", dAccessDto.getDDiaryDto().getDiaryId());
            stringBuffer.append(" and a.status=1");
			return this.count(stringBuffer.toString(), params); 			
		}
		return 0;
	}

}