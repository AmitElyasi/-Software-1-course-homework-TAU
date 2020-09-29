package il.ac.tau.cs.sw1.ex7.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 * Add your code to this class !!! *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T> {
	public HashMap<T, Integer> map;

	public HashMapHistogram() {
		map = new HashMap<T,Integer>();
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(this);
	}

	@Override
	public void addItem(T item) {
		if (map.containsKey(item)) {
			this.map.replace(item, map.get(item) + 1);
		} else {
			map.put(item, 1);
		}

	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if (map.containsKey(item)) {
			map.remove(item);
		} else {
			throw new IllegalItemException();
		}

	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k <= 0) {
			throw new IllegalKValueException(k);
		}
		if (map.containsKey(item)) {
			this.map.replace(item, map.get(item) + k);
		} else {
			map.put(item, k);
		}

	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (!map.containsKey(item)) {
			throw new IllegalItemException();
		}

		int newTimes = map.get(item) - k;

		if (newTimes < 0 || k <= 0) {
			throw new IllegalKValueException(newTimes);
		} else if (newTimes == 0) {
			this.removeItem(item);
		} else {
			this.map.replace(item, newTimes);
		}

	}

	@Override
	public int getCountForItem(T item) {
		if (map.containsKey(item)) {
			return map.get(item);
		} else {
			return 0;
		}
	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item : items) {
			this.addItem(item);
		}

	}

	@Override
	public void clear() {
		map.clear();

	}

	@Override
	public Set<T> getItemsSet() {
		return map.keySet();
	}

	@Override
	public void merge(IHistogram<T> anotherHistogram) {
		for (T item : anotherHistogram) {
			try {
				this.addItemKTimes(item, anotherHistogram.getCountForItem(item));
			} catch (IllegalKValueException e) {
				e.printStackTrace();
			}
		}
	}

}
