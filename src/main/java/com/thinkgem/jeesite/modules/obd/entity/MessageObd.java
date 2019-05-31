/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.entity;

import javax.validation.constraints.NotNull;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * odb表Entity
 * 
 * @author tf
 * @version 2017-12-15
 */
public class MessageObd extends DataEntity<MessageObd> {
	// 每小时油耗 L/H
	public static final String CONTENT_MONITOR_OIL_HOUR = "88";
	// 每100km油耗 L/100KM
	public static final String CONTENT_MONITOR_OIL_100KM = "89";
	// 行驶里程 KM
	public static final String CONTENT_MONITOR_MILEAGE = "8a";
	// 剩余油量 % 或者 L
	public static final String CONTENT_MONITOR_OIL_RESIDUES = "8b";
	//发动机转速
	public static final String CONTENT_MONITOR_ENGINEER_SPEED = "0c";
	//车速
	public static final String CONTENT_MONITOR_CAR_SPEED = "0d";
	private static final long serialVersionUID = 1L;
	private Long carId; // car_id
	private Date carTime; // car_time
	private String sequenceNumber; // sequence_number
	private Date createTime; // create_time
	private String type; // type
	private Double value; // value
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间
	private Long obdId; // obd表的记录id

	public MessageObd() {
		super();
	}

	public MessageObd(String id) {
		super(id);
	}

	@NotNull(message = "car_id不能为空")
	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "car_time不能为空")
	public Date getCarTime() {
		return carTime;
	}

	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}

	@Length(min = 1, max = 6, message = "sequence_number长度必须介于 1 和 6 之间")
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getObdId() {
		return obdId;
	}

	public void setObdId(Long obdId) {
		this.obdId = obdId;
	}

}