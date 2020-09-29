package il.ac.tau.cs.sw1.hw6;

import java.util.LinkedList;

/**
 * @inv !isEmpty() implies top() != null    // no null objects are allowed (this
 *      									   comment explains what is written in the invariant, 
 *      									   and adds no new information)
 */
public class SectionA {

	private final LinkedList<Object> elements = new LinkedList<Object>();

	/**
	 * @post !isEmpty()
	 * @post top() == o
	 */
	public void push(Object o) {
		elements.add(o);
	}

	/**
	 * @pre !isEmpty()
	 * @post @return == top()@pre // the returned value equals the return value of
	 *       top() if it was called right before pop()
	 */
	public Object pop() {
		final Object popped = top();
		elements.removeLast();
		return popped;
	}

	/**
	 * @pre !isEmpty()
	 * @post @return != null
	 */
	public Object top() {
		return elements.getLast();
	}

	/**
	 * 
	 * @post @return == true iff size() > 0
	 */
	public boolean isEmpty() {
		return elements.size() == 0;
	}

	/*
	 * no need to verify this method.
	 */
	public int size() {
		return elements.size();
	}

}
