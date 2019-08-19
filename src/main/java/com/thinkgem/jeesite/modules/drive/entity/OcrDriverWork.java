package com.thinkgem.jeesite.modules.drive.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 
 * @author ybc
 * @since 2018-6-4
 */
public class OcrDriverWork extends DataEntity<OcrDriverWork>{

	private static final long serialVersionUID = 1L;

	private String id;
	
	@ExcelField(title="姓名",align=2, sort=20)
	private String name;
	

	@ExcelField(title="姓名",align=2, sort=20)
	private String sex;

	@ExcelField(title="联系电话",align=2, sort=20)
	private String phone;

	@ExcelField(title="身份证号",align=2, sort=20)
	private String idNum;

	@ExcelField(title="驾驶员编号",align=2, sort=20)
	private String driveNum;

	
	@ExcelField(title="司机所在地",align=2, sort=20)
	private String position;

	
	@ExcelField(title="上班时间",align=2, sort=20)
	private Date onWork;

	
	@ExcelField(title="下班时间",align=2, sort=20)
	private Date offWork;

	private Date workDateStar;

	private Date workDateEnd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getDriveNum() {
		return driveNum;
	}

	public void setDriveNum(String driveNum) {
		this.driveNum = driveNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getOnWork() {
		return onWork;
	}

	public void setOnWork(Date onWork) {
		this.onWork = onWork;
	}

	public Date getOffWork() {
		return offWork;
	}

	public void setOffWork(Date offWork) {
		this.offWork = offWork;
	}

	public Date getWorkDateStar() {
		return workDateStar;
	}

	public void setWorkDateStar(Date workDateStar) {
		this.workDateStar = workDateStar;
	}

	public Date getWorkDateEnd() {
		return workDateEnd;
	}

	public void setWorkDateEnd(Date workDateEnd) {
		this.workDateEnd = workDateEnd;
	}
	
}
