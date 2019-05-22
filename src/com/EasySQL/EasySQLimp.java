package com.EasySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.EasySQL.Exception.StatementException;

public abstract class EasySQLimp implements EasySQL{
	Connection con;
	Statement stat;
	boolean _login = false;
	@Override
	public void NormalCommandExec(String command) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		stat.execute(command);
	}

	@Override
	public ResultSet ResultCommandExec(String command)
			throws StatementException, SQLException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		stat.execute(command);
		ResultSet result = stat.getResultSet();
		if(result == null){
			throw new StatementException("Result is null!");
		}else{
			return result;
		}
	}

	@Override
	public void NormalCommandExecQuery(String command) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		stat.executeQuery(command);
	}

	@Override
	public ResultSet ResultCommandExecQuery(String command)
			throws StatementException, SQLException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		ResultSet result = stat.executeQuery(command);
		if(result == null){
			throw new StatementException("Result is null!");
		}else{
			return result;
		}
	}

	@Override
	public void CreateDatabase(String name, Encoding encoding)
			throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		switch(encoding){
		case ASCII:
			this.NormalCommandExec("CREATE DATABASE "+name+" DEFAULT CHARACTER SET ascii");
			break;
		case UTF8:
			this.NormalCommandExec("CREATE DATABASE "+name+" DEFAULT CHARACTER SET utf8");
			break;
		case BIG5:
			this.NormalCommandExec("CREATE DATABASE "+name+" DEFAULT CHARACTER SET big5");
			break;
		case UTF8MB4:
			this.NormalCommandExec("CREATE DATABASE "+name+" DEFAULT CHARACTER SET utf8mb4");
			break;
		default:
			throw new StatementException("Unsupport encoder!");
		}
	}

	@Override
	public void DeleteDatabase(String name) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		this.NormalCommandExec("DROP DATABASE "+name);
		
	}
	


	@Override
	public void CreateTable(String name, Map<String, String> list)
			throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		StringBuilder sql = new StringBuilder("CREATE TABLE ");
		sql.append(name+" (");
		
		Set<Entry<String,String>> entry = list.entrySet();
		for(Entry<String,String> e:entry){
			sql.append(e.getKey()+" "+e.getValue()+",");
		}

		sql.delete(sql.length()-1, sql.length());
		sql.append(")");
		this.NormalCommandExec(sql.toString());
	}

	@Override
	public void DeleteTable(String name) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException("Unlogin!");
		}
		this.NormalCommandExec("DROP TABLE "+name);
	}

	
}
