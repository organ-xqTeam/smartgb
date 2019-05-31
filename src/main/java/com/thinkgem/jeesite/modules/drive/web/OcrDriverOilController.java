/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drive.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.drive.entity.OcrDriverRecord;
import com.thinkgem.jeesite.modules.drive.service.OcrDriverRecordService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 车辆油耗Controller
 * 
 * @author ybc
 * @version 2018-06-10
 */
@Controller
@RequestMapping(value = "${adminPath}/drive/oil")
public class OcrDriverOilController extends BaseController {

	private static ObjectMapper jsonMapper = new ObjectMapper();
	@Autowired
	private OcrDriverRecordService ocrDriverRecordService;

	@RequestMapping(value = { "list", "" })
	public String list(HttpServletRequest request, Model model) throws ParseException, JsonProcessingException {
		return "modules/drive/ocrCarOilList";
	}
	@ResponseBody
	@RequestMapping(value = { "listData", "" })
	public String listData(HttpServletRequest request) throws ParseException, JsonProcessingException {
		OcrDriverRecord record = new OcrDriverRecord();
		List<OcrDriverRecord> recordList = null;
		if (null != request.getParameter("startDate")) {
			record.setRecordDateStar(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
			if ("".equals(request.getParameter("endDate"))) {
				Calendar c = Calendar.getInstance();
				c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
				c.add(Calendar.DAY_OF_MONTH, 1);
				record.setRecordDateEnd(c.getTime());
			} else {
				record.setRecordDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate")));
			}
			recordList = ocrDriverRecordService.findList(record);
		}
		JSONArray linecontent = new JSONArray(); //车牌号
		JSONArray linecontent1 = new JSONArray();// 油耗
		JSONArray linecontentcolor = new JSONArray(); //颜色
		double num = 0.0;
		JSONObject jsonObject = new JSONObject();
		String totalnum = "";
		if (recordList == null) {
			jsonObject.put("status", false);
			return jsonObject.toString();
		}
		for (int i = 0; i < recordList.size(); i++) {
			OcrDriverRecord ocrDriverRecord = recordList.get(i);
			linecontent.add(ocrDriverRecord.getPlateNum());
			linecontent1.add(ocrDriverRecord.getOilNum());
			linecontentcolor.add("rgb("+Math.floor(Math.random()*256)+","+Math.floor(Math.random()*256)+","+Math.floor(Math.random()*256)+")");
			double a;
			try {
				a = Double.parseDouble(ocrDriverRecord.getOilNum());
			} catch (NumberFormatException e) {
				a = 0.0;
			}
			num +=a;
		}
		totalnum = num + "";
		jsonObject.put("status", true);
		jsonObject.put("linecontent", linecontent);
		jsonObject.put("linecontent1", linecontent1);
		jsonObject.put("linecontentcolor", linecontentcolor);
		jsonObject.put("totalnum", totalnum);
		return jsonObject.toString();
	}

}