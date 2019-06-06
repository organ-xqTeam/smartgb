/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.OcrDestinationArea;
import com.thinkgem.jeesite.modules.sys.service.OcrDestinationAreaService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/ocrDestinationArea")
public class OcrDestinationAreaController extends BaseController {

	@Autowired
	private OcrDestinationAreaService areaService;
	
	@ModelAttribute("area")
	public OcrDestinationArea get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return areaService.get(id);
		}else{
			return new OcrDestinationArea();
		}
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(OcrDestinationArea area, Model model) {
		model.addAttribute("list", areaService.findAll());
		return "modules/sys/ocrDestinationAreaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(OcrDestinationArea ocrDestinationArea, Model model) {
		if (null != ocrDestinationArea.getId()) {
			ocrDestinationArea = areaService.get(ocrDestinationArea.getId());
		}
		if (ocrDestinationArea.getParent()==null||ocrDestinationArea.getParent().getId()==null){
			OcrDestinationArea destinationArea = new OcrDestinationArea();
			destinationArea.setId("1");
			ocrDestinationArea.setParent(destinationArea);
		}
		ocrDestinationArea.setParent(areaService.get(ocrDestinationArea.getParent().getId()));
//		// 自动获取排序号
//		if (StringUtils.isBlank(area.getId())){
//			int size = 0;
//			List<Area> list = areaService.findAll();
//			for (int i=0; i<list.size(); i++){
//				Area e = list.get(i);
//				if (e.getParent()!=null && e.getParent().getId()!=null
//						&& e.getParent().getId().equals(area.getParent().getId())){
//					size++;
//				}
//			}
//			area.setCode(area.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size : 1), 4, "0"));
//		}
		model.addAttribute("area", ocrDestinationArea);
		return "modules/sys/ocrDestinationAreaForm";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "save")
	public String save(OcrDestinationArea area, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/ocrDestinationArea";
		}
		if (!beanValidator(model, area)){
			return form(area, model);
		}
		areaService.save(area);
		addMessage(redirectAttributes, "保存区域'" + area.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/ocrDestinationArea/";
	}
	
	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(OcrDestinationArea area, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/ocrDestinationArea";
		}
		if("1".equals(area.getId())){
			addMessage(redirectAttributes, "请勿删除总区域中国");
			return "redirect:" + adminPath + "/sys/ocrDestinationArea";
		}
//		if (Area.isRoot(id)){
//			addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域或编号为空");
//		}else{
			areaService.delete(area);
			addMessage(redirectAttributes, "删除区域成功");
//		}
		return "redirect:" + adminPath + "/sys/ocrDestinationArea/";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<OcrDestinationArea> list = areaService.findAll();
		for (int i=0; i<list.size(); i++){
			OcrDestinationArea e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
//@RequiresPermissions("user")
@ResponseBody
@RequestMapping(value = "treeDataForDirverAndVehicle")
public List<Map<String, Object>> treeDataForDirverAndVehicle(@RequestParam(required=false) String extId, HttpServletResponse response) {
	List<Map<String, Object>> mapList = Lists.newArrayList();
	List<OcrDestinationArea> list = areaService.findAll();
	for (int i=0; i<list.size(); i++){
		OcrDestinationArea e = list.get(i);
		if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
			if ("3".equals(e.getType())) {		
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getName());
			mapList.add(map);
			}
		}
	}
	return mapList;
}

}