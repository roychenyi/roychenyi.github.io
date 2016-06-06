package com.cx.wxs.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;





/**
 * @author 陈义
 * @date   2015-12-10 下午4:35:27
 */
public class BeanToDto<T1 extends Serializable,D1 extends Serializable> {

	

	/***
	 * 获取最后类名
	 * @param str
	 * @return
	 * @author 陈义
	 * @date   2015-12-10下午5:03:36
	 */
	public  String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }
	
	private  String getFieldValue(Object owner, String fieldName)
    {
        return invokeMethodGET(owner, fieldName,null).toString();
    }
	/**
     * 
     * 执行某个Field的getField方法
     * 
     * @param owner 类
     * @param fieldName 类的属性名称
     * @param args 参数，默认为null
     * @return 
     */
    Object invokeMethodGET(Object owner, String fieldName, Object[] args)
    {
        Class<? extends Object> ownerClass = owner.getClass();
        
        //fieldName -> FieldName  
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        
        Method method = null;  
        try 
        {
            method = ownerClass.getMethod("get" + methodName);
       //     System.out.println(method.getName());
        } 
        catch (SecurityException e) 
        {

            //e.printStackTrace();
        } 
        catch (NoSuchMethodException e) 
        {
            // e.printStackTrace();
            return "";
        }
        
        //invoke getMethod
        try
        {
            return method.invoke(owner);
        } 
        catch (Exception e)
        {
            return "";
        }
    }
	/***
	 * 获取set方法
	 * @param owner
	 * @param fieldName
	 * @param args
	 * @param type
	 * @return
	 * @author 陈义
	 * @date   2015-12-11上午11:15:12
	 */
    public Object invokeMethodSET(Object owner, String fieldName, Object[] args,Class<?> type){
        Class ownerClass = owner.getClass();
        
        //fieldName -> FieldName  
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        
        Method method = null;  
        try 
        {
            method = ownerClass.getMethod("set" + methodName, new Class[] { type });
   //         System.out.println(method);
        } 
        catch (SecurityException e) 
        {

            e.printStackTrace();
        } 
        catch (NoSuchMethodException e) 
        {
             e.printStackTrace();
            return "";
        }
        
        //invoke getMethod
        try
        {
            return method.invoke(owner,args);
        } 
        catch (Exception e)
        {
            return "";
        }
    }
    /***
     * 把T转换成D，即把bean转换成Dto
     * @param t1
     * @param d1
     * @return
     * @author 陈义
     * @date   2015-12-11上午11:15:33
     */
	public  D1 T1ToD1(T1 t1,D1 d1) {
	//	Class<?> d1=(Class)c;
	//	System.out.println("……………………ToDto…………………………");
     try{	
		Field[] fields=t1.getClass().getDeclaredFields();
		BeanUtils.copyProperties(t1, d1);
		for(Field field:fields){
		//	System.out.println(field.getGenericType().toString());
			if(field.getGenericType().toString().contains("class com.cx.wxs.")){
				Object o1= invokeMethodGET(t1,field.getName(),null);				
				if(o1!=null){
				//	System.out.println(field.getGenericType().toString()+"::"+field.getName());
					String fieldName=field.getName();
					Class<?> fieldType=field.getType();
					if(field.getGenericType().toString().contains(".dto.")){
						String first=field.getName().substring(0,1).toUpperCase();
						fieldName=first+field.getName().substring(1,(field.getName().length()-3));
						fieldType=Class.forName("com.cx.wxs.po."+fieldName);
					}else{
						String first=field.getName().substring(0,1).toUpperCase();
						fieldName=first+field.getName().substring(1,field.getName().length());
						fieldName+="Dto";
						if(field.getGenericType().toString().contains("UUser")){
							fieldType=Class.forName("com.cx.wxs.dto.UUserDto");	
						}else{
						fieldType=Class.forName("com.cx.wxs.dto."+fieldName);
						}
					}
					System.out.println(fieldType+"::"+fieldName);
					Object o2=null;
					try {
						 o2=fieldType.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BeanUtils.copyProperties(o1, o2);
					Object[] args=new Object[]{o2};
					invokeMethodSET(d1, fieldName, args, fieldType);
					}
				}
		}
		return d1;
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return d1;
	}
	
	}
	/***
	 * 把D转换成T，把Dto转换成bean
	 * @param t1
	 * @param d1
	 * @return
	 * @author 陈义
	 * @date   2015-12-11上午11:16:16
	 */
	public  T1 D1ToT1(T1 t1,D1 d1) {
		//	Class<?> d1=(Class)c;
		//	System.out.println("……………………ToDto…………………………");
      try{
			Field[] fields=d1.getClass().getDeclaredFields();
			BeanUtils.copyProperties(d1, t1);
			for(Field field:fields){
				if(field.getGenericType().toString().contains("class com.cx.wxs.")){
					Object o1= invokeMethodGET(d1,field.getName(),null);				
					if(o1!=null){
						System.out.println(field.getGenericType().toString()+"::"+field.getName());
						String fieldName=field.getName();
						System.out.println("fieldName:"+fieldName);
						Class<?> fieldType=field.getType();
						if(field.getGenericType().toString().contains(".dto.")){
							String first=field.getName().substring(0,1).toUpperCase();
							fieldName=first+field.getName().substring(1,(field.getName().length()-3));				
							if(field.getGenericType().toString().contains("UUser")){
								fieldType=Class.forName("com.cx.wxs.po.UUser");
							}else{
							fieldType=Class.forName("com.cx.wxs.po."+fieldName);
							}
						}else{
							String first=field.getName().substring(0,1).toUpperCase();
							fieldName=first+field.getName().substring(1,field.getName().length());
							fieldName+="Dto";
							fieldType=Class.forName("com.cx.wxs.dto."+fieldName+"Dto");
						}
						System.out.println(fieldType+"::"+fieldName);
						Object o2=null;
						try {
							 o2=fieldType.newInstance();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						BeanUtils.copyProperties(o1, o2);
						Object[] args=new Object[]{o2};
						invokeMethodSET(t1, fieldName, args, fieldType);
						}
					}
			}
			return t1;
      }catch(Exception e){
    	  e.printStackTrace();
    	  return t1;
      }
	}
      
}
