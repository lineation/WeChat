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
 * 创建菜单
 * @author xxl
 *
 */
public class menuService {


	public static String createMenu(){

		Logger logger = Logger.getLogger(menuService.class);
		//读取按钮的json文件
		logger.info("读取json文件");
		String clickJson = "";
		InputStream in = menuService.class.getResourceAsStream("/com/weichat/service/menuJson.json"); 

		clickJson = File2String.stream2String(in,"utf-8");
		logger.info("读取到的menuJson数据：" );
		logger.info(clickJson.toString());

		String access_token= MenuUtil.getAccess_token();
		String action = ResourceBundle.getBundle("wechatParams").getString("menuCreate_url")+access_token;
		logger.info("发送创建菜单的HTTPS请求到:"+action);
		try {
			URL url = new URL(action);
			HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

			http.setRequestMethod("POST");        
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.setDoOutput(true);        
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			
			http.connect();
			OutputStream os= http.getOutputStream();    
			os.write(clickJson.getBytes("UTF-8"));//传入参数    
			os.flush();
			os.close();

			InputStream is =http.getInputStream();
			int size =is.available();
			byte[] jsonBytes =new byte[size];
			is.read(jsonBytes);
			String message=new String(jsonBytes,"UTF-8");
			return "返回信息"+message;
		} catch (MalformedURLException e) {
			logger.error("https连接失败"+e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("https连接失败"+e);
			e.printStackTrace();
		}    
		return "createMenu 失败";
	}















	/*private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private static final String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	public static void createClickButton() throws ClientProtocolException, IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException{

		Logger logger = Logger.getLogger(menuService.class);
		//读取按钮的json文件
		logger.info("读取json文件");
		String clickJson = "";
		InputStream in = menuService.class.getResourceAsStream("/com/weichat/service/menuJson.json"); 

		clickJson = file2String.stream2String(in,"utf-8");
		logger.info("读取到的menuJson数据：" + clickJson);
		//建立连接
		URL url = new URL(menuUrl);
		HttpsURLConnection conn  = (HttpsURLConnection) url.openConnection();
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		logger.info("创建SSLContext对象，并使用我们指定的信任管理器初始化");
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		logger.info("从上述SSLContext对象中得到SSLSocketFactory对象");
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		logger.info("得到SSLSocketFactory对象成功");

		conn.setSSLSocketFactory(ssf);
		conn.setDoOutput(true);
		conn.connect();
		conn.setRequestMethod("POST");// 设置请求方式（GET/POST）
		logger.info(" 设置请求方式为POST");
		DataOutputStream out = new DataOutputStream(conn .getOutputStream());
		out.write(clickJson.getBytes("utf-8"));
		logger.info("发送https post请求");
		// 刷新、关闭
		out.flush();
		out.close();


	}*/


}
