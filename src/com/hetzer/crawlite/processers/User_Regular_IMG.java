package com.hetzer.crawlite.processers;


public class User_Regular_IMG {
	public static String Regular_Fun()
	{
		return ".*?<img src=\"(.*?)\".*?";
	}

}
