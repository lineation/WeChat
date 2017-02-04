package com.weichat.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class File2String {
	/** 
	 * 文件转换为字符串 
	 * 
	 * @param in  字节流 
	 * @param charset 文件的字符集 
	 * @return 文件内容 
	 */ 
	public static String stream2String(InputStream in, String charset) { 
		Logger logger = Logger.getLogger(File2String.class);
		StringBuffer sb = new StringBuffer(); 
		try { 
			Reader r = new InputStreamReader(in, charset); 
			int length = 0; 
			for (char[] c = new char[1024]; (length = r.read(c)) != -1;) { 
				sb.append(c, 0, length); 
			} 
			r.close(); 
		} catch (UnsupportedEncodingException e) { 
			 logger.error("不支持的编码格式："+e);
		} catch (FileNotFoundException e) { 
			 logger.error("没有找到json文件："+e);
		} catch (IOException e) { 
			logger.error(e);
		} 
		return sb.toString(); 
	} 
}
