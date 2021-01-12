public class Car {
  int modelYear = 999;
  String modelName;
    
  public Car(int year, String name) {
    modelYear = year;
    modelName = name;
    System.out.println("I m in object myCar");
  }
  public Car(String name) {
	    modelName = name;
	    System.out.println("I m in object myCar3");
  }

     public static void main(String[] args) {
	 
    Car myCar = new Car(1969, "Mustang");
    myCar.modelYear = 2021;
    Car myCar4 = new Car(1969, "Mustang");
    myCar4.modelName = "Pacifica";
   //System.out.println(modelYear + " " + modelName);
    Car mycar2 = new Car();
    myCar.printInstanceValues();
    mycar2.printInstanceValues();
    Car myCar3 = new Car("Volvo");
    myCar3.printInstanceValues();
    myCar4.printInstanceValues();
    
  }
     
     public void printInstanceValues() {
  
	  System.out.println(modelYear + " " + modelName);
  }

	 public Car()
	 { 
		 modelYear = 2020;
	 	 modelName = "Honda";
	 	 System.out.println("I m in object myCar2");
	 }
	 
  // public Car() {
    	// System.out.println("I m in object myCar2");
     
     
     }

// Outputs 1969 Mustang


