package me.dreamteam.tardis;

public class Enemy extends Entity {
	// The speed at which the Enemy moves 
    private double moveSpeed = 100;
	private Game game;
	
	public Enemy(Game game,String ref,int x,int y, double s) {
		super(ref,x,y);
		
		this.game = game;
		moveSpeed = s;
		dy = moveSpeed;
	}
	
	public Enemy(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		// sets the Enemy to move downwards at the selected speed
		dy = moveSpeed;
	}

	public void move(long delta) {
		if ((dy < 0) && (y < 10)) {
		}

		if ((dy > 0) && (y > 750)) {
            game.removeEntity(this);
            if (GProperties.debug) {
            System.out.println("DEBUG: [GC] Removed " + this + " as it had reached the bottom");
            }
		
		}
		
		super.move(delta);
	}
	
	public void collidedWith(Entity other) {
		if (other instanceof Player) {
            // remove the affected entities
			GProperties.gameLives--;
			game.removeEntity(this);
		}
		
		if (other instanceof Enemy) {
			if (GProperties.debug) {
			GProperties.debugHits++;
            System.out.println("DEBUG: [INFO] Enemy " + this + " hit " + other);
			}
		}
	
	
	}
}
