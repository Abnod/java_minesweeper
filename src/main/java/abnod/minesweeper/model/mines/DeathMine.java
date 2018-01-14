package abnod.minesweeper.model.mines;

public class DeathMine implements Mine {
    @Override
    public MineType getType() {
        return MineType.DEATH;
    }
}
