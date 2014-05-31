package com.hetzer.crawlite.datamodel;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface CrawlableURL {

	public String getURL();
	public void setwebsource(String WEBSOURCE);
	public String getWebsource();
	public Map<String, byte[]> getmap();
	public void setmap(Map<String,byte[]> map);               //对于图片的操作
	public Map<String,String> getmap_img_url_stockMap();
	public void set_img_url_stockmap(Map<String, String> map_img_url);
	public int getIMG_I();
	public void setIMG_I(int iMG_I);
	
	public int getJS_I();
	public void setJS_I(int jS_I);
	
	public int getCSS_I();
	public void setCSS_I(int cSS_I);
	
	public Map<String, String> getMap_css_url_stockMap() ;
	public void setMap_css_url_stockMap(Map<String, String> map_css_url_stockMap);
	public Map<String, String> getMap_js_url_stockMap();
	public void setMap_js_url_stockMap(Map<String, String> map_js_url_stockMap) ;
	public Map<String, String> getMap_js();
	public void setMap_js(Map<String, String> map_js) ;
	public Map<String, String> getMap_css();
	public void setMap_css(Map<String, String> map_css);
	
	public Map<String, String> getUrl_stockMap();
	public void setUrl_stockMap(Map<String, String> url_stockMap);
	
	public int getUrlnum();
	public void setUrlnum(int num);
	
	public String[] getUrlstock();
	public void setUrlstock(String[] urlstock);
	

	
}

