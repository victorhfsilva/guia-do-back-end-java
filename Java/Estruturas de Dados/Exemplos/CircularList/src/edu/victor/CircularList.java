package edu.victor;

public class CircularList<T> {

	private Node<T> tail;
	private Node<T> head;
	private int size;
	
	public CircularList() {
		this.tail = null;
		this.head = null;
		this.size = 0;
	}
	
	public T get(int index) {
		return getNode(index).getContent();
	}
	
	public void add(T content) {
		Node<T> newNode = new Node<>(content);
				
		if (isEmpty()) {
			newNode.setNextNode(newNode);
			tail = newNode;
			head = newNode;
			size++;
			return;
		}
		
		newNode.setNextNode(tail);
		head.setNextNode(newNode);
		tail = newNode;
		size++;
		
	}
	
	public T remove(int index) {
		
		assertIndexInBounds(index);
		
		Node<T> removedNode = getNode(index);
		
		if (index == 0) {
			if (size == 1) {
				tail = null;
				head = null;
				size--;
				return removedNode.getContent();
			}
			else {
				tail = tail.getNextNode();
				head.setNextNode(tail);
				size--;
				return removedNode.getContent();
			}	
	    }
		
		Node<T> prevNode = getNode(index-1);
		
		if (index == (size - 1)) {
			head = prevNode;
			prevNode.setNextNode(tail);
			size--;
			return removedNode.getContent();
		}
		
	    prevNode.setNextNode(removedNode.getNextNode());
	    size--;
	    return removedNode.getContent();
	}
	
	@Override
	public String toString() {
		
		Node<T> currentNode = tail;
		String nodesContent = "";
		Node<T> nextNode = currentNode.getNextNode();
		
		for (int i = 0; i < size(); i++) {
			nodesContent = nodesContent + currentNode.getContent() + (nextNode != tail ? ", ": "");
			currentNode = nextNode;
			nextNode = currentNode.getNextNode();
		}
		
		return "CircularList[" + nodesContent + "]";
	}
	
	public Node<T> getNode(int index){
		
		assertIndexInBounds(index);
		
		Node<T> currentNode = tail;
				
		if(index == 0) {
			return tail;
		}
				
		for (int i = 0; i < index; i++) {			
			currentNode = currentNode.getNextNode();
		}
				
		return currentNode;	
	
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
			return this.size;
	}
	
	public void assertIndexInBounds(int index) {
		if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
	}
	
}
