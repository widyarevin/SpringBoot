package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class DivisionDAO {
    private Connection connection;

    public DivisionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Division> getAll() {
        List<Division> divisions = new ArrayList<>();
        String Query = "SELECT * FROM division INNER JOIN region ON division.regionId = region.id";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(Query)
                    .executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                Region reg = new Region();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                division.setRegion(reg);
                reg.setId(resultSet.getInt(4));
                reg.setName(resultSet.getString(5));
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;

    }

    public boolean insert(Division division) {
        // Region reg = new Region();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO division(name, regionId) VALUES (?,?)");
            preparedStatement.setString(1, division.getName());
            // division.setRegion(reg);
            preparedStatement.setInt(2, division.getRegion().getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int divisionId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM division WHERE id = ?");
            preparedStatement.setInt(1, divisionId);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Division division) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE division SET name = ?, regionId = ? WHERE id = ?");
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion().getId());
            preparedStatement.setInt(3, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }

    public Division findbyId(Integer divisionId) {
        String Query = "SELECT division.id, division.name, region.id, region.name FROM division JOIN region ON region.id = division.regionId WHERE division.id = ?";
        Division div = new Division();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, divisionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Region reg = new Region();
                div.setId(resultSet.getInt(1));
                div.setName(resultSet.getString(2));
                div.setRegion(reg);
                reg.setId(resultSet.getInt(3));
                reg.setName(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return div;
    }

    

    
}