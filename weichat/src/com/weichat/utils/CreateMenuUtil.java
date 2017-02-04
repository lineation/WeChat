package com.weichat.utils;

import org.apache.log4j.Logger;

import com.weichat.service.menuService;

public class CreateMenuUtil {
	static Logger logger = Logger.getLogger(CreateMenuUtil.class);
	public static void main(String[] args) {
		//创建菜单
		logger.info("开始创建菜单。。。。");
		String createMenuResult = menuService.createMenu();
		logger.info(createMenuResult);
	}
}
