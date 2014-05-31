package com.hetzer.crawlite.framework.urlProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.h2.tools.Server;

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
			stat.execute("CREATE TABLE TEST(URL VARCHAR(255),ISDONE BOOLEAN,JOB VARCHAR(255),ID INT AUTO_INCREMENT(0,1) PRIMARY KEY)");
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

	@Override
	public synchronized CrawlableURL next() {
		Statement statement;
		ResultSet result = null;
		String url = null;
		try {
			statement = connection.createStatement();
			result = statement
					.executeQuery("select top 1 URL from TEST where ISDONE = false ");
			if (result.next()) {
				url = result.getString("URL");
				int r = statement
						.executeUpdate("update TEST SET ISDONE = 1 WHERE URL = '"
								+ url + "';");
				if (r == 0) {
					throw new IllegalAccessError("Can't refresh");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new MockResource(url);
	}

	public synchronized CrawlableURL next(CrawlJob job) {
		Statement statement;
		ResultSet result = null;
		String url = null;
		try {
			statement = connection.createStatement();
			result = statement
					.executeQuery("select top 1 URL from TEST where ISDONE = false "+"and JOB = '" + job.getName() + "';");
			if (result.next()) {
				url = result.getString("URL");
				int r = statement
						.executeUpdate("update TEST SET ISDONE = 1 WHERE URL = '"
								+ url + "' and JOB = '" + job.getName() + "';");
				if (r == 0) {
					throw new IllegalAccessError("Can't refresh");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MockResource(url);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<CrawlableURL> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(CrawlableURL e) {
		Statement stat;
		int result = 0;
		try {
			stat = connection.createStatement();
			result = stat.executeUpdate("insert into TEST VALUES ('"
					+ e.getURL() + "',false)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return result == 1 ? true : false;
	}

	public boolean add(CrawlableURL e, CrawlJob job) {
		Statement stat;
		int result = 0;
		try {
			stat = connection.createStatement();
			result = stat.executeUpdate("insert into TEST SET URL = '"
					+ e.getURL() + "' ,  ISDONE = 'false' ,  JOB = '" + job.getName() + "'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return result == 1 ? true : false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends CrawlableURL> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends CrawlableURL> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public CrawlableURL get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrawlableURL set(int index, CrawlableURL element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, CrawlableURL element) {

	}

	@Override
	public CrawlableURL remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<CrawlableURL> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<CrawlableURL> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrawlableURL> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
