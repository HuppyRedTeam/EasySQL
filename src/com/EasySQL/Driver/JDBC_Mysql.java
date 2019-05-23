package com.EasySQL.Driver;

/**
 * 通过JDBC连接mysql底层实现类.
 * 
 * @author chenhao220
 * @version v1.0 b5
 */
import java.sql.DriverManager;
import java.sql.SQLException;

import com.EasySQL.EasySQLimp;
import com.EasySQL.Exception.StatementException;
import com.EasySQL.Exception.StatementException.Reason;


public class JDBC_Mysql extends EasySQLimp{
	String url_ = "";
	/**使用构造器初始化mysql_jdbc连接，并实例化EasySQL对象.
	 * 
	 * @param ip	数据库IP地址
	 * @param port	数据库端口	
	 * @param database	数据库名称
	 * @param user	登陆用户名
	 * @param password	登陆密码
	 * @throws ClassNotFoundException	缺失mysql-jdbc驱动时抛出
	 * @throws SQLException	账户不正确或数据库不存在时抛出
	 */
	public JDBC_Mysql(String ip, int port, String database, String user,String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
		url_ = "jdbc:mysql://"+ip+":"+port+"/";
	    super.con = DriverManager.getConnection(url,user,password);
		super.stat = con.createStatement();
		super._login = true;
	}
	
	@Deprecated
	public JDBC_Mysql(){
		
	}
	@Override
	@Deprecated
	public void Login(String ip, int port, String database, String user,String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
		url_ = "jdbc:mysql://"+ip+":"+port+"/";
	    super.con = DriverManager.getConnection(url,user,password);
		super.stat = con.createStatement();
		super._login = true;
	}
	@Override
	public void CloseAll() throws SQLException, StatementException {
		if(super._login==false){
			throw new StatementException(Reason.Unlogin);
		}
		if(!super.con.isClosed()){
			super.con.close();
			super.stat.close();
		}
		super._login = false;
	}
}
