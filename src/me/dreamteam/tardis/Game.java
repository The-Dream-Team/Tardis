package me.dreamteam.tardis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.Sys;

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
	private String version = "0.1.7";
	// Version set up so that we can see where we are at
	
	private Entity ship;
	private ArrayList entities = new ArrayList();
	//game player
	
	int fps;
	long lastFPS;
	// To grab the FPS, for debugging purposes. 60 FPS is the best FPS!
	
	
	
	
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
		
		// create the buffering strategy for graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		initEntities();

		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Closing " + gameName + version);
				// Debug to ensure that game exits correctly
				System.exit(0);
			}
		});
	}
	
	
	/**
	 * initialise entities 
	 */
	
	private void initEntities() {
		// create the player ship and place it roughly in the center of the screen
		ship = new ShipEntity(this,"sprites/ship.png",206,508);
		System.out.println("Loading ship..."); //debug
		entities.add(ship);
	}
	
	/** 
	 * Garbage collection and looping
	 */
	private void startGame() {
        entities.clear();
        initEntities();
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
			g.fillRect(0,0,800,650);
			
			
			// Adds the ship into game. Needs resizing
			  for (int i=0;i<entities.size();i++) {
                  Entity entity = (Entity) entities.get(i);
                  
                  entity.draw(g);
          }
			  
			  
			
			// Draw the entities and other items
			g.dispose();
			strategy.show();
			
			
		
			
			
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
