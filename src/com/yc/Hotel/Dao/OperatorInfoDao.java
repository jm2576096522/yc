package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperatorInfoDao {

	// 显示所有管理员信息
	public static List<Map<String, String>>find() {
		DBHelper db = new DBHelper();
		String sql = "select o.opid,o.oname,o.opwd,o.otel,o.ograde from operater o where state=1 order by opid asc ";
		return db.finds(sql, null);
	}

	//添加用户信息
	

	/**
	 * 修改管理员信息
	 * @param oname
	 * @param opwd
	 * @param otel
	 * @param ograde
	 * @return
	 */
	public static int update(String otel, String ograde,String opid) {
		DBHelper db = new DBHelper();
		String sql = "update operater o set o.otel=?,o.ograde=? where opid=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(otel);
		params.add(ograde);
		params.add(opid);
		return db.update(sql, params);
	}
	
	/**
	 * 添加管理员信息
	 * @param oname：管理员姓名
	 * @param opwd：密码
	 * @param otel：电话
	 * @param ograde：等级
	 * @return
	 */
	public  int add( String opwd, String ograde,String oname,String otel){
		DBHelper db=new DBHelper();        
		String sql="insert into operater values(seq_operater_opid.nextval,?,?,?,?,1)";
		List<Object>params=new ArrayList<Object>();
		params.add(opwd);
		params.add(ograde);
		params.add(oname);
		params.add(otel);
		return db.update(sql, params);
	}
}
