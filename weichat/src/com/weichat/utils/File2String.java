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
	 * �ļ�ת��Ϊ�ַ��� 
	 * 
	 * @param in  �ֽ��� 
	 * @param charset �ļ����ַ��� 
	 * @return �ļ����� 
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
			 logger.error("��֧�ֵı����ʽ��"+e);
		} catch (FileNotFoundException e) { 
			 logger.error("û���ҵ�json�ļ���"+e);
		} catch (IOException e) { 
			logger.error(e);
		} 
		return sb.toString(); 
	} 
}
