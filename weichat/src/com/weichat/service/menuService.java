package com.weichat.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.weichat.utils.MenuUtil;
import com.weichat.utils.File2String;

/**
 * �����˵�
 * @author xxl
 *
 */
public class menuService {


	public static String createMenu(){

		Logger logger = Logger.getLogger(menuService.class);
		//��ȡ��ť��json�ļ�
		logger.info("��ȡjson�ļ�");
		String clickJson = "";
		InputStream in = menuService.class.getResourceAsStream("/com/weichat/service/menuJson.json"); 

		clickJson = File2String.stream2String(in,"utf-8");
		logger.info("��ȡ����menuJson���ݣ�" );
		logger.info(clickJson.toString());

		String access_token= MenuUtil.getAccess_token();
		String action = ResourceBundle.getBundle("wechatParams").getString("menuCreate_url")+access_token;
		logger.info("���ʹ����˵���HTTPS����:"+action);
		try {
			URL url = new URL(action);
			HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

			http.setRequestMethod("POST");        
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.setDoOutput(true);        
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//���ӳ�ʱ30��
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //��ȡ��ʱ30��
			
			http.connect();
			OutputStream os= http.getOutputStream();    
			os.write(clickJson.getBytes("UTF-8"));//�������    
			os.flush();
			os.close();

			InputStream is =http.getInputStream();
			int size =is.available();
			byte[] jsonBytes =new byte[size];
			is.read(jsonBytes);
			String message=new String(jsonBytes,"UTF-8");
			return "������Ϣ"+message;
		} catch (MalformedURLException e) {
			logger.error("https����ʧ��"+e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("https����ʧ��"+e);
			e.printStackTrace();
		}    
		return "createMenu ʧ��";
	}















	/*private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private static final String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static void createClickButton() throws ClientProtocolException, IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException{

		Logger logger = Logger.getLogger(menuService.class);
		//��ȡ��ť��json�ļ�
		logger.info("��ȡjson�ļ�");
		String clickJson = "";
		InputStream in = menuService.class.getResourceAsStream("/com/weichat/service/menuJson.json"); 

		clickJson = file2String.stream2String(in,"utf-8");
		logger.info("��ȡ����menuJson���ݣ�" + clickJson);
		//��������
		URL url = new URL(menuUrl);
		HttpsURLConnection conn  = (HttpsURLConnection) url.openConnection();
		// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
		logger.info("����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��");
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// ������SSLContext�����еõ�SSLSocketFactory����
		logger.info("������SSLContext�����еõ�SSLSocketFactory����");
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		logger.info("�õ�SSLSocketFactory����ɹ�");

		conn.setSSLSocketFactory(ssf);
		conn.setDoOutput(true);
		conn.connect();
		conn.setRequestMethod("POST");// ��������ʽ��GET/POST��
		logger.info(" ��������ʽΪPOST");
		DataOutputStream out = new DataOutputStream(conn .getOutputStream());
		out.write(clickJson.getBytes("utf-8"));
		logger.info("����https post����");
		// ˢ�¡��ر�
		out.flush();
		out.close();


	}*/


}
