package com.ps.dealership;

public class SalesContract extends Contract {
    private boolean finance;
    private double salesTax;
    private double recordingFee;
    private double processFee;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance){
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;

        double price = vehicleSold.getPrice();

        this.salesTax = price * 0.05;
        this.recordingFee = 100.0;
        this.processFee = price < 10000? 295.0 : 495.0;

    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessFee() {
        return processFee;
    }

    public void setProcessFee(double processFee) {
        this.processFee = processFee;
    }
    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTax + processFee;
    }
    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0.0;
        }
        double price = getTotalPrice();
        double interestRate;
        int months;

        if (getVehicleSold().getPrice() >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }
        double monthlyRate = interestRate/12;
        return (price * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

    }
}

