public class Car {
  int modelYear;
  String modelName;
    
  public Car(int year, String name) {
    modelYear = year;
    modelName = name;
    System.out.println("I m in object myCar");
  }
  public Car(String name) {
	    modelName = name;
	    System.out.println("I m in object myCar");
  }

     public static void main(String[] args) {
	 
    Car myCar = new Car(1969, "Mustang");
   //System.out.println(modelYear + " " + modelName);
   // Car mycar2 = new Car();
    myCar.printInstanceValues();
   // mycar2.printInstanceValues();
  }
     public void printInstanceValues() {
  
	  System.out.println(modelYear + " " + modelName);
  }
	/*
	 * public Car() { modelYear = 2021; modelName = "Honda"; }
	 */
  // public Car() {
    	// System.out.println("I m in object myCar2");
     }

// Outputs 1969 Mustang


