package com.cx.wxs.dto;

import java.sql.Timestamp;

import com.cx.wxs.base.BeanBase;

/**
 * @author 陈义
 * @date   2015-12-9 上午11:21:33
 */
public class SysRoleDto extends BeanBase{
	// Fields

		private Integer roleId;
		private String name;
		private String value;
		private Timestamp createTime;
		private Timestamp lastTime;
		public SysRoleDto(){
			super();
		}
		public SysRoleDto(Integer roleId, String name, String value,
				Timestamp createTime, Timestamp lastTime) {
			super();
			this.roleId = roleId;
			this.name = name;
			this.value = value;
			this.createTime = createTime;
			this.lastTime = lastTime;
		}
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public Timestamp getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
		public Timestamp getLastTime() {
			return lastTime;
		}
		public void setLastTime(Timestamp lastTime) {
			this.lastTime = lastTime;
		}
		

}
