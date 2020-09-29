package il.ac.tau.cs.sw1.ex7.histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**************************************
 * Add your code to this class !!! *
 **************************************/

public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T> {
	private HashMapHistogram<T> map;
	private ArrayList<T> items;
	private int index=0;

	
	public HashMapHistogramIterator(HashMapHistogram<T> HMH) {
		map = HMH;
		items = new ArrayList<T>(map.getItemsSet());
		items.sort(new HistogramComperator(map));
		Collections.reverse(items);
	}

	@Override
	public boolean hasNext() {
		return (this.index < this.items.size());
	}

	@Override
	public T next() {
		index++;
		return items.get(index-1);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); // no need to change this
	}
	
	public class HistogramComperator implements Comparator<T>{
		HashMapHistogram<T> map;
		
		public HistogramComperator(HashMapHistogram<T> HMH) {
		map=HMH;
		}
		
		@Override
		public int compare(T item1, T item2 ) {
			
			if(map==null) {
				System.out.println("MAP");
			}
			if(item1==null || item2==null) {
				System.out.println("item");
			}
			
			if (map.getCountForItem(item1)-map.getCountForItem(item2)!=0) {
				return map.getCountForItem(item1)-map.getCountForItem(item2);
				
			}else {
				return item2.compareTo(item1);
			}
		}
		
	}
}