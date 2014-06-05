package com.hetzer.crawlite.processers;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class HTMLGetCookie extends AbstractFetcher {
	public void fetch(CrawlableURL source) {
		// String surl = "http://login.goodjobs.cn/index.php/action/UserLogin";

		/**
		 * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using
		 * java.net.URL and //java.net.URLConnection
		 */
		if (((source.getURL().matches("http.*?com.*?"))
				|| (source.getURL().matches(".*?net.*?"))
				|| (source.getURL().matches(".*?org.*?"))
				|| (source.getURL().matches(".*?edu.*?")) || (source.getURL()
				.matches(".*?gov.*?")))
				&& (!source.getURL().matches(".*?gif"))
				&& (!source.getURL().matches(".*?jpg"))
				&& (!source.getURL().matches(".*?png"))
				&& (!source.getURL().matches(".*?js"))
				&& (!source.getURL().matches(".*?css"))) {
			try {
				URL url = new URL(source.getURL());
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();

				/**
				 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
				 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
				 */
				connection.setDoOutput(true);
				/**
				 * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
				 */
				OutputStreamWriter out = new OutputStreamWriter(
						connection.getOutputStream(), "GBK");
				// 其中的memberName和password也是阅读html代码得知的，即为表单中对应的参数名称
				out.write(User_Account.getAccount()); // post的关键所在！
				// remember to clean up
				out.flush();
				out.close();

				// 取得cookie，相当于记录了身份，供下次访问时使用
				String cookieVal = connection.getHeaderField("Set-Cookie");
				source.setCookie(cookieVal);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
