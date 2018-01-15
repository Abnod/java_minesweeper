package abnod.minesweeper.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class LevelController {

    Stage stage;
    //count opened tiles
    int winCounter;
    int freeTilesCount;

    @FXML
    VBox gamePane;
    @FXML
    private Label gameoverLabel;

    public void toMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void run(int sizeX, int sizeY, int minesCount);

    public abstract void draw();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void gameover() {
        gameoverLabel.setText("Game Over!");
        gameoverLabel.setStyle("-fx-text-fill: red");
        gamePane.setDisable(true);
    }

    /**
     * if winCounter equals matrix tiles w/o mines, player wins
     */
    void win() {
        gameoverLabel.setText("You Are Winner!!!");
        gameoverLabel.setStyle("-fx-text-fill: lawngreen");
        gamePane.setDisable(true);
    }
}
