package me.dreamteam.tardis;

public class EnemyEntity extends Entity {
	
	// The speed at which the Enemy moves 
    private double moveSpeed = 100;
	
	private Game game;
	
	public EnemyEntity(Game game,String ref,int x,int y, double s) {
		super(ref,x,y);
		
		this.game = game;
		moveSpeed = s;
		dy = moveSpeed;
	}
	
	public EnemyEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		// sets the Enemy to move downwards at the selected speed
		dy = moveSpeed;
	}

	public void move(long delta) {
		if ((dy < 0) && (y < 10)) {
			game.updateLogic();
		}

		if ((dy > 0) && (y > 750)) {
			game.updateLogic();
            game.removeEntity(this);
            System.out.println("DEBUG: (GC) Removed " + this + " as it had reached the bottom");
		
		}
		
		super.move(delta);
	}
	
	public void collidedWith(Entity other) {
		if (other instanceof ShipEntity) {
            // remove the affected entities
            game.removeEntity(this);
		}
	
	
	}
}
