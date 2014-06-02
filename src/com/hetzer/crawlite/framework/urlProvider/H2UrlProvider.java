package com.hetzer.crawlite.framework.urlProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.h2.tools.Server;

import com.hetzer.crawlite.CrawlJobManager;
import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;
import com.hetzer.crawlite.mock.MockResource;

public class H2UrlProvider implements UrlProvider {

	private Server server;
	private String port = "9090";
	private String dbDir = "./h2db/sample";
	private String user = "zhoujiang";
	private String password = "123456";
	private Connection connection;
	private boolean hasNext = true;
	private boolean is;
	private static H2UrlProvider provider = new H2UrlProvider();

	private H2UrlProvider() {
		startServer();
	}

	public static H2UrlProvider instance() {
		return provider;
	}

	public void startServer() {
		try {
			server = Server.createTcpServer(new String[] { "-tcpPort", port })
					.start();
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:mem:" + dbDir,
					user, password);
			Statement stat = connection.createStatement();
			stat.execute("DROP TABLE IF EXISTS TEST");
			stat.execute("CREATE TABLE TEST(URL VARCHAR(66535),ISDONE BOOLEAN,JOB VARCHAR(255),PIRORITY INTEGER,PRIMARY KEY(JOB,URL))");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopServer() {
		if (server != null) {
			server.stop();
		}
	}

	@Override
	public synchronized boolean hasNext() {
		return hasNext;
	}

	public static int i = 0;
	@Override
	public synchronized CrawlableURL next(CrawlJob job) {
		PreparedStatement statement1;
		PreparedStatement statement2;

		ResultSet result = null;
		String url = null;
		int depth = 0;
		try {
			statement1 = connection
					.prepareStatement("select URL,PIRORITY from TEST where ISDONE = false "
							+ "and JOB = ? order by PIRORITY asc limit 1");
			statement1.setString(1, job.getName());
			result = statement1.executeQuery();

			if (result.next()) {
				url = result.getString("URL");
				depth = result.getInt("PIRORITY");
				statement2 = connection
						.prepareStatement("update TEST SET ISDONE = 1 WHERE URL = ? and JOB = ?");
				statement2.setString(1, url);
				statement2.setString(2, job.getName());
				int r = statement2.executeUpdate();
				if (r == 0) {
					throw new IllegalAccessError("Can't refresh");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CrawlableURL r = CrawlJobManager.makeUrlObject(url);
		r.setDepth(depth);
		return r;
	}

	@Override
	public boolean add(CrawlableURL e, CrawlJob job, int pirority) {
		PreparedStatement preparedStatement;
		int result = 0;
		try {
			preparedStatement = connection
					.prepareStatement("insert into TEST SET URL = ? , ISDONE = 'false' , JOB = ? ,PIRORITY = ?");
			preparedStatement.setString(1, e.getURL());
			preparedStatement.setString(2, job.getName());
			preparedStatement.setInt(3, pirority);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			if (e1.getSQLState().equals("23505")) {
				/*System.out.println("DUPLICATE_KEY:" + e.getURL() + " in "
						+ job.getName());*/
			} else {
				e1.printStackTrace();
			}
		}
		return result == 1 ? true : false;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
