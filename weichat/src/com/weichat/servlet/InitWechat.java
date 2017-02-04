package com.weichat.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
/**
 * 启动微信服务器时，初始化相关
 * @author xxl
 *
 */
public class InitWechat extends HttpServlet implements ServletContextListener {


	private static final long serialVersionUID = 1L;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
