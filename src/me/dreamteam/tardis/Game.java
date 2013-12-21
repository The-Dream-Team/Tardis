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
import me.dreamteam.tardis.Properties;

/**
Main Class
 */

public class Game extends Canvas {
	Properties Properties = new Properties();
	public Game(){
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
		Properties.strategy = getBufferStrategy();
        
        requestFocus();
		initEntities();
		titleScreen();
	}
	
    public void updateLogic() {
    	Properties.logicRequiredThisLoop = true;
     }
		
	/**
	 * Create our ship
	 */
	
	private void initEntities() {
		
		if (Properties.shipS == 0) {
			Properties.ship = new ShipEntity(this,"sprites/ship.png",220,568);
			Properties.entities.add(Properties.ship);
		}
		
		if (Properties.shipS == 1) {
			Properties.ship = new ShipEntity(this,"sprites/ship2.png",220,568);
			Properties.entities.add(Properties.ship);
		}
		
		if (Properties.shipS == 2) {
			Properties.ship = new ShipEntity(this,"sprites/ship3.png",220,568);
			Properties.entities.add(Properties.ship);
		}
	}
	
	private void updateEnt(){
		Properties.moveSpeed = 180+(Properties.tWait*0.7);
		Properties.SpriteLoc = Properties.rSpriteLoc.nextInt(200);
		Properties.SpriteLoc2 = 200+Properties.rSpriteLoc.nextInt(250);
		if(Properties.SpriteLoc2 < Properties.SpriteLoc+56){
			if(Properties.SpriteLoc2 > Properties.SpriteLoc-56){
				Properties.SpriteLoc2 = Properties.SpriteLoc-56;
				if (Properties.SpriteLoc2 > 450)
					Properties.SpriteLoc2 = Properties.SpriteLoc-56;
			}
		}
		if(Properties.tWait != Properties.gameTime){
			int FinalLoc;
			if(Properties.gameTime >= Properties.tWait+2 && Properties.advanceLevel == false){
				Properties.tWait = Properties.gameTime;
				for(int i = 0; i<2; i++){
					if(i==0){
						FinalLoc = Properties.SpriteLoc;
					}else{
						FinalLoc = Properties.SpriteLoc2;
					}
					Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Properties.CurSprite+".png",FinalLoc,-50);
					Properties.entities.add(Enemies);
					Properties.CurSprite += 1;
					if (Properties.CurSprite>5)
						Properties.CurSprite=1;
				}
			}else if (Properties.advanceLevel == true){
				if(Properties.gameTime>= Properties.tWait && Properties.level ==2){
					Properties.tWait = Properties.gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = Properties.SpriteLoc;
						}else{
							FinalLoc = Properties.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Properties.CurSprite+".png",FinalLoc,-50, (Properties.tWait+(100*0.45)-30));
						Properties.entities.add(Enemies);
						Properties.CurSprite += 1;
						if (Properties.CurSprite>5)
							Properties.CurSprite=1;
					}
				}else if(Properties.gameTime>= Properties.tWait && Properties.level ==2){
					Properties.tWait = Properties.gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = Properties.SpriteLoc;
						}else{
							FinalLoc = Properties.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Properties.CurSprite+".png",FinalLoc,-50, (Properties.tWait+(100*0.45)-30));
						Properties.entities.add(Enemies);
						Properties.CurSprite += 1;
						if (Properties.CurSprite>5)
							Properties.CurSprite=1;
					}
				}else if(Properties.gameTime>= Properties.tWait && Properties.level ==2){
					Properties.tWait = Properties.gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = Properties.SpriteLoc;
						}else{
							FinalLoc = Properties.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Properties.CurSprite+".png",FinalLoc,-50, (Properties.tWait+(100*0.45)-30));
						Properties.entities.add(Enemies);
						Properties.CurSprite += 1;
						if (Properties.CurSprite>5)
							Properties.CurSprite=1;
					}
				}else if(Properties.gameTime>= Properties.tWait && Properties.level ==2){
					Properties.tWait = Properties.gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = Properties.SpriteLoc;
						}else{
							FinalLoc = Properties.SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+Properties.CurSprite+".png",FinalLoc,-50, (Properties.tWait+(100*0.45)-30));
						Properties.entities.add(Enemies);
						Properties.CurSprite += 1;
						if (Properties.CurSprite>5)
							Properties.CurSprite=1;
					}
				}
			}
		}
	}
	
	private void startGame() {

		Properties.entities.clear();
		Properties.Background = new Background(this,"sprites/background.png", 0, 0);
		Properties.backgroundImages.add(Properties.Background);
		Properties.Background2 = new Background(this,"sprites/background.png", 0, -650);
		Properties.backgroundImages.add(Properties.Background2);
        initEntities();
        
        // reset key presses
        Properties.leftPressed = false;
        Properties.rightPressed = false;
        
        //reset time
        Properties.timeMil = 0;
        //reset lives
        Properties.gameLives = 3;
        Properties.level = 1;
        Properties.gameStart = true;
        Properties.tWait = 0;
        Properties.gameTime = 0;
        Properties.finalTime = 0;
        Properties.lastLoopTime = System.currentTimeMillis();
        Properties.debugHits = 0;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		
		if (Properties.debug) {
		System.out.println("=============================================");
		System.out.println("Beginning session @ " + dateFormat.format(date));
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
		Utils.blankIcon,
		coptions,
		coptions[0]);

		if (characterS != 2 && characterS != 1 && characterS != 0) {
			titleScreen();
		}
		
		if (characterS == 2) {
			Properties.shipS = 2;
			startGame();
		}
	
		if (characterS == 1) {
			Properties.shipS = 1;
			startGame();
		}
		
		if (characterS == 0) {
			Properties.shipS = 0;
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
		if (Properties.debug) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("GAME OVER DISPLAYED AFTER " + Properties.gameTime + " SECONDS");
		System.out.println("HITS:" + Properties.debugHits + "/3" + " | " + "LEVEL: " + Properties.level);
		}
		
		ImageIcon icon = new ImageIcon(Utils.iconURL);	
		Utils.systemLF();
		
		Object[] options = {Utils.bPlayAgain, Utils.bQuit};
		int goG = JOptionPane.showOptionDialog(null,
		Utils.txtSurvive + Properties.gameTime + Utils.txtSeconds, Utils.goDialogTitle,
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
		Properties.gameStart = false;
		long LoopTempTime = System.currentTimeMillis();
		Object[] options = {Utils.bReturn, Utils.bRestart, Utils.bQuit};
		int pauseG = JOptionPane.showOptionDialog(null,
		Utils.txtPS, Utils.tsDialogTitle,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		double[] entCurYLoc = new double[Properties.entities.size()];
		for (int i=0;i<Properties.entities.size();i++) {
			Entity entity = (Entity) Properties.entities.get(i);
            entCurYLoc[i] = entity.getVerticalMovement();
            entity.setVerticalMovement(0);
		 }
		if (pauseG != 1 && pauseG != 2) {
			for (int i=0;i<Properties.entities.size();i++) {
	            Entity entity = (Entity) Properties.entities.get(i);
	            entity.setVerticalMovement(entCurYLoc[i]);
			 }
			Properties.finalTime = System.currentTimeMillis() - LoopTempTime;
			Properties.gameStart = true;
		}
		
		if (pauseG == 2) {
			System.exit(0);
		}
		
		if (pauseG == 1) {
			Properties.gameTime = 0;
			characterSelect();
		}
				
	}
	
	public void gameLoop() {
		Properties.lastLoopTime = System.currentTimeMillis();
		long bgLoop = System.currentTimeMillis();
		
		while (Properties.isRunning) {
			if(Properties.gameStart == true){
				
				long delta = (System.currentTimeMillis()-Properties.finalTime) - Properties.lastLoopTime;
				Properties.finalTime = 0;
				Properties.lastLoopTime = System.currentTimeMillis();
				Properties.lastTime = getTime();
				updateTime();
				
				// Colour in background		
				Graphics2D g = (Graphics2D) Properties.strategy.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,500,650);
				
				for (int i=0;i<Properties.backgroundImages.size();i++) {
					Entity entity = (Entity) Properties.backgroundImages.get(i);
					
					entity.move(delta);
				}
				
	            for (int i=0;i<Properties.entities.size();i++) {
					Entity entity = (Entity) Properties.entities.get(i);
					
					entity.move(delta);
				}
	            
	            long bgLoopCheck = System.currentTimeMillis();
				for(int i=1; i<3; i++){
					if(i == 2){
						Properties.Background2.setVerticalMovement(10);
					}else{
						Properties.Background.setVerticalMovement(10);
					}
					if((bgLoopCheck -bgLoop)> 63000){
						Properties.Background = new Background(this,"sprites/Background.png",0,-650);
						Properties.backgroundImages.add(Properties.Background);
						bgLoop =System.currentTimeMillis();
					}
						
				}
	            
				//testing for collision of player and enemy  
				// p = ship, s = enemy
				
	            for (int p=0;p<Properties.entities.size();p++) {
	            	for (int s=p+1;s<Properties.entities.size();s++) {
	            		Entity me = (Entity) Properties.entities.get(p);
	            		Entity him = (Entity) Properties.entities.get(s);
	            		
	            		if (me.collidesWith(him)) {
	            			me.collidedWith(him);
	            			him.collidedWith(me); 
	            			
	            		}
	            	}
	            }
	            Properties.entities.removeAll(Properties.removeList);
	            Properties.removeList.clear();
				
	            if (Properties.logicRequiredThisLoop) {
	                    for (int i=0;i<Properties.entities.size();i++) {
	                            Entity entity = (Entity) Properties.entities.get(i);
	                            entity.doLogic();
	                    }
	                    
	                    Properties.logicRequiredThisLoop = false;
	            }
	            
	            for (int i=0;i<Properties.backgroundImages.size();i++) {
	                  Entity entity = (Entity) Properties.backgroundImages.get(i);
	                  entity.draw(g);
				}
				for (int i=0;i<Properties.entities.size();i++) {
	                  Entity entity = (Entity) Properties.entities.get(i);
	                  entity.draw(g);
				}
				
				if(Properties.gameTime >59){
					Properties.advanceLevel = true;
					if(Properties.gameTime < 119){
						Properties.level = 2;
					}else if(Properties.gameTime <179){
						Properties.level = 3;
					}else if(Properties.gameTime <239){
						Properties.level = 4;
					}else if(Properties.gameTime >299){
						Properties.level = 5;
					}
				}
				
				/* 
				 * Game Text
				 */
				
				//Level
				g.setColor(Color.red);
				g.setFont(new Font("Century Gothic", Font.BOLD, Utils.levelFS));
	            g.drawString(Utils.txtLevel + Properties.level,(500-g.getFontMetrics().stringWidth(Utils.txtLevel + Properties.level))/2,18);
				
				// Timer
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.timeFS));
	            g.drawString(Properties.timeDisplay,(70-g.getFontMetrics().stringWidth(Properties.timeDisplay))/2,18);
	            g.drawString(Utils.txtTime,(70-g.getFontMetrics().stringWidth(Utils.txtTime))/2,18);
	            
	            if (Properties.timeMil > 99){
	            	Properties.gameTime = Properties.timeMil/100;
	            }
	            String convtime = String.valueOf(Properties.gameTime);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.timeIFS));
	            g.drawString(Properties.timeDisplay,(175-g.getFontMetrics().stringWidth(Properties.timeDisplay))/2,18);
	            g.drawString(convtime,(175-g.getFontMetrics().stringWidth(convtime))/2,18);
	 
	            //Lives
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.livesFS));
	            g.drawString(Properties.livesDisplay,(875-g.getFontMetrics().stringWidth(Properties.livesDisplay))/2,18);
	            g.drawString(Utils.txtLives,(875-g.getFontMetrics().stringWidth(Utils.txtLives))/2,18);
	            
	            String convlives = String.valueOf(Properties.gameLives);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.livesIFS));
	            g.drawString(Properties.timeDisplay,(965-g.getFontMetrics().stringWidth(Properties.timeDisplay))/2,18);
	            g.drawString(convlives,(965-g.getFontMetrics().stringWidth(convlives))/2,18);
				
				// Clear Graphics
				g.dispose();
				Properties.strategy.show();	
				updateEnt();	
				
				if (Properties.gameLives == 0){
    				gameOver();
    			}
				
				Properties.ship.setHorizontalMovement(0);  
			    // Ship movement
	            if ((Properties.leftPressed) && (!Properties.rightPressed)) {
	            	Properties.ship.setHorizontalMovement(-Properties.moveSpeed);
	            } else if ((Properties.rightPressed) && (!Properties.leftPressed)) {
	            	Properties.ship.setHorizontalMovement(Properties.moveSpeed);
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
	    if (getTime() - Properties.lastTime > 1000) {
	    	Properties.timeMil = 0; //reset the timer counter
	    	Properties.lastTime += 1000; //add one second
	    }
	    Properties.timeMil++;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - Properties.lastFrame);
	    Properties.lastFrame = time;
	 
	    return delta;
	}
	
    public void removeEntity(Entity entity) {
    	Properties.removeList.add(entity);
    }
		
    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {                
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                	Properties.leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                	Properties.rightPressed = true;
                }

                if (e.getKeyChar() == 27 || e.getKeyCode() == KeyEvent.VK_PAUSE || e.getKeyCode() == KeyEvent.VK_P) {
                	pauseGame();
                }

        } 
        
        public void keyReleased(KeyEvent e) {
           
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                	Properties.leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                	Properties.rightPressed = false;
                }        
        }
    }
		
		public static void main(String argv[]) {
			Game g =new Game();
	
			// Start the main game loop
			g.gameLoop();
		}

}
