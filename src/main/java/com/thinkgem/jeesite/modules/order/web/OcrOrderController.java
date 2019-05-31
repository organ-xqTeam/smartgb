/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.web;

import java.math.BigDecimal;
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

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CoordinationConvertUtil.LatLng;
import com.thinkgem.jeesite.common.utils.JPushUserUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.car.entity.OcrVehicle;
import com.thinkgem.jeesite.modules.car.service.OcrVehicleService;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.obd.entity.OilMileageQueryVO;
import com.thinkgem.jeesite.modules.obd.service.MessageGpsService;
import com.thinkgem.jeesite.modules.ocruser.entity.OcrUser;
import com.thinkgem.jeesite.modules.ocruser.service.OcrUserService;
import com.thinkgem.jeesite.modules.order.entity.OcrOrder;
import com.thinkgem.jeesite.modules.order.entity.OcrOrderVO;
import com.thinkgem.jeesite.modules.order.service.OcrOrderService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 订单管理Controller
 * @author zr
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/order/ocrOrder")
public class OcrOrderController extends BaseController {

	@Autowired
	private OcrOrderService ocrOrderService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private OcrUserService ocrUserService;
	@Autowired
	private MessageGpsService messageGpsService;
	@Autowired
	private OcrVehicleService ocrVehicleService;
	
	
	@ModelAttribute
	public OcrOrder get(@RequestParam(required=false) String id) {
		OcrOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrOrderService.get(id);
		}
		if (entity == null){
			entity = new OcrOrder();
		}
		return entity;
	}
	/**
	 * 订单管理（平台）
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = {"officeList"})
	public String list(Office office, Model model) {
		// 添加状态值 by gaoly begin 2017-08-02
		List<Office> officeList = officeService.findList(office);
		if (officeList != null) {
			for (Office officeObject : officeList) {
				if ("1".equals(officeObject.getUseable())) {
					officeObject.setUseable("正常服务");
				} else if ("0".equals(officeObject.getUseable())) {
					officeObject.setUseable("暂停服务");
				}
			}
		}
		model.addAttribute("list", officeList);
		// 添加状态值 by gaoly end 2017-08-02
		return "modules/order/officeList";
	}
	/**
	 * 订单管理
	 * @param ocrOrderVO
	 * @param request
	 * @param response
	 * @param model
	 * @param companyName
	 * @return
	 */
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrOrderVO ocrOrderVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		if (StringUtils.isBlank(ocrOrderVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrOrderVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}
		Page<OcrOrderVO> VOpage = ocrOrderService.findVOPage(new Page<OcrOrderVO>(request, response), ocrOrderVO); 
		//Page<OcrOrder> page = ocrOrderService.findPage(new Page<OcrOrder>(request, response), ocrOrder); 
		model.addAttribute("page", VOpage);
		model.addAttribute("companyName", companyName);
		return "modules/order/ocrOrderList";
	}
	
	/**
	 * 订单管理(单位)
	 * @param ocrOrderVO
	 * @param request
	 * @param response
	 * @param model
	 * @param companyName
	 * @return
	 */
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = "listForCompany")
	public String listForCompany(OcrOrderVO ocrOrderVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		if (StringUtils.isBlank(ocrOrderVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrOrderVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}
		Page<OcrOrderVO> VOpage = ocrOrderService.findVOPageForCompany(new Page<OcrOrderVO>(request, response), ocrOrderVO); 
		//Page<OcrOrder> page = ocrOrderService.findPage(new Page<OcrOrder>(request, response), ocrOrder); 
		model.addAttribute("page", VOpage);
		model.addAttribute("companyName", companyName);
		return "modules/order/ocrOrderListForCompany";
	}
	/**
	 * 订单审核页面
	 * @param ocrOrderVO
	 * @param request
	 * @param response
	 * @param model
	 * @param companyName
	 * @return
	 */
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = "ocrOrderApprove")
	public String ocrOrderApprove(OcrOrderVO ocrOrderVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		if (StringUtils.isBlank(ocrOrderVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrOrderVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}
		Page<OcrOrderVO> VOpage = ocrOrderService.findVOListForApprove(new Page<OcrOrderVO>(request, response), ocrOrderVO); 
		//Page<OcrOrder> page = ocrOrderService.findPage(new Page<OcrOrder>(request, response), ocrOrder); 
		model.addAttribute("page", VOpage);
		model.addAttribute("companyName", companyName);
		return "modules/order/ocrOrderApprove";
	}
	/**
	 * 订单审核
	 * @param ocrOrderVO
	 * @param request
	 * @param response
	 * @param model
	 * @param companyName
	 * @param ApproveStatus 1拒绝,2通过
	 * @return
	 */
	@RequiresPermissions("order:ocrOrder:edit")
	@RequestMapping(value = "adoptOrrefuse")
	public String adoptOrrefuse(OcrOrderVO ocrOrderVO, HttpServletRequest request, HttpServletResponse response,
			Model model,String companyName,String ApproveStatus) {
			OcrOrder order = ocrOrderService.get(ocrOrderVO.getId());
			OcrUser ocrUser  = ocrUserService.get(order.getUserId());
			order.setStatus(ApproveStatus);
			ocrOrderService.save(order);
			if ("8".equals(ApproveStatus)) {
				String companyAdoptMessage = Global.getConfig("company.refuse.message");
				JPushUserUtil.sentMessage(companyAdoptMessage, ocrOrderVO.getId(), "101", ocrOrderVO.getUser().getLoginName());
				addMessage(model, "申请被拒绝");
			} else if("2".equals(ApproveStatus)){
				String companyAdoptMessage = Global.getConfig("company.adopt.message");
				JPushUserUtil.sentMessage(companyAdoptMessage, ocrOrderVO.getId(), "101", ocrOrderVO.getUser().getLoginName());
				addMessage(model, "已通过审核");
			}
		if (StringUtils.isBlank(ocrOrderVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrOrderVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}
		Page<OcrOrderVO> VOpage = ocrOrderService.findVOPageForCompany(new Page<OcrOrderVO>(request, response), ocrOrderVO); 
		//Page<OcrOrder> page = ocrOrderService.findPage(new Page<OcrOrder>(request, response), ocrOrder); 
		model.addAttribute("page", VOpage);
		model.addAttribute("companyName", companyName);
		return "modules/order/ocrOrderApprove";
	}
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = "form")
	public String form(OcrOrder ocrOrder, Model model) {
		model.addAttribute("ocrOrder", ocrOrder);
		return "modules/order/ocrOrderForm";
	}

	@RequiresPermissions("order:ocrOrder:edit")
	@RequestMapping(value = "save")
	public String save(OcrOrder ocrOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrOrder)){
			return form(ocrOrder, model);
		}
		ocrOrderService.save(ocrOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/ocrOrder/?repage";
	}
	
	@RequiresPermissions("order:ocrOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrOrder ocrOrder, RedirectAttributes redirectAttributes) {
		ocrOrderService.delete(ocrOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/ocrOrder/?repage";
	}
	/*
	 * 订单管理平台行驶数据
	 */
	@RequiresPermissions("order:ocrOrder:view")
	@RequestMapping(value = "orderDetailMap")
	public String orderItemMap(OcrOrderVO ocrOrderVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		OcrOrderVO orderVO = ocrOrderService.findVoById(ocrOrderVO);
		List<LatLng> list = messageGpsService.getListByCarIdAndDate(orderVO.getVehicle().getEquipmentImei(),orderVO.getRealDepartureDate(),orderVO.getRealArrivalDate());
		List<MessageObd>  messageObds = ocrOrderService.findObdOilList(orderVO.getVehicle().getEquipmentImei(),orderVO.getRealDepartureDate(),orderVO.getRealArrivalDate());
		OcrVehicle OcrVehicle = ocrVehicleService.fingTankType(orderVO.getVehicle().getId());
		if (messageObds.size() > 1) {
			Double orderOil = messageObds.get(0).getValue()-messageObds.get(messageObds.size()-1).getValue();
			if ("1".equals(OcrVehicle.getTankType())){
				BigDecimal b = new BigDecimal (orderOil*(Double.parseDouble(OcrVehicle.getTankVolume()))/100);
				orderOil = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
			}
			orderVO.setOrderOil(orderOil);			
		}else{
			orderVO.setOrderOil(0d);			
		}
		
		model.addAttribute("ocrOrder", orderVO);
		//获得最新位置数据
		Gson gson = new Gson();
		if (list.size()>0) {
			model.addAttribute("nowPosition", list.get(list.size()-1));
			model.addAttribute("startPosition", list.get(0));
			model.addAttribute("allPosition",gson.toJson(list));			
		}
		return "modules/order/orderDetailMap";
	}

}