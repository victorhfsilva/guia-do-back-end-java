package edu.victor.sorting;

import java.util.Comparator;

public class ComparatorMakeModelYear implements Comparator<Car> {
	
	@Override
	public int compare(Car car1, Car car2) {
		
		int makeCompareTo = car1.getMake().compareTo(car2.getMake());
		if (makeCompareTo != 0) return makeCompareTo;
		
		int modelCompareTo = car1.getModel().compareTo(car2.getModel());
		if (modelCompareTo != 0 ) return modelCompareTo;
		
		int yearCompareTo = Integer.compare(car1.getYear(), car2.getYear());
		return yearCompareTo;
	}
}
