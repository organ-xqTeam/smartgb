package com.thinkgem.jeesite.modules.obd.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.obd.entity.MessageObd;
import com.thinkgem.jeesite.modules.obd.entity.OilMileageQueryVO;
@MyBatisDao
public interface MessageObdDao {

	List<MessageObd> getObdByCondition(MessageObd queryObd);
	
	List<MessageObd> findObdItemById(String id);
	List<MessageObd> findObdOilList(@Param("carId")String carId,@Param("startTime")Date startTime,@Param("endTime")Date endTime);
	
	
	/**
	 * 查询月里程
	 * @param id
	 * @param year
	 * @return
	 */
	List<OilMileageQueryVO> monthMileageList (@Param("id")String id,@Param("year")String year);
}
