package edu.victor;

public class Main {

	public static void main(String[] args) {
		
		Node<String> firstNode = new Node<> ("First Node");
		Node<String> secondNode = new Node<> ("Second Node");
		Node<String> thirdNode = new Node<> ("Third Node");
			
		firstNode.setNextNode(secondNode);
		secondNode.setNextNode(thirdNode);
		
		Node<String> currentNode = firstNode;
		
		while (currentNode != null) {
		    System.out.println(currentNode);
			currentNode = currentNode.getNextNode();
		}
		
	}

}
