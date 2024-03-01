package edu.victor;

public class Main {

	public static void main(String[] args) {
		CircularList<Integer> circularList1 = new CircularList<>();
		
		circularList1.add(1);
		circularList1.add(2);
		circularList1.add(3);
		circularList1.add(4);
						
		System.out.println("Size: " + circularList1.size());
		System.out.println(circularList1);
			
		System.out.println();
		System.out.println("Removed item: " + circularList1.remove(2));
		
		System.out.println();
		System.out.println("Size: " + circularList1.size());
		System.out.println(circularList1);
		
	}

}
