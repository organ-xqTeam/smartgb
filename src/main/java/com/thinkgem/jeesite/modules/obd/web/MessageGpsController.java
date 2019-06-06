/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicleGPSVO;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.obd.entity.MessageGps;
import com.thinkgem.jeesite.modules.obd.service.MessageGpsService;

/**
 * GPS基本信息类Controller
 * @author tf
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/odb/messageGps")
public class MessageGpsController extends BaseController {

	@Autowired
	private MessageGpsService messageGpsService;
	@Autowired
	private OcrVehicleService ocrVehicleService;
	
	@ModelAttribute
	public MessageGps get(@RequestParam(required=false) String id) {
		MessageGps entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = messageGpsService.get(id);
		}
		if (entity == null){
			entity = new MessageGps();
		}
		return entity;
	}
	
	@RequiresPermissions("odb:messageGps:view")
	@RequestMapping(value = {"list", ""})
	public String list(MessageGps messageGps, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageGps> page = messageGpsService.findPage(new Page<MessageGps>(request, response), messageGps); 
		model.addAttribute("page", page);
		return "modules/odb/messageGpsList";
	}

	@RequiresPermissions("odb:messageGps:view")
	@RequestMapping(value = "form")
	public String form(MessageGps messageGps, Model model) {
		model.addAttribute("messageGps", messageGps);
		return "modules/odb/messageGpsForm";
	}

	@RequiresPermissions("odb:messageGps:edit")
	@RequestMapping(value = "save")
	public String save(MessageGps messageGps, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, messageGps)){
			return form(messageGps, model);
		}
		messageGpsService.save(messageGps);
		addMessage(redirectAttributes, "保存GPS信息保存成功");
		return "redirect:"+Global.getAdminPath()+"/odb/messageGps/?repage";
	}
	
	@RequiresPermissions("odb:messageGps:edit")
	@RequestMapping(value = "delete")
	public String delete(MessageGps messageGps, RedirectAttributes redirectAttributes) {
		messageGpsService.delete(messageGps);
		addMessage(redirectAttributes, "删除GPS信息保存成功");
		return "redirect:"+Global.getAdminPath()+"/odb/messageGps/?repage";
	}
	@RequiresPermissions("odb:messageGps:view")
	@RequestMapping(value = "getListByCarId")
	@ResponseBody
	public List<LatLng> getListByCarId(@RequestParam(required=true) String carId,@RequestParam(required=true)String start,@RequestParam(required=true)String end) {
	/*public List<LatLng> getListByCarId(@RequestParam(required=true) OcrOrderVO order) {*/	
		   SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			Date startDate =null;
			Date endDate =null;
			try {
				startDate = StringUtils.isBlank(start)?null:sdf.parse(start);
				endDate = StringUtils.isBlank(end)?null:sdf.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		List<LatLng> list = messageGpsService.getListByCarIdAndDate(carId,startDate,endDate); 
		return list;
	}
	
	@RequestMapping(value = "getNewLatLngByCarId")
	@ResponseBody
	public OcrVehicleGPSVO getNewLatLngByCarId( String id) {
		
		
		OcrVehicleGPSVO ocrVehicleGPSVO = ocrVehicleService.findOcrVehicleGPS(id);
		LatLng lat = CoordinationConvertUtil.transformFromGCJToWGS( Double.parseDouble(ocrVehicleGPSVO.getLatitude()),Double.parseDouble(ocrVehicleGPSVO.getLongitude()));
		ocrVehicleGPSVO.setLatitude(String.valueOf(lat.getLatitude()));
		ocrVehicleGPSVO.setLongitude(String.valueOf(lat.getLongitude()));

		return ocrVehicleGPSVO;
	}
	
	/*//日期转换测试
	public static void main(String[] args) throws Exception {
		//Date d = new Date();
		//System.out.println(d);
		String d ="Thu Dec 14 17:19:21 CST 2017";
		//String d2 =d.substring(4);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date d1 = sdf.parse(d);
		System.out.println(d1);
	}*/
}