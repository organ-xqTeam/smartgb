/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cost.entity.CompanyCostVO;
import com.thinkgem.jeesite.modules.cost.entity.OcrCost;
import com.thinkgem.jeesite.modules.cost.entity.OcrCostDetailedVO;
import com.thinkgem.jeesite.modules.cost.service.OcrCostService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 账务管理Controller
 * @author zr
 * @version 2017-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/cost/ocrCost")
public class OcrCostController extends BaseController {

	@Autowired
	private OcrCostService ocrCostService;
	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public OcrCost get(@RequestParam(required=false) String id) {
		OcrCost entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ocrCostService.get(id);
		}
		if (entity == null){
			entity = new OcrCost();
		}
		return entity;
	}
	/**
	 * 账务明细
	 * @param ocrCostDetailedVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cost:ocrCost:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrCostDetailedVO ocrCostDetailedVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		/*if (StringUtils.isBlank(ocrCostDetailedVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrCostDetailedVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}*/
		Page<OcrCostDetailedVO> page1 = ocrCostService.findCostList(new Page<OcrCostDetailedVO>(request, response),ocrCostDetailedVO); 

		model.addAttribute("page", page1);
		model.addAttribute("companyName", companyName);
		model.addAttribute("companyId", ocrCostDetailedVO.getCompanyId());
		return "modules/cost/ocrCostList";
	}
	
	/**
	 * 账务明细(单位)
	 * @param ocrCostDetailedVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cost:ocrCost:view")
	@RequestMapping(value = {"companyCostList"})
	public String companyCostList(OcrCostDetailedVO ocrCostDetailedVO, HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		if (StringUtils.isBlank(ocrCostDetailedVO.getCompanyId())) {
			User user = UserUtils.getUser();
			ocrCostDetailedVO.setCompanyId(user.getCompany().getId());
			companyName=user.getCompany().getName();
		}
		Page<OcrCostDetailedVO> page1 = ocrCostService.findCostList(new Page<OcrCostDetailedVO>(request, response),ocrCostDetailedVO); 

		model.addAttribute("page", page1);
		model.addAttribute("companyName", companyName);
		model.addAttribute("companyId", ocrCostDetailedVO.getCompanyId());
		return "modules/cost/companyCostList";
	}
	/**
	 * 账务管理
	 * @param office
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cost:ocrCost:view")
	@RequestMapping(value = {"officeList"})
	public String officeList(Office	office, Model model) {
		CompanyCostVO companyCostVO = new CompanyCostVO();
		companyCostVO.setName(office.getName());
		List<CompanyCostVO> list = ocrCostService.companyCostList(companyCostVO);
		model.addAttribute("list", list);
		return "modules/cost/officeList";
	}

	@RequiresPermissions("cost:ocrCost:view")
	@RequestMapping(value = "form")
	public String form(OcrCost ocrCost, Model model) {
		model.addAttribute("ocrCost", ocrCost);
		return "modules/cost/ocrCostForm";
	}

	@RequiresPermissions("cost:ocrCost:edit")
	@RequestMapping(value = "save")
	public String save(OcrCost ocrCost, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ocrCost)){
			return form(ocrCost, model);
		}
		ocrCostService.save(ocrCost);
		addMessage(redirectAttributes, "保存账务成功");
		return "redirect:"+Global.getAdminPath()+"/cost/ocrCost/?repage";
	}
	
	@RequiresPermissions("cost:ocrCost:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrCost ocrCost, RedirectAttributes redirectAttributes) {
		ocrCostService.delete(ocrCost);
		addMessage(redirectAttributes, "删除账务成功");
		return "redirect:"+Global.getAdminPath()+"/cost/ocrCost/?repage";
	}
	@RequiresPermissions("cost:ocrCost:edit")
	@RequestMapping(value = "clear")
	public String clearAccounts(OcrCostDetailedVO ocrCostDetailedVO, RedirectAttributes redirectAttributes,
			 HttpServletRequest request, HttpServletResponse response, Model model,String companyName) {
		ocrCostService.clearAccounts(ocrCostDetailedVO);
		addMessage(redirectAttributes, "清账成功");
		Page<OcrCostDetailedVO> page1 = ocrCostService.findCostList(new Page<OcrCostDetailedVO>(request, response),ocrCostDetailedVO); 

		model.addAttribute("page", page1);
		model.addAttribute("companyName", companyName);
		model.addAttribute("companyId", ocrCostDetailedVO.getCompanyId());
		return "modules/cost/ocrCostList";
	}

}