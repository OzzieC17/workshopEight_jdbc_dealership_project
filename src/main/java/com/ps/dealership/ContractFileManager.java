package com.ps.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            StringBuilder stringBuilder = new StringBuilder();

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                stringBuilder.append("SALE").append("|");
                stringBuilder.append(salesContract.getDate()).append("|");
                stringBuilder.append(salesContract.getCustomerName()).append("|");
                stringBuilder.append(salesContract.getCustomerEmail()).append("|");

                Vehicle vehicle = contract.getVehicleSold();
                stringBuilder.append(vehicle.getVin()).append("|")
                        .append(vehicle.getYear()).append("|")
                        .append(vehicle.getMake()).append("|")
                        .append(vehicle.getModel()).append("|")
                        .append(vehicle.getVehicleType()).append("|")
                        .append(vehicle.getColor()).append("|")
                        .append(vehicle.getMileage()).append("|")
                        .append(String.format("%.2f", vehicle.getPrice())).append("|");


                stringBuilder.append(String.format("%.2f", salesContract.getSalesTax())).append("|")
                        .append(String.format("%.2f", salesContract.getRecordingFee())).append("|")
                        .append(String.format("%.2f", salesContract.getProcessFee())).append("|")
                        .append(String.format("%.2f", salesContract.getTotalPrice())).append("|")
                        .append(salesContract.isFinance() ? "Yes" : "No").append("|")
                        .append(String.format("%.2f", salesContract.getMonthlyPayment()));

            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                stringBuilder.append("LEASE").append("|");
                stringBuilder.append(leaseContract.getDate()).append("|");
                stringBuilder.append(leaseContract.getCustomerName()).append("|");
                stringBuilder.append(leaseContract.getCustomerEmail()).append("|");

                Vehicle vehicle = contract.getVehicleSold();
                stringBuilder.append(vehicle.getVin()).append("|")
                        .append(vehicle.getYear()).append("|")
                        .append(vehicle.getMake()).append("|")
                        .append(vehicle.getModel()).append("|")
                        .append(vehicle.getVehicleType()).append("|")
                        .append(vehicle.getColor()).append("|")
                        .append(vehicle.getMileage()).append("|")
                        .append(String.format("%.2f", vehicle.getPrice())).append("|");
                stringBuilder.append(String.format("%.2f", leaseContract.getExpectedEndingValue())).append("|")
                        .append(String.format("%.2f", leaseContract.getLeaseFee())).append("|")
                        .append(String.format("%.2f", leaseContract.getTotalPrice())).append("|")
                        .append(String.format("%.2f", leaseContract.getMonthlyPayment())).append("|");
            }
            stringBuilder.append("\n");
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

