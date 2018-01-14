package abnod.minesweeper.model.mines;

public enum MineType {
    CLASSIC {
        public Mine create() {
            return new StandartMine();
        }
    },
    DEATH {
        public Mine create() {
            return new DeathMine();
        }
    };

    public abstract Mine create();
}
