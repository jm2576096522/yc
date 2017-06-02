package com.yc.utils;

import java.util.Map;

import org.eclipse.swt.custom.StackLayout;

import com.yc.Hotel.ui.AddOperaterInfo;
import com.yc.Hotel.ui.CXKF;
import com.yc.Hotel.ui.DLJM1;
import com.yc.Hotel.ui.DLJM3;
import com.yc.Hotel.ui.FJGL;
import com.yc.Hotel.ui.KHXX;
import com.yc.Hotel.ui.Reservation;
import com.yc.Hotel.ui.RuzhuInfo;
import com.yc.Hotel.ui.YGGL;

public class UICollection {
	public static StackLayout stackLayout=new StackLayout();
	public static Reservation reservation;//预定界面
	public static DLJM1 dljm1;//登录界面
	public static DLJM3 dljm3;//登录界面
	public static KHXX  khxx;//客户信息
	public static FJGL fjgl;//房间管理
	public static CXKF cxkf;//查询客房
	public static YGGL yggl;//员工管理
	
	public static Map<String,String> currentLoginUser;//当前登录用户
	public static AddOperaterInfo addOperaterInfo;;//增加员工信息
	public static RuzhuInfo ruzhuInfo;//前台
}
