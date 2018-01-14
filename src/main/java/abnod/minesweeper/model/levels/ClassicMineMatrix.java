package abnod.minesweeper.model.levels;

import abnod.minesweeper.model.mines.Mine;
import abnod.minesweeper.model.mines.MineType;

import java.util.List;
import java.util.Random;

public class ClassicMineMatrix implements Level {

    private int matrixWidth;
    private int matrixHeight;
    private int mineCounter;

    private List<MineType> mineTypes;
    private Mine[][] matrix;

    private Random random = new Random();

    public ClassicMineMatrix(int matrixWidth, int matrixHeight, int mineCounter, List<MineType> mineTypes) {
        this.matrixWidth = matrixWidth;
        this.matrixHeight = matrixHeight;
        this.mineCounter = mineCounter;
        this.mineTypes = mineTypes;
        createMatrix();
    }


    void createMatrix() {
        matrix = new Mine[matrixHeight][matrixWidth];
        for (int i = 0; i < mineCounter; i++) {

            randomizeMinePosition(mineTypes.get(random.nextInt(mineTypes.size())));
        }
    }

    /**
     * randomly put mine to matrix
     */
    void randomizeMinePosition(MineType mineToPut) {
        boolean minePlaced = false;
        do {
            int posY = random.nextInt(matrixHeight);
            int posX = random.nextInt(matrixWidth);
            if (matrix[posY][posX] == null) {
                matrix[posY][posX] = mineToPut.create();
                minePlaced = true;
            }
        } while (!minePlaced);
    }
}
