package com.hetzer.crawlite.processers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extractor_Implementation extends AbstractExtractor {
	public boolean Extract(CrawlableURL source,CrawlJob crawlJob) {
		String[] urlstock = new String[300000];
		// String regular_css="<link rel=\"stylesheet\" href=\"(.*?)\".*?";
		// Map<String, String> url_stock;
		if (source.getWebsource() != null) {
			String regular_css = User_Regular_CSS.Regular_CSS_Fun();
			Pattern p_css;
			Matcher m_css;
			p_css = Pattern.compile(regular_css);
			m_css = p_css.matcher(source.getWebsource());
			// String [] store_css = new String[30];
			int i_css = 0;
			while (m_css.find()) {
				// source.setUrl_stockmap.put("JS_URL"+i_css, m_css.group(1));
				// source.getUrl_stockMap().put(
				// "URL" + source.getUrlnum() + i_css, m_css.group(1));
				urlstock[i_css] = m_css.group(1);
				i_css++;
			}
			source.setUrlnum(i_css);
			// source.setCSS_I(i_css);
			// source.setMap_css_url_stockMap(map_css_url);
			String temp;
			// Map<String, String> img_url_stock = new HashMap<String,
			// String>();
			// String[] imgstoreurl = new String[100];
			int i_img = 0;
			temp = source.getWebsource();

			String regular_img = User_Regular_IMG.Regular_Fun(); // Regular_FunÎª¾²Ì¬º¯Êý
			// System.out.println(regular_img);

			// String regular_img = ".*?<img src=\"(.*?)\".*?";
			Pattern p_img;
			Matcher m_img;
			p_img = Pattern.compile(regular_img);
			m_img = p_img.matcher(temp);
			int img_initial = source.getUrlnum();
			while (m_img.find()) {
				// source.getUrl_stockMap().put(
				// "URL" + source.getUrlnum() + i_img, m_img.group(1));
				urlstock[img_initial + i_img] = m_img.group(1);
				
				i_img++;
			}
			// source.set_img_url_stockmap(img_url_stock);
			// source.setIMG_I(i_img);

			source.setUrlnum(i_img + source.getUrlnum());

			// Map<String, String> map_js_url = new HashMap<String, String>();
			String regular_js = User_Regular_JS.Regular_JS_Fun();

			String temp1 = source.getWebsource();

			Pattern p_js;
			Matcher m_js;
			p_js = Pattern.compile(regular_js);
			m_js = p_js.matcher(temp1);
			// String [] store_js = new String[30];
			int js_initial = source.getUrlnum();
			int i_js = 0;
			while (m_js.find()) {
				// source.getUrl_stockMap().put("URL" + source.getUrlnum() +
				// i_js,
				// m_js.group(1));
				urlstock[i_js + js_initial] = m_js.group(1);
				i_js++;
			}
			source.setUrlnum(i_js + source.getUrlnum());

			String regular_html = User_Regular_HTML.Regular_Fun();
			Pattern p_html;
			Matcher m_html;
			p_html = Pattern.compile(regular_html);
			m_html = p_html.matcher(source.getWebsource());
			int html_initial = source.getUrlnum();
			// String [] store_css = new String[30];
			int i_html = 0;
			while (m_html.find()) {
				// source.setUrl_stockmap.put("JS_URL"+i_css, m_css.group(1));
				// source.getUrl_stockMap().put(
				// "URL" + source.getUrlnum() + i_css, m_css.group(1));
				urlstock[html_initial + i_html] = m_html.group(1);
				crawlJob.getUrlProvider().add(new MockResource(urlstock[html_initial + i_html]),crawlJob);
				i_html++;
			}
			source.setUrlnum(html_initial + source.getUrlnum());
		}

		source.setUrlstock(urlstock);

		return true;

	}

}
