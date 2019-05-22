package com.EasySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.EasySQL.Exception.StatementException;

/**
 * EasySQL接口，提供大多数操作数据库的办法.
 * <p>目前可以使用com.EasySQL.JDBC_Driver实例化，需求mysql-connector支持库提供JDBC驱动.
 * 
 * @version v1.0 b4
 * @author chenhao220
 * 
 */
public interface EasySQL extends Statable{

	
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
	 * @throws StatementException	尝试未登录调用时抛出
	 * @since v1.0 b2
	 */
	void DeleteDatabase(String name) throws SQLException, StatementException;

	/**使用CREATE TABLE SQL语句在指定库中创建表.
	 * <p>注意：不推荐使用此方法，已被{@link com.EasySQL.EasySQL#CreateTable(String, LinkedHashMap)}替代.
	 * <p>注意：此方法不检查表状态，若表已经存在则会抛出异常.
	 * 
	 * @param name	表名
	 * @param list	含有Key为列名,Value为列数据类型的hMAP,推荐使用LinkedHashMap.
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException	尝试未登录调用时抛出
	 * @since v1.0 b4
	 */
	@Deprecated
	void CreateTable(String name,Map<String, String> list) throws SQLException, StatementException;
	
	/**使用CREATE TABLE SQL语句在指定库中创建表.
	 * <p>注意：此方法不检查表状态，若表已经存在则会抛出异常.
	 * @param name	表名
	 * @param list	含有Key为列名,Value为列数据类型的LinkedHashMap.
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException	尝试未登录调用时抛出
	 * @since v1.0 b6
	 */
	void CreateTable(String name,LinkedHashMap<String, String> list)throws SQLException, StatementException;
	
	/**关闭所有打开的连接，并登出数据库登陆，重置所有资源
	 * 
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException 尝试未登录调用时抛出
	 * @since v1.0 b3
	 */
	void CloseAll() throws SQLException, StatementException;
	
	/**使用DROP TABLE SQL语句在指定库中删除表.
	 * 
	 * @param name	表名
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException 尝试未登录调用时抛出
	 * @since v1.0 b4
	 */
	void DeleteTable(String name) throws SQLException, StatementException;
	
	/**使用SELECT DISTINCT SQL语句查询指定表的指定列无重复元素.
	 * 
	 * @param Table	表名
	 * @param Column	列名，可输入多个列或*，若为空则默认*.
	 * @return	含有查询结果的ResultSet
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException	尝试未登录调用时抛出
	 */
	ResultSet getDistinctResult(String Table,String... Column) throws SQLException, StatementException;
	
	/**使用SELECT SQL语句查询指定表指定列的元素.
	 * 
	 * @param Table	表名
	 * @param Column	列名，可输入多个列或*，若为空则默认*.
	 * @return	含有查询结果的ResultSet
	 * @throws SQLException	出现数据库问题时抛出
	 * @throws StatementException	尝试未登录调用时抛出
	 */
	ResultSet getNormalResult(String Table,String... Column) throws SQLException, StatementException;
	
}
