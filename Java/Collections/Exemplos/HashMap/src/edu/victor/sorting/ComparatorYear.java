package edu.victor.sorting;

import java.util.Comparator;
import java.util.Map;

public class ComparatorYear implements Comparator<Map.Entry<String, Car>>{

	@Override
	public int compare(Map.Entry<String, Car> car1, Map.Entry<String, Car> car2) {
		return Integer.compare(car1.getValue().getYear(), car2.getValue().getYear());
	}
	
}
