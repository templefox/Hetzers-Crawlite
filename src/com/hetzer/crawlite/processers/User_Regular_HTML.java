package com.hetzer.crawlite.processers;

public class User_Regular_HTML {
	public static String Regular_Fun() {
		return ".*?<a href=\"(.*?)\".*?";
	}

}
