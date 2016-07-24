package com.cx.wxs.action.index;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;

/**
 * @author 陈义
 * @date   2016-6-24 下午11:23:47
 *首页的文章管理
 */
@Controller("IndexDiaryAction")
public class DiaryAction {

	@Resource
	private DDiaryService diaryService;
	@Resource
	private DCatalogService catalogService;
	
	public List<DDiaryDto> getDiary(){
		List<DDiaryDto> list=new ArrayList<DDiaryDto>();
		return list;
	}
}
