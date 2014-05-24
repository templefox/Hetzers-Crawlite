package com.hetzer.crawlite.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.hetzer.crawlite.datamodel.CrawlableURL;
import com.hetzer.crawlite.framework.UrlProvider;
import com.hetzer.crawlite.job.CrawlJob;

public class MockURLChain implements UrlProvider {
	ArrayList<CrawlableURL> list = new ArrayList<CrawlableURL>();
	int current = 0;
	final static int timeOut = 10;

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<CrawlableURL> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(CrawlableURL e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends CrawlableURL> c) {
		// TODO Auto-generated method stub
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends CrawlableURL> c) {
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public CrawlableURL get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public CrawlableURL set(int index, CrawlableURL element) {
		// TODO Auto-generated method stub
		return list.set(index, element);
	}

	@Override
	public void add(int index, CrawlableURL element) {
		list.add(index, element);
	}

	@Override
	public CrawlableURL remove(int index) {
		// TODO Auto-generated method stub
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<CrawlableURL> listIterator() {
		// TODO Auto-generated method stub
		return list.listIterator();
	}

	@Override
	public ListIterator<CrawlableURL> listIterator(int index) {
		// TODO Auto-generated method stub
		return listIterator(index);
	}

	@Override
	public List<CrawlableURL> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public synchronized boolean hasNext() {
		if (current + 1 > list.size()) {
			try {
				Thread.sleep(timeOut);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (current + 1 > list.size()) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public synchronized CrawlableURL next() {
		return list.get(current++);
	}

	@Override
	public void remove() {
		throw new IllegalStateException();
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public CrawlableURL next(CrawlJob job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(CrawlableURL e, CrawlJob job) {
		// TODO Auto-generated method stub
		return false;
	}

}
