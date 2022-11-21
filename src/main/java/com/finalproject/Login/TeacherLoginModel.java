package com.finalproject.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.finalproject.dbUnit.dbConnection;

public class TeacherLoginModel {

    Connection conn = null;

    public TeacherLoginModel() {
        this.conn = dbConnection.getConnection();
        if (this.conn == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.conn != null;
    }

    public boolean isTechLogin(String username, String password) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM teachers_tbl WHERE teacher_id = ? AND password = ?";

        try {
            statement = this.conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
