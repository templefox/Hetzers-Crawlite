package com.hetzer.crawlite.processers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class Extractor_Implementation extends AbstractExtractor {
	public boolean Extract(CrawlableURL source, CrawlJob crawlJob) {
		String[] urlstock = new String[300000];
		if (source.getWebsource() != null) {
			String regular_css = User_Regular_CSS.Regular_CSS_Fun();
			Pattern p_css;
			Matcher m_css;
			p_css = Pattern.compile(regular_css);
			m_css = p_css.matcher(source.getWebsource());
			int i_css = 0;
			while (m_css.find()) {
				urlstock[i_css] = m_css.group(1);
				i_css++;
			}
			source.setUrlnum(i_css);

			String temp;
			int i_img = 0;
			temp = source.getWebsource();
			String regular_img = User_Regular_IMG.Regular_Fun(); // Regular_FunÎª¾²Ì¬º¯Êý
			Pattern p_img;
			Matcher m_img;
			p_img = Pattern.compile(regular_img);
			m_img = p_img.matcher(temp);
			int img_initial = source.getUrlnum();
			while (m_img.find()) {
				urlstock[img_initial + i_img] = m_img.group(1);

				i_img++;
			}
			source.setUrlnum(i_img + source.getUrlnum());

			String regular_js = User_Regular_JS.Regular_JS_Fun();
			String temp1 = source.getWebsource();
			Pattern p_js;
			Matcher m_js;
			p_js = Pattern.compile(regular_js);
			m_js = p_js.matcher(temp1);
			int js_initial = source.getUrlnum();
			int i_js = 0;
			while (m_js.find()) {
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
			int i_html = 0;
			while (m_html.find()) {
				urlstock[html_initial + i_html] = m_html.group(1);
				crawlJob.getUrlProvider().add(
						new MockResource(urlstock[html_initial + i_html]),
						crawlJob);
				i_html++;
			}
			source.setUrlnum(html_initial + source.getUrlnum());
		}

		// source.setUrlstock(urlstock);
		// <<<<<<< HEAD
		// for(int i=0;i<source.getUrlnum();i++)
		// {
		// System.out.println(urlstock[i]);
		// }
		//
		// =======

		return true;

	}

}
