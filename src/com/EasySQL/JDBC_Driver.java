package com.EasySQL;

/**
 * 通过JDBC连接mysql底层实现类.
 * 
 * @author chenhao220
 * @version v1.0 b5
 */
import java.sql.DriverManager;
import java.sql.SQLException;

import com.EasySQL.Exception.StatementException;


public class JDBC_Driver extends EasySQLimp{
	String url_ = "";

	@Override
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
			throw new StatementException("Unlogin!");
		}
		if(!super.con.isClosed()){
			super.con.close();
			super.stat.close();
		}
		super._login = false;
	}
}
