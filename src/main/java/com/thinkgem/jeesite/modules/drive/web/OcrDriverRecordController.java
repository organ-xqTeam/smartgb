/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverRecord;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverRecordService;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverTravelService;

/**
 * 行驶记录Controller
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/record")
public class OcrDriverRecordController extends BaseController {
	
	private Logger log = LogManager.getLogger(OcrDriverRecordController.class);

	@Autowired
	private OcrDriverRecordService ocrDriverRecordService;
	
	@Autowired
	private OcrDriverTravelService ocrDriverTravelService;
	
	@RequiresPermissions("drive:work:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDriverRecord driverRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrDriverRecord> page = ocrDriverRecordService.findPage(new Page<OcrDriverRecord>(request, response), driverRecord); 
		model.addAttribute("page", page);
		return "modules/drive/ocrDriverRecordList";
	}
	
	@RequestMapping(value = {"travelMap", ""})
	public String travelMap(String equipmentImei,String goDate,HttpServletRequest request, HttpServletResponse response, Model model){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(goDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date tomorrowDate = c.getTime();
			List<LatLng> list = ocrDriverTravelService.getListByCarIdAndDate(equipmentImei,date,tomorrowDate);
			// 去掉重复坐标点
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = list.size() - 1; j > i; j--) {
					if (list.get(j).getLatitude() == list.get(i).getLatitude()
							&& list.get(j).getLongitude() == list.get(i).getLongitude()) {
						list.remove(j);
					}
				}
			}
			OcrDriverRecord driverRecord = ocrDriverRecordService.getRecordByCarId(equipmentImei);
			model.addAttribute("driverRecord",driverRecord);
			//获得最新位置数据
			Gson gson = new Gson();
			if (list.size()>0) {
				model.addAttribute("nowPosition", list.get(list.size()-1));
				model.addAttribute("startPosition", list.get(0));
				model.addAttribute("allPosition",gson.toJson(list));
			}
		} catch (Exception e) {
			log.error("OcrDriverRecordController travelMap",e);
		}
		return "modules/drive/ocrDriverMap";
	}
	
}