package edu.victor;

public class Stack<T> {

	private Node<T> topNode;
	
	public Stack() {
		this.topNode = null;
	}
	
	public Node<T> getTopNode() {
		return topNode;
	}
	
	public boolean isEmpty() {
		return topNode == null ? true : false;	
	}
	
	public Node<T> top() {
		return topNode;
	}
	
	public Node<T> pop() {
		if (!isEmpty()) {
			Node<T> previousTopNode = this.topNode;
			topNode = previousTopNode.getNextNode();
			return previousTopNode;
		}
		return null;
	}
	
	public void push(Node<T> newNode) {
		Node<T> previousTopNode = this.topNode;
		topNode = newNode;
		topNode.setNextNode(previousTopNode);
	}

	@Override
	public String toString() {
		
		Node<T> currentNode = topNode;
		String nodesContent = "";
		Node<T> nextNode = currentNode != null ? currentNode.getNextNode() : null;
		
		while (currentNode != null) {
			nodesContent = nodesContent + currentNode.getContent() + (nextNode != null ? ", ": "");
			currentNode = nextNode;
			nextNode = currentNode != null ? currentNode.getNextNode() : null;
		}
		
		return "Stack [" + nodesContent + "]";
	}
	
}
