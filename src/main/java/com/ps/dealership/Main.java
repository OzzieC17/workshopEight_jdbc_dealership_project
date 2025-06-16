package com.ps.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error");
            System.exit(1);
        }
        String username = args[0];
        String password = args[1];

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership");

        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        DataBaseManager dataBaseManager = new DataBaseManager(basicDataSource);

        try (Connection connection = basicDataSource.getConnection()) {
            System.out.println("Connected to database.");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicles");

            while (resultSet.next()) {
                String vin = resultSet.getString("VIN");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                int mileage = resultSet.getInt("mileage");
                double price = resultSet.getDouble("price");
                boolean sold = resultSet.getBoolean("SOLD");

                System.out.printf("VIN: %s | Year: %d | Make: %s | Model: %s | Color: %s | Mileage: %d | Price: $%.2f | Sold: %s\n",
                        vin, year, make, model, color, mileage, price, sold ? "Yes" : "No");
            }

        } catch (SQLException e) {
            System.out.println("Failed to connect:");
            e.printStackTrace();
            System.exit(1);
        }


        //UserInterface userInterface = new UserInterface();
        //userInterface.display();

    }
}
