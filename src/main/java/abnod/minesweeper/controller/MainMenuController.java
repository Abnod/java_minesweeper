package abnod.minesweeper.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private RadioButton customClassicCheck;
    @FXML
    private TextArea classicWidth, classicHeight, classicMinesCount;

    private Stage stage;
    private Parent root;

    public void initialize() {

        //set width, height and count fields to numeric only
        classicWidth.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                classicWidth.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        classicHeight.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                classicHeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        classicMinesCount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                classicMinesCount.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        customClassicCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (customClassicCheck.isSelected()) {
                    classicMinesCount.setDisable(false);
                    classicWidth.setDisable(false);
                    classicHeight.setDisable(false);
                } else {
                    classicMinesCount.setDisable(true);
                    classicWidth.setDisable(true);
                    classicHeight.setDisable(true);
                }
            }
        });


    }

    public void generateLevel(int width, int height, int count, LevelController levelController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameField.fxml"));
            loader.setController(levelController);
            Parent newRoot = loader.load();
            levelController.setStage(stage);
            levelController.run(width, height, count);
            stage.setScene(new Scene(newRoot, stage.getScene().getWidth(), stage.getScene().getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runClassicLevel() {
        RadioButton selectedButton = (RadioButton) classicGroup.getSelectedToggle();
        int width, height, count;
        switch (selectedButton.getId()) {
            case "easyClassicCheck": {
                generateLevel(6, 6, 9, new ModeClassicController());
                break;
            }
            case "normalClassicCheck": {
                generateLevel(10, 10, 25, new ModeClassicController());
                break;
            }
            case "hardClassicCheck": {
                generateLevel(15, 15, 56, new ModeClassicController());
                break;
            }
            case "customClassicCheck": {
                //classic field checks
                if (classicWidth.getText().isEmpty() || classicHeight.getText().isEmpty() || classicMinesCount.getText().isEmpty()) {
                    return;
                }
                width = Integer.parseInt(classicWidth.getText());
                height = Integer.parseInt(classicHeight.getText());
                count = Integer.parseInt(classicMinesCount.getText());
                if (width * height <= count) {
                    return;
                }

                generateLevel(width, height, count, new ModeClassicController());
                break;
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
