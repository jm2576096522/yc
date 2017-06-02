package com.yc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.yc.Hotel.Dao.DBHelper;

public class Register {
/**
 * 向注册表中添加数据
 * @param map
 */
	public static void add(Map<String,String>map){
		//regedit注册表信息--HKEY_CURRENT_USER--software--Java--prefs
		Preferences pf=Preferences.userNodeForPackage(Register.class);
		if(map!=null&&map.size()>0){
			Set<String>keys=map.keySet();
			for(String key:keys){
				pf.put(key, map.get(key));
			}
		}
		try {
			pf.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有的注册表中的数据
	 * @return
	 */
	public static Map<String,String>getAll(){
		//先将注册表的路径设置为当前类下
		Preferences pf=Preferences.userNodeForPackage(Register.class);
		Map<String,String>map=new HashMap<String ,String>();
		try {
			String []keys=pf.keys();
			if(keys!=null&&keys.length>0){
				for(String key:keys){
					map.put(key,pf.get(key,""));
				
				}
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据指定的键，返回对应的值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		Preferences pf=Preferences.userNodeForPackage(Register.class);
		return pf.get(key, null);
	}
	
	/**
	 * 根据指定的键，返回对应的值
	 * @param key
	 * @return
	 */
	public static void remove(String key){
		Preferences pf=Preferences.userNodeForPackage(Register.class);
		pf.remove(key);
		
	}
	//查询管理员姓名
	public List<Map<String, String>> searchaname(String numID) {
		DBHelper db = new DBHelper();
		String sql = "select oname from operater where OpID="+numID+"";
		return db.finds(sql, null);
	}
}
