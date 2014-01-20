package me.dreamteam.tardis;

public class Background extends Entity {
    private Game game;

    public Background(Game game, String ref, int x, int y) {
        super(ref, x, y);

        this.game = game;
    }

    public void move(long delta) {

        super.move(delta);
    }

    public void collidedWith(Entity other) {
    }
}
