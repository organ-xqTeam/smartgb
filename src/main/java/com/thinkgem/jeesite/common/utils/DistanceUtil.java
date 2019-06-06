package com.thinkgem.jeesite.common.utils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;


public class DistanceUtil {

	public static Random random = new Random();

	// 地球半径为6371公里
	private static double RADIUS = 6371;

	/**
	 * 创建32位UUID
	 * 
	 * @return
	 */
	public static String generateToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 创建6位数字验证码
	 * 
	 * @return
	 */
	public static String generateVerificationCode() {
		return StringUtils.leftPad("" + random.nextInt(999999), 6, '0');
	}

	/**
	 * 以传入的经纬度坐标为中心，distance(公里）为半径的，经纬度范围
	 * 
	 * @param lat
	 * @param lon
	 * @param distance
	 *            半径(公里)
	 * @param radius
	 *            地球半径，默认为6371公里
	 * @return
	 */
	public static double[] boundingCoordinates(double lat, double lon,
			double distance) {
		lat = lat * Math.PI / 180;
		lon = lon * Math.PI / 180; // 先换算成弧度
		/* double[] result =null; */
		double rad_dist = distance / RADIUS; // 计算X公里在地球圆周上的弧度
		double lat_min = lat - rad_dist;
		double lat_max = lat + rad_dist; // 计算纬度范围

		double lon_min, lon_max;
		// 因为纬度在-90度到90度之间，如果超过这个范围，按情况进行赋值
		if (lat_min > -Math.PI / 2 && lat_max < Math.PI / 2) {
			// 开始计算经度范围
			double lon_t = Math.asin(Math.sin(rad_dist) / Math.cos(lat));
			lon_min = lon - lon_t;
			// 同理，经度的范围在-180度到180度之间
			if (lon_min < -Math.PI)
				lon_min += 2 * Math.PI;
			lon_max = lon + lon_t;
			if (lon_max > Math.PI)
				lon_max -= 2 * Math.PI;
		} else {
			lat_min = Math.max(lat_min, -Math.PI / 2);
			lat_max = Math.min(lat_max, Math.PI / 2);
			lon_min = -Math.PI;
			lon_max = Math.PI;
		}
		// 最后置换成角度进行输出
		lat_min = lat_min * 180 / Math.PI;
		lat_max = lat_max * 180 / Math.PI;
		lon_min = lon_min * 180 / Math.PI;
		lon_max = lon_max * 180 / Math.PI;
		double[] result = { lat_min, lat_max, lon_min, lon_max };
		return result;
	}

	/**
	 * 根据两点经纬度坐标获得距离，单位：米
	 * 
	 * @param lat_a
	 * @param lon_a
	 * @param lat_b
	 * @param lon_b
	 * @return
	 */
	public static double getDistanceOfMeter(double lat_a, double lon_a,
			double lat_b, double lon_b) {
		lat_a = lat_a * Math.PI / 180;
		lon_a = lon_a * Math.PI / 180;
		lat_b = lat_b * Math.PI / 180;
		lon_b = lon_b * Math.PI / 180;

		return Math.acos(Math.sin(lat_a) * Math.sin(lat_b) + Math.cos(lat_a)
				* Math.cos(lat_b) * Math.cos(lon_a - lon_b)) * 6371;
	}

	/**
	 * 根据经纬度范围，返回经纬度坐标
	 * 
	 * @param minLontitude
	 * @param maxLontitude
	 * @param minLatitude
	 * @param maxLatitude
	 * @return
	 */
	public static double[] randomLonLat(double min1, double max1, double min2,
			double max2) {
		BigDecimal db = new BigDecimal(Math.random() * (max1 - min1) + min1);
		String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
		db = new BigDecimal(Math.random() * (max2 - min2) + min2);
		String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
		// return lon+" "+lat;
		double[] result = { Double.valueOf(lon), Double.valueOf(lat) };
		return result;
	}
	
	/**
	 * 传入中心点坐标，及范围，随机返回该范围内的一个点，建议传入需要范围的70%
	 * @param lat
	 * @param lon
	 * @param distance 米
	 * @return
	 */
	public static double[] getRandomLonLatByCenter(double lat, double lon,
			double distance){
		double[] around = boundingCoordinates(lat,lon,distance);
		return randomLonLat(around[2],around[3], around[0], around[1]);
	}

	// 经纬度距离测试
	/*public static void main(String[] args) {
		double lat1 = 34.2;
		double lng1 = 144.8;
		double raidus = 0.07;
		double[] around = boundingCoordinates(lat1, lng1, raidus);
		// 获得矩形坐标
		double[] randomLonLat = randomLonLat(around[2],
				around[3], around[0], around[1]);
		System.out.println(randomLonLat[0]+" "+randomLonLat[1]);
	}*/
}
