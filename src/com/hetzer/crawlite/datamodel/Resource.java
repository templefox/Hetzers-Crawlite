package com.hetzer.crawlite.datamodel;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Resource implements CrawlableURL {
	private Map<String, byte[]> byteMap;
	private Map<String, Boolean> boolMap;
	private Map<String, String> stringMap;
	private String url;
	private int depth = 0;

	public Resource(String url) {
		this.url = url;
	}

	private Map<String, byte[]> getByteMap() {
		if (byteMap == null) {
			byteMap = new HashMap<String, byte[]>(8);
		}
		return byteMap;
	}

	private Map<String, Boolean> getBoolMap() {
		if (boolMap == null) {
			boolMap = new HashMap<String, Boolean>(8);
		}
		return boolMap;
	}

	private Map<String, String> getStringMap() {
		if (stringMap == null) {
			stringMap = new HashMap<String, String>(8);
		}
		return stringMap;
	}

	@Override
	public String getURL() {
		return url;
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return depth;
	}

	@Override
	public void setDepth(int num) {
		depth = num;
	}

	@Override
	public byte[] getBytes(String key) {
		return getByteMap().get(key);
	}

	@Override
	public byte[] putBytes(String key, byte[] value) {
		return getByteMap().put(key, value);
	}

	@Override
	public String getString(String key) {
		return getStringMap().get(key);
	}

	@Override
	public String putString(String key, String value) {
		return getStringMap().put(key, value);
	}

	@Override
	public Boolean getBoolean(String key) {
		return getBoolMap().get(key);
	}

	@Override
	public Boolean putBoolean(String key, boolean value) {
		return getBoolMap().put(key, value);
	}
}
