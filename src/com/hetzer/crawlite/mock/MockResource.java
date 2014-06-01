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
	public void setwebsource(String WEBSOURCE) {
		// TODO Auto-generated method stub
		this.websource = WEBSOURCE;
	}

	@Override
	public String getWebsource() {
		return this.websource;
	}

	@Override
	public Map<String, byte[]> getmap() {
		return map;
	}

	@Override
	public void setmap(Map<String, byte[]> map) {
		this.map = map;
	}

	@Override
	public int getUrlnum() {
		return Urlnum;
	}

	@Override
	public void setUrlnum(int urlnum) {
		Urlnum = urlnum;
	}

	public MockResource(String s) {
		url = s;
	}

	@Override
	public String getContent() {
		return Content;
	}

	@Override
	public void setContent(String content) {
		Content = content;
	}

	@Override
	public boolean isHTML_Flag() {
		return HTML_Flag;
	}

	@Override
	public void setHTML_Flag(boolean hTML_Flag) {
		HTML_Flag = hTML_Flag;
	}

	@Override
	public boolean isJS_Flag() {
		return JS_Flag;
	}

	@Override
	public void setJS_Flag(boolean jS_Flag) {
		JS_Flag = jS_Flag;
	}

	@Override
	public boolean isIMG_Flag() {
		return IMG_Flag;
	}

	@Override
	public void setIMG_Flag(boolean iMG_Flag) {
		IMG_Flag = iMG_Flag;
	}

	@Override
	public boolean isCSS_Flag() {
		return CSS_Flag;
	}

	@Override
	public void setCSS_Flag(boolean cSS_Flag) {
		CSS_Flag = cSS_Flag;
	}

}
