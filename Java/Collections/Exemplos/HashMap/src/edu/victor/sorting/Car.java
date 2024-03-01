package edu.victor.sorting;

import java.util.Objects;

public class Car implements Comparable<Car> {
	
    private String make;
    private String model;
    private int year;
    private int currentSpeed;
    
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.currentSpeed = 0;
    }
    
    public void accelerate(int mph) {
        this.currentSpeed += mph;
    }
    
    public void brake(int mph) {
        this.currentSpeed -= mph;
    }
    
    public String getMake() {
        return this.make;
    }

	public String getModel() {
        return this.model;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public int getCurrentSpeed() {
        return this.currentSpeed;
    }
    
    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
	
    @Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", year=" + year + "]";
	}

	@Override
	public int compareTo(Car car) {
		int makeCompareTo = this.getMake().compareTo(car.getMake());
		if (makeCompareTo != 0) return makeCompareTo;
		
		int modelCompareTo = this.getModel().compareTo(car.getModel());
		if (modelCompareTo != 0 ) return modelCompareTo;
		
		int yearCompareTo = Integer.compare(this.getYear(), car.getYear());
		return yearCompareTo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentSpeed, make, model, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return currentSpeed == other.currentSpeed && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && year == other.year;
	}
  
}
