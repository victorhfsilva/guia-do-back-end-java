package edu.victor;

public class Queue<T> {
	
	private Node<T> lastNode;
	
	public Queue() {
		this.lastNode = null;
	}
	
	public Node<T> getLastNode() {
		return lastNode;
	}

	public void setLastNode(Node lastNode) {
		this.lastNode = lastNode;
	}

	public boolean isEmpty() {
		return lastNode == null;
	}
	
	public void enqueue(Node newNode) {
		newNode.setNextNode(lastNode);
		lastNode = newNode;
	}
	
	
	public Node<T> first() {
		
		if(!this.isEmpty()) {
			Node<T> currentNode = lastNode;
			
			while (currentNode.getNextNode() != null) {
				currentNode = currentNode.getNextNode();
			}
			return currentNode;
		}
		
		return null;	
	}
	
	public Node<T> dequeue() {
		
		if(!this.isEmpty()) {
			
			Node<T> currentNode = lastNode;
			Node<T> previousNode = lastNode;
			
			while (currentNode.getNextNode() != null) {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
			
			boolean isTheQueueLimitedToOneNode = previousNode.getNextNode() == null;
			
			if (isTheQueueLimitedToOneNode) {
				lastNode = null;
			}
			else {
				previousNode.setNextNode(null);
			}
			return currentNode;
		}
		
		return null;	
		
	}
	
	@Override
	public String toString() {
		
		Node<T> currentNode = lastNode;
		String nodesContent = "";
		Node<T> nextNode = currentNode != null ? currentNode.getNextNode() : null;
		
		while (currentNode != null) {
			nodesContent = nodesContent + currentNode.getContent() + (nextNode != null ? ", ": "");
			currentNode = nextNode;
			nextNode = currentNode != null ? currentNode.getNextNode() : null;
		}
		
		return "Queue [" + nodesContent + "]";
	}
	
	
}
