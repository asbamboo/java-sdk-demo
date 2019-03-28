package asbamboo;

import java.io.File;

/**
 * 
 * @author 李春寅<licy2013@aliyun.com>
 * @date 2019年1月24日
 */
public class Configure 
{
	public static String API_URL			= "http://developer.asbamboo.com/api";
	
	public static String API_APP_KEY		= "5c3074f378913";
	
	public static String API_SECRET			= "3a91aa8a688e812ad8c9013eea122dae";

	public static String API_VERSION		= "v1.0";
	
	public static String PROJECT_LOG_PATH	= System.getProperty("user.dir").concat(File.separator).concat("var").concat(File.separator).concat("project.log");
	
	public static String[] channels			= {"ALIPAY_PC", "ALIPAY_QRCD", "WXPAY_H5", "WXPAY_QRCD"};
	
	public static void run()
	{
		asbamboo.java.sdk.Configure.API_URL 			= Configure.API_URL;
		asbamboo.java.sdk.Configure.API_APP_KEY			= Configure.API_APP_KEY;
		asbamboo.java.sdk.Configure.API_SECRET 			= Configure.API_SECRET;
		asbamboo.java.sdk.Configure.API_VERSION			= Configure.API_VERSION;
		asbamboo.java.sdk.Configure.PROJECT_LOG_PATH	= Configure.PROJECT_LOG_PATH;
	}
}
