package edu.victor.methods;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<>(Map.of("Alice", 25, "Bob", 30, "Charlie", 35, "David", 40));
		System.out.println(map);
		
		System.out.println(map);
		
		//TreeMap
		Map<String, Integer> treeMap = new TreeMap<>(Map.of("Alice", 25, "Bob", 30, "Charlie", 35, "David", 40));
		System.out.println(treeMap);
		
		//LinkedMap sorts the elements by inserting order
		Map<String, Integer> linkedMap = new LinkedHashMap<>(Map.of("Alice", 25, "Bob", 30, "Charlie", 35, "David", 40));
		System.out.println(linkedMap);
				
		//put
		map.put("Linda", 25);
		System.out.println(map);
		
		//put can also be used to overwrite the value of a key
		map.put("Bob", 27);
		System.out.println(map);
		
		//containsKey
		boolean contains = map.containsKey("Alice");
		System.out.println(contains);
		
		//get
		int get =map.get("Linda");
		System.out.println(get);
		
		//keyset
		Set<String> keys = map.keySet();
		System.out.println(keys);
		
		//values
		Collection<Integer> values = map.values();
		System.out.println(values);
		
		//min
		int min = Collections.min(map.values());
		System.out.println(min);
		
		//max
		int max = Collections.max(map.values());
		System.out.println(max);
		

		//Make each entry a map
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue().equals(min)) {
				String lowestValue = entry.getKey();
				System.out.println("Lowest Value: " + lowestValue);
			}
		}
		
		//iterator: sum example
		Iterator<Integer> iterator = map.values().iterator();
		Integer sum = 0;
		while(iterator.hasNext()) {
			sum += iterator.next();
		}
		System.out.println(sum);
		
		//size
		int size = map.size();
		System.out.println(size);
				
		//remove by object
		map.remove("Alice");
		System.out.println(map);
		
		//clear
		map.clear();
		System.out.println(map);
		
		//isEmpty
		boolean isEmpty = map.isEmpty();
		System.out.println(isEmpty);
	
	}

}
