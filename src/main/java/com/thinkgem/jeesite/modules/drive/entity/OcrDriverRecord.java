package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 
 * @author ybc
 * @since 2018-6-4
 */
public class OcrDriverRecord extends DataEntity<OcrDriverRecord>{

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String carId;
	
	private String plateNum;
	
	private Date goDate;
	
	private Date arriveDate;
	
	private String carBand;
	
	private String oilNum;
	
	private Date createDate;
	
	private Date recordDateStar;
	
	private Date recordDateEnd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getCarBand() {
		return carBand;
	}

	public void setCarBand(String carBand) {
		this.carBand = carBand;
	}

	public String getOilNum() {
		return oilNum;
	}

	public void setOilNum(String oilNum) {
		this.oilNum = oilNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGoDate() {
		return goDate;
	}

	public void setGoDate(Date goDate) {
		this.goDate = goDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Date getRecordDateStar() {
		return recordDateStar;
	}

	public void setRecordDateStar(Date recordDateStar) {
		this.recordDateStar = recordDateStar;
	}

	public Date getRecordDateEnd() {
		return recordDateEnd;
	}

	public void setRecordDateEnd(Date recordDateEnd) {
		this.recordDateEnd = recordDateEnd;
	}

	@Override
	public String toString() {
		return "OcrDriverRecord [id=" + id + ", carId=" + carId + ", plateNum=" + plateNum + ", goDate=" + goDate
				+ ", arriveDate=" + arriveDate + ", carBand=" + carBand + ", oilNum=" + oilNum + ", createDate="
				+ createDate + "]";
	}

}
