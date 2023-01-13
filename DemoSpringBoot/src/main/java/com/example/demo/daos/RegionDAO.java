package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Region;

public class RegionDAO {
    private Connection connection;

    public RegionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String Query = "SELECT * FROM REGION";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(Query)
                    .executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;

    }

    public boolean insert(Region region) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO REGION(name) VALUES (?)");
            preparedStatement.setString(1, region.getName()); //Gaperlu buat preparedStatement untukid karna sudah auto increment
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int regionId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM region WHERE id = ?");
            preparedStatement.setInt(1, regionId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Region region) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE REGION SET name = ? WHERE id = ? ");
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }

    public Region findbyId(Integer regionId) {
        String Query = "SELECT * FROM REGION WHERE id = ?";
        Region reg = new Region();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, regionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reg.setId(resultSet.getInt(1));
                reg.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reg;
    }

}
