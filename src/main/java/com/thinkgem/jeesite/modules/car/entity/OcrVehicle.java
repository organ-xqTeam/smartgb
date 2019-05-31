/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.car.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.drive.entity.OcrTaskVO;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;

/**
 * 车辆管理Entity
 * @author zr
 * @version 2017-08-03
 */
public class OcrVehicle extends DataEntity<OcrVehicle> {
	
	private static final long serialVersionUID = 1L;
	private String vehicleBrand;		// 车辆厂牌
	private String brandModels;		// 品牌车型
	private String vehicleType;		// 车辆类型
	private String licensePlateNumber;		// 车牌号
	private String drivingLicenseNumber;		// 行驶证编号
	private String engineNumber;		// 发动机编号
	private Area area; //车辆所在地区
	private String vehicleIdentificationNumber;		// 车架号
	private String tankVolume;		// 油箱容积(L)
	private String equipmentImei;		// 设备IMEI
	private String equipmentStatus;		// 设备绑定状态
	private Date insuranceDate;		// 保险日期
	private Date annualInspectionDate;		// 年检日期
	private String seatCount;		// 座位数量
	private String status;		// 状态
	private String year;		// 年份
	private String month;		// 月份
	private String tankType;
	private List<OcrTaskVO> ocrTaskVOList;
	
	private OcrTask ocrTask1;// 上一任务
	private OcrTask ocrTask2;// 下一任务
	
	private Date departureDate;		// 出发时间
	private Date planArrivalDate;		// 预计到达时间

	
	
	public String getTankType() {
		return tankType;
	}

	public void setTankType(String tankType) {
		this.tankType = tankType;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getPlanArrivalDate() {
		return planArrivalDate;
	}

	public void setPlanArrivalDate(Date planArrivalDate) {
		this.planArrivalDate = planArrivalDate;
	}

	public OcrTask getOcrTask1() {
		return ocrTask1;
	}

	public void setOcrTask1(OcrTask ocrTask1) {
		this.ocrTask1 = ocrTask1;
	}

	public OcrTask getOcrTask2() {
		return ocrTask2;
	}

	public void setOcrTask2(OcrTask ocrTask2) {
		this.ocrTask2 = ocrTask2;
	}

	public List<OcrTaskVO> getOcrTaskVOList() {
		return ocrTaskVOList;
	}

	public void setOcrTaskVOList(List<OcrTaskVO> ocrTaskVOList) {
		this.ocrTaskVOList = ocrTaskVOList;
	}
	
	public OcrVehicle() {
		super();
	}

	public OcrVehicle(String id){
		super(id);
	}

	@Length(min=0, max=100, message="车辆厂牌长度必须介于 0 和 100之间")
	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	@Length(min=1, max=100, message="品牌车型长度必须介于 1 和 10 之间")
	public String getBrandModels() {
		return brandModels;
	}

	public void setBrandModels(String brandModels) {
		this.brandModels = brandModels;
	}
	
	@Length(min=1, max=100, message="车辆类型长度必须介于 1 和 100 之间")
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Length(min=1, max=100, message="车牌号长度必须介于 1 和 100 之间")
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
	
	@Length(min=1, max=100, message="行驶证编号长度必须介于 1 和 100 之间")
	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}
	
	@Length(min=1, max=11, message="发动机编号长度必须介于 1 和 11 之间")
	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	
	@Length(min=1, max=100, message="车架号长度必须介于 1 和 100 之间")
	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}
	
	@Length(min=0, max=11, message="座位数量长度必须介于 0 和 11 之间")
	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	@Length(min=0, max=11, message="油箱容积(L)长度必须介于 0 和 11 之间")
	public String getTankVolume() {
		return tankVolume;
	}

	public void setTankVolume(String tankVolume) {
		this.tankVolume = tankVolume;
	}
	
	@Length(min=0, max=20, message="设备IMEI长度必须介于 0 和 20 之间")
	public String getEquipmentImei() {
		return equipmentImei;
	}

	public void setEquipmentImei(String equipmentImei) {
		this.equipmentImei = equipmentImei;
	}
	
	@Length(min=0, max=1, message="设备绑定状态长度必须介于 0 和 1 之间")
	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInsuranceDate() {
		return insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAnnualInspectionDate() {
		return annualInspectionDate;
	}

	public void setAnnualInspectionDate(Date annualInspectionDate) {
		this.annualInspectionDate = annualInspectionDate;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}



	//构建油耗返回值对象
	public static class OilCosumptionForMonth{
		private String year;
		private String month;  //月份
		private Double drivingMileage;//行驶里程
		private Double oilConsumptionForHundredKMS; //平均每百公里油耗
		private Double oilConsumptionForMonth;//当月油耗
		
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public Double getDrivingMileage() {
			return drivingMileage;
		}
		public void setDrivingMileage(Double drivingMileage) {
			this.drivingMileage = drivingMileage;
		}
		public Double getOilConsumptionForHundredKMS() {
			return oilConsumptionForHundredKMS;
		}
		public void setOilConsumptionForHundredKMS(Double oilConsumptionForHundredKMS) {
			this.oilConsumptionForHundredKMS = oilConsumptionForHundredKMS;
		}
		public Double getOilConsumptionForMonth() {
			return oilConsumptionForMonth;
		}
		public void setOilConsumptionForMonth(Double oilConsumptionForMonth) {
			this.oilConsumptionForMonth = oilConsumptionForMonth;
		}
		
	}
	
	//每日油耗详情，构造油耗详情对象
	public static class OilCosumptionForDay{
		private String day;
		private Double drivingMileageForDay;//行驶里程
		private Double oilConsumptionForDay; //每日油耗
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
		}
		public Double getDrivingMileageForDay() {
			return drivingMileageForDay;
		}
		public void setDrivingMileageForDay(Double drivingMileageForDay) {
			this.drivingMileageForDay = drivingMileageForDay;
		}
		public Double getOilConsumptionForDay() {
			return oilConsumptionForDay;
		}
		public void setOilConsumptionForDay(Double oilConsumptionForDay) {
			this.oilConsumptionForDay = oilConsumptionForDay;
		}
		
	}
}