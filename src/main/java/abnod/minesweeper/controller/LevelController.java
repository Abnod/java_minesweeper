package abnod.minesweeper.controller;

import javafx.stage.Stage;

public interface LevelController {

    boolean gameOver = false;

    void run(int sizeX, int sizeY, int minesCount);

    void draw();

    void setStage(Stage stage);
}
