package com.hetzer.crawlite.processers;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class Analyze_Implementation extends Analyze_Abstract {
	public void doit(CrawlableURL source)
	{
		source.setwebsource(null);
		source.set_img_url_stockmap(null);
		source.setCSS_I(0);
		source.setIMG_I(0);
		source.setJS_I(0);
		source.setmap(null);
		source.setMap_css(null);
		source.setMap_css_url_stockMap(null);
		source.setMap_js(null);
		source.setMap_js_url_stockMap(null);
	}

}
