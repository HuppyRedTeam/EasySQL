package com.EasySQL;

/**
 * EasySQL高级实现抽象类，定义所有除底层连接外方法.
 * 
 * @author chenhao220
 * @version v1.0 b5
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.EasySQL.Exception.StatementException;
import com.EasySQL.Exception.StatementException.Reason;

public abstract class EasySQLimp implements EasySQL{
	protected Connection con;
	protected Statement stat;
	protected boolean _login = false;
	@Override
	public void NormalCommandExec(String command) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		stat.execute(command);
	}

	@Override
	public ResultSet ResultCommandExec(String command)
			throws StatementException, SQLException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		stat.execute(command);
		ResultSet result = stat.getResultSet();
		if(result == null){
			throw new StatementException(Reason.NullPoint);
		}else{
			return result;
		}
	}

	@Override
	public void NormalCommandExecQuery(String command) throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		stat.executeQuery(command);
	}

	@Override
	public ResultSet ResultCommandExecQuery(String command)
			throws StatementException, SQLException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		ResultSet result = stat.executeQuery(command);
		if(result == null){
			throw new StatementException(Reason.NullPoint);
		}else{
			return result;
		}
	}

	@Override
	public void CreateDatabase(String name, Encoding encoding)
			throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
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
			throw new StatementException(Reason.Unlogin);
		}
		this.NormalCommandExec("DROP DATABASE "+name);
		
	}
	
	@Override
	@Deprecated
	public void CreateTable(String name, Map<String, String> list)
			throws SQLException, StatementException {
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
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
			throw new StatementException(Reason.Unlogin);
		}
		this.NormalCommandExec("DROP TABLE "+name);
	}

	public void CreateTable(String name,LinkedHashMap<String, String> list)throws SQLException, StatementException{
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
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
	public ResultSet getDistinctResult(String Table,String... Column) throws SQLException, StatementException{
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		ResultSet result;
		StringBuilder sql = new StringBuilder("SELECT DISTINCT ");
		if(Column.length==0){
			sql.append("*");
		}else{
			for(String s:Column){
				sql.append(s+",");
			}
			sql.delete(sql.length()-1, sql.length());

		}
		sql.append(" FROM ");
		sql.append(Table);
		result = this.ResultCommandExec(sql.toString());
		return result;
	}
	
	@Override
	public ResultSet getNormalResult(String Table,String... Column) throws SQLException, StatementException{
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		ResultSet result;
		StringBuilder sql = new StringBuilder("SELECT ");
		if(Column.length==0){
			sql.append("*");
		}else{
			for(String s:Column){
				sql.append(s+",");
			}
			sql.delete(sql.length()-1, sql.length());

		}
		sql.append(" FROM ");
		sql.append(Table);
		result = this.ResultCommandExec(sql.toString());
		return result;
	}
	@Override
	public void UpdateCommandExec(String command)throws StatementException, SQLException{
		if(_login==false){
			throw new StatementException(Reason.Unlogin);
		}
		stat.executeUpdate(command);
	}
	
	@Override
	public ResultSet ResultUpdateExec(String command)throws StatementException, SQLException{
		stat.executeUpdate(command);
		ResultSet result = stat.getResultSet();
		return result;
	}
	
}
