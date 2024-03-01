package edu.victor;

public class Main {

	public static void main(String[] args) {
		
		Stack<Integer> stack1 = new Stack<>();
		
		stack1.push(new Node<Integer>(1));
		stack1.push(new Node<Integer>(2));
		stack1.push(new Node<Integer>(3));
		
		while(stack1.getTopNode() != null) {
			System.out.println();
			System.out.println(stack1.toString());
			System.out.println("Top Node: "+stack1.pop().getContent());
		}
		
	}

}
