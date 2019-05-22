package com.EasySQL.Exception;

/**所有Statable接口方法在某些情况下抛出的异常
 * 
 * @author	chenhao220
 * @version	v1.0 b1
 *
 */

public class StatementException extends Exception{
	private static final long serialVersionUID = 1L;
	private String err;
	public StatementException(String Errmessage){
		super(Errmessage);
		this.err = Errmessage;
	}
	
	@Override
	public String toString(){
		return err;
	};
	
	
}
