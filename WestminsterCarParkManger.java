import java.io.*;
import java.util.*;

import vehicle.park.MotorBike;
import vehicle.park.Van;
import vehicle.park.Car;
import vehicle.park.Vehicle;

public class WestminsterCarParkManger implements CarParkManager {
	//arraylist to hold objects
	static List<Vehicle> vehicles = new ArrayList<>();
	
	static int parkingSlots = 0;
	static int carCount = 0;
	static int vanCount = 0;
	static int bikeCount = 0 ;
	static int numberOfVehicles = 0;
	static int hours = 0;
	static int parkingSlot = 0;
	
	static String vehicleType = null;
	static String vehicleId = null;
	static String vehicleBrand =  null;
	static String date = null;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException{
		WestminsterCarParkManger carpark = new WestminsterCarParkManger();
		
		String menu = null;
		
		do{
			System.out.println();

			//menu to select functions
			System.out.println("************************************************************************************************");
			System.out.println("*                 Welcome to WESTMINSTER car park manager                                      *");
			System.out.println("************************************************************************************************");
			System.out.println("* A : Press A to checkin the Vehicles                                                          *");
			System.out.println("* D : Press D to checkout the vehicles                                                         *");
			System.out.println("* V : Press V to get the current list of the vehicles in the park                              *");
			System.out.println("* R : Press R to get the current list of the vehicles in the park according to the date        *");
			System.out.println("* P : Press P to get the percentage of each vehicle type                                       *");
			System.out.println("* I : Press I to get the list of the vehicles ordered Chronologically                          *");
			System.out.println("* L : Press L to get the vehicles for the longest parked time and newest vehicle               *");
			System.out.println("* B : Press B to get the BILL for each vehicle in the parking lot                              *");
			System.out.println("************************************************************************************************");

			menu = sc.next().toUpperCase();
			switch (menu) {
				
				case "A": {
					carpark.addVehicle();
					break;
				}
				case "D": {
					carpark.remove();
					break;
				}
				case "V": {
					carpark.view();
					break;
				}
				case "R": {
					carpark.specifiedDate();
					break;
				}
				case "B": {
					carpark.calculateBill();
					break;
				}
				case "P": {
					carpark.vehiclePercentage();
					break;
				}
				case "L": {
					carpark.longestParkedVehicle();
					break;
				}
				case "I": {
					chronological();
					break;
				}
				case "X": {
					System.out.println("Exit");
					System.exit(0);
					break;
				}
				default :{
					System.out.println("Please Enter a valid Input...");
					break;
				}
			}

		}while(menu != "X");
		
		
	}
	
	//function to view the vehicles currently parked
	public void view(){
		for(int i=0;i<vehicles.size();i++){
			System.out.println(vehicles.get(i).getVehicleId());
		}
	}
	
	//functions to add vehicles to the parking lot
	public void addVehicle(){
		//declaring the variables to take inputs
		String carColor = null;
		int numberofDoors = 0;
		double cargoVolume = 0.0;
		double engineSize = 0.0;
		
		int day =0;
		int month =0;
		int year =0;
		
		String str[];
		
		try{
		//getting inputs through scanner
		System.out.println("Enter the Vehicle Plate Number :");
		vehicleId = sc.next().toUpperCase();
		
		System.out.println("Eneter the brand of the Vehicle :");
		vehicleBrand = sc.next().toUpperCase();
		
		System.out.println("Enter the Parking Slot Number :");
		parkingSlot = sc.nextInt();
		
		System.out.println("Enter the Vehicle type :");
		vehicleType = sc.next().toUpperCase();
		
		System.out.println("Enter the Date in the format DD.MM.YYYY :");
		date = sc.next();
		//splitter function to split the date into day,month and year
		str = date.split("\\.");
		day = Integer.parseInt(str[0]);
		if(day<32 && day>0){
			month = Integer.parseInt(str[1]);
			if(month>0 && month<13){
				year = Integer.parseInt(str[2]);
			}else{
				System.out.println("Invalid Month");
			}
		}else{
			System.out.println("Invalid Date");
		}
		
		System.out.println("Enter the time in hours the vehicle checks in :");
		hours = sc.nextInt();
		//checking the vehicle type and getting inputs according to the vehicle
		if(vehicleType.equalsIgnoreCase("CAR") && parkingSlots<21){
			System.out.println("Enter the color of the CAR :");
			carColor = sc.next().toUpperCase();
			System.out.println("Eneter the NUmber of Doors in the CAR :");
			numberofDoors = sc.nextInt();
			
			Car car = new Car(vehicleId,vehicleBrand,vehicleType,year,month,day,hours,numberofDoors,carColor); 
			
			vehicles.add(car);
			parkingSlots = parkingSlots +1;
			carCount++;
			numberOfVehicles++;
		}
		
		if(vehicleType.equalsIgnoreCase("VAN") && parkingSlots<21){
			System.out.println("Enter the Cargo Volume of the VAN :");
			cargoVolume = sc.nextDouble();
			
			Van van = new Van(vehicleId,vehicleBrand,vehicleType,year,month,day,hours,cargoVolume);
			
			parkingSlots = parkingSlots +2;
			vehicles.add(van);
			vanCount++;
			numberOfVehicles++;
		}
		
		if(vehicleType.equalsIgnoreCase("MOTORBIKE") && parkingSlots<21){
			System.out.println("Enter the Engine Capacity of the MotorBike :");
			engineSize = sc.nextDouble();
			
			MotorBike bike = new MotorBike(vehicleId,vehicleBrand,vehicleType,year,month,day,hours,engineSize);
			
			parkingSlots = parkingSlots +1;
			vehicles.add(bike);
			bikeCount++;
			numberOfVehicles++;
		}
		}catch(Exception e){
			System.out.println("Invalid Input..Please enter a valid Input...");
		}
		
		//printing the vehicles entered into the parkinglot in each day
		try {
			File carPark = new File(date);//file name is set as date 
			PrintWriter printWriter = new PrintWriter(carPark);
			for (int j = 0; j < vehicles.size(); j++) {
				if (vehicles.get(j).getVehicleId() != null) {
					printWriter.println(vehicles.get(j).getVehicleId());
				} else {
					printWriter.println(vehicles.get(j).getVehicleId());
				}
			}
			System.out.println("Succesfully added");
			printWriter.close();
		} 
		catch (IOException e) {
			System.out.println("Check your file.");
		}
		
	}
	
	//function to remove a vehicle from the parkinglot
	public void remove(){
		try{
		System.out.println("Enter the vehicle PLATE NUMBER that is leaving :");
		vehicleId = sc.next().toUpperCase();
		
		System.out.println("Enter the type of the vehicle :");
		vehicleType = sc.next().toUpperCase();
		
		System.out.println("Enter the Parking Slot Number :");
		parkingSlot = sc.nextInt();
		
		for(int i=0;i<vehicles.size();i++){
			
			if(vehicles.get(i).getVehicleId().equalsIgnoreCase(vehicleId) && vehicles.get(i).getVehicleType().equalsIgnoreCase(vehicleType)){
				System.out.println("Details for vehicle "+vehicles.get(i).getVehicleId()+" deleted..");
				if(vehicleType.equalsIgnoreCase("CAR") || vehicleType.equalsIgnoreCase("MOTORBIKE")){
					parkingSlots = parkingSlots - 1;
				}
				
				if(vehicleType.equalsIgnoreCase("VAN")){
					parkingSlot = parkingSlot - 2;
				}
				vehicles.remove(i);
				numberOfVehicles--;
			}
		}
		}catch(Exception e){
			System.out.println("Invalid Input..Please enter a valid Input...");
		}
		
	}
	
	//function to view vehicles chronologically sorted
	public static void chronological(){
		try{
			System.out.println("List of the vehicles ordered Chronologically : ");
			int index =vehicles.size()-1;
			for(int i=0;i<vehicles.size();i++){
				System.out.println(vehicles.get(index).getVehicleType()+" "+vehicles.get(index).getVehicleId()+" "+vehicles.get(index).getHours()+"h");
				index--;
			}
		}catch(Exception e){
			System.out.println("No vehicles in the parkinglot...");
		}
	}
	
	//function to view the percentage of each vehicle type currently in the parkinglot
	public void vehiclePercentage(){
		double carPercentage=0;
		double vanPercentage=0;
		double bikePercentage=0;
		try{
			for ()
		for(int i=0;i<vehicles.size();i++){
			if(vehicles.get(i).getVehicleType().equalsIgnoreCase("CAR")){
				carPercentage = (carCount*100)/numberOfVehicles;
			}
			if(vehicles.get(i).getVehicleType().equalsIgnoreCase("MOTORBIKE")){
				bikePercentage = (bikeCount*100)/numberOfVehicles;
			}
			if(vehicles.get(i).getVehicleType().equalsIgnoreCase("VAN")){
				vanPercentage = (vanCount*100)/numberOfVehicles;
			}
		}
		
		System.out.println("Percentage of the CARS in the parking is : "+carPercentage+"%");
		System.out.println("Percentage of the VANS in the parking is : "+vanPercentage+"%");
		System.out.println("Percentage of the BIKES in the parking is : "+bikePercentage+"%");
		}catch(Exception e){
			System.out.println("No vehicles in the Parkinglot...");
		}
	}
	
	//function to view the vehicle that was parked for the longest time and last parked vehicle
	public void longestParkedVehicle(){
		if(vehicles.size()!=0){
		System.out.println("The vehicle that is parked for the longest time :");
		System.out.println("Vehicle plate Number:"+ vehicles.get(0).getVehicleId()+"\nVehicle Type:"+vehicles.get(0).getVehicleType()+"\nVehicle Entry Time:"+vehicles.get(0).getHours()+" h");
		
		int i = vehicles.size()-1;
		System.out.println();
		System.out.println("The last vehicle that was parked :");
		System.out.println("Vehicle plate Number:"+ vehicles.get(i).getVehicleId()+"\nVehicle Type:"+vehicles.get(i).getVehicleType()+"\nVehicle Entry Time:"+vehicles.get(i).getHours()+" h");
		}else{
			System.out.println("No Vehicles in the parking lot...");
		}
		
	}
	
	//function to view the vehicles that were parked in a specified day
	public void specifiedDate() throws IOException{
		try{
		System.out.println("Enter the date to view the vehicles in the park in the specified date in the format DD.MM.YYYYY :");
		date = sc.next();
		
		BufferedReader firstReader = new BufferedReader(new FileReader(date));
		String sCurrentLine;
		while((sCurrentLine = firstReader.readLine()) != null){
			System.out.println(sCurrentLine);
		}
		firstReader.close();
		}catch(Exception e){
			System.out.println("No vehicle details found in the specified date...");
		}
	}
	
	//function to calculate the bill for the currently parked vehicles
	public void calculateBill(){
		System.out.println("Enter the current time in the mode of hours only :");
		int hours = sc.nextInt();
		int checkedInTime=0;
		int timeDifference=0;
		int timeDifference2=0; 
		
		for(int i=0;i<vehicles.size();i++){
			checkedInTime = vehicles.get(i).getHours();
			timeDifference = hours - checkedInTime;
			
			if(timeDifference <= 3 && timeDifference > 0){
				System.out.println(vehicles.get(i).getVehicleId()+" "+timeDifference*3+"$");
			}else if(timeDifference > 3 && timeDifference >0){
				timeDifference2 = (timeDifference-3)+9;
				System.out.println(vehicles.get(i).getVehicleId()+" "+timeDifference2+"$");
				
			}else if(timeDifference == 0 ){
				System.out.println(vehicles.get(i).getVehicleId()+" "+30+"$");
			}
			
		}
	}
	
	public boolean validation(int input,String str){
		boolean validity = true;
		do{
			switch(str){
			case"A":{
				if(str.matches("\\d{2}.\\d{2}.\\d{4}")){
					validity = true;
				}else{
					System.out.println("Invalid input...");
				}
				
			}
			}
		}while(validity!=true);
		return validity;
		
	}

}
