/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.car.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.brand.entity.OcrBrand;
import com.thinkgem.jeesite.modules.brand.service.OcrBrandService;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle.OilCosumptionForMonth;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicleGPSVO;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.obd.entity.OcrOilConsumption;
import com.thinkgem.jeesite.modules.obd.service.MessageGpsService;

/**
 * 车辆管理Controller
 * @author zr
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/car/ocrVehicle")
public class OcrVehicleController extends BaseController {

	@Autowired
	private OcrVehicleService ocrVehicleService;
	@Autowired
	private OcrBrandService ocrBrandService;
	@Autowired
	private MessageGpsService messageGpsService;
	
	@ModelAttribute
	public OcrVehicle get(@RequestParam(required=false) String id) {
		OcrVehicle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrVehicleService.get(id);
		}
		if (entity == null){
			entity = new OcrVehicle();
		}
		return entity;
	}
	
	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = {"list", "IAI"})
	public String listIAI(OcrVehicle ocrVehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrVehicle> page = ocrVehicleService.findPage(new Page<OcrVehicle>(request, response), ocrVehicle); 
		List<OcrVehicle> ocrVehicleList = new ArrayList<OcrVehicle>();
		ocrVehicleList = page.getList();
		if (ocrVehicleList != null && ocrVehicleList.size() > 0) {
			for (OcrVehicle ov : ocrVehicleList) {
				Date insuranceDate = ov.getInsuranceDate();// 保险日期
				if (insuranceDate != null) {
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(insuranceDate);
					rightNow.add(Calendar.YEAR, 1);// 日期加1年
					Date dt = rightNow.getTime();
					ov.setInsuranceDate(dt);
				}
				Date annualInspectionDate = ov.getAnnualInspectionDate();// 年检日期
				if (annualInspectionDate != null) {
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(annualInspectionDate);
					rightNow.add(Calendar.YEAR, 2);// 日期加2年
					Date dt = rightNow.getTime();
					ov.setAnnualInspectionDate(dt);
				}
			}
		}
		model.addAttribute("page", page);
		return "modules/car/ocrVehicleListIAI";
	}
	
	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = {"list", "AQ"})
	public String listAQ(OcrVehicle ocrVehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrVehicle> page = ocrVehicleService.findPage(new Page<OcrVehicle>(request, response), ocrVehicle); 
		model.addAttribute("page", page);
		return "modules/car/ocrVehicleListAQ";
	}
	
	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrVehicle ocrVehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrVehicle> page = ocrVehicleService.findPage(new Page<OcrVehicle>(request, response), ocrVehicle); 
		model.addAttribute("page", page);
/*		String strs[] = {"2018-01-01","2018-01-02","2018-01-03","2018-01-04","2018-01-05","2018-01-06","2018-01-07","2018-01-08"
				,"2018-01-09","2018-01-10","2018-01-11","2018-01-12","2018-01-13","2018-01-14","2018-01-15","2018-01-16","2018-01-17"
				,"2018-01-18","2018-01-19","2018-01-20","2018-01-21","2018-01-22","2018-01-23","2018-01-24","2018-01-25"
				,"2018-01-26","2018-01-27","2018-01-28","2018-01-29"};*/
	/*	String strs[] = {"2018-02-01"};
 		List<String> list = Arrays.asList(strs);
 		for (String string : list) {
 			ocrOilConsumptionService.getOilConsumptionTest(null, null, null,string);
		}*/
		return "modules/car/ocrVehicleList";
	}

	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = "form")
	public String form(OcrVehicle ocrVehicle, Model model) {
		OcrBrand ob= new OcrBrand();
		List<OcrBrand> brands = ocrBrandService.findList(ob);
		model.addAttribute("brands", brands);		
		model.addAttribute("ocrVehicle", ocrVehicle);		
		return "modules/car/ocrVehicleForm";
	}
	@RequiresPermissions("car:ocrVehicle:view")
	@RequestMapping(value = "view")
	public String view(OcrVehicle ocrVehicle, Model model) {
		model.addAttribute("ocrVehicle", ocrVehicle);
		OcrBrand ob= new OcrBrand();
		List<OcrBrand> brands = ocrBrandService.findList(ob);
		model.addAttribute("brands", brands);
		return "modules/car/ocrVehicleView";
	}

	@RequiresPermissions("car:ocrVehicle:edit")
	@RequestMapping(value = "save")
	public String save(OcrVehicle ocrVehicle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrVehicle)){
			return form(ocrVehicle, model);
		}
		if (ocrVehicle.getIsNewRecord()) {
			ocrVehicle.setStatus("0");
		}
		ocrVehicleService.save(ocrVehicle);
		addMessage(redirectAttributes, "保存车辆成功");
		return "redirect:"+Global.getAdminPath()+"/car/ocrVehicle/?repage";
	}
	
	@RequiresPermissions("car:ocrVehicle:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrVehicle ocrVehicle, RedirectAttributes redirectAttributes) {
		ocrVehicleService.delete(ocrVehicle);
		addMessage(redirectAttributes, "删除车辆成功");
		return "redirect:"+Global.getAdminPath()+"/car/ocrVehicle/?repage";
	}

	@RequiresPermissions("car:oilConsumption:view")
	@RequestMapping(value = "oilView")
	public String oilview(String id, Model model,String selYear) {
		
		if (StringUtils.isBlank(selYear)) {
			Calendar date = Calendar.getInstance();
			selYear = String.valueOf(date.get(Calendar.YEAR));
		}
			OcrVehicle ocrVehicle = ocrVehicleService.get(id);
		
		
		//需要返回值：月份，行驶里程（公里），平均每百公里油耗（100公里/L)，当月油耗(L)
		List<OilCosumptionForMonth> monthList = ocrVehicleService.getOilCosumptionForMonth(ocrVehicle.getId(),selYear);
		List<String> xAxis = Lists.newArrayList();
		List<String> yAxis = Lists.newArrayList();
		for (OilCosumptionForMonth om : monthList) {
			xAxis.add(om.getMonth());
			yAxis.add(String.valueOf(om.getOilConsumptionForMonth()));
		}
		
		
		model.addAttribute("ocrVehicle", ocrVehicle);
		model.addAttribute("monthList", monthList);
		model.addAttribute("xAxis", xAxis);
		model.addAttribute("yAxis", yAxis);
		return "modules/car/oilConsumption";
	}
	@RequiresPermissions("car:oilConsumption:view")
	@RequestMapping(value = "oilDayView")
	public String oilDayView(String id, Model model,String year,String month) {
		OcrVehicle ocrVehicle = ocrVehicleService.get(id);
		
		List<OcrOilConsumption> dayList = ocrVehicleService.dayOilAndMileageConsumptionList(id, year, month);

		List<String> xAxis = Lists.newArrayList();
		List<String> yAxis = Lists.newArrayList();
		for (OcrOilConsumption om : dayList) {
			xAxis.add("\""+om.getDateForQuery()+"\"");
			yAxis.add(String.valueOf(om.getOilConsumptionDay()));
		}
		
		

		model.addAttribute("xAxis", xAxis);
		model.addAttribute("yAxis", yAxis);
		
		model.addAttribute("ocrVehicle", ocrVehicle);
		model.addAttribute("dayList", dayList);
		model.addAttribute("queryDate", year+"/"+month);
		return "modules/car/oilDayConsumption";
	}
	
	@RequestMapping(value = "findObdItemById")
	@ResponseBody
	public Map<String, Object> findObdItemById(String id){
		Map<String, Object> obdMap = Maps.newHashMap();
		Map<String, Object> map = Maps.newHashMap();

		
		 List<MessageObd> obds = ocrVehicleService.findObdItemById(id);
		 OcrVehicleGPSVO ocrVehicleGPSVO = ocrVehicleService.findOcrVehicleGPS(id);
		 //处理obd信息
		 for (MessageObd messageObd : obds) {
			 if ("88".equals(messageObd.getType())) {
				 obdMap.put("hourOil", messageObd.getValue());
			}
			 if ("89".equals(messageObd.getType())) {
				 obdMap.put("hundredKilometersOil", messageObd.getValue());
			}
			 if ("8A".equalsIgnoreCase(messageObd.getType())) {
				 obdMap.put("KilometreNumber", messageObd.getValue());
			}
			 if ("8B".equalsIgnoreCase(messageObd.getType())) {
				 obdMap.put("tankOil", messageObd.getValue());
			}
			 
		}
		 map.put("obd", obdMap);
		 map.put("gps", ocrVehicleGPSVO);

		return map;
	}
	
	
	/*
	 * 订单管理平台行驶数据
	 */
	@RequestMapping(value = "dayTrajectoryMap")
	public String dayTrajectoryMap(OcrVehicle ocrVehicle,String carDate, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) throws ParseException {
		java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		Date date =  formatter.parse(carDate);
		 Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.DAY_OF_MONTH, 1);
	        Date tomorrowDate = c.getTime();
		List<LatLng> list = messageGpsService.getListByCarIdAndDate(ocrVehicle.getEquipmentImei(),date,tomorrowDate);
		
		// 去掉重复坐标点
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getLatitude() == list.get(i).getLatitude()
						&& list.get(j).getLongitude() == list.get(i).getLongitude()) {
					list.remove(j);
				}
			}
		}
		
		//获得最新位置数据
		Gson gson = new Gson();
		if (list.size()>0) {
			model.addAttribute("nowPosition", list.get(list.size()-1));
			model.addAttribute("startPosition", list.get(0));
			model.addAttribute("allPosition",gson.toJson(list));			
		}
		return "modules/car/dayTrajectoryMap";
	}
	
	
}