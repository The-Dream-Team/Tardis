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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.lwjgl.Sys;

import me.dreamteam.tardis.Entity;
import me.dreamteam.tardis.ShipEntity;
import me.dreamteam.tardis.EnemyEntity;


/**
Main Class
 */

public class Game extends Canvas {
	
	/**
	 * Begin the game parameters that will allow us to define certain elements.
	 */
	
	private BufferStrategy strategy;
	// This provides hardware acceleration
	
	private boolean isRunning = true;
	
	private String gameName = "Codename TARDIS ";
	private String build = "Alpha ";
	private String version = "0.3.3";
	
	private Entity ship;
	private int shipS = 0;
	private ArrayList entities = new ArrayList();
	
	private ArrayList enemies = new ArrayList();
 
	
	private double moveSpeed = 180;
	
    private boolean leftPressed = false;
    private boolean rightPressed = false;
	
	private boolean logicRequiredThisLoop = false;
	
	long lastFrame;
	
	private String timeDisplay = "";
	private String livesDisplay = "";
	int timeMil;
	public int gameTime = 0;
	public int gameLives = 3; 
	//setup for ent mvement
	int SpriteLoc;
	int twait = 0;
	int CurSprite = 1;
	ImageIcon blankIcon = new ImageIcon();
	Random rSpriteLoc = new Random();
	
	long lastTime;
	
	URL iconURL = getClass().getResource("/sprites/ship.png");
	URL ship1URL = getClass().getResource("/sprites/ship.png");
	URL ship2URL = getClass().getResource("/sprites/ship2.png");
	URL ship3URL = getClass().getResource("/sprites/ship3.png");
	
	
	public Game() {
		
		JFrame container = new JFrame(gameName + "- " + build + version);
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(500,650));
		panel.setLayout(null);
		
		
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,500,650);
		panel.add(this);

		setIgnoreRepaint(true);
		
		//Don't move this variable as it will add extra padding if moved below pack
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
		
		
		//How to respond to user exiting the window
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitGame();
				System.exit(0);
				// Debug to ensure that game exits correctly
			}
		});
		
		ImageIcon icon = new ImageIcon(iconURL);
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
		
	
	public void characterSelect() {
		ImageIcon icon = new ImageIcon(iconURL);
		ImageIcon ship1 = new ImageIcon(ship1URL);
		ImageIcon ship2 = new ImageIcon(ship2URL);
		ImageIcon ship3 = new ImageIcon(ship3URL);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}        
		
		Object[] coptions = {"<html><img src=\""+ship1+"\"></img><br><center>PEN-15</center></html>", "<html><img src=\""+ship2+"\"></img><br><center>FALCON</center></html>", "<html><img src=\""+ship3+"\"></img><br><center>MOTH</center></html>"};
		int characterS = JOptionPane.showOptionDialog(null,
		"<html><b>&raquo; Choose your spaceship to begin:</b></html>", "Get ready!", JOptionPane.YES_NO_CANCEL_OPTION,
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
		ImageIcon icon = new ImageIcon(iconURL);	
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} 
		
		Object[] options = {"Play", "Quit Game"};
		int startG = JOptionPane.showOptionDialog(null,
		"Welcome to " + gameName + version, "Tardis",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		icon,
		options,
		options[0]);
		
		if (startG != 0 && startG != 1) {
			quitGame();
		}
	
		if (startG == 1) {
			System.exit(0);
		}
		
		if (startG == 0) {
			characterSelect();
		}
		
	}
	
	
    public void updateLogic() {
        logicRequiredThisLoop = true;
     }
    
	
    public void quitGame() {
    	ImageIcon icon = new ImageIcon(iconURL);
		JOptionPane.showMessageDialog(null, "Closing " + gameName + version,"Debug",JOptionPane.INFORMATION_MESSAGE,icon);
		// Debug to ensure that game exits correctly
		System.exit(0);
    }
	
	
	/**
	 * initialise entities/ Create the Sprites
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
		SpriteLoc = rSpriteLoc.nextInt(450);
		for(int i = 0; i<9; i++){
			if(twait != gameTime){
				twait = gameTime;
				Entity Enemies = new EnemyEntity(this,"sprites/enemies/0"+CurSprite+".png",SpriteLoc,-50);
				entities.add(Enemies);
				CurSprite += 1;
			}
		}
		if (CurSprite>3)
			CurSprite=1;
	}
	
	/** 
	 * Garbage collection and looping
	 */
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
	
	/**
	 * GAME LOOP and Main below
	 */
	
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		while (isRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			lastTime = getTime();
			updateTime();

			
			// Colour in background		
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,500,650);
			
			int fontSize = 18;
			int rfontSize = 12;
			
			g.setColor(Color.red);
			g.setFont(new Font("Century Gothic", Font.PLAIN, rfontSize));
            g.drawString("-- WORK IN PROGRESS --",(500-g.getFontMetrics().stringWidth("-- WORK IN PROGRESS --"))/2,12);
			
			
			// Timer
			g.setColor(Color.white);
			g.setFont(new Font("Lucida Console", Font.BOLD, fontSize));
            g.drawString(timeDisplay,(70-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
            g.drawString("Time:",(70-g.getFontMetrics().stringWidth("Time:"))/2,18);
            
            
            if (timeMil > 99){
            	gameTime = timeMil/100;
            }
            String convtime = String.valueOf(gameTime);
            g.setColor(Color.white);
			g.setFont(new Font("Lucida Console", Font.ITALIC, fontSize));
            g.drawString(timeDisplay,(175-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
            g.drawString(convtime,(175-g.getFontMetrics().stringWidth(convtime))/2,18);
 
            //Lives
			g.setColor(Color.white);
			g.setFont(new Font("Lucida Console", Font.BOLD, fontSize));
            g.drawString(livesDisplay,(875-g.getFontMetrics().stringWidth(livesDisplay))/2,18);
            g.drawString("Lives:",(875-g.getFontMetrics().stringWidth("Lives:"))/2,18);
            
            String convlives = String.valueOf(gameLives);
            g.setColor(Color.white);
			g.setFont(new Font("Lucida Console", Font.ITALIC, fontSize));
            g.drawString(timeDisplay,(965-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
            g.drawString(convlives,(965-g.getFontMetrics().stringWidth(convlives))/2,18);
			


			
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
		}
	}
	
	
    private class KeyInputHandler extends KeyAdapter {

        

        public void keyPressed(KeyEvent e) {                
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = true;
                }

                if (e.getKeyChar() == 27) {
                	titleScreen();
            }
        } 
        

        public void keyReleased(KeyEvent e) {
               
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = false;
                }
                
        }
        
        
    }
	
    
    
	
	
		/**
		 * Game Start
		 */
		public static void main(String argv[]) {
			Game g =new Game();
	
			// Start the main game loop
			g.gameLoop();
		}

	
}
