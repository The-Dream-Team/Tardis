package me.dreamteam.tardis;

public class Timer{
	
	Game gettime = new Game();
	public void run(){
		int timerthreads=0;
		while(timerthreads>=0){
			try{
				timerthreads++;
				Thread.sleep(1000L);
				gettime.gameTime = timerthreads;
			}catch(InterruptedException e){}
		}
	}
	
}