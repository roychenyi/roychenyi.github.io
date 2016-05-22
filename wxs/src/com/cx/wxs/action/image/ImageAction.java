package com.cx.wxs.action.image;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cx.wxs.dto.BSiteDto;
import com.cx.wxs.dto.IAlbumDto;
import com.cx.wxs.dto.IImageDto;
import com.cx.wxs.dto.UUserDto;
import com.cx.wxs.service.BSiteService;
import com.cx.wxs.service.IImageService;
import com.cx.wxs.service.UUserService;
import com.cx.wxs.utils.DateUtils;
import com.cx.wxs.utils.ClientInfo;
import com.cx.wxs.utils.imageUtils;

/**
 * @author 陈义
 * @date   2016-3-29 下午9:22:01
 */
@Controller
@RequestMapping("/{vip}/image")
public class ImageAction {

	@Resource
	private IImageService iImageService;
	@Resource
	private BSiteService bSiteService;
	@Resource
	private UUserService uUserService;

	
	@RequestMapping(value="/updateuserlogo")
	@ResponseBody
	public IImageDto updateUserLogo( @PathVariable("vip") String vip,
			 Integer x, Integer y, Integer width, Integer height,Integer degree,
			@RequestParam(value = "avatar_file") MultipartFile imageFile, HttpServletRequest request,HttpServletResponse response){
		IImageDto imageDto=new IImageDto();
		//获取服务器的实际路径
 		String realPath = request.getSession().getServletContext().getRealPath("/");
	    System.out.println("x:"+x+"y:"+y+"width:"+width+"height:"+height+"degree:"+degree);
		System.out.println(realPath);
		//需要上传的路径，我的路径根据用户的和当前日期划分路径
		String resourcePath="upload/image";
		UUserDto userDto=(UUserDto) request.getSession().getAttribute("user");
		resourcePath+="/"+userDto.getUserId();
	
		 if(imageFile!=null){
			 try{
			//文件名
			 String name= imageFile.getOriginalFilename();
			 //获取时间的路径
			 Date date=new Date();			 
			 int year=DateUtils.getYear(date);
			 int month=DateUtils.getMonth(date);
			 int day=DateUtils.getDay(date);
			 resourcePath+="/"+year+"/"+month+"/"+day+"/";
			 File dir=new File(realPath+resourcePath);
			 if(!dir.exists()){
				 dir.mkdirs();
			 }
			 //先把用户上传到原图保存到服务器上
			 File file=new File(dir,date.getTime()+".jpg");
			 imageFile.transferTo(file);
			 if(file.exists()){
				 String src=realPath+resourcePath+date.getTime();
				 
				 boolean[] flag=new boolean[6];
				 //旋转后剪裁图片
				 imageUtils.convert(src+".jpg", "jpg", src+".jpg");
				 flag[0]=imageUtils.cutAndRotateImage(src+".jpg", src+"_s.jpg", x, y, width, height, degree);
				 //缩放图片,生成不同大小的图片，应用于不同的大小的头像显示
				 flag[1]= imageUtils.scale2(src+"_s.jpg", src+"_s_200.jpg", 200, 200, true);
				 flag[2]= imageUtils.scale2(src+"_s.jpg", src+"_s_100.jpg", 100, 100, true);
				 flag[3]= imageUtils.scale2(src+"_s.jpg", src+"_s_50.jpg", 50, 50, true);
				 flag[4]= imageUtils.scale2(src+"_s.jpg", src+"_s_30.jpg", 30, 30, true);
				 //生成预览图
				 flag[5]= imageUtils.scale2(src+".jpg", src+"_200.jpg", 200, 200, true);
				 
				 if(flag[0]&&flag[1]&&flag[2]&&flag[3]&&flag[4]&&flag[5]){
					 //图像处理完成，将数据写入数据库中
					 imageDto.setYear((short) year);
					 imageDto.setMount((short)month);
					 imageDto.setDay((short)day);
					 imageDto.setUUserDto(userDto);
					 imageDto.setName(date.getTime()+".jpg");
					 imageDto.setFileName(name);
					 imageDto.setUrl(resourcePath+date.getTime()+".jpg");
					 imageDto.setPreviewUrl(resourcePath+date.getTime()+"_200.jpg");
					 imageDto.setTime(new Timestamp(date.getTime()));
					 imageDto.setWidth((short)imageUtils.getImageWidth(file.getPath()));
					 imageDto.setHeight((short)imageUtils.getImageHeight(file.getPath()));
					 imageDto.setClientIp(ClientInfo.getIpAddr(request));
					 imageDto.setClientAgent(ClientInfo.getAgent(request));
					 imageDto.setClientType((short)(ClientInfo.isMoblie(request)?1:0));
					 imageDto.setStatus((short)1);
					 imageDto.setExt3(resourcePath+date.getTime()+"_s_200.jpg");
					 imageDto.setExt4(resourcePath+date.getTime()+"_s_100.jpg");
					//设置相册，头像设置进入默认相册
					 IAlbumDto albumDto=new IAlbumDto();
					 albumDto.setAlbumId(1);
					 imageDto.setIAlbumDto(albumDto);
					 int id= iImageService.addIImage(imageDto);
					 if(id>0){
						 BSiteDto siteDto=userDto.getBSiteDto();
						 siteDto.setLogo(resourcePath+date.getTime()+"_s_100.jpg");
						 siteDto.setLastTime( new Timestamp(date.getTime()));
						 bSiteService.updateBSite(siteDto);
						 userDto.setPortrait(resourcePath+date.getTime()+"_s_50.jpg");
						 uUserService.updateUuser(userDto);
						 userDto=uUserService.getUuser(userDto);
						 imageDto.setStatusFlag("1");
						 return imageDto;
					 }
				 }
			 }
			 }catch (Exception e) {
				// TODO: handle exception
				 e.printStackTrace();
				 return imageDto;
			}

		 }
		return imageDto;
	}
}
