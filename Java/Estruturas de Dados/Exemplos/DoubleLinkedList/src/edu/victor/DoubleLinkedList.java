package edu.victor;

public class DoubleLinkedList<T> {

	private Node<T> firstNode;
	private Node<T> lastNode;
	
	private int size;
	
	public DoubleLinkedList() {
		this.firstNode = null;
		this.lastNode = null;
		this.size = 0;
	}
	
	public T get(int index) {
		return getNode(index).getContent();
	}
	
	public void add(T content) {
		
		Node<T> newNode = new Node<>(content);
		
		if (isEmpty()) {
			firstNode = newNode;
			lastNode = newNode;
			size++;
			return;
		}
		
		newNode.setPrevNode(lastNode);
		lastNode.setNextNode(newNode);
		lastNode = newNode;
		
		size++;
	}
	
public void add(T content, int index) {
		
		if (index == size) {
			add(content);
			size++;
			return;
		}
		
		assertIndexInBounds(index);
		
		Node<T> newNode = new Node<>(content);
		Node<T> indexNode = getNode(index);
				
		if (index == 0) {
	        newNode.setNextNode(firstNode);
	        firstNode.setPrevNode(newNode);
	        firstNode = newNode;
	        size++;
	        return;
	    }
				
		Node<T> prevNode = getNode(index-1);
	    
		newNode.setNextNode(indexNode);
	    newNode.setPrevNode(prevNode);
	    prevNode.setNextNode(newNode);
	    indexNode.setPrevNode(newNode);  
	    size++;
	}

	public T remove(int index) {
		
		assertIndexInBounds(index);
		
		Node<T> removedNode = getNode(index);
		
		if (index == 0) {
			firstNode = firstNode.getNextNode();
			firstNode.setPrevNode(null);
			size--;
			return removedNode.getContent();
	    }
		
		if (index == size - 1) {
			lastNode = lastNode.getPrevNode();
			lastNode.setNextNode(null);
			size--;
			return removedNode.getContent();
		}
		
		Node<T> prevNode = removedNode.getPrevNode();
		Node<T> nextNode = removedNode.getNextNode();
		
	    prevNode.setNextNode(nextNode);
	    nextNode.setPrevNode(prevNode);
	    size--;
	    return removedNode.getContent();
	}
	
	public Node<T> getNode(int index){
		
		boolean isTheIndexInTheFirstHalf = index - size <= size/2;
		Node<T> currentNode = isTheIndexInTheFirstHalf ? firstNode: lastNode;
		
		assertIndexInBounds(index);
		
		if(index == 0) {
			return firstNode;
		}
		
		if (index == size - 1) {
			return lastNode;
		}
		
		if (isTheIndexInTheFirstHalf) {
			for (int i = 0; i < index; i++) {			
				currentNode = currentNode.getNextNode();
			}
		}
		else {
			for (int i = 0; i < size - index; i++) {
				currentNode = currentNode.getPrevNode();
			}
		}
		
		return currentNode;	
	
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
		
		return "DoubleLinkedList[" + nodesContent + "]";
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
