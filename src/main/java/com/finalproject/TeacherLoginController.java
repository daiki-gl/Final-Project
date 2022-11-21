package com.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.finalproject.Login.TeacherLoginModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TeacherLoginController implements Initializable {
    TeacherLoginModel techLoginModel = new TeacherLoginModel();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button techLoginBtn;
    @FXML
    private Label loginStatus;
    @FXML
    private Hyperlink signup;
    @FXML
    private Hyperlink admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (techLoginModel.isDatabaseConnected()) {
            // inject a text to dbStatus that db is connected
            System.out.println("Connected to Database");
        } else {
            System.out.println("Not Connected to Database");
        }

    }

    @FXML
    public void Login(ActionEvent event) {

        if (this.techLoginModel.isTechLogin(this.username.getText(), this.password.getText())) {
            Stage stage = (Stage) this.techLoginBtn.getScene().getWindow();
            stage.close();

            homePage();
        } else {
            this.loginStatus.setText("Wrong Credentials");
        }
    }

    // Register a new user

    public void homePage() {

        Stage homeStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));

            homeStage.setScene(scene);
            homeStage.setTitle("Home Page");
            homeStage.setResizable(false);
            homeStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Signup(ActionEvent event) {
        Stage stage = (Stage) this.signup.getScene().getWindow();
        stage.close();
        signUpPage();
    }

    public void signUpPage() {
        Stage signUpStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Signup.fxml")));

            signUpStage.setScene(scene);
            signUpStage.setTitle("Sign Up Page");
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To admin login page
    @FXML
    public void Admin(ActionEvent event) {
        Stage stage = (Stage) this.admin.getScene().getWindow();
        stage.close();
        adminLoginPage();
    }

    public void adminLoginPage() {
        Stage adminStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

            adminStage.setScene(scene);
            adminStage.setTitle("Admin Login Page");
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
