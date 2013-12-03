package me.dreamteam.tardis;

public class EnemyEntity extends Entity {
	
	// The speed at which the alient moves horizontally 
    private double moveSpeed = 75;
	
	private Game game;
	
	public EnemyEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		dy = moveSpeed;
	}

	
	public void move(long delta) {
		if ((dy < 0) && (y < 10)) {
			game.updateLogic();
		}

		if ((dy > 0) && (y > 750)) {
			game.updateLogic();
		}
		
		super.move(delta);
	}
	

	
	public void collidedWith(Entity other) {
		
	}
}
