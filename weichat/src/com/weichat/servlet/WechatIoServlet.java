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
		if(sig.equals(hash)){ // 验证下签名是否正确
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

		//解决乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//logger.debug("XML转json:"+XML2Json.xml2JSON("menuXML.xml"));
		
		
		//解析微信客户端post发送过来的数据，放到map集合中
		Map<String, String> map = null;
		try {
			map = MsgTypeUtil.parseXml(request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		//调用微信处理流程，获取处理结果
		String result = new WechatController().wechatProgress(map);
		logger.info("微信处理流程结束，获得处理结果为："+result);
		//返回处理完成的结果给微信服务器
		OutputStream os = response.getOutputStream();
		os.write(result.getBytes("UTF-8"));
		os.flush();
		os.close();
	}

}
