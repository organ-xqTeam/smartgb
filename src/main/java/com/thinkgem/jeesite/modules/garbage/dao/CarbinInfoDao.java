package com.thinkgem.jeesite.modules.garbage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.garbage.entity.CarbinInfo;
import com.thinkgem.jeesite.modules.garbage.entity.GarbageBinClean;

@MyBatisDao
public interface CarbinInfoDao extends CrudDao<CarbinInfo> {

}
