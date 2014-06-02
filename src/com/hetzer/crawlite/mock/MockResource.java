package com.hetzer.crawlite.mock;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class MockResource implements CrawlableURL {
	public String websource;
	public String url = null;

	public int Urlnum = 0;

	public Map<String, byte[]> map = new HashMap<String, byte[]>();

	public boolean CSS_Flag = false;
	public boolean HTML_Flag = false;
	public boolean JS_Flag = false;
	public boolean IMG_Flag = false;
	public String Content = null;

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public int getDepth() {
		return Urlnum;
	}

	@Override
	public void setDepth(int urlnum) {
		Urlnum = urlnum;
	}

	public MockResource(String s) {
		url = s;
	}

	public Boolean putBoolean(String key, boolean value) {
		return null;
	}

	public Boolean getBoolean(String key) {
		return false;
	}

	public String putString(String key, String value) {
		websource = value;
		return null;
	}

	public String getString(String key) {
		return websource;
	}

	public byte[] putBytes(String key, byte[] value) {
		return null;
	}

	public byte[] getBytes(String key) {
		return null;
	}

}
