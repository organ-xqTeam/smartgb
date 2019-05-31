/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 心跳Entity
 * @author zr
 * @version 2017-12-13
 */
public class MessageHeartbeat extends DataEntity<MessageHeartbeat> {
	
	private static final long serialVersionUID = 1L;
	private Long carId;		// car_id
	private String gpsStatus;		// gps_status
	private String accStatus;		// acc_status
	private String defenceStatus;		// defence_status
	private String oilElectricityStatus;		// oil_electricity_status
	private String chargeStatus;		// charge_status
	private String unused;		// unused
	private String inputPort;		// input_port
	private String sequenceNumber;		// sequence_number
	private Date createTime;		// create_time
	
	public MessageHeartbeat() {
		super();
	}

	public MessageHeartbeat(String id){
		super(id);
	}

	@NotNull(message="car_id不能为空")
	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	@Length(min=1, max=4, message="gps_status长度必须介于 1 和 4 之间")
	public String getGpsStatus() {
		return gpsStatus;
	}

	public void setGpsStatus(String gpsStatus) {
		this.gpsStatus = gpsStatus;
	}
	
	@Length(min=1, max=4, message="acc_status长度必须介于 1 和 4 之间")
	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	
	@Length(min=1, max=4, message="defence_status长度必须介于 1 和 4 之间")
	public String getDefenceStatus() {
		return defenceStatus;
	}

	public void setDefenceStatus(String defenceStatus) {
		this.defenceStatus = defenceStatus;
	}
	
	@Length(min=1, max=4, message="oil_electricity_status长度必须介于 1 和 4 之间")
	public String getOilElectricityStatus() {
		return oilElectricityStatus;
	}

	public void setOilElectricityStatus(String oilElectricityStatus) {
		this.oilElectricityStatus = oilElectricityStatus;
	}
	
	@Length(min=1, max=4, message="charge_status长度必须介于 1 和 4 之间")
	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	
	@Length(min=1, max=4, message="unused长度必须介于 1 和 4 之间")
	public String getUnused() {
		return unused;
	}

	public void setUnused(String unused) {
		this.unused = unused;
	}
	
	@Length(min=1, max=4, message="input_port长度必须介于 1 和 4 之间")
	public String getInputPort() {
		return inputPort;
	}

	public void setInputPort(String inputPort) {
		this.inputPort = inputPort;
	}
	
	@Length(min=1, max=6, message="sequence_number长度必须介于 1 和 6 之间")
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}