package com.cx.wxs.dao;

import java.util.List;

import com.cx.wxs.dto.UsignDto;
import com.cx.wxs.po.USign;
import com.cy.wxs.base.dao.IBaseDao;

/**
 * @author 陈义
 * @date   2015-12-3 下午2:22:05
 */
public interface UsignDao extends IBaseDao<USign,Integer>{
	
	/***
	 * 通过id获取用户签名
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:31:03
	 */
	public UsignDto getUsignByID(UsignDto usignDto);
	/***
	 * 通过用户的找到用户的最新的一条的签名信息
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-8下午9:58:38
	 */
	public UsignDto getUsignByUserid(UsignDto usignDto);
	/***
	 * 通过签名的其他的信息获取部分签名
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:31:29
	 */
	public List<UsignDto> getUsignList(UsignDto usignDto);
	
	/***
	 * 添加签名
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午9:07:11
	 */
	public Integer addSign(UsignDto usignDto);
	/***
	 * 更新签名档
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:32:00
	 */
	public Integer updateSign(UsignDto usignDto);
	/***
	 * 删除签名档
	 * @param usignDto
	 * @return
	 * @author 陈义
	 * @date   2015-12-7下午5:32:17
	 */
	public Integer deleteSign(UsignDto usignDto);

}
