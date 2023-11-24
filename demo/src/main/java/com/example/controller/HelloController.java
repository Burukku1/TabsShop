package com.example.controller;

import com.example.core.dto.UserDto;
import com.example.db.UserDao;
import com.example.db.entity.User;
import com.example.service.RegistrationService;
import com.example.service.api.IRegistrationService;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class HelloController {


    public HelloController() {

    }

    private IRegistrationService registrationService = new RegistrationService();

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Label signupErrorLabel;

    @FXML
    private TextField su_email;

    @FXML
    private Hyperlink si_forgotPassword;

    @FXML
    private Button si_logginButton;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_createButton;

    @FXML
    private AnchorPane side_form;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signUpButton;

    @FXML
    private AnchorPane su_signUpForm;

    @FXML
    private TextField su_username;

    TranslateTransition slider = new TranslateTransition();


    public void loginButtonClicked(ActionEvent event) {
        if (event.getSource() == si_logginButton) {
            UserDto userDto = new UserDto();

            String login = si_username.getText();
            String password = si_password.getText();

            userDto.setLogin(login);
            userDto.setPassword(password);

            if (registrationService.checkUserLog(userDto).isPresent() && registrationService.checkUserPass(userDto)) {
                User user = registrationService.checkUserLog(userDto).get();
                loginErrorLabel.setVisible(false);
                openSecondWindow(event, user);
            } else {
                loginErrorLabel.setVisible(true);
            }
        }
    }


    public void signUpButtonClicked(ActionEvent event) {
        if (event.getSource() == su_signUpButton) {

            String login = su_username.getText();
            String password = su_password.getText();
            String email = su_email.getText();

            UserDto userDto = new UserDto();
            userDto.setLogin(login);
            userDto.setPassword(password);
            userDto.setEmail(email);

            if (!registrationService.checkUserLog(userDto).isPresent()) {

                registrationService.createUser(userDto);

                User user = registrationService.checkUserLog(userDto).get();

                signupErrorLabel.setVisible(false);

                System.out.println("open second window");

                openSecondWindow(event, user);
            } else {
                signupErrorLabel.setVisible(true);
            }
        }

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == side_createButton) {
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_createButton.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_createButton.setVisible(true);
            });

            slider.play();
        }
    }

    public void openSecondWindow(ActionEvent event, User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/core/MainWindow.fxml"));
            Parent root1 = fxmlLoader.load();

            // Создайте новый Stage
            Stage stage = new Stage();
            stage.setTitle("Second Window");

            // Установите сцену для второго окна
            Scene scene = new Scene(root1);
            stage.setScene(scene);

            MainController mainController = fxmlLoader.getController();
            mainController.setSingInUser(user);
            // Покажите второе окно
            stage.show();

            // Закройте текущее окно (если необходимо)
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

