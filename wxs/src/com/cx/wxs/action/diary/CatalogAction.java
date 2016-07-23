package com.cx.wxs.action.diary;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.wxs.action.BaseDiaryAction;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.DUpvoteDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.DCatalogService;
import com.cx.wxs.service.DDiaryService;

/**
 * @author 陈义
 * @date   2016-5-12 下午11:14:55
 */
@Controller
@RequestMapping("/{vip}/article")
public class CatalogAction extends BaseDiaryAction{

	@Resource
	private DCatalogService catalogService;
	@Resource
	private DDiaryService diaryService;
	/**
	 * 获取文章分类目录
	 * @param vip
	 * @param request
	 * @param reqResponse
	 * @param diaryDto
	 * @return
	 * @author 陈义
	 * @date   2016-5-12下午11:17:39
	 */
	@RequestMapping("/catalog")
	@ResponseBody
	public List<DCatalogDto> getCatalogs(@PathVariable("vip") String vip,
			HttpServletRequest request,HttpServletResponse reqResponse,DDiaryDto diaryDto){
		UUserDto userDto=this.getUserDtoByNickname(vip);
	    DCatalogDto catalogDto=new DCatalogDto();
	    catalogDto.setUUserDto(userDto);
	    List<DCatalogDto> list=catalogService.getDCatalogList(catalogDto);
		return list;
	}
	

	
	
	
}
