package edu.victor.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

		List<Car> cars = new ArrayList<>(Arrays.asList(car1, car2, car3, car4, car5, 
														car6, car7, car8, car9, car10));
		
		
		//Inserting Order
		System.out.println(cars);
		
		//Random Sorting
		Collections.shuffle(cars);
		System.out.println(cars);
		
		//Sorting by Make (the attribute used in compareTo)
		Collections.sort(cars);
		System.out.println(cars);
		
		//Sorting by Model
		Collections.sort(cars, new ComparatorModel());
		//the following code also can be used: cars.sort(new ComparatorModel());
		System.out.println(cars);
		
		//Sorting by Model
		Collections.sort(cars, new ComparatorYear());
		//the following code also can be used: cars.sort(new ComparatorYear());
		System.out.println(cars);
		
		//Sorting by Make, Model and Year
		Collections.sort(cars, new ComparatorMakeModelYear());
		//the following code also can be used: cars.sort(new ComparatorMakeModelYear()));
		System.out.println(cars);

	}

}
