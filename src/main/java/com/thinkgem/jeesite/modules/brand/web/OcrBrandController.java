/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brand.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.brand.entity.OcrBrand;
import com.thinkgem.jeesite.modules.brand.service.OcrBrandService;

/**
 * 车辆厂牌Controller
 * @author zr
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/brand/ocrBrand")
public class OcrBrandController extends BaseController {

	@Autowired
	private OcrBrandService ocrBrandService;
	
	@ModelAttribute
	public OcrBrand get(@RequestParam(required=false) String id) {
		OcrBrand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrBrandService.get(id);
		}
		if (entity == null){
			entity = new OcrBrand();
		}
		return entity;
	}
	
	@RequiresPermissions("brand:ocrBrand:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrBrand ocrBrand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrBrand> page = ocrBrandService.findPage(new Page<OcrBrand>(request, response), ocrBrand); 
		model.addAttribute("page", page);
		return "modules/brand/ocrBrandList";
	}

	@RequiresPermissions("brand:ocrBrand:view")
	@RequestMapping(value = "form")
	public String form(OcrBrand ocrBrand, Model model) {
		model.addAttribute("ocrBrand", ocrBrand);
		return "modules/brand/ocrBrandForm";
	}

	@RequiresPermissions("brand:ocrBrand:edit")
	@RequestMapping(value = "save")
	public String save(OcrBrand ocrBrand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrBrand)){
			return form(ocrBrand, model);
		}
		ocrBrandService.save(ocrBrand);
		addMessage(redirectAttributes, "保存车辆厂牌成功");
		return "redirect:"+Global.getAdminPath()+"/brand/ocrBrand/?repage";
	}
	
	@RequiresPermissions("brand:ocrBrand:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrBrand ocrBrand, RedirectAttributes redirectAttributes) {
		ocrBrandService.delete(ocrBrand);
		addMessage(redirectAttributes, "删除车辆厂牌成功");
		return "redirect:"+Global.getAdminPath()+"/brand/ocrBrand/?repage";
	}

}