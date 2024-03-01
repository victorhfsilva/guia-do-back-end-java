package edu.victor.sorting;

import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;


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

		Set<Car> cars = new TreeSet<>(Arrays.asList(car1, car2, car3, car4, car5, 
														car6, car7, car8, car9, car10));
		
		//TreeSet sorts the elements accordingly to the comparable method
		System.out.println(cars);
		
		//Sorting by the year
		Set<Car> carsSortedByYear = new TreeSet<>(new ComparatorYear());
		for (Car car : cars) {
			carsSortedByYear.add(car);
		}
		System.out.println(carsSortedByYear);

	}

}
