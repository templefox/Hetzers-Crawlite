package com.hetzer.crawlite.datamodel;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface CrawlableURL {

	public static final String WEB_SOURCE = "webSource";
	public String getURL(); // 获取URL

	public Map<String, byte[]> getmap();// 图片流

	public void setmap(Map<String, byte[]> map); // 对于图片的操作

	public int getUrlnum();

	public void setUrlnum(int num);

	public boolean isCSS_Flag();

	public void setCSS_Flag(boolean flag);

	public String getContent();

	public void setContent(String Content);

	public boolean isHTML_Flag();

	public void setHTML_Flag(boolean flag);

	public boolean isJS_Flag();

	public void setJS_Flag(boolean flag);

	public boolean isIMG_Flag();

	public void setIMG_Flag(boolean flag);

	public abstract byte[] putBoolean(String key, boolean value);

	public abstract boolean getBoolean(String key);

	public abstract byte[] putString(String key, String value);

	public abstract String getString(String key);

	public abstract byte[] putBytes(String key, byte[] value);

	public abstract byte[] getBytes(String key);

}
