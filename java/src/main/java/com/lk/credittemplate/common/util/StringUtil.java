package com.lk.credittemplate.common.util;

/**
 * 
 * @author ramamohan
 *
 */
public class StringUtil {

	/**
	 * notEmpty
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean notEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * isEmpty
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		return object == null;
	}
	
	/**
	 * isEmpty
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}
	
}
