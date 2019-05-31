/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.MD5Util;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriver;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverService;

/**
 * 司机管理Controller
 * @author zr
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/ocrDriver")
public class OcrDriverController extends BaseController {

	@Autowired
	private OcrDriverService ocrDriverService;
	
	@ModelAttribute
	public OcrDriver get(@RequestParam(required=false) String id) {
		OcrDriver entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrDriverService.get(id);
		}
		if (entity == null){
			entity = new OcrDriver();
		}
		return entity;
	}
	
	@RequiresPermissions("drive:ocrDriver:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDriver ocrDriver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrDriver> page = ocrDriverService.findPage(new Page<OcrDriver>(request, response), ocrDriver); 
		model.addAttribute("page", page);
		return "modules/drive/ocrDriverList";
	}

	@RequiresPermissions("drive:ocrDriver:view")
	@RequestMapping(value = "form")
	public String form(OcrDriver ocrDriver, Model model) {
		model.addAttribute("ocrDriver", ocrDriver);
		return "modules/drive/ocrDriverForm";
	}

	@RequiresPermissions("drive:ocrDriver:edit")
	@RequestMapping(value = "save")
	public String save(OcrDriver ocrDriver, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrDriver)){
			return form(ocrDriver, model);
		}
		if (ocrDriver.getIsNewRecord()) {
			OcrDriver loginNameocrDriver = new OcrDriver();
			loginNameocrDriver.setLoginName(ocrDriver.getLoginName());
			List<OcrDriver> loginNameList = ocrDriverService.findList(loginNameocrDriver);
			if (loginNameList.size()>0) {
				addMessage(model, "用户名已存在");
				model.addAttribute("ocrDriver", ocrDriver);
				return "modules/drive/ocrDriverForm";
				}
			ocrDriver.setStatus("0");
			String jobNumber = "DR";
			if ("1".equals(ocrDriver.getSex())) {
				jobNumber=jobNumber+"M";
			}else {
				jobNumber=jobNumber+"F";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss0SSS");//设置日期格式
			String date = df.format(new Date());
			jobNumber=jobNumber+date;
			ocrDriver.setJobNumber(jobNumber);
		}
			if (StringUtils.isNotBlank(ocrDriver.getNewPassword())) {
				ocrDriver.setPassword(MD5Util.string2MD5(ocrDriver.getNewPassword()));
			}
		ocrDriverService.save(ocrDriver);
		addMessage(redirectAttributes, "保存司机成功");
		return "redirect:"+Global.getAdminPath()+"/drive/ocrDriver/?repage";
	}
	
	@RequiresPermissions("drive:ocrDriver:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDriver ocrDriver, RedirectAttributes redirectAttributes) {
		ocrDriverService.delete(ocrDriver);
		addMessage(redirectAttributes, "删除司机成功");
		return "redirect:"+Global.getAdminPath()+"/drive/ocrDriver/?repage";
	}
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("drive:ocrDriver:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String loginName) {
		OcrDriver loginNameocrDriver = new OcrDriver();
		loginNameocrDriver.setLoginName(loginName);
		List<OcrDriver> loginNameList = ocrDriverService.findList(loginNameocrDriver);
		if (loginNameList.size()<=0) {
			return "true";			
			}
		return "false";
	}

}