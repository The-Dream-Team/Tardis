package me.dreamteam.tardis;

public class Timer{

	public void run(){
		int timerthreads=0;
		while(timerthreads>=0){
			try{
				timerthreads++;
				Thread.sleep(1000L);
	
			}catch(InterruptedException e){}
		}
	}
	
}