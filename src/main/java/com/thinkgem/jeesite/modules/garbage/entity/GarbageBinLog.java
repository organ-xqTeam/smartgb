package com.thinkgem.jeesite.modules.garbage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @author ybc
 * @since 2018-6-4
 */
public class GarbageBinLog extends DataEntity<GarbageBinLog>{

	private static final long serialVersionUID = 1L;

	private String id;
	
	//创建时间
	private Date createDate;
	
	private String msg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
