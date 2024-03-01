package edu.victor;

public class BinaryTree<T extends Comparable<T>> {
	
	private Node<T> root;
	
	public BinaryTree() {
		this.root = null;
	}

	public void insert(T content){
		Node<T> newNode = new Node<>(content);
		root = insert(root, newNode);
	}
	
	private Node<T> insert(Node<T> currentNode, Node<T> newNode){
		
		if (currentNode == null) {
			return newNode;
		}
		else if(newNode.getContent().compareTo(currentNode.getContent()) < 0) {
			currentNode.setLeftNode(insert(currentNode.getLeftNode(), newNode));
		}
		else {
			currentNode.setRightNode(insert(currentNode.getRightNode(), newNode));
		}
		return currentNode;
	}
	
	public void remove(T content) {
	    root = remove(root, content);
	}

	private Node<T> remove(Node<T> currentNode, T content) {
	    if (currentNode == null) {
	        return null;
	    }

	    int compareResult = content.compareTo(currentNode.getContent());

	    if (compareResult < 0) {
	    	
	        currentNode.setLeftNode(remove(currentNode.getLeftNode(), content));
	        
	    } else if (compareResult > 0) {
	    	
	        currentNode.setRightNode(remove(currentNode.getRightNode(), content));
	        
	    } else {
	    	
	        // Node to remove has no children or only one child
	        if (currentNode.getLeftNode() == null) {
	        	
	            return currentNode.getRightNode();
	            
	        } else if (currentNode.getRightNode() == null) {
	        	
	            return currentNode.getLeftNode();
	            
	        }
	        // Node to remove has two children
	        else {
	        	
	            Node<T> minNode = findMin(currentNode.getRightNode());
	            currentNode.setContent(minNode.getContent());
	            currentNode.setRightNode(remove(currentNode.getRightNode(), minNode.getContent()));
	        }
	        
	    }
	    return currentNode;
	}

	private Node<T> findMin(Node<T> node) {
	    while (node.getLeftNode() != null) {
	        node = node.getLeftNode();
	    }
	    return node;
	}
	
	public void printInOrder() {
		System.out.println();
		printInOrder(root);
	}
	
	private void printInOrder(Node<T> currentNode) {
		
		if (currentNode != null) {
			printInOrder(currentNode.getLeftNode());
			System.out.print(currentNode.getContent() + ", ");
			printInOrder(currentNode.getRightNode());
		}
		
	}
	
	public void printPosOrder() {
		System.out.println();
		printPosOrder(root);
	}
	
	private void printPosOrder(Node<T> currentNode) {
		
		if (currentNode != null) {
			printInOrder(currentNode.getLeftNode());
			printInOrder(currentNode.getRightNode());
			System.out.print(currentNode.getContent() + ", ");
		}
		
	}
	
	public void printPreOrder() {
		System.out.println();
		printPreOrder(root);
	}
	
	private void printPreOrder(Node<T> currentNode) {
		
		if (currentNode != null) {
			System.out.print(currentNode.getContent() + ", ");
			printInOrder(currentNode.getLeftNode());
			printInOrder(currentNode.getRightNode());
		}
		
	}
	
	
}
