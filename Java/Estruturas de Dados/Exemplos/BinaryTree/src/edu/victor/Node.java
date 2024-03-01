package edu.victor;

public class Node<T extends Comparable<T>> {
	
	private T content;
	private Node<T> leftNode;
	private Node<T> rightNode;
	
	public Node(T content) {
		this.leftNode = null;
		this.rightNode = null;
		this.content = content;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
	}

	public Node<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
	}

	@Override
	public String toString() {
		return "Node [content=" + content + "]";
	}
	
	
}
