/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.brand.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.brand.entity.OcrBrand;
import com.thinkgem.jeesite.modules.brand.dao.OcrBrandDao;

/**
 * 车辆厂牌Service
 * @author zr
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class OcrBrandService extends CrudService<OcrBrandDao, OcrBrand> {

	public OcrBrand get(String id) {
		return super.get(id);
	}
	
	public List<OcrBrand> findList(OcrBrand ocrBrand) {
		return super.findList(ocrBrand);
	}
	
	public Page<OcrBrand> findPage(Page<OcrBrand> page, OcrBrand ocrBrand) {
		return super.findPage(page, ocrBrand);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrBrand ocrBrand) {
		super.save(ocrBrand);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrBrand ocrBrand) {
		super.delete(ocrBrand);
	}
	
}