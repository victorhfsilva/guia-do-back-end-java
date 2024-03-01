package edu.victor;

public class LinkedList<T> {

	private Node<T> firstNode;
	
	
	public LinkedList() {
		this.firstNode = null;
	}
				
	
	public Node<T> getFirstNode() {
		return firstNode;
	}

	
	public void setFirstNode(Node<T> firstNode) {
		this.firstNode = firstNode;
	}

	
	public T get(int index) {
		return getNode(index).getContent();
	}
	
	
	public void add(T content) {
		Node<T> newNode = new Node<>(content);
				
		if (isEmpty()) {
			firstNode = newNode;
			return;
		}
		
		Node<T> lastNode = getNode(size()-1);
		
		lastNode.setNextNode(newNode);
		
	}
		
	
	public void add(T content, int index) {
		
		if (index == size()) {
			add(content);
			return;
		}
		
		assertIndexInBounds(index);
		
		Node<T> newNode = new Node<>(content);
		Node<T> indexNode = getNode(index);
				
		if (index == 0) {
	        newNode.setNextNode(firstNode);
	        firstNode = newNode;
	        return;
	    }
		
		Node<T> prevNode = getNode(index-1);
				
	    newNode.setNextNode(indexNode);
	    prevNode.setNextNode(newNode);		
	
	}
		
	
	public T remove(int index) {
		
		assertIndexInBounds(index);
		
		Node<T> removedNode = getNode(index);
		
		if (index == 0) {
			firstNode = firstNode.getNextNode();
			return removedNode.getContent();
	    }
		
		Node<T> prevNode = getNode(index-1);
		
	    prevNode.setNextNode(removedNode.getNextNode());
	    return removedNode.getContent();
	}
	
	
	public Node<T> getNode(int index){
		Node<T> currentNode = firstNode;
		
		assertIndexInBounds(index);
				
		for (int i = 0; i < index; i++) {			
			currentNode = currentNode.getNextNode();
		}
		return currentNode;
		
	}
	
	
	public int size() {
		
		int size = 0;
		Node<T> currentNode = firstNode;
		
		while(currentNode != null) {
			size++;
			currentNode = currentNode.getNextNode();
		}
		
		return size;
		
	}
	
	
	public boolean isEmpty() {
		return firstNode == null;
	}
	
	
	public void assertIndexInBounds(int index) {
		if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
	}


	@Override
	public String toString() {
		
		Node<T> currentNode = firstNode;
		String nodesContent = "";
		Node<T> nextNode = currentNode != null ? currentNode.getNextNode() : null;
		
		for (int i = 0; i < size(); i++) {
			nodesContent = nodesContent + currentNode.getContent() + (nextNode != null ? ", ": "");
			currentNode = nextNode;
			nextNode = currentNode != null ? currentNode.getNextNode() : null;
		}
		
		return "LinkedList[" + nodesContent + "]";
	}
	
	
	
}
