package me.dreamteam.tardis;

public class ShipEntity extends Entity {
	private Game game;
	
	public ShipEntity(Game game,String ref,int x,int y) {
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
	
            // if its an alien, notify the game that the player
            // is dead
            if (other instanceof EnemyEntity) {
                System.out.println("Enemy Hit " + other);   
                   
            }
    }
		
}
