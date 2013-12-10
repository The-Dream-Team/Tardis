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
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.Sys;

import me.dreamteam.tardis.Entity;
import me.dreamteam.tardis.ShipEntity;
import me.dreamteam.tardis.EnemyEntity;
import me.dreamteam.tardis.Utils;
import me.dreamteam.tardis.UtilsHTML;


/**
Main Class
 */

public class Game extends Canvas {
	private BufferStrategy strategy;
	// This provides hardware acceleration
	
	private boolean isRunning = true;
	private boolean gameStart = false;
	
	private Entity ship;
	private int shipS = 0;
	double curY = 0;
	long lastLoopTime;
	private ArrayList entities = new ArrayList();
	private ArrayList enemies = new ArrayList();
 
	
	private double moveSpeed = 180;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
	private boolean logicRequiredThisLoop = false;
	private boolean advanceLevel = false;
	long lastFrame;
	long finalTime = 0;
	private String timeDisplay = "";
	private String livesDisplay = "";
	int timeMil;
	public int gameTime = 0;
	public int gameLives = 3; 
	int SpriteLoc;
	int SpriteLoc2;
	int SpriteLoc3;
	int twait = 0;
	int CurSprite = 1;
	ImageIcon blankIcon = new ImageIcon();
	Random rSpriteLoc = new Random();
	
	long lastTime;
	
	private int level = 1;
	
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
		
		ImageIcon icon = new ImageIcon(Utils.iconURL);
		container.setIconImage(icon.getImage());
		
		// Init keys
        addKeyListener(new KeyInputHandler());
        
		// create the buffering strategy for graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
        
        requestFocus();
		initEntities();
		titleScreen();
	}

	
    public void updateLogic() {
        logicRequiredThisLoop = true;
     }
	
	
	/**
	 * Create our ship
	 */
	
	private void initEntities() {
		
		if (shipS == 0) {
		ship = new ShipEntity(this,"sprites/ship.png",220,568);
		entities.add(ship);
		}
		
		if (shipS == 1) {
		ship = new ShipEntity(this,"sprites/ship2.png",220,568);
		entities.add(ship);
		}
		
		if (shipS == 2) {
		ship = new ShipEntity(this,"sprites/ship3.png",220,568);
		entities.add(ship);
		}
	}
	
	private void updateEnt(){
		moveSpeed = 180+(twait*0.7);
		SpriteLoc = rSpriteLoc.nextInt(200);
		SpriteLoc2 = 200+rSpriteLoc.nextInt(250);
		if(SpriteLoc2 < SpriteLoc+56){
			if(SpriteLoc2 > SpriteLoc-56){
				SpriteLoc2 = SpriteLoc-56;
				if (SpriteLoc2 > 450)
					SpriteLoc2 = SpriteLoc-56;
			}
		}
		if(twait != gameTime){
			int FinalLoc;
			if(gameTime >= twait+2 && advanceLevel == false){
				twait = gameTime;
				
				for(int i = 0; i<2; i++){
					if(i==0){
						FinalLoc = SpriteLoc;
					}else{
						FinalLoc = SpriteLoc2;
					}
					Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+CurSprite+".png",FinalLoc,-50);
					entities.add(Enemies);
					CurSprite += 1;
					if (CurSprite>5)
						CurSprite=1;
				}
			}else if (advanceLevel == true){
				if(gameTime>= twait && level ==2){
					twait = gameTime;
					for(int i = 0; i<2; i++){
						if(i==0){
							FinalLoc = SpriteLoc;
						}else{
							FinalLoc = SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+CurSprite+".png",FinalLoc,-50, (twait+(100*0.45)-30));
						entities.add(Enemies);
						CurSprite += 1;
						if (CurSprite>5)
							CurSprite=1;
					}
				}else if(gameTime>= twait && level >2){
					twait = gameTime;
					for(int i = 0; i<2; i++){ 
						if(i==0){ 
							FinalLoc = SpriteLoc;
						}else{
							FinalLoc = SpriteLoc2;
						}
						Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+CurSprite+".png",FinalLoc,-50, (twait+(100*0.45)-30));
						entities.add(Enemies);
						CurSprite += 1;
						if (CurSprite>5)
							CurSprite=1;
					}
				}
			}
		}
	}
	

	private void startGame() {

        entities.clear();
        initEntities();
        
        
        // reset key presses
        leftPressed = false;
        rightPressed = false;
        
        //reset time
        timeMil = 0;
        //reset lives
        gameLives = 3;
        level = 1;
        gameStart = true;
        twait = 0;
        gameTime = 0;
        finalTime = 0;
        lastLoopTime = System.currentTimeMillis();
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
		blankIcon,
		coptions,
		coptions[0]);

		if (characterS != 2 && characterS != 1 && characterS != 0) {
			titleScreen();
		}
		
		if (characterS == 2) {
			shipS = 2;
			startGame();
		}
	
		if (characterS == 1) {
			shipS = 1;
			startGame();
		}
		
		if (characterS == 0) {
			shipS = 0;
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
	
	public void pauseGame() {
		ImageIcon icon = new ImageIcon(Utils.iconURL);	
		Utils.systemLF();
		gameStart = false;
		long LoopTempTime = System.currentTimeMillis();
		Object[] options = {Utils.bReturn, Utils.bRestart, Utils.bQuit};
		int pauseG = JOptionPane.showOptionDialog(null,
		Utils.txtPS, Utils.tsDialogTitle,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		double[] entCurYLoc = new double[entities.size()];
		for (int i=0;i<entities.size();i++) {
			Entity entity = (Entity) entities.get(i);
            entCurYLoc[i] = entity.getVerticalMovement();
            entity.setVerticalMovement(0);
		 }
		if (pauseG != 1 && pauseG != 2) {
			for (int i=0;i<entities.size();i++) {
	            Entity entity = (Entity) entities.get(i);
	            entity.setVerticalMovement(entCurYLoc[i]);
			 }
			finalTime = System.currentTimeMillis() - LoopTempTime;
			gameStart = true;
		}
		
		if (pauseG == 2) {
			System.exit(0);
		}
		
		if (pauseG == 1) {
			gameTime = 0;
			characterSelect();
		}
				
	}
	
	
	public void gameLoop() {
		lastLoopTime = System.currentTimeMillis();
		
		while (isRunning) {
			if(gameStart == true){
				
				long delta = (System.currentTimeMillis()-finalTime) - lastLoopTime;
				finalTime = 0;
				lastLoopTime = System.currentTimeMillis();
				lastTime = getTime();
				updateTime();
				// Colour in background		
				Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,500,650);
											
	            for (int i=0;i<entities.size();i++) {
					Entity entity = (Entity) entities.get(i);
					
					entity.move(delta);
				}
				
	
	            if (logicRequiredThisLoop) {
	                    for (int i=0;i<entities.size();i++) {
	                            Entity entity = (Entity) entities.get(i);
	                            entity.doLogic();
	                    }
	                    
	                    logicRequiredThisLoop = false;
	            }
				
				for (int i=0;i<entities.size();i++) {
	                  Entity entity = (Entity) entities.get(i);
	                  
	                  entity.draw(g);
				}
				
				if(gameTime >90){
					advanceLevel = true;
					if(gameTime > 200){
						level = 3;
					}else{
						level = 2;
					}
				}
				
				/* 
				 * Game Text
				 */
				
				g.setColor(Color.red);
				g.setFont(new Font("Century Gothic", Font.BOLD, Utils.levelFS));
	            g.drawString(Utils.txtLevel + level,(500-g.getFontMetrics().stringWidth(Utils.txtLevel + level))/2,18);
				
				// Timer
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.timeFS));
	            g.drawString(timeDisplay,(70-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
	            g.drawString(Utils.txtTime,(70-g.getFontMetrics().stringWidth(Utils.txtTime))/2,18);
	            
	            
	            if (timeMil > 99){
	            	gameTime = timeMil/100;
	            }
	            String convtime = String.valueOf(gameTime);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.timeIFS));
	            g.drawString(timeDisplay,(175-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
	            g.drawString(convtime,(175-g.getFontMetrics().stringWidth(convtime))/2,18);
	 
	            //Lives
				g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.BOLD, Utils.livesFS));
	            g.drawString(livesDisplay,(875-g.getFontMetrics().stringWidth(livesDisplay))/2,18);
	            g.drawString(Utils.txtLives,(875-g.getFontMetrics().stringWidth(Utils.txtLives))/2,18);
	            
	            String convlives = String.valueOf(gameLives);
	            g.setColor(Color.white);
				g.setFont(new Font("Lucida Console", Font.ITALIC, Utils.livesIFS));
	            g.drawString(timeDisplay,(965-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
	            g.drawString(convlives,(965-g.getFontMetrics().stringWidth(convlives))/2,18);
				  
				  
				  	
				// Clear Graphics
				g.dispose();
				strategy.show();	
				updateEnt();
				
				ship.setHorizontalMovement(0);  
			    // Ship movement
	            if ((leftPressed) && (!rightPressed)) {
	            	ship.setHorizontalMovement(-moveSpeed);
	            } else if ((rightPressed) && (!leftPressed)) {
	                ship.setHorizontalMovement(moveSpeed);
	            }
	              
	            //testing for collision of player and enemy  
	            for (int p=0;p<entities.size();p++) {
	                      for (int s=p+1;s<entities.size();s++) {
	                              Entity me = (Entity) entities.get(p);
	                              Entity him = (Entity) entities.get(s);
	                              
	                              if (me.collidesWith(him)) {
	                                      me.collidedWith(him);
	                                      him.collidedWith(me);
	                              }
	                      }
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
	    if (getTime() - lastTime > 1000) {
	        timeMil = 0; //reset the FPS counter
	        lastTime += 1000; //add one second
	    }
	    timeMil++;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	 
	    return delta;
	}
	
	
    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {                
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                        leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                        rightPressed = true;
                }

                if (e.getKeyChar() == 27 || e.getKeyCode() == KeyEvent.VK_PAUSE || e.getKeyCode() == KeyEvent.VK_P) {
                	pauseGame();
                }

        } 
        

        public void keyReleased(KeyEvent e) {
           
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                        leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                        rightPressed = false;
                }        
        }
    }
	
	
		public static void main(String argv[]) {
			Game g =new Game();
	
			// Start the main game loop
			g.gameLoop();
		}

	
}
