package com.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.shiro.ShiroUser;
/**
 * 公用类
 * @author luojiao
 * @date 2014-12-11
 *
 */
public class CommonUtil {
	/**
	 * MD5加密
	 * @param plainText 加密字符串
	 * @return 
	 */
	public static String md5(String plainText){
		StringBuffer buf = new StringBuffer("");
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 

			int i; 

			buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
			buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
			} 

			System.out.println("result: " + buf.toString());//32位的加密 

			System.out.println("result: " + buf.toString().substring(8,24));//16位的加密 

			return buf.toString();
			} catch (NoSuchAlgorithmException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			} 
		    return buf.toString();
	}

	/*
	 *  函数功能说明 TODO:获取当前登录用户实体类 Administrator修改者名字 2013-5-10修改日期 修改内容
	 * 
	 * @Title: getCurrendUser
	 * @Description: TODO:
	 * @param @return 设定文件
	 * @return Users 返回类型
	 * @throws
	 */
	public static ShiroUser getCurrendUser() {
		Subject subject = SecurityUtils.getSubject();
		return (ShiroUser) subject.getSession().getAttribute(Constants.SHIRO_USER);
	}
	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
}
