/**
 * File : HttpClientException.java <br/>
 * Author : lenovo <br/>
 * Version : 1.1 <br/>
 * Date : 2016年11月21日 <br/>
 * Modify : <br/>
 * Package Name : com.zhongpin.zp.common.exception <br/>
 * Project Name : zp-common <br/>
 * Description : <br/>
 * 
 */

package com.liupeihao.wchat.plugin.utils.http;

/**
 * ClassName : HttpClientException <br/>
 * Function : HttpClient类，异常类. <br/>
 * Reason : HttpClient类，异常类. <br/>
 * Date : 2016年11月21日 下午5:27:49 <br/>
 * 
 * @author : LPH <br/>
 * @version : 1.1 <br/>
 * @since : JDK 1.6 <br/>
 * @see
 */

public class HttpClientException extends RuntimeException {
	

	/**  
	 * serialVersionUID : 
	 */
	private static final long serialVersionUID = -3145712246188907187L;

	public HttpClientException() {
		super();
	}
	
	public HttpClientException(String message){
		super(message);
	}
	
	public HttpClientException(String message, Throwable cause){
		super(message,cause);
	}
	
	public HttpClientException(Throwable cause){
		super(cause);
	}

}
