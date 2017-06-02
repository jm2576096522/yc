package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 查询客房预定信息
 * @author Administrator
 *
 */

public class ChackInfoDao {
	
	//查询入住
	private Object reservation;


	public List<Map<String, String>> find(String cname,String rno,String preno) {
		DBHelper db = new DBHelper();
		StringBuffer sql =new StringBuffer( "select * from reservation i where order_state=1");
		List<Object>params=new ArrayList<Object>();
		if(rno!=null&&"".equals(rno)){
			sql.append(" and rno=?");
			params.add(rno);
		}
		if(cname!=null&&"".equals(cname)){
			sql.append(" and cname=?");
			params.add(cname);
		}
		if(reservation!=null&&"".equals(reservation)){
			sql.append(" and reservation=?");
			params.add(reservation);
		}
		return db.finds(sql.toString(),params);
	}
	//房号查找
	public List<Map<String, String>> roomIDfind(String roomID){
		DBHelper db = new DBHelper();
		String sql="select *  from reservation where rno ='"+roomID+"'";
		return db.finds(sql, null);
	}
	//查找房名
		public List<Map<String, String>> roomname(String roomID){
			DBHelper db = new DBHelper();
			String sql="select typename  from roomtype where typeno ='"+roomID+"'";
			return db.finds(sql, null);
		}
	
	 
	//当前客户名字查找
	public  List<Map<String, String>> cnamefind(String cname) {
		DBHelper db = new DBHelper();
		String sql="select *  from reservation where cname='"+cname+"'";
		return db.finds(sql, null);
	}
	
	//查询全部	
	public  List<Map<String, String>> reservationfind(String reservation) {
		DBHelper db = new DBHelper();
		String sql="select *  from reservation";
		return db.finds(sql, null);
	}
}
