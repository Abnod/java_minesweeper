package abnod.minesweeper.model.mines;

public class StandartMine implements Mine {
    @Override
    public MineType getType() {
        return MineType.CLASSIC;
    }
}
