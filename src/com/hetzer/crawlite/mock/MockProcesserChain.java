package com.hetzer.crawlite.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.hetzer.crawlite.framework.ProcesserChain;
import com.hetzer.crawlite.framework.Processor;

public class MockProcesserChain implements ProcesserChain {
	ArrayList<Processor> list = new ArrayList<Processor>();
	Iterator<Processor> iterator = list.iterator();
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
	public Iterator<Processor> iterator() {
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
	public boolean add(Processor e) {
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
	public boolean addAll(Collection<? extends Processor> c) {
		// TODO Auto-generated method stub
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Processor> c) {
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
	public Processor get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public Processor set(int index, Processor element) {
		// TODO Auto-generated method stub
		return list.set(index, element);
	}

	@Override
	public void add(int index, Processor element) {
list.add(index, element);
	}

	@Override
	public Processor remove(int index) {
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
	public ListIterator<Processor> listIterator() {
		// TODO Auto-generated method stub
		return list.listIterator();
	}

	@Override
	public ListIterator<Processor> listIterator(int index) {
		// TODO Auto-generated method stub
		return listIterator(index);
	}

	@Override
	public List<Processor> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public synchronized boolean hasNext() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}

	@Override
	public synchronized Processor next() {
		// TODO Auto-generated method stub
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new IllegalStateException();
	}
}
