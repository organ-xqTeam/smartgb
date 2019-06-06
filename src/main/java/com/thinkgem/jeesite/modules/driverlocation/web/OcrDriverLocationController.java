/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.driverlocation.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.common.utils.GodHttpRequest;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicleGPSVO;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.driverlocation.entity.OcrDriverLocation;
import com.thinkgem.jeesite.modules.driverlocation.service.OcrDriverLocationService;
import com.thinkgem.jeesite.modules.sys.entity.GodGPSVO;

/**
 * 司机位置Controller
 * @author zr
 * @version 2017-10-09
 */
@Controller
@RequestMapping(value = "${adminPath}/driverlocation/ocrDriverLocation")
public class OcrDriverLocationController extends BaseController {

	@Autowired
	private OcrDriverLocationService ocrDriverLocationService;
	@Autowired
	private OcrVehicleService ocrVehicleService;
	@ModelAttribute
	public OcrDriverLocation get(@RequestParam(required=false) String id) {
		OcrDriverLocation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrDriverLocationService.get(id);
		}
		if (entity == null){
			entity = new OcrDriverLocation();
		}
		return entity;
	}
	
	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = {"ocrVehicleGPSAndMapList"})
	public String ocrVehicleGPSAndMapList(OcrVehicle ocrVehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrVehicle> page = ocrVehicleService.findPage(new Page<OcrVehicle>(request, response), ocrVehicle); 
		model.addAttribute("page", page);
		List<OcrVehicleGPSVO> VO = ocrVehicleService.findOcrVehicleGPSList();
		String locations = "";
		for (OcrVehicleGPSVO ocrVehicleGPSVO : VO) {
			LatLng latLng = CoordinationConvertUtil.transformFromGCJToWGS(
					Double.parseDouble(ocrVehicleGPSVO.getLatitude()), 
					Double.parseDouble(ocrVehicleGPSVO.getLongitude()));    
			String longitude = String
					.valueOf(new BigDecimal(latLng.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
			String latitude = String
					.valueOf(new BigDecimal(latLng.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
			if (longitude.contains("-") || latitude.contains("-")) {
				longitude = ocrVehicleGPSVO.getLatitude();
				latitude = ocrVehicleGPSVO.getLongitude();
			}
			locations = locations + longitude + "," + latitude + ";";
		}
		
		String result = GodHttpRequest.GodGPSSendGet(locations);
		Gson gson = new Gson();	
		GodGPSVO godGPSVO =  gson.fromJson(result, GodGPSVO.class);
		List<String> locationsList= Arrays.asList(godGPSVO.getLocations().split(";"));
		for (int i = 0; i < VO.size(); i++) {
			VO.get(i).setLatitude(Arrays.asList(locationsList.get(i).split(",")).get(1));
			VO.get(i).setLongitude(Arrays.asList(locationsList.get(i).split(",")).get(0));
		}		
		String locationList = gson.toJson(VO);
		model.addAttribute("locationList", locationList);
		return "modules/car/ocrVehicleGPSAndMapList";
	}
	
	@RequestMapping(value = {"ocrVehicleGPS"})
	@ResponseBody
	public Map<String, Object> ocrVehicleGPS(String id) {
		Map<String, Object>  map = Maps.newHashMap();
		OcrVehicleGPSVO ocrVehicleGPSVO = ocrVehicleService.findOcrVehicleGPS(id);
		LatLng latLng = CoordinationConvertUtil.transformFromGCJToWGS(
				Double.parseDouble(ocrVehicleGPSVO.getLatitude()), 
				Double.parseDouble(ocrVehicleGPSVO.getLongitude()));
		ocrVehicleGPSVO.setLatitude(String.valueOf(latLng.getLatitude()));
		ocrVehicleGPSVO.setLongitude(String.valueOf(latLng.getLongitude()));
		map.put("data", ocrVehicleGPSVO);
	return map;	
	}
	
	@RequestMapping(value = {"ocrVehicleGPSList"})
	@ResponseBody
	public List<OcrVehicleGPSVO> ocrVehicleGPSList() {
		List<OcrVehicleGPSVO> VO = ocrVehicleService.findOcrVehicleGPSList();
		String locations = "";
		for (OcrVehicleGPSVO ocrVehicleGPSVO : VO) {
			LatLng latLng = CoordinationConvertUtil.transformFromGCJToWGS(
					Double.parseDouble(ocrVehicleGPSVO.getLatitude()), 
					Double.parseDouble(ocrVehicleGPSVO.getLongitude()));    
			String longitude = String
					.valueOf(new BigDecimal(latLng.getLongitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
			String latitude = String
					.valueOf(new BigDecimal(latLng.getLatitude()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
			if (longitude.contains("-") || latitude.contains("-")) {
				longitude = ocrVehicleGPSVO.getLatitude();
				latitude = ocrVehicleGPSVO.getLongitude();
			}
			locations = locations + longitude + "," + latitude + ";";
		}
		
		String result = GodHttpRequest.GodGPSSendGet(locations);
		Gson gson = new Gson();	
		GodGPSVO godGPSVO =  gson.fromJson(result, GodGPSVO.class);
		List<String> locationsList= Arrays.asList(godGPSVO.getLocations().split(";"));
		for (int i = 0; i < VO.size(); i++) {
			VO.get(i).setLatitude(Arrays.asList(locationsList.get(i).split(",")).get(1));
			VO.get(i).setLongitude(Arrays.asList(locationsList.get(i).split(",")).get(0));
		}	
		return VO;
	}
	
	//车辆位置地图
	@RequiresPermissions("driverlocation:ocrDriverLocation:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDriverLocation ocrDriverLocation, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<OcrDriverLocation> page = ocrDriverLocationService.findPage(new Page<OcrDriverLocation>(request, response), ocrDriverLocation); 
//		Date now = new Date();
//		Date now_10 = new Date(now.getTime() - 600000);
//		ocrDriverLocation.setUpdateDate(now_10);
		List<OcrDriverLocation> list = ocrDriverLocationService.findList(ocrDriverLocation);
		Gson gson = new Gson();
		String locationList = gson.toJson(list);
		model.addAttribute("locationList", locationList);

		//model.addAttribute("page", page);
		return "modules/driverlocation/ocrDriverLocationList";
	}
	//查询车辆位置方法
	@RequestMapping(value = {"getDriverLocationList"})
	@ResponseBody
	public List<OcrDriverLocation> getDriverLocationList() {
		OcrDriverLocation ocrDriverLocation = new OcrDriverLocation();
		ocrDriverLocation.setDelFlag("0");
//		Date now = new Date();
//		Date now_10 = new Date(now.getTime() - 600000);
//		ocrDriverLocation.setUpdateDate(now_10);
		List<OcrDriverLocation> list = ocrDriverLocationService.findList(ocrDriverLocation);
		
		return list;
	}

	@RequiresPermissions("driverlocation:ocrDriverLocation:view")
	@RequestMapping(value = "form")
	public String form(OcrDriverLocation ocrDriverLocation, Model model) {
		model.addAttribute("ocrDriverLocation", ocrDriverLocation);
		return "modules/driverlocation/ocrDriverLocationForm";
	}

	@RequiresPermissions("driverlocation:ocrDriverLocation:edit")
	@RequestMapping(value = "save")
	public String save(OcrDriverLocation ocrDriverLocation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrDriverLocation)){
			return form(ocrDriverLocation, model);
		}
		ocrDriverLocationService.save(ocrDriverLocation);
		addMessage(redirectAttributes, "保存司机位置成功");
		return "redirect:"+Global.getAdminPath()+"/driverlocation/ocrDriverLocation/?repage";
	}
	
	@RequiresPermissions("driverlocation:ocrDriverLocation:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDriverLocation ocrDriverLocation, RedirectAttributes redirectAttributes) {
		ocrDriverLocationService.delete(ocrDriverLocation);
		addMessage(redirectAttributes, "删除司机位置成功");
		return "redirect:"+Global.getAdminPath()+"/driverlocation/ocrDriverLocation/?repage";
	}

}