package me.dreamteam.tardis;

public class EnemyEntity extends Entity {
	
	private Game game;
	
	public EnemyEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
	}

	
	public void collidedWith(Entity other) {
		
	}
}
