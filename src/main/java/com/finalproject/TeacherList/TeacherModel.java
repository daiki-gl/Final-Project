package com.finalproject.TeacherList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.Arrays;

import com.finalproject.dbUnit.dbConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherModel {

    Connection conn = null;
    private ObservableList<TeacherData> teacherData;

    public TeacherModel() {
        this.conn = dbConnection.getConnection();

        if (this.conn == null) {
            System.exit(0);
        }
    }

    public ObservableList<TeacherData> getTeachers() {
        String query = "SELECT * FROM teachers_tbl ORDER BY teacher_id ASC";

        try {
            this.teacherData = FXCollections.observableArrayList();

            ResultSet resultSet = conn.createStatement().executeQuery(query);

            // id | createAt | name | department

            while (resultSet.next()) {
                this.teacherData.add(new TeacherData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        // resultSet.getString(4),
                        resultSet.getString(4)));
            }

            return teacherData;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Theres an Error!!!!!!!!!!!!!!!!!!");
        }

        return null;

    }

    // Add hireDate column
    public void addTeacher(String name, String password) {
        String query = "INSERT INTO teachers_tbl (name, password) VALUES (?, ?)";
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, password);

            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTeacher(int index) {
        String query = "DELETE FROM teachers_tbl WHERE teacher_id = ?";
        PreparedStatement statement = null;

        try {
            int id = Integer.parseInt(teacherData.get(index).getId());

            statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // =====================
    // UPDATE METHOD
    // Add hireDate column
    // =====================
    // public void updateTeacher(int index, String name, String department) {

    // String query = "UPDATE teacher_tbl SET name = CASE WHEN ? = '' THEN ? ELSE ?
    // END, password = CASE WHEN ? = '' THEN ? ELSE ? END WHERE id = ?";
    // PreparedStatement statement = null;

    // try {
    // int id = Integer.parseInt(teacherData.get(index).getId());
    // String originalName = teacherData.get(index).getName();
    // String originalPassword = teacherData.get(index).getPassword();

    // statement = conn.prepareStatement(query);
    // statement.setString(1, name);
    // statement.setString(2, originalName);
    // statement.setString(3, name);

    // statement.setString(4, department);
    // statement.setString(5, originalPassword);
    // statement.setString(6, department);
    // statement.setInt(7, id);

    // statement.executeUpdate();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // statement.close();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
    // }
}