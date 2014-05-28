package com.hetzer.crawlite.processers;


public class User_Regular_JS {
	public static String Regular_JS_Fun()
	{
		return ".*?javascript\" src=\"(.*?)\".*?";
	}

}
