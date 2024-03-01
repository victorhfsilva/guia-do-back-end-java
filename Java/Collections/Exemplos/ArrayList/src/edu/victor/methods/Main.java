package edu.victor.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Double> list = new ArrayList<>(Arrays.asList(5.14, 2.16, 3.14, 9d, 17.21, 5.14, 13.16));
		
		//toString
		System.out.println(list);
		
		//indexOf
		double index = list.indexOf(2.16);
		System.out.println(index);
		
		//add
		list.add(3.14);
		System.out.println(list);
		
		//set
		list.set(3, 5.14);
		System.out.println(list);
		
		//contains
		boolean contains = list.contains(17.21);
		System.out.println(contains);
		
		//get
		double get = list.get(7);
		System.out.println(get);
		
		//Collections.min
		double min = Collections.min(list);
		System.out.println(min);
		
		//Collections.max
		double max = Collections.max(list);
		System.out.println(max);
		
		//iterator: sum example
		Iterator<Double> iterator = list.iterator();
		Double sum = 0d;
		while(iterator.hasNext()) {
			sum += iterator.next();
		}
		System.out.println(sum);
		
		//size
		int size = list.size();
		System.out.println(size);
		
		//remove by index
		list.remove(2);
		System.out.println(list);
		
		//remove by object
		list.remove(17.21);
		System.out.println(list);
		
		//clear
		list.clear();
		System.out.println(list);
		
		//isEmpty
		boolean isEmpty = list.isEmpty();
		System.out.println(isEmpty);
	}

}
