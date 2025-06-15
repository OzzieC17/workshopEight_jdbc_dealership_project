package com.ps.dealership;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);

        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;
        this.leaseFee = vehicleSold.getPrice() * 0.7;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    @Override
    public double getTotalPrice() {
        return leaseFee;
    }
    @Override
    public double getMonthlyPayment() {
        double monthlyRate = 0.04 / 12;
        int termMonths = 36;
        double principal = getTotalPrice();
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termMonths));
        return monthlyPayment;
    }
}

