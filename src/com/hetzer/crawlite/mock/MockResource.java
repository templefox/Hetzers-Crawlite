package com.hetzer.crawlite.mock;

import java.util.HashMap;
import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class MockResource implements CrawlableURL {
	public String websource;
	public String url = null;
	public String[] urlstock = new String[100];
	// public String[] IMGurlstock = new String[100];
	public int IMG_I;
	public String regular_img;
	public int JS_I;
	// public String[] JSurlstock = new String[100];
	public int CSS_I;
	public int Urlnum;
	// public String[] CSSurlstock= new String[100];
	// public Map map = new HashMap();

	public Map<String, String> Url_stockMap = new HashMap<String, String>();

	public Map<String, byte[]> map = new HashMap<String, byte[]>();
	public Map<String, String> map_js = new HashMap<String, String>();
	public Map<String, String> map_css = new HashMap<String, String>();
	public Map<String, String> map_img_url_stockMap = new HashMap<String, String>();
	public Map<String, String> map_css_url_stockMap = new HashMap<String, String>();
	public Map<String, String> map_js_url_stockMap = new HashMap<String, String>();

	// public Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
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
	public Map<String, String> getmap_img_url_stockMap() {
		return map_img_url_stockMap;
	}

	@Override
	public void set_img_url_stockmap(Map<String, String> map_img_url) {
		this.map_img_url_stockMap = map_img_url;
	}

	@Override
	public String[] getUrlstock() {
		return urlstock;
	}
	@Override
	public void setUrlstock(String[] urlstock) {
		this.urlstock = urlstock;
	}
	@Override
	public int getJS_I() {
		return JS_I;
	}
	@Override
	public void setJS_I(int jS_I) {
		JS_I = jS_I;
	}
	@Override
	public int getCSS_I() {
		return CSS_I;
	}
	@Override
	public void setCSS_I(int cSS_I) {
		CSS_I = cSS_I;
	}
	@Override
	public Map<String, String> getMap_css_url_stockMap() {
		return map_css_url_stockMap;
	}
	@Override
	public void setMap_css_url_stockMap(Map<String, String> map_css_url_stockMap) {
		this.map_css_url_stockMap = map_css_url_stockMap;
	}
	@Override
	public Map<String, String> getMap_js_url_stockMap() {
		return map_js_url_stockMap;
	}
	@Override
	public void setMap_js_url_stockMap(Map<String, String> map_js_url_stockMap) {
		this.map_js_url_stockMap = map_js_url_stockMap;
	}
	@Override
	public int getIMG_I() {
		return IMG_I;
	}
	@Override
	public void setIMG_I(int iMG_I) {
		IMG_I = iMG_I;
	}
	@Override
	public Map<String, String> getMap_js() {
		return map_js;
	}
	@Override
	public void setMap_js(Map<String, String> map_js) {
		this.map_js = map_js;
	}
	@Override
	public Map<String, String> getMap_css() {
		return map_css;
	}
	@Override
	public void setMap_css(Map<String, String> map_css) {
		this.map_css = map_css;
	}
	@Override
	public Map<String, String> getUrl_stockMap() {
		return Url_stockMap;
	}
	@Override
	public void setUrl_stockMap(Map<String, String> url_stockMap) {
		Url_stockMap = url_stockMap;
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

}
