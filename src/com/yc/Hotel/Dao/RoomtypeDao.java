package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 房间类型的数据库操作
 * @author Administrator
 *
 */
public class RoomtypeDao {
	/**
	 * 查询所有客房类型
	 * @return :所有商品信息
	 */
	public List<Map<String, String>> find() {
		DBHelper db = new DBHelper();
		String sql = "select * from roomtype order by typeno asc";
		return db.finds(sql, null);
	}
	
	/**
	 * 添加房间类型 
	 * @param tname：要添加的商品类型名
	 * @return
	 */
	public int add(String typeno,String typename,String price,String beds) {
		DBHelper db = new DBHelper();
		String sql = "insert into roomtype values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(typeno);
		params.add(typename);
		params.add(price);
		params.add(beds);
		return db.update(sql, params);
	}

	/**
	 * 修改房间类型
	 * 
	 * @param tname：商品类型名
	 * @param tid：要修改的商品类型ID
	 * @return
	 */	
	public int update(String typeno,String typename,String price,String beds) {
		DBHelper db = new DBHelper();
		String sql = "update roomtype set typename=? and price=? and beds=? where typeno=?";
		List<Object> params = new ArrayList<Object>();		
		params.add(typename);
		params.add(price);
		params.add(beds);
		params.add(typeno);
		return db.update(sql, params);
	}

	/**
	 * 删除操作
	 * 
	 * @param tid:要删除的商品类型ID，如果同时要删除多个，每个ID之间要用逗号隔开
	 * @return
	 */
	public int del(String typeno) {
		DBHelper db = new DBHelper();
		String sql;
		List<Object> params = new ArrayList<Object>();
		if (typeno != null && !"".equals(typeno) && typeno.contains(",") && !"or".contains(typeno)) {
			sql = "delete from roomtype where typeno in(" + typeno + ")";
		} else {
			sql = "delete from roomtype where typeno =?";
			params.add(typeno);
		}
		return db.update(sql, params);
	}
}
