package edu.victor.sorting;

import java.util.Comparator;

public class ComparatorYear implements Comparator<Car>{

	@Override
	public int compare(Car car1, Car car2) {
		return Integer.compare(car1.getYear(), car2.getYear());
	}
	
}
