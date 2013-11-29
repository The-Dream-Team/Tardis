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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.Sys;

import me.dreamteam.tardis.Entity;
import me.dreamteam.tardis.Entity;
import me.dreamteam.tardis.ShipEntity;


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
	// Is the game running or not?
	
	private String gameName = "Codename TARDIS ";
	private String build = "Alpha ";
	private String version = "0.1.9";
	
	private Entity ship;
	private ArrayList entities = new ArrayList();
	
    private double moveSpeed = 180;
	
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacebarPressed = false;
	
	private boolean logicRequiredThisLoop = false;
	
	
	private String timeDisplay = "";
	private String livesDisplay = "";
	public int gameTime =0;
	int gameLives; 
	
	
	int fps;
	long lastFPS;
	// To grab the FPS, for debugging purposes. 60 FPS is the best FPS!
	Timer newtimer = new Timer();
	
	
	
	public Game() {
		// create a frame to contain our game
		JFrame container = new JFrame(gameName + "- " + build + version + " | FPS: " + lastFPS);
		
		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(500,650));
		panel.setLayout(null);
		
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,500,650);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		//Don't move this variable as it will add extra padding if moved below pack
		container.setResizable(false);
		
		container.pack();
		
		// Make sure the window shows up smack bang in the centre
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = container.getSize().width;
		int h = container.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		container.setLocation(x, y);
		
		// Then set the container as visible
		container.setBackground(Color.black);
		container.setVisible(true);
		
		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Closing " + gameName + version);
				// Debug to ensure that game exits correctly
				System.exit(0);
			}
		});
		
		// Init keys
        addKeyListener(new KeyInputHandler());
        
		// create the buffering strategy for graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
        
        requestFocus();
		initEntities();
		
		

	}
	
    public void updateLogic() {
        logicRequiredThisLoop = true;
     }
    
	
    public void quitGame() {
		JOptionPane.showMessageDialog(null, "Closing " + gameName + version);
		// Debug to ensure that game exits correctly
		System.exit(0);
    }
	
	
	/**
	 * initialise entities 
	 */
	
	private void initEntities() {
		// create the player ship and place it roughly in the center of the screen
		ship = new ShipEntity(this,"sprites/ship.png",220,568);
		entities.add(ship);
		
	
	
		Entity Enemy = new EnemyEntity(this,"sprites/alien1.png",150,50);
		entities.add(Enemy);

		
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
        spacebarPressed = false;
        
        

    	
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
	/**
	 * GAME LOOP and Main below
	 */
	
	
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		while (isRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			lastFPS = getTime();
			
			// Colour in background
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,500,650);
			
			// Timer
			int fontSize = 18;
			
			g.setColor(Color.white);
			g.setFont(new Font("Terminal", Font.PLAIN, fontSize));
            g.drawString(timeDisplay,(55-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
            g.drawString("Time:",(55-g.getFontMetrics().stringWidth("Time:"))/2,18);
            
            String convtime = gameTime+"";
            g.setColor(Color.white);
			g.setFont(new Font("Terminal", Font.PLAIN, fontSize));
            g.drawString(timeDisplay,(155-g.getFontMetrics().stringWidth(timeDisplay))/2,18);
            g.drawString(convtime,(155-g.getFontMetrics().stringWidth(convtime))/2,18);
        	
            
            //Score
			g.setColor(Color.white);
			g.setFont(new Font("Terminal", Font.PLAIN, fontSize));
            g.drawString(livesDisplay,(755-g.getFontMetrics().stringWidth(livesDisplay))/2,18);
            g.drawString("Score:",(755-g.getFontMetrics().stringWidth("Score:"))/2,18);
			
			// Adds the ship into game.
			
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
	          
			  // Staggers ship movement - cleans up PR #18
		      ship.setHorizontalMovement(0);
		      
		      // Ship movement
              if ((leftPressed) && (!rightPressed)) {
                  ship.setHorizontalMovement(-moveSpeed);
              } else if ((rightPressed) && (!leftPressed)) {
                  ship.setHorizontalMovement(moveSpeed);
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    	spacebarPressed = true;
                }
                if (e.getKeyChar() == 27) {
                	quitGame();
                	// Quit game using the escape key
            }
        } 
        

        public void keyReleased(KeyEvent e) {
               
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                	spacebarPressed = false;
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
