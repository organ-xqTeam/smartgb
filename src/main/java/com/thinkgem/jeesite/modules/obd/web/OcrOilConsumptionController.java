/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.obd.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.obd.entity.OcrOilConsumption;
import com.thinkgem.jeesite.modules.obd.service.OcrOilConsumptionService;

/**
 * 油量消耗Controller
 * @author tf
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/odb/ocrOilConsumption")
public class OcrOilConsumptionController extends BaseController {

	@Autowired
	private OcrOilConsumptionService ocrOilConsumptionService;
	
	@ModelAttribute
	public OcrOilConsumption get(@RequestParam(required=false) String id) {
		OcrOilConsumption entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrOilConsumptionService.get(id);
		}
		if (entity == null){
			entity = new OcrOilConsumption();
		}
		return entity;
	}
	
	@RequiresPermissions("odb:ocrOilConsumption:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrOilConsumption ocrOilConsumption, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrOilConsumption> page = ocrOilConsumptionService.findPage(new Page<OcrOilConsumption>(request, response), ocrOilConsumption); 
		model.addAttribute("page", page);
		return "modules/odb/ocrOilConsumptionList";
	}

	@RequiresPermissions("odb:ocrOilConsumption:view")
	@RequestMapping(value = "form")
	public String form(OcrOilConsumption ocrOilConsumption, Model model) {
		model.addAttribute("ocrOilConsumption", ocrOilConsumption);
		return "modules/odb/ocrOilConsumptionForm";
	}

	@RequiresPermissions("odb:ocrOilConsumption:edit")
	@RequestMapping(value = "save")
	public String save(OcrOilConsumption ocrOilConsumption, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrOilConsumption)){
			return form(ocrOilConsumption, model);
		}
		ocrOilConsumptionService.save(ocrOilConsumption);
		addMessage(redirectAttributes, "保存保存油量消耗成功成功");
		return "redirect:"+Global.getAdminPath()+"/odb/ocrOilConsumption/?repage";
	}
	
	@RequiresPermissions("odb:ocrOilConsumption:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrOilConsumption ocrOilConsumption, RedirectAttributes redirectAttributes) {
		ocrOilConsumptionService.delete(ocrOilConsumption);
		addMessage(redirectAttributes, "删除保存油量消耗成功成功");
		return "redirect:"+Global.getAdminPath()+"/odb/ocrOilConsumption/?repage";
	}

}