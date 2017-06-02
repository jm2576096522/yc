package com.yc.Hotel.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomInfoDao {
	private DBHelper db = new DBHelper();

	/**
	 * 查询i楼层的房间数
	 * 
	 * @param i
	 * @return
	 */
	public int roomCount(int i) {
		String sql = "select rooms from floors where fid=" + i + "";
		return (int) db.getTotal(sql, null);
	}

	/**
	 * 根据房号查询对应状态
	 * 
	 * @param i
	 * @return
	 */
	public int roomZT(int fh) {
		String sql = "select state from room where rno=" + fh + "";
		return (int) db.getTotal(sql, null);
	}

	/**
	 * 根据房号查询对应房间类型标识编号
	 * 
	 * @param i
	 * @return
	 */
	public int roomLX(int fh) {
		String sql = "select typeno from room where rno=" + fh + "";
		return (int) db.getTotal(sql, null);
	}

	/**
	 * 根据房号查询结账状态
	 * 
	 * @param fh
	 * @return
	 */
	public int roomJZ(int fh) {
		String sql = "select bill_state from check_in where rno=" + fh + "";
		return (int) db.getTotal(sql, null);
	}
	public int jz(int fh) {// 结账完成之后，账单状态变为1
		String sql = "update check_in set bill_state=1 where rno=" + fh + "";
		return db.update(sql, null);
	}
	/**
	 * 根据房号查询押金
	 * 
	 * @param fh
	 */
	public int roomYJ(int fh) {
		String sql = "select cash from check_in where rno=" + fh + "";
		return (int) db.getTotal(sql, null);
	}

	/**
	 * 根据房号查询入住时间（入住）
	 * 
	 * @param fh
	 * @return
	 */
	public String roomRZSJ(int fh) {
		String sql = "select date_in from check_in where rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		list = db.find(sql, null);
		return list.get("date_in");
	}

	/**
	 * 根据房号查询预定入住时间（预定）
	 * 
	 * @param fh
	 * @return
	 */
	public String roomYDSJ(int fh) {
		String sql = "select order_in from reservation where rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		list = db.find(sql, null);
		return list.get("order_in");
	}
	/**
	 * 根据房号查询预订信息
	 * @param fh
	 * @return
	 */
	public Map<String, String> resinfo(int fh) {
		String sql = "select r.cname cname,r.card_id card_id,r.sex sex,r.tel tel,r.email email,r.order_out order_out from reservation r  where r.rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		list=db.find(sql, null);
		System.out.println(list);
		return list;
		 
	}
	/**
	 * 根据房号查询预定退房时间（预定）
	 * 
	 * @param fh
	 * @return
	 */
	public String roomYDTF(int fh) {
		String sql = "select order_out from reservation where rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		list = db.find(sql, null);
		return list.get("order_out");
	}

	/**
	 * 根据房号查询客户名（入住）
	 * 
	 * @param fh
	 */
	public String roomKHM(int fh) {
		String sql = "select cname from check_in where rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		list = db.find(sql, null);
		return list.get("cname");
	}

	/**
	 * 根据房号查询客户名（预定）
	 * 
	 * @param fh
	 */
	public String roomKHM1(int fh) {
		String sql = "select cname from reservation re where re.rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		list = db.find(sql, null);
		return list.get("cname");
	}

	/**
	 * 根据房号查询入住客户预计退房时间
	 * 
	 * @param fh
	 * @return
	 */
	public String roomTFSJ(int fh) {
		String sql = "select date_out from check_in where rno=" + fh + "";
		Map<String, String> list = new HashMap<String, String>();
		List<Object> params = new ArrayList<Object>();
		params.add(fh);
		list = db.find(sql, params);
		return list.get("date_out");
	}

	/**
	 * 更改房间状态
	 * 
	 * @param i
	 */
	public int roomGGZT(int i, int fh) {
		String sql = null;
		int zt = this.roomZT(fh);
		if (i == 1) {// 修复损坏的房间
			if (zt == 3) {
				sql = "update room set state=0 where rno=" + fh + "";
				db.update(sql, null);
				return 1;
			}
		}
		if (i == 2) {// 清理
			if (zt == 4) {
				sql = "update room set state=0 where rno=" + fh + "";
				db.update(sql, null);
				return 1;
			}
		}
		if (i == 3) {// 房间损坏不可用
			if (zt != 3) {
				sql = "update room set state=3 where rno=" + fh + "";
				db.update(sql, null);
				return 1;
			}
		}
		return 0;

	}

	/**
	 * 客房入住
	 * 
	 * @param rno
	 * @param cname
	 * @param sex
	 * @param tel
	 * @param card_id
	 * @param date_in
	 * @param date_out
	 * @param cash
	 * @param email
	 * @param opid
	 * @return
	 */
	
	public int addRZXX1(int rno, String cname, String sex, String tel, String card_id, String date_in, String date_out,
			String cash, String email, String opid) {
//插入到客户信息表
		String sql1 = "insert into customer values(seq_customer_cid.nextval,?,?,?,?,1,?,0)";
		List<Object> params = new ArrayList<Object>();
		params.add(cname);
		params.add(sex);
		params.add(card_id);
		params.add(tel);
		params.add(email);
		System.out.println("客户表***");
		return db.update(sql1, params);
		
	}
	
	public int addRZXX2(int rno, String cname, String sex, String tel, String card_id, String date_in, String date_out,
			String cash, String email, String opid) {
		
		//插入入住信息表
		String sql2 = "insert into check_in values(seq_orderno.nextval,?,?,?,?,?,0,?)";
		List<Object> params1 = new ArrayList<Object>();
		params1.add(cname);
		params1.add(rno);
		params1.add(cash);
		params1.add(date_in);
		params1.add(date_out);
		params1.add(opid);
		System.out.println("入住表***");
		return db.update(sql2, params1);

		
		}

		
	public int addRZXX3(int rno, String cname, String sex, String tel, String card_id, String date_in, String date_out,
			//修改入住成功房间状态
			String cash, String email, String opid) {
			String sql3 = "update room set state=1 where rno=" + rno + "";
			System.out.println("房价状态**");
			return db.update(sql3, null);
		
		}
	
	public int delres(int fh) {//入住后删除预定信息
		String sql = "delete reservation where preno in (select preno from reservation where rno=" + fh + ")";	
		return db.update(sql, null);
	}
	
	

	/**
	 * 客房预定(重做)
	 * 
	 * @param rno
	 * @param cname
	 * @param sex
	 * @param tel
	 * @param card_id
	 * @param date_in
	 * @param date_out
	 * @param cash
	 * @param email
	 * @param age
	 * @return
	 */
	public int addYDXX(int rno, String cname, String sex, String tel, String card_id, String date_in, String date_out,
			String cash, String email, String opid) {

		String sql1 = "insert into customer values(seq_customer_cid.nextval,?,?,?,?,1,?,0)";
		List<Object> params = new ArrayList<Object>();
		params.add(cname);
		params.add(sex);
		params.add(card_id);
		params.add(tel);
		params.add(email);
		int i1 = db.update(sql1, params);

		String sql2 = "insert into reservation values(seq_preno.nextval,?,?,?,?,?,?,?,?,10086,?,1,?)";
		List<Object> params1 = new ArrayList<Object>();
		int lx = this.roomLX(rno);
		params1.add(lx);
		params1.add(rno);
		params1.add(cname);
		params1.add(sex);
		params1.add(tel);
		params1.add(card_id);
		params1.add(date_in);
		String price;
		if (lx == 1)
			price = "120";
		else if (lx == 2)
			price = "180";
		else if (lx == 3)
			price = "240";
		else if (lx == 4)
			price = "320";
		else if (lx == 5)
			price = "600";
		else
			price = "900";
		params1.add(price);
		params1.add(date_out);
		params1.add(email);
		int i2 = db.update(sql2, params1);

		if (i1 > 0 && i2 > 0) {
			String sql = "update room set state=2 where rno=" + rno + "";
			int i = db.update(sql, null);
			return i;
		}

		return 0;
	}

	/**
	 * 退房（入住）
	 * 
	 * @param fh
	 * @param date
	 * @return
	 */
	
	public int TForderno(int fh) {
		String sql = "select orderno from check_in where rno=" + fh + "";
		return  (int)db.getTotal(sql, null);
	}
	public int roomTF(int fh, String date) {
		String sql = "delete check_in  where orderno in (select orderno from check_in where rno=" + fh + ")";	
		return db.update(sql, null);
	}
	public int roomstate(int fh) {
		String sql = "update room set state=4 where rno=" + fh + "";//房间状态改为脏房
		return db.update(sql, null);
	}
	

}
