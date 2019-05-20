package com.EasySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EasySQL.Exception.StatementException;

/**
 * EasySQL接口，提供大多数操作数据库的办法.
 * <p>目前可以使用com.EasySQL.JDBC_Driver实例化，需求mysql-connector支持库提供JDBC驱动.
 * 
 * @version v1.0 b2
 * @author chenhao220
 * 
 */
public interface EasySQL {
	/**
	 * 使用指定的地址、端口、用户名、密码登陆数据库，并打开数据库连接.
	 * <p>在使用其他方法前，必须调用此方法登陆.
	 * 
	 * @param ip	连接到的目标数据库地址
	 * @param port	目标数据库端口
	 * @param database	目标数据库名
	 * @param user	数据库登陆用户名
	 * @param password	数据库登陆密码
	 * @throws ClassNotFoundException 缺失驱动程序时抛出
	 * @throws SQLException 登录失败时抛出
	 */
	void Login(String ip,int port,String database,String user,String password) throws ClassNotFoundException, SQLException;
	
	/**调用{@link java.sql.Statement#execute(String)}方法执行无运行结果的SQL语句.
	 * 
	 * @param command	执行的SQL语句
	 * @throws SQLException 出现数据库问题时抛出
	 */
	void NormalCommandExec(String command) throws SQLException;
	
	/**调用{@link java.sql.Statement#execute()}执行多重结果SQL语句，调用{@link java.sql.Statement#getResultSet()}
	 * 获取运行结果并返回.
	 * 
	 * @param command	执行的SQL语句
	 * @return	返回SQL运行结果的语句
	 * @throws SQLException 出现数据库问题时抛出
	 * @throws StatementException	返回ResultSet为null时抛出
	 */
	ResultSet ResultCommandExec(String command) throws StatementException, SQLException;
	
	/**调用{@link java.sql.Statement#executeQuery()}执行无运行结果的SQL语句.
	 * 
	 * @param command	执行的SQL语句
	 * @throws SQLException	出现数据库问题时抛出
	 */
	void NormalCommandExecQuery(String command) throws SQLException;
	
	/**调用{@link java.sql.Statement#executeQuery()}执行单重运行结果的SQL语句，并返回运行结果。
	 * @param command	执行的SQL语句
	 * @return	返回SQL运行结果的语句
	 * @throws StatementException	返回Result为null时抛出
	 * @throws SQLException	出现数据库问题时抛出
	 */
	ResultSet ResultCommandExecQuery(String command)throws StatementException, SQLException;
	
	/**使用CREATE DATABASE SQL语句创建指定名称、编码方式的数据库.
	 * 
	 * @param name	数据库名称
	 * @param encoding	数据库编码方式
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException	使用不支持的编码方式时抛出
	 * @since v1.0 b2
	 */
	void CreateDatabase(String name,Encoding encoding) throws SQLException, StatementException;
	
	/**使用DROP DATABASE SQL语句删除指定名称的数据库.
	 * 
	 * @param name	数据库名称
	 * @throws SQLException	出现数据库问题时抛出
	 * @since v1.0 b2
	 */
	void DeleteDatabase(String name) throws SQLException;

}
