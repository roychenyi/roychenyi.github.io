package com.cx.wxs.dao;

import java.util.List;

import com.cx.wxs.dto.SysRoleDto;
import com.cx.wxs.po.SysRole;
import com.cx.wxs.base.dao.IBaseDao;

/**
 * @author 陈义
 * @date 2015-12-13 19:01:59
 */
public interface SysRoleDao extends IBaseDao<SysRole,Integer>{
    /**
    * 通过id获取SysRoleDto
    * @author 陈义
    * @date 2015-12-13 19:01:59
    */
    public SysRoleDto getSysRoleByID(SysRoleDto sysRoleDto);

    /**
    * 通过相关数据获取SysRoleDtoList
    * @author 陈义
    * @date 2015-12-13 19:01:59
    */
    public List<SysRoleDto> getSysRoleList(SysRoleDto sysRoleDto);

    /**
    * 添加一个新的SysRole到数据库
    * @author 陈义
    * @date 2015-12-13 19:01:59
    */
    public Integer addSysRole(SysRoleDto sysRoleDto);

    /**
    * 更新SysRole
    * @author 陈义
    * @date 2015-12-13 19:01:59
    */
    public Integer updateSysRole(SysRoleDto sysRoleDto);

    /**
    * 删除SysRole
    * @author 陈义
    * @date 2015-12-13 19:01:59
    */
    public Integer deleteSysRole(SysRoleDto sysRoleDto);

}