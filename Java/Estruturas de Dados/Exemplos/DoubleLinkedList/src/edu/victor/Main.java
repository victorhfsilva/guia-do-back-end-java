package edu.victor;

public class Main {

	public static void main(String[] args) {
		DoubleLinkedList<Integer> doubleLinkedList1 = new DoubleLinkedList<>();
		
		doubleLinkedList1.add(1);
		doubleLinkedList1.add(4);
		doubleLinkedList1.add(2,1);
		doubleLinkedList1.add(3,2);
						
		System.out.println("Size: " + doubleLinkedList1.size());
		System.out.println(doubleLinkedList1);
		
		
		System.out.println();
		System.out.println("Removed item: " + doubleLinkedList1.remove(2));
		
		System.out.println();
		System.out.println("Size: " + doubleLinkedList1.size());
		System.out.println(doubleLinkedList1);
		
	}

}
