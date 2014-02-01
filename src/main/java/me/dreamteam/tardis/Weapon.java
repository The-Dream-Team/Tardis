package me.dreamteam.tardis;

public class Weapon extends Entity {

    private double moveSpeed = -300;

    private Game game;
    Sound sound;


    public Weapon(Game game, String sprite, int x, int y) {
        super(sprite, x, y);

        this.game = game;

        dy = moveSpeed;
    }


    public void move(long delta) {
        super.move(delta);
    }

    public void collidedWith(Entity other) {

        if (other instanceof Enemy) {

            game.removeEntity(this);
            game.removeEntity(other);

            Sound.soundExplosion.play(); // This will play an explosion sound on enemy hit

        }
    }
}