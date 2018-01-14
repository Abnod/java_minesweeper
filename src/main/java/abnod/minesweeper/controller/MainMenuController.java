package abnod.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private ToggleGroup classicGroup;
    @FXML
    private TextArea classicWidth, classicHeight, classicMinesCount;

    private FXMLLoader loader;
    private Stage stage;

    public void initialize() {
    }

    public void runClassicLevel() {
        RadioButton selectedButton = (RadioButton) classicGroup.getSelectedToggle();
        if (selectedButton.getId().equals("customClassicCheck")) {
            if (classicWidth.getText().isEmpty() || classicHeight.getText().isEmpty() || classicMinesCount.getText().isEmpty()) {
                return;
            } else if (Integer.parseInt(classicWidth.getText()) * Integer.parseInt(classicHeight.getText()) <= Integer.parseInt(classicMinesCount.getText())) {
                return;
            }
        }
        try {
            loader = new FXMLLoader(getClass().getResource("/ModeClassic.fxml"));
            Parent root = loader.load();
            ModeClassicController classicController = loader.getController();
            classicController.setStage(stage);
            switch (selectedButton.getId()) {
                case "easyClassicCheck": {
                    classicController.run(6, 6, 9);
                    break;
                }
                case "normalClassicCheck": {
                    classicController.run(10, 10, 25);
                    break;
                }
                case "hardClassicCheck": {
                    classicController.run(15, 15, 56);
                    break;
                }
                case "customClassicCheck": {

                    break;
                }
            }
            stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
