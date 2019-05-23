package com.EasySQL.Exception;

/**所有Statable接口方法在某些情况下抛出的异常
 * 
 * @author	chenhao220
 * @version	v1.0 b1
 *
 */

public class StatementException extends Exception{
	public enum Reason{Unlogin,NullPoint,Empty};
	private static final long serialVersionUID = 1L;
	private String err;
	public StatementException(Reason r){
		switch(r){
		case Unlogin:
			err = "com.EasySQL.Exception.StatementException:尝试未登录调用！";
			super.printStackTrace();
			break;
		case NullPoint:
			err = "com.EasySQL.Exception.StatementException:查询类SQL语句返回值为空！";
			super.printStackTrace();
			break;
		case Empty:
			err = "com.EasySQL.Exception.StatementException:LinkedHashMap值为空！";
		}
	}
	public StatementException(String err){
		super(err);
	}
	@Override
	public String toString(){
		return err;
	};
	
	
}
