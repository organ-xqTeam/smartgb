/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.OcrDestinationAreaDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.OcrDestinationArea;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OcrDestinationAreaService extends TreeService<OcrDestinationAreaDao, OcrDestinationArea> {

	public List<OcrDestinationArea> findAll(){
		return dao.findAllList(new OcrDestinationArea());
	}

	@Transactional(readOnly = false)
	public void save(OcrDestinationArea ocrDestinationArea) {
		super.save(ocrDestinationArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrDestinationArea ocrDestinationArea) {
		super.delete(ocrDestinationArea);
	}
	
}
