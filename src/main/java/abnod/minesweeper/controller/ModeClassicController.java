package abnod.minesweeper.controller;

import abnod.minesweeper.model.levels.ClassicMineMatrix;
import abnod.minesweeper.model.mines.Mine;
import abnod.minesweeper.model.mines.MineType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ModeClassicController extends LevelController {

    private ClassicMineMatrix classicMineMatrix;
    private Mine[][] matrix;
    private EventHandler<MouseEvent> mouseClickHandler;

    public void initialize() {
        mouseClickHandler = event -> {
            ImageView tile = (ImageView) event.getSource();
            if (event.getButton() == MouseButton.SECONDARY) {
                if (tile.getUserData() != null && !tile.getUserData().equals("opened")) {
                    tile.setImage(new Image("/classicImages/ClassicTile.jpg"));
                    tile.setUserData(null);
                } else if (tile.getUserData() == null) {
                    tile.setImage(new Image("/classicImages/flag.jpg"));
                    tile.setUserData("flagged");
                }
            } else if (tile.getUserData() == null) {
                checkTile(tile);
            }
        };
    }

    public void run(int sizeX, int sizeY, int minesCount) {
        freeTilesCount = sizeX * sizeY - minesCount;
        List<MineType> mineTypes = new ArrayList<>();
        mineTypes.add(MineType.CLASSIC);
        classicMineMatrix = new ClassicMineMatrix(sizeX, sizeY, minesCount, mineTypes);
        matrix = classicMineMatrix.getMatrix();
        draw();
    }

    public void draw() {
        gamePane.getChildren().clear();
        for (int i = 0; i < matrix.length; i++) {
            HBox hBox = new HBox(2);
            hBox.setAlignment(Pos.CENTER);
            for (int j = 0; j < matrix[i].length; j++) {
                ImageView tile = new ImageView("/classicImages/ClassicTile.jpg");
                tile.setFitWidth(20);
                tile.setFitHeight(20);
                tile.setId(i + "x" + j);
                tile.setOnMouseClicked(mouseClickHandler);
                hBox.getChildren().add(tile);
            }
            gamePane.getChildren().add(hBox);
        }
    }

    /**
     * change clicked tile on game field from matrix content
     *
     * @param tile
     */
    public void checkTile(ImageView tile) {
        String[] coords = tile.getId().split("x");
        int y = Integer.parseInt(coords[0]);
        int x = Integer.parseInt(coords[1]);

        if (matrix[y][x] != null) {
            switch (matrix[y][x].getType()) {
                case CLASSIC: {
                    tile.setImage(new Image("/classicImages/StandartMine.png"));
                    gameover();
                    break;
                }
                case DEATH: {
                    break;
                }
            }
        } else {
            int counter = 0;
            for (int i = y - 1; i <= y + 1; i++) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[i].length) {
                        if (matrix[i][j] != null) {
                            counter++;
                        }
                    }
                }
            }
            if (counter == 0) {
                tile.setImage(new Image("/classicImages/ClassicTileOpened.jpg"));
                tile.setUserData("opened");
                winCounter++;
            } else {
                tile.setImage(new Image("/classicImages/ClassicTile" + counter + ".jpg"));
                tile.setUserData("opened");
                winCounter++;
            }
        }
        if (winCounter == freeTilesCount) {
            win();

        }
    }
}
