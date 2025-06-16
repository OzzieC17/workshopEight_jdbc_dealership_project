package com.ps.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private BasicDataSource dataSource;

    public VehicleDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Vehicle> getVehiclesByPriceRange(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, make);
            statement.setString(2, model);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE color = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, color);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, minMileage);
            statement.setInt(2, maxMileage);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE type = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(mapRowToVehicle(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle mapRowToVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setMileage(resultSet.getInt("mileage"));
        vehicle.setPrice(resultSet.getDouble("price"));
        vehicle.setSold(Boolean.parseBoolean(resultSet.getString("SOLD")));
        return vehicle;
    }
}
