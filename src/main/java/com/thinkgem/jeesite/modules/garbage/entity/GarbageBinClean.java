package com.thinkgem.jeesite.modules.garbage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @author ybc
 * @since 2018-6-4
 */
public class GarbageBinClean extends DataEntity<GarbageBinClean>{

	private static final long serialVersionUID = 1L;

	private String id;
	
	//设备编码6*BYTE
	private String dtuId;
	
	//垃圾量
	private String garbageMany;
	
	//位置
	private String position;
	
	//清理时间
	private Date cleanDate;
	
	//创建时间
	private Date createDate;
	
	private Date cleanDateStart;
	
	private Date cleanDateEnd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDtuId() {
		return dtuId;
	}

	public void setDtuId(String dtuId) {
		this.dtuId = dtuId;
	}

	public String getGarbageMany() {
		return garbageMany;
	}

	public void setGarbageMany(String garbageMany) {
		this.garbageMany = garbageMany;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getCleanDate() {
		return cleanDate;
	}

	public void setCleanDate(Date cleanDate) {
		this.cleanDate = cleanDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCleanDateStart() {
		return cleanDateStart;
	}

	public void setCleanDateStart(Date cleanDateStart) {
		this.cleanDateStart = cleanDateStart;
	}

	public Date getCleanDateEnd() {
		return cleanDateEnd;
	}

	public void setCleanDateEnd(Date cleanDateEnd) {
		this.cleanDateEnd = cleanDateEnd;
	}

}
