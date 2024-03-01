package edu.victor.sorting;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		Car car1 = new Car("Toyota", "Camry", 2010);
		Car car2 = new Car("Honda", "Civic", 2015);
		Car car3 = new Car("Ford", "Mustang", 2020);
		Car car4 = new Car("Chevrolet", "Impala", 2012);
		Car car5 = new Car("Nissan", "Altima", 2018);
		Car car6 = new Car("Tesla", "Model S", 2021);
		Car car7 = new Car("BMW", "M5", 2019);
		Car car8 = new Car("Mercedes-Benz", "C-Class", 2016);
		Car car9 = new Car("Audi", "A4", 2014);
		Car car10 = new Car("Porsche", "911", 2017);

		Map<String,Car> cars = new HashMap<>() {{
			put("4RPG769", car1);
			put("52330A", car2);
			put("HL1L0Z", car3);
			put("2ATSH4", car4);
			put("JFY8643", car5);
			put("GIJ461", car6);
			put("JFZ445", car7);
			put("BWZ2298", car8);
			put("2579025", car9);
			put("3LXM546", car10);
		}};
		
		System.out.println(cars);
		
		//Sorting by inserting order
		Map<String,Car> carsInInsertingOrder = new LinkedHashMap<>() {{
			put("4RPG769", car1);
			put("52330A", car2);
			put("HL1L0Z", car3);
			put("2ATSH4", car4);
			put("JFY8643", car5);
			put("GIJ461", car6);
			put("JFZ445", car7);
			put("BWZ2298", car8);
			put("2579025", car9);
			put("3LXM546", car10);
		}};
		System.out.println(carsInInsertingOrder);

		//Sorting by license plate order (alphabetically)
		Map<String,Car> carsOrdereByLicensePlateOrder = new TreeMap<>();
		for (Map.Entry<String, Car> entry : cars.entrySet()) {
			carsOrdereByLicensePlateOrder.put(entry.getKey(), entry.getValue());
		}
		System.out.println(carsOrdereByLicensePlateOrder);
		
		//Sorting by year
		Set<Map.Entry<String, Car>> carsOrderedByYear = new TreeSet<>(new ComparatorYear());
		carsOrderedByYear.addAll(cars.entrySet());
		System.out.println(carsOrderedByYear);
		
	}

}
