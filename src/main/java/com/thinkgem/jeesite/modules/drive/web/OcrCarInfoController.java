/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrCarInfo;
import com.thinkgem.jeesite.modules.drive.service.OcrCarInfoService;

/**
 * 车辆信息管理Controller
 * @author ybc
 * @version 2018-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/carInfo")
public class OcrCarInfoController extends BaseController {

	@Autowired
	private OcrCarInfoService ocrCarInfoService;
	
	@RequiresPermissions("drive:carInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrCarInfo ocrCarInfo,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrCarInfo> page = ocrCarInfoService.findPage(new Page<OcrCarInfo>(request, response), ocrCarInfo); 
		model.addAttribute("page", page);
		return "modules/drive/ocrCarInfoList";
	}

	@RequiresPermissions("drive:carInfo:view")
	@RequestMapping(value = "form")
	public String form(OcrCarInfo ocrCarInfo, Model model) {
		model.addAttribute("ocrCarInfo", ocrCarInfo);
		return "modules/drive/ocrCarInfoForm";
	}

	@RequiresPermissions("drive:carInfo:edit")
	@RequestMapping(value = "save")
	public String save(OcrCarInfo ocrCarInfo, Model model, RedirectAttributes redirectAttributes) {
		ocrCarInfoService.save(ocrCarInfo);
		addMessage(redirectAttributes, "保存车辆信息成功");
		return "redirect:"+Global.getAdminPath()+"/drive/carInfo/list?repage";
	}
	
	@RequiresPermissions("drive:carInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrCarInfo ocrCarInfo, RedirectAttributes redirectAttributes) {
		ocrCarInfoService.delete(ocrCarInfo);
		addMessage(redirectAttributes, "删除车辆信息成功");
		return "redirect:"+Global.getAdminPath()+"/drive/carInfo/list?repage";
	}

}