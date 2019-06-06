package com.thinkgem.jeesite.modules.garbage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @author ybc
 * @since 2018-6-4
 */
public class GarbageBinInfo extends DataEntity<GarbageBinInfo>{

	private static final long serialVersionUID = 1L;

	//设备编码6*BYTE
	private String dtuId;
	
	//垃圾量
	private String garbageMany;
	
	//垃圾深度(默认为200cm)
	private Integer garbageTotal;
	
	//位置
	private String position;
	
	//纬度
	private String latitude;
	
	//经度
	private String longitude;
	
	//连接状态
	private String connectState;
	
	//监测状态
	private String monitorState;
	
	//GPS状态
	private String gpsState;
	
	//创建时间
	private Date createDate;
	
	//更新时间
	private Date updateDate;

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

	public Integer getGarbageTotal() {
		return garbageTotal;
	}

	public void setGarbageTotal(Integer garbageTotal) {
		this.garbageTotal = garbageTotal;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getConnectState() {
		return connectState;
	}

	public void setConnectState(String connectState) {
		this.connectState = connectState;
	}

	public String getMonitorState() {
		return monitorState;
	}

	public void setMonitorState(String monitorState) {
		this.monitorState = monitorState;
	}

	public String getGpsState() {
		return gpsState;
	}

	public void setGpsState(String gpsState) {
		this.gpsState = gpsState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
