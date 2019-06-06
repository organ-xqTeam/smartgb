/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cost.dao.OcrCostDao;
import com.thinkgem.jeesite.modules.cost.entity.CompanyCostVO;
import com.thinkgem.jeesite.modules.cost.entity.OcrCost;
import com.thinkgem.jeesite.modules.cost.entity.OcrCostDetailedVO;

/**
 * 账务管理Service
 * @author zr
 * @version 2017-08-10
 */
@Service
@Transactional(readOnly = true)
public class OcrCostService extends CrudService<OcrCostDao, OcrCost> {
	
	/**
	 * cost表清账（clear_accounts_flag：0——>1）
	 * order表清账（status：5未支付——>6已完成）
	 * @param year
	 * @param month
	 * @param companyId
	 * @return
	 */
	@Transactional(readOnly = false)
	public void clearAccounts(OcrCostDetailedVO ocrCostDetailedVO){
		dao.clearAccounts(ocrCostDetailedVO);
		dao.clearAccountsOrder(ocrCostDetailedVO);
	}

	public Page<OcrCostDetailedVO> findCostList(Page<OcrCostDetailedVO> page, OcrCostDetailedVO ocrCostDetailedVO) {
		ocrCostDetailedVO.setPage(page);
		page.setList(dao.findCostList(ocrCostDetailedVO));
		return page;
	}
	
	public List<CompanyCostVO> companyCostList(CompanyCostVO companyCostVO){
		return dao.companyCostList(companyCostVO);
	}

	public OcrCost get(String id) {
		return super.get(id);
	}
	
	public List<OcrCost> findList(OcrCost ocrCost) {
		return super.findList(ocrCost);
	}
	
	public Page<OcrCost> findPage(Page<OcrCost> page, OcrCost ocrCost) {
		return super.findPage(page, ocrCost);
	}
	
	@Transactional(readOnly = false)
	public void save(OcrCost ocrCost) {
		super.save(ocrCost);
	}
	
	@Transactional(readOnly = false)
	public void delete(OcrCost ocrCost) {
		super.delete(ocrCost);
	}
	
}