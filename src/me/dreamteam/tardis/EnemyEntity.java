package me.dreamteam.tardis;

public class EnemyEntity extends Entity {
	
	// The speed at which the alien moves 
    private double moveSpeed = 100;
	
	private Game game;
	
	public EnemyEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		// sets the alien to move downwards at the selected speed
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
	//collision is handled in other file 	
	}
}
