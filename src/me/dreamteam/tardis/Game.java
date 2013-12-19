package me.dreamteam.tardis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.Sys;

import me.dreamteam.tardis.Entity;
import me.dreamteam.tardis.ShipEntity;
import me.dreamteam.tardis.EnemyEntity;
import me.dreamteam.tardis.Background;
import me.dreamteam.tardis.Utils;
import me.dreamteam.tardis.UtilsHTML;
import me.dreamteam.tardis.Tardis;

/**
Main Class
 */

public class Game extends Canvas {
	
	public Game() {
		
		JFrame container = new JFrame(Utils.gameName + "- " + Utils.build + Utils.version);
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(500,650));
		panel.setLayout(null);
		
		setBounds(0,0,500,650);
		panel.add(this);
		setIgnoreRepaint(true);
		container.setResizable(false);
		container.pack();
		
		// Window setup
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = container.getSize().width;
		int h = container.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		container.setLocation(x, y);
		container.setBackground(Color.black);
		container.setVisible(true);
		
		//What to do when user choose to close
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Utils.quitGame();
			}
		});
		
		container.addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				// To be done later, minimize event
			}
		});
		
		container.addWindowListener(new WindowAdapter() {
			public void windowDeiconified(WindowEvent e) {
			// To be done later, restore event	
			}
		});
		
		
		ImageIcon icon = new ImageIcon(Utils.iconURL);
		container.setIconImage(icon.getImage());
		
		// Init keys
        addKeyListener(new KeyInputHandler());
        
		// create the buffering strategy for graphics
		createBufferStrategy(2);
		Tardis.strategy = getBufferStrategy();
        
        requestFocus();
		initEntities();
		titleScreen();
	}
	
    public void updateLogic() {
        Tardis.logicRequiredThisLoop = true;
     }
		
	/**
	 * Create our ship
	 */
	
	private void initEntities() {
		
		if (Tardis.shipS == 0) {
			Tardis.ship = new ShipEntity(this,"sprites/ship.png",220,568);
			Tardis.entities.add(Tardis.ship);
		}
		
		if (Tardis.shipS == 1) {
			Tardis.ship = new ShipEntity(this,"sprites/ship2.png",220,568);
			Tardis.entities.add(Tardis.ship);
		}
		
		if (Tardis.shipS == 2) {
			Tardis.ship = new ShipEntity(this,"sprites/ship3.png",220,568);
			Tardis.entities.add(Tardis.ship);
		}
	}
	
	private void updateEnt(){
		Tardis.moveSpeed = 180+(Tardis.tWait*0.7);
		Tardis.SpriteLoc = Tardis.rSpriteLoc.nextInt(200);
		Tardis.SpriteLoc2 = 200+Tardis.rSpriteLoc.nextInt(250);
		if(Tardis.SpriteLoc2 < Tardis.SpriteLoc+56){
			if(Tardis.SpriteLoc2 > Tardis.SpriteLoc-56){
				Tardis.SpriteLoc2 = Tardis.SpriteLoc-56;
				if (Tardis.SpriteLoc2 > 450)
					Tardis.SpriteLoc2 = Tardis.SpriteLoc-56;
			}
		}
		if(Tardis.tWait != Tardis.gameTime){
			int FinalLoc;
			if(Tardis.gameTime >= Tardis.tWait+2 && Tardis.advanceLevel == false){
				Tardis.tWait = Tardis.gameTime;
				for(int i = 0; i<2; i++){
					if(i==0){
						FinalLoc = Tardis.SpriteLoc;
					}else{
						FinalLoc = Tardis.SpriteLoc2;
					}
					Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Tardis.CurSprite+".png",FinalLoc,-50);
					Tardis.entities.add(Enemies);
					Tardis.CurSprite += 1;
					if (Tardis.CurSprite>5)
						Tardis.CurSprite=1;
				}
			}else if (Tardis.advanceLevel == true){
				if(Tardis.gameTime>= Tardis.tWait && Tardis.level ==2){
					Tardis.tWait = Tardis.gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = Tardis.SpriteLoc;
						}else{
							FinalLoc = Tardis.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Tardis.CurSprite+".png",FinalLoc,-50, (Tardis.tWait+(100*0.45)-30));
						Tardis.entities.add(Enemies);
						Tardis.CurSprite += 1;
						if (Tardis.CurSprite>5)
							Tardis.CurSprite=1;
					}
				}else if(Tardis.gameTime>= Tardis.tWait && Tardis.level == 3){
					Tardis.tWait = Tardis.gameTime;
					for(int i = 0; i<2; i++){ 
						if(i==0){ 
							FinalLoc = Tardis.SpriteLoc;
						}else{
							FinalLoc = Tardis.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Tardis.CurSprite+".png",FinalLoc,-50, (Tardis.tWait+(100*0.45)-30));
						Tardis.entities.add(Enemies);
						Tardis.CurSprite += 1;
						if (Tardis.CurSprite>5)
							Tardis.CurSprite=1;
					}
				}else if(Tardis.gameTime>= Tardis.tWait && Tardis.level == 4){
					Tardis.tWait = Tardis.gameTime;
					for(int i = 0; i<2; i++){ 
						if(i==0){ 
							FinalLoc = Tardis.SpriteLoc;
						}else{
							FinalLoc = Tardis.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Tardis.CurSprite+".png",FinalLoc,-50, (Tardis.tWait+(100*0.45)-30));
						Tardis.entities.add(Enemies);
						Tardis.CurSprite += 1;
						if (Tardis.CurSprite>5)
							Tardis.CurSprite=1;
					}
				}else if(Tardis.gameTime>= Tardis.tWait && Tardis.level >4){
					Tardis.tWait = Tardis.gameTime;
					for(int i = 0; i<2; i++){ 
						if(i==0){ 
							FinalLoc = Tardis.SpriteLoc;
						}else{
							FinalLoc = Tardis.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Tardis.CurSprite+".png",FinalLoc,-50, (Tardis.tWait+(100*0.45)-30));
						Tardis.entities.add(Enemies);
						Tardis.CurSprite += 1;
						if (Tardis.CurSprite>5)
							Tardis.CurSprite=1;
					}
				}
			}
		}
	}
	
	private void startGame() {

		Tardis.entities.clear();
		Tardis.Background = new Background(this,"sprites/background.png", 0, 0);
		Tardis.backgroundImages.add(Tardis.Background);
		Tardis.Background2 = new Background(this,"sprites/background.png", 0, -650);
		Tardis.backgroundImages.add(Tardis.Background2);
        initEntities();
        
        // reset key presses
        Tardis.leftPressed = false;
        Tardis.rightPressed = false;
        
        //reset time
        Tardis.timeMil = 0;
        //reset lives
        Tardis.gameLives = 3;
        Tardis.level = 1;
        Tardis.gameStart = true;
        Tardis.tWait = 0;
        Tardis.gameTime = 0;
        Tardis.finalTime = 0;
        Tardis.lastLoopTime = System.currentTimeMillis();
        Tardis.debugHits = 0;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		
		if (Tardis.debug) {
		System.out.println("=============================================");
		System.out.println("Beginning session @" + dateFormat.format(date));
		System.out.println("=============================================");
		}
        
	}
	
	public void characterSelect() {
		ImageIcon ship1 = new ImageIcon(Utils.ship1URL);
		ImageIcon ship2 = new ImageIcon(Utils.ship2URL);
		ImageIcon ship3 = new ImageIcon(Utils.ship3URL);
		
		Utils.systemLF();    
		
		Object[] coptions = {UtilsHTML.bpcsStart + ship1 + UtilsHTML.bpcsMiddle + Utils.ship1Name + UtilsHTML.bpcsEnd,
							 UtilsHTML.bpcsStart + ship2 + UtilsHTML.bpcsMiddle + Utils.ship2Name + UtilsHTML.bpcsEnd,
							 UtilsHTML.bpcsStart + ship3 + UtilsHTML.bpcsMiddle + Utils.ship3Name + UtilsHTML.bpcsEnd};
		int characterS = JOptionPane.showOptionDialog(null,
		UtilsHTML.csDialog, Utils.csDialogTitle, JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.INFORMATION_MESSAGE,
		Tardis.blankIcon,
		coptions,
		coptions[0]);

		if (characterS != 2 && characterS != 1 && characterS != 0) {
			titleScreen();
		}
		
		if (characterS == 2) {
			Tardis.shipS = 2;
			startGame();
		}
	
		if (characterS == 1) {
			Tardis.shipS = 1;
			startGame();
		}
		
		if (characterS == 0) {
			Tardis.shipS = 0;
			startGame();
		}
	}
		
	public void titleScreen() {
		ImageIcon icon = new ImageIcon(Utils.iconURL);	
		Utils.systemLF();
		
		Object[] options = {Utils.bPlay, Utils.bQuit};
		int startG = JOptionPane.showOptionDialog(null,
		Utils.txtTS, Utils.tsDialogTitle,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		
		if (startG != 0 && startG != 1) {
			Utils.quitGame();
		}
	
		if (startG == 1) {
			System.exit(0);
		}
		
		if (startG == 0) {
			characterSelect();
		}
		
	}
	
	public void gameOver() {
		if (Tardis.debug) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("GAME OVER DISPLAYED AFTER " + Tardis.gameTime + " SECONDS");
		System.out.println("HITS:" + Tardis.debugHits + "/3" + " | " + "LEVEL: " + Tardis.level);
		}
		
		ImageIcon icon = new ImageIcon(Utils.iconURL);	
		Utils.systemLF();
		
		Object[] options = {Utils.bPlayAgain, Utils.bQuit};
		int goG = JOptionPane.showOptionDialog(null,
		Utils.txtSurvive + Tardis.gameTime + Utils.txtSeconds, Utils.goDialogTitle,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		
		if (goG != 0 && goG != 1) {
			Utils.quitGame();
		}
	
		if (goG == 1) {
			System.exit(0);
		}
		
		if (goG == 0) {
			characterSelect();
		}
		
	}
	
	public void pauseGame() {
		ImageIcon icon = new ImageIcon(Utils.iconURL);	
		Utils.systemLF();
		Tardis.gameStart = false;
		long LoopTempTime = System.currentTimeMillis();
		Object[] options = {Utils.bReturn, Utils.bRestart, Utils.bQuit};
		int pauseG = JOptionPane.showOptionDialog(null,
		Utils.txtPS, Utils.tsDialogTitle,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		double[] entCurYLoc = new double[Tardis.entities.size()];
		for (int i=0;i<Tardis.entities.size();i++) {
			Entity entity = (Entity) Tardis.entities.get(i);
            entCurYLoc[i] = entity.getVerticalMovement();
            entity.setVerticalMovement(0);
		 }
		if (pauseG != 1 && pauseG != 2) {
			for (int i=0;i<Tardis.entities.size();i++) {
	            Entity entity = (Entity) Tardis.entities.get(i);
	            entity.setVerticalMovement(entCurYLoc[i]);
			 }
			Tardis.finalTime = System.currentTimeMillis() - LoopTempTime;
			Tardis.gameStart = true;
		}
		
		if (pauseG == 2) {
			System.exit(0);
		}
		
		if (pauseG == 1) {
			Tardis.gameTime = 0;
			characterSelect();
		}
				
	}
	
	public void gameLoop() {
		Tardis.lastLoopTime = System.currentTimeMillis();
		long bgLoop = System.currentTimeMillis();
		
		while (Tardis.isRunning) {
			if(Tardis.gameStart == true){
				
				long delta = (System.currentTimeMillis()-Tardis.finalTime) - Tardis.lastLoopTime;
				Tardis.finalTime = 0;
				Tardis.lastLoopTime = System.currentTimeMillis();
				Tardis.lastTime = getTime();
				updateTime();
				
				// Colour in background		
				Graphics2D g = (Graphics2D) Tardis.strategy.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,500,650);
				
				for (int i=0;i<Tardis.backgroundImages.size();i++) {
					Entity entity = (Entity) Tardis.backgroundImages.get(i);
					
					entity.move(delta);
				}
				
	            for (int i=0;i<Tardis.entities.size();i++) {
					Entity entity = (Entity) Tardis.entities.get(i);
					
					entity.move(delta);
				}
	            
	            long bgLoopCheck = System.currentTimeMillis();
				for(int i=1; i<3; i++){
					if(i == 2){
						Tardis.Background2.setVerticalMovement(10);
					}else{
						Tardis.Background.setVerticalMovement(10);
					}
					if((bgLoopCheck -bgLoop)> 63000){
						Tardis.Background = new Background(this,"sprites/Background.png",0,-650);
						Tardis.backgroundImages.add(Tardis.Background);
						bgLoop =System.currentTimeMillis();
					}
						
				}
	            
				//testing for collision of player and enemy  
				// p = ship, s = enemy
				
	            for (int p=0;p<Tardis.entities.size();p++) {
	            	for (int s=p+1;s<Tardis.entities.size();s++) {
	            		Entity me = (Entity) Tardis.entities.get(p);
	            		Entity him = (Entity) Tardis.entities.get(s);
	            		
	            		if (me.collidesWith(him)) {
	            			me.collidedWith(him);
	            			him.collidedWith(me); 
	            			
	            		}
	            	}
	            }
	            Tardis.entities.removeAll(Tardis.removeList);
	            Tardis.removeList.clear();
				
	            if (Tardis.logicRequiredThisLoop) {
	                    for (int i=0;i<Tardis.entities.size();i++) {
	                            Entity entity = (Entity) Tardis.entities.get(i);
	                            entity.doLogic();
	                    }
	                    
	                    Tardis.logicRequiredThisLoop = false;
	            }
	            
	            for (int i=0;i<Tardis.backgroundImages.size();i++) {
	                  Entity entity = (Entity) Tardis.backgroundImages.get(i);
	                  entity.draw(g);
				}
				for (int i=0;i<Tardis.entities.size();i++) {
	                  Entity entity = (Entity) Tardis.entities.get(i);
	                  entity.draw(g);
				}
				
				if(Tardis.gameTime >59){
					Tardis.advanceLevel = true;
					if(Tardis.gameTime < 119){
						Tardis.level = 2;
					}else if(Tardis.gameTime <179){
						Tardis.level = 3;
					}else if(Tardis.gameTime <239){
						Tardis.level = 4;
					}else if(Tardis.gameTime >299){
						Tardis.level = 5;
					}
				}
				
				/* 
				 * Game Text
				 */
				
				//Level
				g.setColor(Color.red);
				g.setFont(new Font("Century Gothic", Font.BOLD, Utils.levelFS));
	            g.drawString(Utils.txtLevel + Tardis.level,(500-g.getFontMetrics().stringWidth(Utils.txtLevel + Tardis.level))/2,18);
				
				// Timer
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.timeFS));
	            g.drawString(Tardis.timeDisplay,(70-g.getFontMetrics().stringWidth(Tardis.timeDisplay))/2,18);
	            g.drawString(Utils.txtTime,(70-g.getFontMetrics().stringWidth(Utils.txtTime))/2,18);
	            
	            if (Tardis.timeMil > 99){
	            	Tardis.gameTime = Tardis.timeMil/100;
	            }
	            String convtime = String.valueOf(Tardis.gameTime);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.timeIFS));
	            g.drawString(Tardis.timeDisplay,(175-g.getFontMetrics().stringWidth(Tardis.timeDisplay))/2,18);
	            g.drawString(convtime,(175-g.getFontMetrics().stringWidth(convtime))/2,18);
	 
	            //Lives
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.livesFS));
	            g.drawString(Tardis.livesDisplay,(875-g.getFontMetrics().stringWidth(Tardis.livesDisplay))/2,18);
	            g.drawString(Utils.txtLives,(875-g.getFontMetrics().stringWidth(Utils.txtLives))/2,18);
	            
	            String convlives = String.valueOf(Tardis.gameLives);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.livesIFS));
	            g.drawString(Tardis.timeDisplay,(965-g.getFontMetrics().stringWidth(Tardis.timeDisplay))/2,18);
	            g.drawString(convlives,(965-g.getFontMetrics().stringWidth(convlives))/2,18);
				
				// Clear Graphics
				g.dispose();
				Tardis.strategy.show();	
				updateEnt();	
				
				if (Tardis.gameLives == 0){
    				gameOver();
    			}
				
				Tardis.ship.setHorizontalMovement(0);  
			    // Ship movement
	            if ((Tardis.leftPressed) && (!Tardis.rightPressed)) {
	            	Tardis.ship.setHorizontalMovement(-Tardis.moveSpeed);
	            } else if ((Tardis.rightPressed) && (!Tardis.leftPressed)) {
	            	Tardis.ship.setHorizontalMovement(Tardis.moveSpeed);
	            }
	            
	              try { Thread.sleep(10); } catch (Exception e) {}
			 }else{
				 try { Thread.sleep(10); } catch (Exception e) {}
			 }
		}
	}
	
	/**
	 * Update the game time
	 */
	
	public void updateTime() {
	    if (getTime() - Tardis.lastTime > 1000) {
	    	Tardis.timeMil = 0; //reset the timer counter
	    	Tardis.lastTime += 1000; //add one second
	    }
	    Tardis.timeMil++;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - Tardis.lastFrame);
	    Tardis.lastFrame = time;
	 
	    return delta;
	}
	
    public void removeEntity(Entity entity) {
    	Tardis.removeList.add(entity);
    }
		
    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {                
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                	Tardis.leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                	Tardis.rightPressed = true;
                }

                if (e.getKeyChar() == 27 || e.getKeyCode() == KeyEvent.VK_PAUSE || e.getKeyCode() == KeyEvent.VK_P) {
                	pauseGame();
                }

        } 
        
        public void keyReleased(KeyEvent e) {
           
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                	Tardis.leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                	Tardis.rightPressed = false;
                }        
        }
    }
		
		public static void main(String argv[]) {
			Game g =new Game();
	
			// Start the main game loop
			g.gameLoop();
		}

}
