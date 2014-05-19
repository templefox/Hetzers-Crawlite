package com.hetzer.crawlite.mock;

import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.CThreadPool;

public class MockCThreadPool implements CThreadPool {

	@Override
	public CThread[] getActiveThreads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CThread[] getInactiveThreads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(int max) {
		System.out.println("mock init:"+ max);
	}

	@Override
	public CThread[] apply(int num) {
		System.out.println("apply");
		return new CThread[]{new MockCThread(),new MockCThread()};
	}

}
