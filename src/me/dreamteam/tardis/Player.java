package me.dreamteam.tardis;

public class Player extends Entity {
	private Game game;
	
	public Player(Game game,String ref,int x,int y) {
		super(ref,x,y);
		this.game = game;
	}
	
	public void move(long delta) {
		if ((dx < 0) && (x < 10)) {
			return;
		}

		if ((dx > 0) && (x > 440)) {
			return;
		}
		
		super.move(delta);
	}	
	
	public void collidedWith(Entity other) {
            // what to do if collied
            if (other instanceof Enemy) {
            	if (Properties.debug) {
                System.out.println("DEBUG: (-) Enemy Hit " + other);
            	}
            }
    }
		
}