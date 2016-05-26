package com.cx.wxs.action.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.service.DDiaryService;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

/**
 * @author 陈义
 * @date   2016-5-25 下午10:23:16
 */
@Controller
public class SearchAction {
	
	@Resource
	private DDiaryService diaryService;
	/***
	 * 搜索，并进入搜索界面
	 * @param request
	 * @param response
	 * @return
	 * @author 陈义
	 * @date   2016-5-25下午10:27:36
	 */
	@RequestMapping(value="/search")
	public ModelAndView search(HttpServletRequest request,HttpServletResponse response,String content){
		ModelAndView mv = new ModelAndView("search/main");
		Map<Object, Object> result=this.getSearchResult(request, response, content);
		mv.addObject("result", result);
		return mv;
	}
	@RequestMapping(value="/search/result")
	@ResponseBody
	public Map<Object,Object> getSearchResult(HttpServletRequest request,HttpServletResponse response,String content){
		Map<Object, Object> result=new HashMap<Object, Object>();
		DDiaryDto diaryDto = new DDiaryDto();
		diaryDto.setContent(content);
		diaryDto.setTitle(content);
		if(request.getParameter("page")!=null){
			diaryDto.setPage(Integer.parseInt(request.getParameter("page")));
		}else{
			diaryDto.setPage(1);
		}
		diaryDto.setRows(10);
		List<DDiaryDto>  listDiaryDto = diaryService.getDiaryByLikeTitleOrContent(diaryDto);
		result.put("diarys",listDiaryDto);
		return result;
	}


}
