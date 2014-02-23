package me.dreamteam.tardis;

public class Pack extends Entity {
	
    private Game game;
    
    public Pack(Game game, String ref, int x, int y) {
        super(ref, x, y);

        this.game = game;
    }

    public void move(long delta) {

        super.move(delta);
    }

    public void collidedWith(Entity other) {
    	 if (other instanceof Player) {
             GProperties.gameLives++;
             game.removeEntity(this);
             GProperties.pack = true;
             Sound.soundPowerup.play();
         }
    }
}
