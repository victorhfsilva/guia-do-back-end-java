package edu.victor;

public class Main {
	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
		
		binaryTree1.insert(21);
		binaryTree1.insert(13);
		binaryTree1.insert(9);
		binaryTree1.insert(15);
						
		binaryTree1.printInOrder();
			
		binaryTree1.remove(9);
		
		binaryTree1.printInOrder();

	}
}
