package com.hetzer.crawlite.processers;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hetzer.crawlite.datamodel.CrawlableURL;

public class HTMLGetCookie extends AbstractFetcher {
	public void fetch(CrawlableURL source) {
		// String surl = "http://login.goodjobs.cn/index.php/action/UserLogin";

		/**
		 * ����Ҫ��URL�µ�URLConnection�Ի��� URLConnection���Ժ����׵Ĵ�URL�õ������磺 // Using
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
				 * Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ��
				 * ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡��������������
				 */
				connection.setDoOutput(true);
				/**
				 * ���Ϊ�˵õ�OutputStream�������������Լ����Writer���ҷ���POST��Ϣ�У����磺 ...
				 */
				OutputStreamWriter out = new OutputStreamWriter(
						connection.getOutputStream(), "GBK");
				// ���е�memberName��passwordҲ���Ķ�html�����֪�ģ���Ϊ���ж�Ӧ�Ĳ�������
				out.write(User_Account.getAccount()); // post�Ĺؼ����ڣ�
				// remember to clean up
				out.flush();
				out.close();

				// ȡ��cookie���൱�ڼ�¼����ݣ����´η���ʱʹ��
				String cookieVal = connection.getHeaderField("Set-Cookie");
				source.setCookie(cookieVal);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
