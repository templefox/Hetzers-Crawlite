package com.hetzer.crawlite.mock;

import java.util.Map;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class MockResource implements CrawlableURL {
	String url;
	
	public MockResource(String s) {
		url = s;
	}
	@Override
	public String getURL() {
		return url;
	}
	@Override
	public void setwebsource(String WEBSOURCE) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getWebsource() {
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
	public Map<String, String> getmap_img_url_stockMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set_img_url_stockmap(Map<String, String> map_img_url) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getIMG_I() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setIMG_I(int iMG_I) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getJS_I() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setJS_I(int jS_I) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getCSS_I() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setCSS_I(int cSS_I) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getMap_css_url_stockMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMap_css_url_stockMap(Map<String, String> map_css_url_stockMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getMap_js_url_stockMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMap_js_url_stockMap(Map<String, String> map_js_url_stockMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getMap_js() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMap_js(Map<String, String> map_js) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getMap_css() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setMap_css(Map<String, String> map_css) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getUrl_stockMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUrl_stockMap(Map<String, String> url_stockMap) {
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
	public String[] getUrlstock() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUrlstock(String[] urlstock) {
		// TODO Auto-generated method stub
		
	}
	
}
