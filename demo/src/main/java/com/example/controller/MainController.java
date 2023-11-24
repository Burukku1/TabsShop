package com.example.controller;

import com.example.db.entity.Tabs;
import com.example.db.entity.User;
import com.example.service.MainService;
import com.example.service.api.IMainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;


public class MainController {

    private boolean isUserPaneOpened;

    private boolean isTabPaneOpened;
    private User singInUser = null;

    public User getSingInUser() {
        return singInUser;
    }

    public void setSingInUser(User singInUser) {
        this.singInUser = singInUser;
    }

    private IMainService mainService = new MainService();


    @FXML
    private Label loginLabel; //todo to show user login when we show userPane(init this label when we show this window)
    @FXML
    private AnchorPane userPane;

    @FXML
    private AnchorPane tabPane;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private TextArea tabArea;

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

        List<Tabs> tabs = mainService.showSearchResult(searchText);

        // Пример добавления результатов вручную
        for (Tabs tab : tabs) {

            Button resultButton = createResultButton(tab.getSongName());
            resultButton.setId(tab.getLinkPath());
            Button plusButton = createResultButton("+");
            plusButton.setShape(new Circle(3));
            plusButton.setId(tab.getTabId().toString());
            plusButton.setOnAction(e -> handlePlusButtonClick(plusButton));
            resultButton.setOnAction(e -> handleResultButtonClick(resultButton));

            resultsVBox.getChildren().add(resultButton);
            circleBox.getChildren().add(plusButton);
        }

        resultsScrollPane.setVisible(!searchText.isEmpty());
    }


    private void handlePlusButtonClick(Button plusButton) {
        Long id = Long.valueOf(plusButton.getId());
        mainService.addTabToUserLibrary(singInUser, id);
    }

    private Button createResultButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-border-color: black; -fx-padding: 5px;");
        return button;
    }

    private void handleResultButtonClick(Button resultButton) {
        String linkPath = resultButton.getId();
        Tabs tab = new Tabs();
        tab.setLinkPath(linkPath);
        String str = mainService.displayTab(tab);
        tabArea.clear();
        tabArea.appendText(str);

    }


    @FXML
    public void showUserLibrary(ActionEvent event) {
        if (event.getSource() == userLibrary) {

            if (isTabPaneOpened) {

                tabArea.clear();
                tabArea.setVisible(false);
                isUserPaneOpened = false;

            } else {
                isUserPaneOpened = true;

                List<Tabs> tabList = mainService.showMyTabs(singInUser);

                tabArea.clear();

                // Iterate through the list and append names to the textArea
                for (Tabs tab : tabList) {
                    tabArea.appendText(tab.getSongName() + "\n");
                }
                tabArea.setVisible(true);
            }
        }
    }

    @FXML
    public void userButtonPushed(ActionEvent event) {
        if (event.getSource() == userButton) {
            if (isUserPaneOpened) {
                userPane.setVisible(false);
                isUserPaneOpened = false;
            } else {
                userPane.setVisible(true);
                isUserPaneOpened = true;
            }
        }
    }

}