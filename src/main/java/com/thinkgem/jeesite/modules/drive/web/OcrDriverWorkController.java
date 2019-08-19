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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverWork;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverWorkService;

/**
 * 司机管理Controller
 * @author zr
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/work")
public class OcrDriverWorkController extends BaseController {

	@Autowired
	private OcrDriverWorkService ocrDriverWorkService;
	
	@RequiresPermissions("drive:work:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDriverWork ocrDriver, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OcrDriverWork> page = ocrDriverWorkService.findPage(new Page<OcrDriverWork>(request, response), ocrDriver); 
		model.addAttribute("page", page);
		return "modules/drive/ocrDriverWorkList";
	}

	/**
	 * 导出报名信息数据
	 * @param test
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("drive:work:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OcrDriverWork testMeet, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "考勤信息数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<OcrDriverWork> page = ocrDriverWorkService.findPage(new Page<OcrDriverWork>(request, response, -1), testMeet);
    		new ExportExcel("考勤信息数据", OcrDriverWork.class).setDataList(page.getList()).write(response, fileName).dispose();
//			System.out.println(666);
//			System.out.println(666);
//			System.out.println(666);
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出报名信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/drive/work/?repage";
    }
	
	@RequiresPermissions("drive:work:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDriverWork ocrDriver, RedirectAttributes redirectAttributes) {
		ocrDriverWorkService.delete(ocrDriver);
		addMessage(redirectAttributes, "删除司机成功");
		return "redirect:"+Global.getAdminPath()+"/drive/work/?repage";
	}

}