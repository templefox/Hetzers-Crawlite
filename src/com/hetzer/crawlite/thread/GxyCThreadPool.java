package com.hetzer.crawlite.thread;



import com.hetzer.crawlite.exception.OverFlowException;
import com.hetzer.crawlite.framework.CThread;
import com.hetzer.crawlite.framework.CThreadPool;

public class GxyCThreadPool implements CThreadPool {
	private ThreadGroup tg;
	private int max=0;
	public GxyCThreadPool(String name)
	{
		tg = new ThreadGroup(name);
	}
	
	@Override
	public void initialize(int max) {
			for(int i=0;i<max;i++)
			{
				GxyCThread thrd = new GxyCThread(tg,""+i);
				thrd.start();
				
			}
			this.max=max;
	}
	
	@Override
	public CThread[] getActiveThreads() {
		GxyCThread[] thrds = new GxyCThread[tg.activeCount()];
		GxyCThread[] temp = new GxyCThread[tg.activeCount()];
	    tg.enumerate(thrds);
	    int j=0;
	    for(int i=0;i<tg.activeCount();i++)
	    {
	    	if(!(thrds[i].getTheState()) && (!thrds[i].getAbandon()))
	    	{
	    		temp[j]=thrds[i];
	    		j=j+1;
	    	}
	    }
	    return temp;
	}

	@Override
	public CThread[] getInactiveThreads() {
		GxyCThread[] thrds = new GxyCThread[tg.activeCount()];
		GxyCThread[] temp = new GxyCThread[tg.activeCount()];
	    tg.enumerate(thrds);
	    int j=0;
	    for(int i=0;i<tg.activeCount();i++)
	    {
	    	if(thrds[i].getTheState() && (!thrds[i].getAbandon()))
	    	{
	    		temp[j]=thrds[i];
	    		j=j+1;
	    	}
	    }
	    return temp;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println(tg.activeCount());
		return tg.activeCount();
	}

	

	@Override
	public CThread[] apply(int num) throws OverFlowException {
		GxyCThread[] thrds = new GxyCThread[max];
		GxyCThread[] temp = new GxyCThread[num];
		int j = 0;
		tg.enumerate(thrds);
		synchronized (this) {
			for(int i=0;i<max && j<num;i++)
		    {
		    	if((thrds[i].getAbandon()==thrds[i].getRunTime()) && (thrds[i].isIschosen()==false))
		    	{
		    		temp[j]=thrds[i];
		    		temp[j].setIschosen(true);
		    		j=j+1;
		    	}
		    }
			if(j<num)
			{
				throw new OverFlowException();
			}
		}
			return temp;
	}
	
}
