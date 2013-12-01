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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private String version = "0.2.1";
	
	private Entity ship;
	private ArrayList entities = new ArrayList();
	
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
	
	
	long lastTime;
	
	URL iconURL = getClass().getResource("/sprites/ship.png");
	
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
				// Debug to ensure that game exits correctly
				System.exit(0);
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
		
	public void titleScreen() {
		
    ImageIcon icon = new ImageIcon(iconURL);	
		
	Object[] options = {"Play Game", "Quit Game"};
	int startG = JOptionPane.showOptionDialog(null,
			"Welcome to " + gameName + version, "Title Screen",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					icon,
					options,
					options[0]);
	
	if (startG == 1) {
	 quitGame();
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
		ship = new ShipEntity(this,"sprites/ship.png",220,568);
		entities.add(ship);
		
		Entity Enemy = new EnemyEntity(this,"sprites/alien1.png",150,50);
		entities.add(Enemy);
		Entity Enemy2 = new EnemyEntity(this,"sprites/alien1.png",250,50);
		entities.add(Enemy2);
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

                if (e.getKeyChar() == 27) {
                	quitGame();
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
