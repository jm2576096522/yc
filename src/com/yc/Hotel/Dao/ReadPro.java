package com.yc.Hotel.Dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class ReadPro extends Properties{

	private static ReadPro instance=new ReadPro();
	
	/**
	 * 构造方法私有化
	 * 需要从db.properties文件中将内容读取出来，转化为一个Properties对象信息
	 * 
	 */
	private ReadPro(){
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ReadPro getInstance(){
		if(instance ==null){
			instance=new ReadPro();
		}
		return instance;
	}
}
