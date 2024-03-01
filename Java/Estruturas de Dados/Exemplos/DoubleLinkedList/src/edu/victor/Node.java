package edu.victor;

public class Node<T> {

	private T content;
	Node<T> prevNode;
	Node<T> nextNode;
	
	public Node(T content) {
		this.prevNode = null;
		this.nextNode = null;
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Node<T> getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node<T> prevNode) {
		this.prevNode = prevNode;
	}

	public Node<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		return "Node [content=" + content + "]";
	}
	
}
