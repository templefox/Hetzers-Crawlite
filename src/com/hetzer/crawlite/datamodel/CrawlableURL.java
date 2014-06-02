package com.hetzer.crawlite.datamodel;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface CrawlableURL {

	public static final String WEB_SOURCE = "WEBSOURCE";
	public static final String URL = "URL";
	public static final String CSS = "CSS";
	public static final String JS = "JS";
	public static final String IMG = "IMG";
	public static final String HTML = "HTML";

	public String getURL();

	public int getDepth();

	public void setDepth(int depth);

	public abstract Boolean putBoolean(String key, boolean value);

	public abstract boolean getBoolean(String key);

	public abstract String putString(String key, String value);

	public abstract String getString(String key);

	public abstract byte[] putBytes(String key, byte[] value);

	public abstract byte[] getBytes(String key);

}
