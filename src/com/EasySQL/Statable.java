package com.EasySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EasySQL.Exception.StatementException;
/**
 * EasySQL基础接口，封装基本SQL函数.
 * <p>不建议直接实例化该接口.
 * 
 * @version v1.0 b4
 * @author chenhao220
 * 
 */

public interface Statable {
	/**
	 * 使用指定的地址、端口、用户名、密码登陆数据库，并打开数据库连接.
	 * <p>在使用其他方法前，必须调用此方法登陆.
	 * <p>不推荐调用此方法登陆，请使用EasySQLimp子类含参构造器进行实例化与登陆操作.
	 * @param ip	连接到的目标数据库地址
	 * @param port	目标数据库端口
	 * @param database	目标数据库名
	 * @param user	数据库登陆用户名
	 * @param password	数据库登陆密码
	 * @throws ClassNotFoundException 缺失驱动程序时抛出
	 * @throws SQLException 登录失败时抛出
	 */
	@Deprecated
	void Login(String ip,int port,String database,String user,String password) throws ClassNotFoundException, SQLException;
	
	/**调用{@link java.sql.Statement#execute(String)}方法执行无运行结果的SQL语句.
	 * 
	 * @param command	执行的SQL语句
	 * @throws SQLException 出现数据库问题时抛出
	 * @throws StatementException	尝试未登录调用时抛出
	 */
	void NormalCommandExec(String command) throws SQLException, StatementException;
	
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
	 * @throws StatementException	尝试未登录调用时抛出
	 */
	void NormalCommandExecQuery(String command) throws SQLException, StatementException;
	
	/**调用{@link java.sql.Statement#executeQuery()}执行单重运行结果的SQL语句，并返回运行结果。
	 * @param command	执行的SQL语句
	 * @return	返回SQL运行结果的语句
	 * @throws StatementException	返回Result为null时抛出
	 * @throws SQLException	出现数据库问题时抛出
	 */
	ResultSet ResultCommandExecQuery(String command)throws StatementException, SQLException;
	
	/**调用{@link java.sql.Statement#executeUpdate()}执行无运行结果的SQL语句.
	 * 
	 * @param command 执行的SQL语句
	 * @throws StatementException 返回Result为null时抛出
	 * @throws SQLException 出现数据库问题时抛出
	 */
	void UpdateCommandExec(String command)throws StatementException, SQLException;
	
	/**调用{@link java.sql.Statement#executeUpdate()}执行无运行结果的SQL语句.
	 * 
	 * @param command 执行的SQL语句
	 * @return 返回SQL运行结果的语句
	 * @throws StatementException 返回Result为null时抛出
	 * @throws SQLException 出现数据库问题时抛出
	 */
	ResultSet ResultUpdateExec(String command)throws StatementException, SQLException;

}
