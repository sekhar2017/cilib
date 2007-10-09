package net.sourceforge.cilib.type.types.container;

import java.util.Iterator;
import java.util.Stack;

import net.sourceforge.cilib.container.Queue;
import net.sourceforge.cilib.container.visitor.PreOrderVisitorDecorator;
import net.sourceforge.cilib.container.visitor.PrePostVisitor;
import net.sourceforge.cilib.container.visitor.Visitor;
import net.sourceforge.cilib.type.types.AbstractType;

public abstract class AbstractTree<E extends Comparable<E>> extends AbstractType implements Tree<E> {

	protected E key;
	
	public abstract AbstractTree<E> clone();

	public void breadthFirstTraversal(Visitor<E> visitor) {
		Queue<Tree<E>> queue = new Queue<Tree<E>>();
		
		if (!isEmpty())
			queue.enqueue(this);
		
		while (!queue.isEmpty() && !visitor.isDone()) {
			Tree<E> head = queue.dequeue();
			visitor.visit(head.getKey());
			for (int i = 0; i < head.size(); i++) {
				Tree<E> child = head.getSubTree(i);
				if (!child.isEmpty())
					queue.enqueue(child);
			}
		}
	}

	public void depthFirstTraversal(PrePostVisitor<E> visitor) {
		if (visitor.isDone())
			return;
		
		if (!isEmpty()) {
			visitor.preVisit(getKey());
			for (int i = 0; i < this.size(); i++)
				this.getSubTree(i).depthFirstTraversal(visitor);

			visitor.postVisit(getKey());
		}
	}

	public void accept(Visitor<E> visitor) {
		depthFirstTraversal(new PreOrderVisitorDecorator<E>(visitor));		
	}

	public E getKey() {
		if (isEmpty())
			throw new UnsupportedOperationException("Empty trees do not have valid keys");
		
		return this.key;
	}
	
	public void setKey(E element) {
		this.key = element;
	}
	
	public boolean isEmpty() {
		return this.key == null;
	}

	public boolean addEdge(E a, E b) {
		throw new UnsupportedOperationException("Arbitary adding of edges is not allowed for Tree structures");
	}

	public int vertices() {
		throw new UnsupportedOperationException("The number of vertices is not a valid operation for Tree node structures - default value is 1");
	}

	public void randomise() {
		throw new UnsupportedOperationException("Not Implemented");		
	}

	public boolean isInsideBounds() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	public void reset() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public boolean isConnected(E a, E b) {
		throw new UnsupportedOperationException("Not implemented");
	}

	public boolean addAll(Structure<? extends E> structure) {
		throw new UnsupportedOperationException("Implementation needed");
	}

	public boolean removeAll(Structure<E> structure) {
		throw new UnsupportedOperationException("Implementation needed");
	}

	public Iterator<E> iterator() {
		return (Iterator<E>) new TreeIterator();
	}
	
	public int size() {
		return this.getDegree();
	}

	public int edges() {
		return this.getDegree();
	}

	public int getDimension() {
		throw new UnsupportedOperationException("Implementation needed");
	}

	public String getRepresentation() {
		StringBuffer buffer = new StringBuffer();
		PrintingVisitor<E> visitor = new PrintingVisitor<E>(buffer);
		this.accept(visitor);
		
		return buffer.toString();
	}
	
	@SuppressWarnings("hiding")
	private class PrintingVisitor<E> extends PrePostVisitor<E> {
		private StringBuffer buffer;
		
		public PrintingVisitor(StringBuffer buffer) {
			this.buffer = buffer;
		}

		@Override
		public void visit(E o) {
			if (buffer.length() != 0)
				buffer.append(",");
			
			buffer.append(o.toString());			
		}
	}
	
	/**
	 * Provides a simple Iterator for trees
	 */
	protected class TreeIterator implements Iterator<E> {
		
		private Stack<Tree<E>> stack;
		
		public TreeIterator() {
			stack = new Stack<Tree<E>>();
			stack.push(AbstractTree.this);
		}

		public boolean hasNext() {
			return !stack.isEmpty();
		}

		public E next() {
			if (stack.isEmpty())
				throw new UnsupportedOperationException();
			
			Tree<E> top = stack.pop();
			for (int i = top.size() - 1; i >= 0; i--) {
				Tree<E> subTree = top.getSubTree(i);
				if (!subTree.isEmpty())
					stack.push(subTree);
			}
			
			return top.getKey();
		}

		public void remove() {
			throw new UnsupportedOperationException("Cannot remove a tree using an iterator.");
		}
	}

}