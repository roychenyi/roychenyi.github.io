package com.cx.wxs.action.club;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;

/**
 * @author 陈义
 * @date   2016-6-24 下午11:24:31
 * 社团的文章展示
 */
@RequestMapping("{vip}")
@Controller
public class DiaryAction {

	@Resource
	private DDiaryService diaryService;
	@Resource
	private DCatalogService catalogService;
	
	
	public List<DDiaryDto> getDiary(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse response,DDiaryDto diaryDto){
		 List<DDiaryDto> list=new ArrayList<DDiaryDto>();
		 return list;
	}
}
