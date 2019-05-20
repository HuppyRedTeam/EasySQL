package com.EasySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.EasySQL.Exception.StatementException;

public class JDBC_Driver implements EasySQL{
	Connection con;
	Statement stat;

	@Override
	public void Login(String ip, int port, String database, String user,String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
	    con = DriverManager.getConnection(url,user,password);
		stat = con.createStatement();
	}

	@Override
	public void NormalCommandExec(String command) throws SQLException {
		stat.execute(command);
	}

	@Override
	public ResultSet ResultCommandExec(String command)
			throws StatementException, SQLException {
		stat.execute(command);
		ResultSet result = stat.getResultSet();
		if(result == null){
			throw new StatementException("Result is null!");
		}else{
			return result;
		}
	}

	@Override
	public void NormalCommandExecQuery(String command) throws SQLException {
		stat.executeQuery(command);
	}

	@Override
	public ResultSet ResultCommandExecQuery(String command)
			throws StatementException, SQLException {
		ResultSet result = stat.executeQuery(command);
		if(result == null){
			throw new StatementException("Result is null!");
		}else{
			return result;
		}
	}
	
	
	
	

}
