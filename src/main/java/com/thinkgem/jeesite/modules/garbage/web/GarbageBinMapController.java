package com.thinkgem.jeesite.modules.garbage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverTravel;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverTravelService;
import com.thinkgem.jeesite.modules.garbage.entity.CarbinInfo;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinInfo;
import com.thinkgem.jeesite.modules.garbage.service.CarbinInfoService;
import com.thinkgem.jeesite.modules.garbage.service.GarbageBinInfoService;

/**
 * 垃圾桶地图Controller
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/garbagebin/gbMap")
public class GarbageBinMapController extends BaseController {

	@Autowired
	private GarbageBinInfoService gbInfoService;
	
	@Autowired
	private OcrDriverTravelService ocrDriverTravelService;
	
	@RequiresPermissions("gb:map:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		return "modules/garbage/garbageMap";
	}
	
	@ResponseBody
	@RequiresPermissions("gb:map:view")
	@RequestMapping(value="getData")
	public Map<String, Object> getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper= new ObjectMapper();
		List<GarbageBinInfo> allGbInfo = gbInfoService.findListAll();
		List<OcrDriverTravel> allCarInfo = ocrDriverTravelService.getTravelList();
		map.put("allGbInfo", allGbInfo);
		map.put("allCarInfo", allCarInfo);
		return map;
	}
	
}