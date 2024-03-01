package edu.victor;

public class Main {

	public static void main(String[] args) {
		Queue<Integer> queue1 = new Queue<>();
		
		queue1.enqueue(new Node<Integer>(1));
		queue1.enqueue(new Node<Integer>(2));
		queue1.enqueue(new Node<Integer>(3));
		
		while(queue1.getLastNode() != null) {
			System.out.println();
			System.out.println(queue1.toString());
			System.out.println("First Node: "+queue1.dequeue().getContent());
		}
	}

}
