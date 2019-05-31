/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.document.web;

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
import com.thinkgem.jeesite.modules.document.entity.OcrDocument;
import com.thinkgem.jeesite.modules.document.service.OcrDocumentService;

/**
 * 文档设置Controller
 * @author gaoly
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/document/ocrDocument")
public class OcrDocumentController extends BaseController {

	@Autowired
	private OcrDocumentService ocrDocumentService;
	
	@ModelAttribute
	public OcrDocument get(@RequestParam(required=false) String id) {
		OcrDocument entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrDocumentService.get(id);
		}
		if (entity == null){
			entity = new OcrDocument();
		}
		return entity;
	}
	
	@RequiresPermissions("document:ocrDocument:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDocument ocrDocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrDocument> page = ocrDocumentService.findPage(new Page<OcrDocument>(request, response), ocrDocument); 
		model.addAttribute("page", page);
		return "modules/document/ocrDocumentList";
	}

	@RequiresPermissions("document:ocrDocument:view")
	@RequestMapping(value = "form")
	public String form(OcrDocument ocrDocument, Model model) {
		model.addAttribute("ocrDocument", ocrDocument);
		return "modules/document/ocrDocumentForm";
	}

	@RequiresPermissions("document:ocrDocument:edit")
	@RequestMapping(value = "save")
	public String save(OcrDocument ocrDocument, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrDocument)){
			return form(ocrDocument, model);
		}
		ocrDocumentService.save(ocrDocument);
		addMessage(redirectAttributes, "保存文档成功");
		return "redirect:"+Global.getAdminPath()+"/document/ocrDocument/?repage";
	}
	
	@RequiresPermissions("document:ocrDocument:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDocument ocrDocument, RedirectAttributes redirectAttributes) {
		ocrDocumentService.delete(ocrDocument);
		addMessage(redirectAttributes, "删除文档成功");
		return "redirect:"+Global.getAdminPath()+"/document/ocrDocument/?repage";
	}

}