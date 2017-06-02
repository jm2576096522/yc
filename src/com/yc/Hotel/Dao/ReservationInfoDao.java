package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 预定信息
 * 
 * @author Administrator
 *
 */
public class ReservationInfoDao {
	/**
	 * 添加预订信息
	 * 
	 * @param cname:顾客姓名
	 * @param sex：顾客性别
	 * @param tel：电话
	 * @param email：邮件
	 * @param card_id：证件号
	 * @param typeno：客房类型名
	 * @param rno：房间号
	 * @param order_in：预入住时间
	 * @param order_out：预退房时间
	 * @return
	 */
	public int add(String typeno, String rno, String cname, String sex, String tel, String card_id, String order_in,
			String price, String order_out, String email) {
		DBHelper db = new DBHelper();
		String sql1 = "insert into reservation values(seq_preno.nextval,?,?,?,?,?,?,?,?,default,?,1,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(typeno);
		params.add(rno);
		params.add(cname);
		params.add(sex);
		params.add(tel);
		params.add(card_id);
		params.add(order_in);
		params.add(price);
		params.add(order_out);
		params.add(email);

		int i1= db.update(sql1, params);
	
		String sql2 = "insert into customer values(seq_customer_cid.nextval,?,?,?,?,1,?,0)";
		List<Object> params1 = new ArrayList<Object>();
		params1.add(cname);
		params1.add(sex);
		params1.add(card_id);
		params1.add(tel);
		params1.add(email);
		int i2= db.update(sql2, params1);
	
		if(i1>0&&i2>0){
			System.out.println(rno);
			String sql3 = "update room r set r.state=2 where r.rno="+rno+" ";
			System.out.println("ok成功啦");
		return db.update(sql3, null);
		}
		return 0;
	}

	// 查找房间号
	public List<Map<String, String>> search(String typeNum, String orderin) {
		DBHelper db = new DBHelper();
	//	System.out.println(typeNum+"****"+orderin);
		String sql = "select r.rno from room r where typeno=?"
				+ "and rno not in (select re.rno from reservation re where ? between re.order_in AND re.order_out)"
				+ "and rno not in(select c.rno from check_in c where ? between c.date_in and c.date_out)"; // 日期在某个时间段内的房间号
		List<Object> params = new ArrayList<Object>();
		params.add(typeNum);
		params.add(orderin);
		params.add(orderin);
		return db.finds(sql, params);
		//return db.finds(sql, null);

	}

	public List<Map<String, String>> rnosearch(String rno) {
		DBHelper db = new DBHelper();
		String sql = "select * from reservation where rno=" + rno + "";
		return db.finds(sql, null);

	}

}
