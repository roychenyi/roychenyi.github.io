package com.cx.wxs.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.wxs.dao.DCatalogDao;
import com.cx.wxs.dao.DDiaryDao;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.service.DCatalogService;

/**
 * @author 陈义
 * @date 2016-01-19 14:41:49
 */

@Service("DCatalogService")
public class DCatalogServiceImpl implements DCatalogService {
    @Autowired
    private DCatalogDao dCatalogDao;
    @Autowired
    private DDiaryDao dDiaryDao;

    public void setDCatalogDao(DCatalogDao dCatalogDao){
        this.dCatalogDao=dCatalogDao;
    }

    /**
    * 通过用户id获取DCatalogDto信息
    * @author 陈义
    * @date 2016-01-19 14:41:49
    */
    @Override
    public DCatalogDto getDCatalogByID(DCatalogDto dCatalogDto){
        return dCatalogDao.getDCatalogByID(dCatalogDto);
    }
    /**
     * 通过分类名字获取分类
     */
    @Override
    public DCatalogDto getDCatalog(DCatalogDto dCatalogDto){
    	return dCatalogDao.getDCatalog(dCatalogDto);
    }

    /**
    * 通过相关信息获取DCatalogDtoList信息
    * @author 陈义
    * @date 2016-01-19 14:41:49
    */
    @Override
    public List<DCatalogDto> getDCatalogList(DCatalogDto dCatalogDto){
    	List<DCatalogDto> list=dCatalogDao.getDCatalogList(dCatalogDto);
    	for(DCatalogDto catalogDto:list){
    		DDiaryDto diaryDto=new DDiaryDto();
    		diaryDto.setDCatalogDto(catalogDto);
    		diaryDto.setUUserDto(catalogDto.getUUserDto());
    		int count=dDiaryDao.getDiaryCount(diaryDto);
    		catalogDto.setArticleCount(count);
    		dCatalogDao.updateDCatalog(catalogDto);
    	}
    	return dCatalogDao.getDCatalogList(dCatalogDto);
    }

    /**
    * 添加一个新的DCatalog到数据库
    * @author 陈义
    * @date 2016-01-19 14:41:49
    */
    @Override
    public Integer addDCatalog(DCatalogDto dCatalogDto){
        return dCatalogDao.addDCatalog(dCatalogDto);
    }

    /**
    * 更新DCatalog
    * @author 陈义
    * @date 2016-01-19 14:41:49
    */
    @Override
    public Integer updateDCatalog(DCatalogDto dCatalogDto){
        return dCatalogDao.updateDCatalog(dCatalogDto);
    }

    /**
    * 删除DCatalog
    * @author 陈义
    * @date 2016-01-19 14:41:49
    */
    @Override
    public Integer deleteDCatalog(DCatalogDto dCatalogDto){
        return dCatalogDao.deleteDCatalog(dCatalogDto);
    }

}