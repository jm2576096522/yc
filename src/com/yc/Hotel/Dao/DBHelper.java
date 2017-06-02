package com.yc.Hotel.Dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	/**
	 *加载数据库的驱动
	 */
	static {
		try {
			Class.forName(ReadPro.getInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {//类无法加载
			e.printStackTrace();
		}
	}
	/**
	 *获取连接的方法 
	 * @return
	 */
	
	private Connection getConnection(){
		Connection con=null;//数据库的连接对象
		try {
			con=DriverManager.getConnection(ReadPro.getInstance().getProperty("url"),
					ReadPro.getInstance().getProperty("user"),
					ReadPro.getInstance().getProperty("password"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭连接的方法
	 * @param con：要关闭的连接
	 * @param pstmt：要关闭的预编译块
	 */
	private void closeAll(Connection con,PreparedStatement pstmt){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭连接的方法
	 * @param con：要关闭的连接
	 * @param pstmt：要关闭的编译方法
	 * @param rs：要关闭的结果集
	 */
	private void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
		this.closeAll(con, pstmt);

		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给预编译的sql语句中的占位符赋值
	 * @param pstmt：预编译对象
	 * @param params：语句中对应占位符？的参数值
	 */
	private void setValues(PreparedStatement pstmt,List<Object>params){//params参数
		if(params!=null&&params.size()>0){
			System.out.println(params);
			for(int i=0,len=params.size();i<len;i++){
				Object obj=params.get(i);
				String type;				
				try {
					if (obj != null) {
						type = obj.getClass().getSimpleName();
						pstmt.setString(i+1, String.valueOf(obj));
					}else{
						pstmt.setString(i+1, (String)obj);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新操作
	 * @param sql：要执行的更新语句
	 * @param params：更新语句中对应占位符？的参数值
	 * @return
	 */
	public int update(String sql,List<Object>params){//只适合输出一行
		int result=0;
		Connection con=null;//连接
		PreparedStatement pstmt=null;//预处理编译

		try {
			con=this.getConnection();//获取连接
			pstmt=con.prepareStatement(sql);//预编译sql语句

			//给预编译sql语句中的占位符赋值
			this.setValues(pstmt, params);

			//执行更新
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, pstmt);
		}
		return result;
	}

	/**
	 * 带事物的更新操作
	 * @param sqls：要执行的sql 语句集合
	 * @param params：每条sql对应的占位符的参数值
	 * @return
	 */
	public int update(List<String>sqls,List<List<Object>> params){//输出多行，且实现有关联的两个表数据同时更改
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;

		try {	
			con=this.getConnection();//获取连接

			//将自动提交事务设为false
			con.setAutoCommit(false);
			if(sqls!=null&&sqls.size()>0){
				for(int i=0,len=sqls.size();i<len;i++){
					pstmt=con.prepareStatement(sqls.get(i));// 创建一个 PreparedStatement 对象来将参数化的 SQL 语句发送到数据库。  
					//给编译sql语句的占位符赋值
					this.setValues(pstmt, params.get(i));
					//执行更新
					result =pstmt.executeUpdate();
				}
			}
			con.commit();//如果没有异常抛出，则提交事务
		} catch (SQLException e) {
			try {
				con.rollback();//如果一旦抛出异常，则回滚所有事务
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.closeAll(con, pstmt);
		}
		return result;
	}

	/**
	 * 获取记录值，这个方法只能返回单个值
	 * @param sql：要执行的查询语句
	 * @param params： 语句中对应占位符?的参数值
	 * @return
	 */
	public double getTotal(String sql,List<Object>params){//select count(deptno) from dept;
		double total =0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			//获取连接
			con=this.getConnection();
			
			//预编译语句
			pstmt=con.prepareStatement(sql);
			
			//给语句中的占位符赋值
			this.setValues(pstmt,params);
			
			//执行并获取结果集
			rs=pstmt.executeQuery();
			
			//我们只取这个结果集中的第一行第一列的数据
			if(rs.next()){//入股有结果返回，则取出第一列的值并返回
				total=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//关闭连接
			this.closeAll(con, pstmt,rs);
		}
		return total;

	}

	/**
	 * 获取指定结果集中所有列的名称
	 * @param rs：要获取列名的结果集
	 * @return：返回该结果集中所有列的名称
	 */
	public  String [] getColumnNames(ResultSet rs){
		String []colNames=null;
		try {
			//获取此ResultSet对象的列的编号，类型和属性
			ResultSetMetaData rsmd=rs.getMetaData();
			
			int len = rsmd.getColumnCount();//获取结果集中列的数量
			
			colNames =new String[len];//实例化数组，长度为结果集中列的数量
			for(int i=0;i<len;i++){
				colNames[i]=rsmd.getColumnName(i+1).toLowerCase();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return colNames;
	}

	/**
	 * 聚合查询。如select count(deptno)count,max(sal) sal ,min(sal)min,avg(sal)avg,sum(sal)sum from emp
	 * @param sql：要执行的查询语句
	 * @param params：语句中对应占位符?的参数值
	 * @return：map中以列名为键，以对应各列的值为值
	 */
	public Map<String,String> find(String sql,List<Object>params){
		Map<String,String> map=new HashMap<String,String>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//获取连接
			con=this.getConnection();
			
			//预编译语句
			pstmt=con.prepareStatement(sql);
			
			//给语句中的占位符赋值
			this.setValues(pstmt, params);
			
			//执行并获取结果集
			rs=pstmt.executeQuery();
			
			//因为需要以每个列的列名为键，所以我们必须先获取返回结果集中每一个列的列名
			String[]colNames=this.getColumnNames(rs);
			
			//我们只取这个结果集中的第一行和第一列的数据
			if(rs.next()){//如果有结果返回，则取出第一行中的所有列的值
				for(int i=0,len=colNames.length;i<len;i++){//这里是循环所有的列，并取出每个列的值
					map.put(colNames[i], rs.getString(colNames[i]));//根据列名取出该列的值，并存放在map中
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//关闭连接
			this.closeAll(con, pstmt,rs);
		}
		return map;
	}

	/**
	 * 查询
	 * @param sql：要执行的查询语句
	 * @param params：语句中对应占位符?的参数值
	 * @return
	 */
	public List<Map<String,String>>finds(String sql,List<Object>params){
		List<Map<String,String>>list =new ArrayList<Map<String,String>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=this.getConnection();//获取连接
			
			pstmt=con.prepareStatement(sql);//预编译语句
			
			this.setValues(pstmt, params);//给语句中的占位符赋值
			
			rs=pstmt.executeQuery();//执行并获取结果集
			
			String[]colNames=this.getColumnNames(rs);//因为需要以每个列的列名为键，所以我们必须先获取返回结果集中每一个列的列名
			
			//我们只取这个结果集中的第一行和第一列的数据
			Map<String,String>map=null;
			while(rs.next()){//如果有结果返回，则取出第一行中的所有列的值
				map=new HashMap<String,String>();
				for(int i=0,len=colNames.length;i<len;i++){//这里是循环所有的列，并取出每个列的值
					map.put(colNames[i], rs.getString(colNames[i]));//根据列名取出该列的值，并存放在map中
				}
				//当这一行的数据全部读完后，将这个map存放在list中
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//关闭连接
			this.closeAll(con, pstmt,rs);
		}
		return list;
	}
	
/*	*//**
	 * 查询
	 * 
	 * @param sql：要执行的查询语句
	 * @param params：语句中对应占位符?的参数值
	 * @return
	 *//*
	public List<Map<String, Object>> findPic(String sql, List<Object> params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = this.getConnection();// 获取连接

			pstmt = con.prepareStatement(sql);// 预编译语句

			this.setValues(pstmt, params);// 给语句中的占位符赋值

			rs = pstmt.executeQuery();// 执行并获取结果集

			String[] colNames = this.getColumnNames(rs);// 因为需要以每个列的列名为键，所以我们必须先获取返回结果集中每一个列的列名

			// 我们只取这个结果集中的第一行和第一列的数据
			Map<String, Object> map = null;
			String type;
			Object obj;
			while (rs.next()) {// 如果有结果返回，则取出第一行中的所有列的值
				map = new HashMap<String, Object>();
				for (int i = 0, len = colNames.length; i < len; i++) {// 这里是循环所有的列，并取出每个列的值
					obj = rs.getObject(colNames[i]);
					if (obj == null) {
						map.put(colNames[i], null);// 根据列名取出该列的值，并存放在map中
					} else {
						// 获取这个列的类型
						type = obj.getClass().getSimpleName();

						if ("BigDecimal".equals(type)) {
							map.put(colNames[i], Double.parseDouble(String.valueOf(obj)));
						} else if ("BLOB".equals(type)) {
							BufferedInputStream bis = null;
							byte[] bt = null;
							Blob blob = rs.getBlob(colNames[i]);
							bis = new BufferedInputStream(blob.getBinaryStream());
							try {
								bt = new byte[(int) blob.length()];
								bis.read(bt);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (bis != null) {
									try {
										bis.close();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
							map.put(colNames[i], bt);
						} else {
							map.put(colNames[i], (String) obj);
						}
					}
				}
				// 当这一行的数据全部读完后，将这个map存放在list中
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 关闭连接
			this.closeAll(con, pstmt, rs);
		}
		return list;
	}*/

}
