package com.ps.dealership;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init(){
        dealership = DealershipFileManager.getDealership();
    }
    public UserInterface(){
        init();

    }
    public void display(){
        System.out.println("Welcome to the dealership program");

        int mainMenuCommand;

        do{
            System.out.println("1. Get by price");
            System.out.println("2. Get by make/model");
            System.out.println("3. Get by year");
            System.out.println("4. Get by color");
            System.out.println("5. Get by mileage");
            System.out.println("6. Get by type");
            System.out.println("7. Get by all");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("10. Sell/Lease vehicle");
            System.out.println("0. Exit");

            System.out.print("Command: ");
            mainMenuCommand = scanner.nextInt();

            switch(mainMenuCommand){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellOrLeaseVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found. Try again!");
            }

        } while(mainMenuCommand != 0);
    }

    private void processGetByPriceRequest(){
        System.out.println("--------Display vehicles by price--------");
        System.out.print("Min: ");
        double min = scanner.nextDouble();

        System.out.print("Max: ");
        double max = scanner.nextDouble();

        // ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(startingPrice, endingPrice);
        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByPrice(min, max);

        // Display vehicles with for loop
        displayVehicles(filteredVehicles);

    }

    private void processGetByMakeModelRequest(){
        System.out.println("--------Display vehicles by make and model--------");
        System.out.print("Make: ");
        scanner.nextLine();
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMakeModel(make, model);
        displayVehicles(filteredVehicles);
    }
    private void processGetByYearRequest(){
        System.out.println("--------Display vehicles by year--------");
        System.out.print("Min year: ");
        int min = scanner.nextInt();

        System.out.print("Max year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByYear(min, max);
        displayVehicles(filteredVehicles);


    }
    private void processGetByColorRequest(){
        System.out.println("--------Display vehicles by color--------");
        scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByColor(color);
        displayVehicles(filteredVehicles);

    }
    private void processGetByMileageRequest(){
        System.out.println("--------Display vehicles by mileage--------");
        System.out.print("Min mileage: ");
        int min = scanner.nextInt();

        System.out.print("Max mileage: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMileage(min, max);
        displayVehicles(filteredVehicles);

    }
    private void processGetByVehicleTypeRequest(){
        System.out.println("--------Display vehicles by type--------");

        System.out.println("Vehicle type: ");
        scanner.nextLine();

        String type = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByType(type);
        displayVehicles(filteredVehicles);

    }
    private void processGetAllVehiclesRequest(){
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("---------Printing all vehicles-----------");
        displayVehicles(vehicles);

    }

    private void processAddVehicleRequest(){
        System.out.println("--------Add new vehicle--------");

        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price, sold);
        dealership.addVehicle(newVehicle);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("Saved!");

    }
    private void processRemoveVehicleRequest(){
        System.out.println("--------Remove vehicle--------");

        System.out.println("Enter VIN of the vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        boolean removed = dealership.removeVehicle(vin);
        if(removed) {
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed!");
        } else {
            System.out.println("Vehicle VIN " + vin + " not found.");
        }
    }
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for(Vehicle vehicle: vehicles){
            System.out.println(vehicle);
        }
    }
    private void processSellOrLeaseVehicleRequest(){
        System.out.println("Enter VIN of vehicle to sell or lease: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.println("Enter contract date (YYYYMMDD): ");
        String date = scanner.nextLine();

        System.out.println("Sell or Lease (S/L): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        Contract contract = null;

        if (choice.equals("S")) {
            System.out.println("Finance? (yes/no): ");
            boolean finance = scanner.nextLine().trim().equalsIgnoreCase("yes");
            contract = new SalesContract(date, customerName, customerEmail, vehicle, finance);

        } else if (choice.equals("L")) {
            contract = new LeaseContract(date, customerName, customerEmail, vehicle);

        } else {
            System.out.println("Invalid option.");
            return;
        }
        ContractFileManager manager = new ContractFileManager();
        manager.saveContract(contract);

        dealership.removeVehicle(vin);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("Transaction completed and saved.");


    }

}

