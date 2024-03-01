package edu.victor;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<Integer> linkedList1 = new LinkedList<>();
		
		linkedList1.add(1);
		linkedList1.add(3);
		linkedList1.add(2,1);
						
		System.out.println("Size: " + linkedList1.size());
		System.out.println(linkedList1);
		
		System.out.println();
		System.out.println("Removed item: " + linkedList1.remove(1));
		
		System.out.println();
		System.out.println("Size: " + linkedList1.size());
		System.out.println(linkedList1);
	}

}
