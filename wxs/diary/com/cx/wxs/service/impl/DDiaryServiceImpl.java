package com.cx.wxs.service.impl;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.security.krb5.internal.PAEncTSEnc;

import com.cx.wxs.dao.DAccessDao;
import com.cx.wxs.dao.DDiaryDao;
import com.cx.wxs.dao.DFavoriteDao;
import com.cx.wxs.dao.DReply1Dao;
import com.cx.wxs.dao.DUpvoteDao;
import com.cx.wxs.dto.DAccessDto;
import com.cx.wxs.dto.DCatalogDto;
import com.cx.wxs.dto.DDiaryDto;
import com.cx.wxs.dto.DFavoriteDto;
import com.cx.wxs.dto.DReply1Dto;
import com.cx.wxs.dto.DUpvoteDto;
import com.cx.wxs.service.DDiaryService;
import com.cx.wxs.utils.HTMLutil;
import com.cx.wxs.utils.StringUtils;

/**
 * @author 陈义
 * @date 2016-01-19 14:41:49
 */

@Service("DDiaryService")
public class DDiaryServiceImpl implements DDiaryService {
	@Autowired
	private DDiaryDao dDiaryDao;
	/*    @Autowired
    private DFavoriteDao dFavoriteDao;
    @Autowired
    private DUpvoteDao dUpvoteDao;
    @Autowired
    private DReply1Dao dReply1Dao;*/

	public void setDDiaryDao(DDiaryDao dDiaryDao){
		this.dDiaryDao=dDiaryDao;
	}

	/**
	 * 通过用户id获取DDiaryDto信息
	 * @author 陈义
	 * @date 2016-01-19 14:41:49
	 */
	@Override
	public DDiaryDto getDDiaryByID(DDiaryDto dDiaryDto){
		//	int count=dDiaryDao.getDiaryCount(dDiaryDto);  //查询日志总数量
		DDiaryDto dDiaryDto2  =dDiaryDao.getDiaryRow(dDiaryDto);      //查询所在行
		int count=dDiaryDto2.getRows();
		int row=dDiaryDto2.getRow();
		int pageCount=count/10;
		int page =row/10;
		dDiaryDto= dDiaryDao.getDDiaryByID(dDiaryDto);
		dDiaryDto.setPageCount((count%10>0)?pageCount+1:pageCount);
		dDiaryDto.setPage((row%10 > 0)?page+1:page);
		dDiaryDto.setRows(count);
		dDiaryDto.setRow(row);
		//   	this.setDiaryCountInfo(dDiaryDto);
		return dDiaryDto;
	}

	/**
	 * 通过相关信息获取DDiaryDtoList信息
	 * @author 陈义
	 * @date 2016-01-19 14:41:49
	 */
	@Override
	public List<DDiaryDto> getDDiaryList(DDiaryDto dDiaryDto){
		List<DDiaryDto> list=dDiaryDao.getDDiaryList(dDiaryDto);
		if(list!=null&&list.size()>0){
			Integer count=dDiaryDao.getDiaryCount(dDiaryDto);
			int pageCount=0;
			if(dDiaryDto.getRows()!=null){
				pageCount=count/dDiaryDto.getRows()+count%dDiaryDto.getRows()>0?1:0;
				//	pageCount+=count%dDiaryDto.getRows()>0?1:0;
			}else{
				pageCount=count/10+(count%10>0?1:0);
				//	pageCount+=(count%10>0)?1:0;
			}
			list.get(0).setRows(count);
			list.get(0).setPageCount(pageCount);
		}

		return this.getPreview(list);
	}

	/**
	 * 添加一个新的DDiary到数据库
	 * @author 陈义
	 * @date 2016-01-19 14:41:49
	 */
	@Override
	public Integer addDDiary(DDiaryDto dDiaryDto){
		return dDiaryDao.addDDiary(dDiaryDto);
	}

	/**
	 * 更新DDiary
	 * @author 陈义
	 * @date 2016-01-19 14:41:49
	 */
	@Override
	public Integer updateDDiary(DDiaryDto dDiaryDto){
		return dDiaryDao.updateDDiary(dDiaryDto);
	}

	/**
	 * 删除DDiary
	 * @author 陈义
	 * @date 2016-01-19 14:41:49
	 */
	@Override
	public Integer deleteDDiary(DDiaryDto dDiaryDto){
		return dDiaryDao.deleteDDiary(dDiaryDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.DDiaryService#diaryMove1(com.cx.wxs.dto.DCatalogDto)
	 */
	@Override
	public Integer diaryMove(DDiaryDto diaryDto,DCatalogDto catalogDto) {
		// TODO Auto-generated method stub
		return dDiaryDao.diaryMove(diaryDto,catalogDto);
	}

	/* (non-Javadoc)
	 * @see com.cx.wxs.service.DDiaryService#getCurrentDiarys(com.cx.wxs.dto.DDiaryDto)
	 * 获取最近的文章依旧上下两篇文章，并且更新文章数据
	 */
	@Override
	public List<DDiaryDto> getCurrentDiarys(DDiaryDto diaryDto) {
		// TODO Auto-generated method stub
		//收藏数量
		/*		DDiaryDto dDiaryDto=new DDiaryDto();
		dDiaryDto.setDiaryId(diaryDto.getDiaryId());
		DFavoriteDto dFavoriteDto=new DFavoriteDto();
		dFavoriteDto.setDDiaryDto(dDiaryDto);
		int favoriteCount =dFavoriteDao.getDFavoriteCount(dFavoriteDto);
		dDiaryDto.setFavoriteCount(favoriteCount);
		//点赞数量
		DUpvoteDto dUpvoteDto=new DUpvoteDto();
		dUpvoteDto.setDDiaryDto(dDiaryDto);
		int upvoteCount=dUpvoteDao.getDUpvoteCount(dUpvoteDto);
		dDiaryDto.setUpvoteCount(upvoteCount);
		//评论数量
		DReply1Dto dReply1Dto=new DReply1Dto();
		dReply1Dto.setDDiaryDto(dDiaryDto);
		int replyCount=dReply1Dao.getDReply1Count(dReply1Dto);
		dDiaryDto.setReplyCount(replyCount);
		//更新统计数据
		dDiaryDao.updateDDiary(dDiaryDto);*/
		//查询日志所在行和页数
		DDiaryDto dDiaryDto2  =dDiaryDao.getDiaryRow(diaryDto);      //查询所在行
		int count=dDiaryDto2.getRows();
		int row=dDiaryDto2.getRow();
		int pageCount=count/10;
		int page =row/10;    	
		List<DDiaryDto> list=dDiaryDao.getCurrentDiarys(diaryDto);
		if(list!=null){
			if(list.get(0)!=null){
				list.get(0).setPageCount((count%10>0)?pageCount+1:pageCount);
				list.get(0).setPage((row%10 > 0)?page+1:page);
				list.get(0).setRows(count);
				list.get(0).setRow(row);
			}
			if(list.get(1)!=null){
				list.get(1).setTitle(StringUtils.compressLength(list.get(1).getTitle(), 12));
			}
			if(list.get(2)!=null){
				list.get(2).setTitle(StringUtils.compressLength(list.get(2).getTitle(), 12));
			}
		}
		return list;
	}
	@Override
	public DDiaryDto getPageInfo(DDiaryDto diaryDto){
		int count=dDiaryDao.getDiaryCount(diaryDto);
		int pageCount=0;
		if(count%diaryDto.getRows()>0){
			pageCount=(count/diaryDto.getRows())+1;
		}else{
			pageCount=count/diaryDto.getRows();
		}
		diaryDto.setPageCount(pageCount);
		return diaryDto;
	}

	@Override
	public List<DDiaryDto> getDDiaryPreList(DDiaryDto diaryDto) {
		List<DDiaryDto> list = dDiaryDao.getDDiaryList(diaryDto);
		return getPreview(list);
	}
	@Override
	public List<DDiaryDto> getDiaryByLikeTitleOrContent(DDiaryDto diaryDto) {
		Integer pageCount = getPageByLike(diaryDto);
		List<DDiaryDto> list = dDiaryDao.getDiaryByLikeTitleOrContent(diaryDto);
		return getPreview(list);
	}
	/**
	 * 获取内容摘要的工具类
	 * @param source
	 * @param num
	 * @return
	 */
	public List<DDiaryDto> getPreview(List<DDiaryDto> source){
		/*List<DDiaryDto> result = null;
		if(source != null&&!source.isEmpty()){
			result = new ArrayList<DDiaryDto>();
			for(DDiaryDto dDiaryDto :source){
				dDiaryDto.setContent(HTMLutil.preview(dDiaryDto.getContent(), 300));//num个字符包括html字符
				dDiaryDto.setPage(page);
				dDiaryDto.setPageCount(pageCount);
				result.add(dDiaryDto);
			}
		}*/
		if(source!=null){
			for(int i=0;i<source.size();i++){
				source.get(i).setContent(HTMLutil.preview(source.get(i).getContent(), 300));
			}
			return source;
		}else{
			return null;
		}
	}

	@Override
	public Integer getPageByLike(DDiaryDto diaryDto) {
		int count=dDiaryDao.getLikeCount(diaryDto);
		int pageCount=0;
		if(count%diaryDto.getRows()>0){
			pageCount=(count/diaryDto.getRows())+1;
		}else{
			pageCount=count/diaryDto.getRows();
		}
		return pageCount;
	}
	/***
	 * 设置diaryDto的相关计数信息，page,row,rows,pageCount
	 * 
	 * @author 陈义
	 * @date   2016-5-11下午3:58:32
	 */
	@Override
	public void setDiaryCountInfo(DDiaryDto dDiaryDto){
		DDiaryDto dDiaryDto2  =dDiaryDao.getDiaryRow(dDiaryDto);      //查询所在行
		int count=dDiaryDto2.getRows();
		int row=dDiaryDto2.getRow();
		int pageCount=count/10;
		int page =row/10;
		dDiaryDto.setPageCount((count%10>0)?pageCount+1:pageCount);
		dDiaryDto.setPage((row%10 > 0)?page+1:page);
		dDiaryDto.setRows(count);
		dDiaryDto.setRow(row);
	}
	//获取文章数量
	@Override
	public Integer getDiaryCount(DDiaryDto dDiaryDto){
		return dDiaryDao.getDiaryCount(dDiaryDto);
	}
}
