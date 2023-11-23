package com.example.controller;

import com.example.service.MainService;
import com.example.service.api.IMainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class MainController {

    private boolean isUserPaneOpened;

    private IMainService mainService = new MainService();


    @FXML
    private Label loginLabel; //todo to show user login when we show userPane(init this label when we show this window)
    @FXML
    private AnchorPane userPane;

    @FXML
    private Button userLibrary;
    @FXML
    private Button userButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ScrollPane resultsScrollPane;

    @FXML
    private VBox resultsVBox;

    @FXML
    private VBox circleBox;

    @FXML
    private void initialize() {
        resultsScrollPane.setVisible(false);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSearchTextChange();
        });
    }

    @FXML
    private void handleSearchTextChange() {
        String searchText = searchTextField.getText();

        resultsVBox.getChildren().clear();
        circleBox.getChildren().clear();

        // todo: Запрос к базе данных для поиска ваших табов (go to service)

        // Пример добавления результатов вручную
        for (int i = 0; i < 5; i++) {
            Button resultButton = createResultButton("Результат " + i);
            Button plusButton = createResultButton("+");
            plusButton.setShape(new Circle(3));
            plusButton.setOnAction(e -> handlePlusButtonClick(plusButton.getText()));
            resultButton.setOnAction(e -> handleResultButtonClick(resultButton.getText()));
            resultsVBox.getChildren().add(resultButton);
            circleBox.getChildren().add(plusButton);
        }

        resultsScrollPane.setVisible(!searchText.isEmpty());
    }



    private void handlePlusButtonClick(String buttonText) {
        System.out.println("Plus button clicked: " + buttonText);
    }
    private Button createResultButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-border-color: black; -fx-padding: 5px;");
        return button;
    }

    private void handleResultButtonClick(String buttonText) {
        //todo open new big pane with tabs (go to service)
    }


    @FXML
    public void showUserLibrary(ActionEvent event){
        if (event.getSource() == userLibrary){
            //todo  show userLibrary (go to service)
        }
    }
@FXML
    public void userButtonPushed(ActionEvent event){
        if (event.getSource() == userButton){
            if (isUserPaneOpened){
                userPane.setVisible(false);
                isUserPaneOpened = false;
            }else {
                userPane.setVisible(true);
                isUserPaneOpened = true;
            }
        }
    }

}