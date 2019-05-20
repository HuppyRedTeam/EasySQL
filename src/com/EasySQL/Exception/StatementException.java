package com.EasySQL.Exception;

/**
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
