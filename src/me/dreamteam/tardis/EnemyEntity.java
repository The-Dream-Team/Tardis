package me.dreamteam.tardis;

public class EnemyEntity extends Entity {
	private Game game;
	
	public EnemyEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
	}

	
	public void move(long delta) {
		if ((dx < 0) && (x < 10)) {
			game.updateLogic();
		}

		if ((dx > 0) && (x > 750)) {
			game.updateLogic();
		}
		
		super.move(delta);
	}
	
	public void collidedWith(Entity other) {
		
	}
}
