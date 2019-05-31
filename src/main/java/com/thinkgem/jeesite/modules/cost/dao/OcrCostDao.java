/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cost.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cost.entity.OcrCost;
import com.thinkgem.jeesite.modules.cost.entity.OcrCostDetailedVO;
import com.thinkgem.jeesite.modules.cost.entity.CompanyCostVO;

/**
 * 账务管理DAO接口
 * @author zr
 * @version 2017-08-10
 */
@MyBatisDao
public interface OcrCostDao extends CrudDao<OcrCost> {
	/**
	 * 获取账务公司列表
	 * @param companyCostVO
	 * @return
	 */
	public List<CompanyCostVO> companyCostList(CompanyCostVO companyCostVO);
	/**
	 * 获取账务明细（每月）
	 * @param ocrCostDetailedVO
	 * @return
	 */
	public List<OcrCostDetailedVO> findCostList(OcrCostDetailedVO ocrCostDetailedVO);
	/**
	 * cost表清账（clear_accounts_flag：0——>1）
	 * @param year
	 * @param month
	 * @param companyId
	 * @return
	 */
	int clearAccounts(OcrCostDetailedVO ocrCostDetailedVO);
	/**
	 * order表清账（status：5未支付——>6已完成）
	 * @param year
	 * @param month
	 * @param companyId
	 * @return
	 */
	int clearAccountsOrder(OcrCostDetailedVO ocrCostDetailedVO);
}