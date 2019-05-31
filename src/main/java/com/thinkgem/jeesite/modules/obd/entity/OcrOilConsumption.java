/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 油量消耗Entity
 * 
 * @author tf
 * @version 2017-12-15
 */
public class OcrOilConsumption extends DataEntity<OcrOilConsumption> {

	private static final long serialVersionUID = 1L;
	private Long carId; // 车机的car_id
	private Double oilConsumptionDay; // 每日油量消耗
	private Date recordDate; // 记录数据日期
	private String dateForQuery;//传向页面的日期
	private String mileage;//公里

	public OcrOilConsumption() {
		super();
	}

	public OcrOilConsumption(String id) {
		super(id);
	}

	@NotNull(message = "车机的car_id不能为空")
	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@NotNull(message = "每日油量消耗不能为空")
	public Double getOilConsumptionDay() {
		return oilConsumptionDay;
	}

	public void setOilConsumptionDay(Double oilConsumptionDay) {
		this.oilConsumptionDay = oilConsumptionDay;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getDateForQuery() {
		return dateForQuery;
	}

	public void setDateForQuery(String dateForQuery) {
		this.dateForQuery = dateForQuery;
	}



	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	

}