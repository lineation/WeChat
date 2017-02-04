package com.weichat.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.weichat.service.WechatController;
import com.weichat.service.menuService;
import com.weichat.utils.MsgTypeUtil;

public class WechatIoServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sig =  request.getParameter("signature");
		String timestamp =  request.getParameter("timestamp");
		String nonce =  request.getParameter("nonce");
		String echostr =  request.getParameter("echostr");
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("wechatParams");
		String token = resourceBundle.getString("Token");
		List<String> list = new ArrayList<String>();
		list.add(nonce);
		list.add(token);
		list.add(timestamp);
		Collections.sort(list);
		String hash = getHash(list.get(0)+list.get(1)+list.get(2), "SHA-1");
		if(sig.equals(hash)){ // ��֤��ǩ���Ƿ���ȷ
			response.getWriter().println(echostr);
		}else{
			response.getWriter().println("");
		}
	}

	public  String getHash(String source, String hashType) {
		StringBuilder sb = new StringBuilder();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance(hashType);
			md5.update(source.getBytes());
			for (byte b : md5.digest()) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Logger logger = Logger.getLogger(WechatIoServlet.class);

		//�������
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//logger.debug("XMLתjson:"+XML2Json.xml2JSON("menuXML.xml"));
		
		
		//����΢�ſͻ���post���͹��������ݣ��ŵ�map������
		Map<String, String> map = null;
		try {
			map = MsgTypeUtil.parseXml(request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		//����΢�Ŵ������̣���ȡ������
		String result = new WechatController().wechatProgress(map);
		logger.info("΢�Ŵ������̽�������ô�����Ϊ��"+result);
		//���ش�����ɵĽ����΢�ŷ�����
		OutputStream os = response.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();
	}

}
