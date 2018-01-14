package abnod.minesweeper.controller;

import abnod.minesweeper.model.levels.ClassicMineMatrix;
import abnod.minesweeper.model.mines.MineType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ModeClassicController {

    Stage stage;

    public void initialize() {
    }

    public void run(int sizeX, int sizeY, int minesCount) {
        List<MineType> mineTypes = new ArrayList<>();
        mineTypes.add(MineType.CLASSIC);
        new ClassicMineMatrix(sizeX, sizeY, minesCount, mineTypes);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
