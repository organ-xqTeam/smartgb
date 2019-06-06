/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.journey.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.JPushDriverUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverService;
import com.thinkgem.jeesite.modules.journey.entity.OcrJourney;
import com.thinkgem.jeesite.modules.journey.service.OcrJourneyService;
import com.thinkgem.jeesite.modules.order.entity.OcrOrder;
import com.thinkgem.jeesite.modules.order.service.OcrOrderService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.task.entity.OcrTask;
import com.thinkgem.jeesite.modules.task.service.OcrTaskService;

/**
 * 行程管理Controller
 * @author gaoly
 * @version 2017-08-09
 */
@Controller
@RequestMapping(value = "${adminPath}/journey/ocrJourney")
public class OcrJourneyController extends BaseController {

	@Autowired
	private OcrJourneyService ocrJourneyService;
	
	@Autowired
	private OcrDriverService ocrDriverService;
	
	@Autowired
	private OcrVehicleService ocrVehicleService;
	
	@Autowired
	private OcrTaskService ocrTaskService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private AreaService  areaService;
	
	@Autowired
	private OcrOrderService  ocrOrederSever;
	
	@ModelAttribute
	public OcrJourney get(@RequestParam(required=false) String id) {
		OcrJourney entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrJourneyService.get(id);
		}
		if (entity == null){
			entity = new OcrJourney();
		}
		return entity;
	}
	
	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrJourney ocrJourney, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrJourney> page = ocrJourneyService.findPage(new Page<OcrJourney>(request, response), ocrJourney); 
		model.addAttribute("page", page);
		User user =UserUtils.getUser();
	   if(user!=null){
		   List list =systemService.getForArea(user).getAreaList();
			model.addAttribute("areas", systemService.getForArea(user).getAreaList());
	   }else{
		   List  list=new ArrayList();
		   model.addAttribute("areas", list);
	   }
		return "modules/journey/ocrJourneyList";
	}
	
	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = {"canceList"})
	public String canceList(OcrJourney ocrJourney, HttpServletRequest request, HttpServletResponse response, Model model) {
		ocrJourney.setStatus("7");// 已取消
		Page<OcrJourney> page = ocrJourneyService.findPage(new Page<OcrJourney>(request, response), ocrJourney); 
		model.addAttribute("page", page);
		return "modules/journey/ocrJourneyCancelList";
	}
	
	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = "designate")
	public String designate(OcrJourney ocrJourney, HttpServletRequest request, HttpServletResponse response, Model model) {
		//ocrJourney.setAreaName( ) ;  
		Area area = areaService.get(ocrJourney.getAreaId());
		if(area!=null){
			ocrJourney.setAreaName(area.getName());
		}
		model.addAttribute("ocrJourney", ocrJourney);
		OcrDriver ocrDriver = new OcrDriver();
//		Page<OcrDriver> page = ocrDriverService.findVOList(new Page<OcrDriver>(request, response), ocrDriver, ocrJourney);
		ocrDriver.setArea(area);
		Page<OcrDriver> page = ocrDriverService.findVOList2(new Page<OcrDriver>(request, response), ocrDriver, ocrJourney);
		model.addAttribute("page", page);
		return "modules/journey/ocrDesignateTask";
	}
	
	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = "appoint")
	public String appoint(OcrJourney ocrJourney, HttpServletRequest request, HttpServletResponse response, Model model) {
		Area area = areaService.get(ocrJourney.getAreaId());
		if(area!=null){
			ocrJourney.setAreaName(area.getName());
		}
		model.addAttribute("ocrJourney", ocrJourney);
		OcrDriver ocrDriver = ocrDriverService.getVO(ocrJourney.getDriverId(), ocrJourney);
		OcrVehicle ocrVehicle = new OcrVehicle();
		ocrVehicle.setVehicleType(ocrJourney.getVehicleType());
//		Page<OcrVehicle> page = ocrVehicleService.findVOList(new Page<OcrVehicle>(request, response), ocrVehicle, ocrJourney);
		ocrVehicle.setArea(area);
		Page<OcrVehicle> page = ocrVehicleService.findVOList2(new Page<OcrVehicle>(request, response), ocrVehicle, ocrJourney);
		model.addAttribute("ocrDriver", ocrDriver);
		model.addAttribute("page", page);
		return "modules/journey/ocrAppointVehicle";
	}
	
	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = "reList")
	public String reList(RedirectAttributes redirectAttributes, OcrJourney ocrJourney, String vehicleId) {
		// 更新订单表
		ocrJourney.setStatus("3");// 状态【0：审核中，1：已拒绝，2：已通过，3：待出发，4：行程中，5：待支付，6：已完成，7：已取消】
		ocrJourneyService.save(ocrJourney);
		// 保存任务表
		OcrTask ocrTask = new OcrTask();
		ocrTask.setOrderId(ocrJourney.getId());
		ocrTask = ocrTaskService.getByOrderId(ocrTask);
		if(ocrTask == null){
			ocrTask =  new OcrTask();
		}
		if (ocrJourney != null) {
			ocrTask.setOrderId(ocrJourney.getId());// 订单ID
			ocrTask.setDriverId(ocrJourney.getDriverId());// 司机ID
			ocrTask.setVehicleId(vehicleId);// 车辆ID
			ocrTask.setStatus("1");// 状态【0：待指派，1：待确认，2：已确认，3：被拒绝，4：行程中，5：已送达，6：已取消】
			ocrTaskService.save(ocrTask);
		}
		addMessage(redirectAttributes, "任务指派完成");
		String driverAcceptMessage = Global.getConfig("driver.accept.message");

		JPushDriverUtil.sentMessage(driverAcceptMessage, ocrTask.getId(), "101", ocrJourney.getDriverLoginName());
		return "redirect:"+Global.getAdminPath()+"/journey/ocrJourney/?repage";
	}

	@RequiresPermissions("journey:ocrJourney:view")
	@RequestMapping(value = "form")
	public String form(OcrJourney ocrJourney, Model model) {
		model.addAttribute("ocrJourney", ocrJourney);
		return "modules/journey/ocrJourneyForm";
	}

	@RequiresPermissions("journey:ocrJourney:edit")
	@RequestMapping(value = "save")
	public String save(OcrJourney ocrJourney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrJourney)){
			return form(ocrJourney, model);
		}
		ocrJourneyService.save(ocrJourney);
		addMessage(redirectAttributes, "保存行程成功");
		return "redirect:"+Global.getAdminPath()+"/journey/ocrJourney/?repage";
	}
	
	@RequiresPermissions("journey:ocrJourney:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrJourney ocrJourney, RedirectAttributes redirectAttributes) {
		ocrJourneyService.delete(ocrJourney);
		addMessage(redirectAttributes, "删除行程成功");
		return "redirect:"+Global.getAdminPath()+"/journey/ocrJourney/?repage";
	}

}