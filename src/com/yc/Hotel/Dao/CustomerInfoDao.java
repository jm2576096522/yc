package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 顾客信息数据库操作
 * 
 * @author Administrator
 *
 */
public class CustomerInfoDao {
	
	/*
	 * // 获取客户个数 public static int count() { String sql =
	 * "select count(cid) from customer"; return (int) db.getTotal(sql, null); }
	 */

	// 显示所有顾客信息
	public static List<Map<String, String>> All() {
		DBHelper db = new DBHelper();
		String sql = "select c.*,v.vgrade from customer c ,vip v where c.ctype=v.vid order by cid asc";
		return db.finds(sql, null);
	}

	// 查询所有顾客类型
	public static List<Map<String, String>> CustomerType() {
		 DBHelper db = new DBHelper();
		String sql = "select * from vip v order by vid asc";
		return db.finds(sql, null);
	}

	// 条件查询
	public static List<Map<String, String>> find(String card_id, String cname,String ctype) {
		 DBHelper db = new DBHelper();
		StringBuffer sql = new StringBuffer(
				"select c.cid, c.cname, c.csex,c.ctel, c.card_id,c.email,v.vgrade from customer c ,vip v where c.ctype=v.vid ");
		 List<Object> params = new ArrayList<Object>();
		// 按照身份证号查询所有顾客信息
		if (card_id != null && !"".equals(card_id)) {
			sql.append(" and card_id like ?");
			params.add("%"+card_id+"%");
		}
		// 按照姓名查询所有顾客的信息
		if (cname != null && !"".equals(cname)) {
			sql.append(" and cname like ?");
			params.add("%" + cname + "%");
		}
		//按照顾客类型查询
		if(ctype!=null&&!"".equals(ctype)){
			sql.append(" and ctype=?");
			params.add(ctype);
		}
		return db.finds(sql.toString(), params);
	}

	/**
	 * 修改顾客信息
	 * 
	 * @param ctel
	 * @param email
	 * @param cid:顾客编号
	 * @return
	 */
	public static int update(String ctel, String ctype, String email, String cid) {
		DBHelper db = new DBHelper();
		String sql = "update customer c  set c.ctel=?, c.ctype=? ,c.email=? where cid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(ctel);
		params.add(ctype);
		params.add(email);
		params.add(cid);
		return db.update(sql, params);
	}

	// 删除
	public static int del(String cid) {
		 DBHelper db = new DBHelper();
		String sql;
		 List<Object> params = new ArrayList<Object>();
		if (cid!=null&&cid.contains(",") && !cid.contains(" or")) {
			sql = "delete from customer  where cid in(" + cid + ")";
		} else {
			sql = "delete from customer  where cid=?";
			params.add(cid);
		}
		return db.update(sql, params);
	}
}
