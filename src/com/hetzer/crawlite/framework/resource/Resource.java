package com.hetzer.crawlite.framework.resource;

import java.nio.charset.Charset;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Resource implements CrawlableURL {
	private Map<String, byte[]> map;

	public Resource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, byte[]> getmap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setmap(Map<String, byte[]> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getUrlnum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUrlnum(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCSS_Flag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCSS_Flag(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContent(String Content) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHTML_Flag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHTML_Flag(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isJS_Flag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJS_Flag(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIMG_Flag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIMG_Flag(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getBytes(String key) {
		return map.get(key);
	}

	@Override
	public byte[] putBytes(String key, byte[] value) {
		return map.put(key, value);
	}

	@Override
	public String getString(String key) {
		return new String(map.get(key));
	}

	@Override
	public byte[] putString(String key, String value) {
		return map.put(key, value.getBytes());
	}

	@Override
	public boolean getBoolean(String key) {
		byte b = map.get(key)[0];
		return b == 0x0;
	}

	@Override
	public byte[] putBoolean(String key, boolean value) {
		byte b;
		if (value) {
			b = 0x0;
		} else {
			b = 0xF;
		}
		return map.put(key, new byte[] { b });
	}
}
