package nn.util;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
	private T[] array;
	int pos;
	
	public ArrayIterator(T[] array) {
		this.array = array;
		pos = 0;
	}
	
	@Override
	public boolean hasNext() {
		return pos < array.length;
	}

	@Override
	public T next() {
		return array[pos++];
	}
}
