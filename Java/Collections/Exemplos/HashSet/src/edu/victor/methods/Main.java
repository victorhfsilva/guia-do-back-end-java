package edu.victor.methods;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {

	public static void main(String[] args) {
		
		Set<Double> set = new HashSet<>(Arrays.asList(5.14, 2.16, 3.14, 9d, 17.21, 5.14, 13.16));
		System.out.println(set);
		
		//TreeSet sorts the elements by value
		Set<Double> treeSet = new TreeSet<>(Arrays.asList(5.14, 2.16, 3.14, 9d, 17.21, 5.14, 13.16));
		System.out.println(treeSet);
		
		//LinkedSet sorts the elements by inserting order
		Set<Double> linkedSet = new LinkedHashSet<>(Arrays.asList(5.14, 2.16, 3.14, 9d, 17.21, 5.14, 13.16));
		System.out.println(linkedSet);
				
		//add
		set.add(12.8);
		System.out.println(set);
		
		//contains
		boolean contains = set.contains(17.21);
		System.out.println(contains);
		
		
		//Collections.min
		double min = Collections.min(set);
		System.out.println(min);
		
		//Collections.max
		double max = Collections.max(set);
		System.out.println(max);
		
		//iterator: sum example
		Iterator<Double> iterator = set.iterator();
		Double sum = 0d;
		while(iterator.hasNext()) {
			sum += iterator.next();
		}
		System.out.println(sum);
		
		//size
		int size = set.size();
		System.out.println(size);
				
		//remove by object
		set.remove(17.21);
		System.out.println(set);
		
		//clear
		set.clear();
		System.out.println(set);
		
		//isEmpty
		boolean isEmpty = set.isEmpty();
		System.out.println(isEmpty);
	
	}

}
