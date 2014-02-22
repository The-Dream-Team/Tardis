package me.dreamteam.tardis;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class GProperties {

	/*
     * IMPORTANT INFORMATION:
	 * This class is for the purpose of declaring game variables to be used in the main class (or in some cases, other classes)
	 */
	
	/* Hardware Acceleration */

    public BufferStrategy strategy;
	
	/* Debug */

    public static boolean debug = true;
    public static int debugHits = 0;
	
	/* Game Initilisation Variables */

    public boolean Running = true;
    public boolean gameStart = false;
    public long lastLoopTime;
    public Image splashbg;
    public static int wake = 0;
    public static String username = "";
	
	/* Entities */

    public Entity Background;
    public Entity Background2;
    public Entity ship;
    public int shipS = 0;

    public ArrayList entities = new ArrayList();
    public ArrayList backgroundImages = new ArrayList();
    public ArrayList removeList = new ArrayList();

    public double moveSpeed = 180;

    /* Sprites */
    public int spriteLoc;
    public int spriteLoc2;
    public int spriteLoc3;

    public int curSprite = 1;
    public double curY = 0;
    public Random rSpriteLoc = new Random();

	/* Keyboard Listeners */

    public boolean leftPressed = false;
    public boolean rightPressed = false;
    public boolean firing = false;
    
	/* Game Logic and Time */

    public boolean advanceLevel = false;
    public int level = 1;
    public long lastFrame;
    public long finalTime = 0;
    public String timeDisplay = "";
    public String livesDisplay = "";
    public boolean usedPack = false;
    public boolean pack = false;
    public ArrayList packList = new ArrayList();
    public Entity Pack;
    public int weapon1 = 1;
    public int weapon2 = 0;
    public int weapon3 = 0;
    public String weaponDisplay = weapon1 + "/" + weapon2 + "/" + weapon3;
    public static int gameTime = 0;
    public static int gameLives = 3;
    public int timeMil;
    public long lastTime;
    public int tWait = 0;
    public long lastFire = 0;
    public long timeBetweenShots = 110;
    public long weaponLoopTime;
    public int curWeapon = 1;
    public boolean weaponPressed = false;

    public void setWeapon(int w) {
        weapon1 = w;
        weaponDisplay = weapon1 + "/" + weapon2 + "/" + weapon3;
    }

    public int getWeapon() {
        return weapon1;
    }
	
	/* Window Toolkit */

    public static int sX = 0;
    public static int sY = 0;

    public void propertiesFile() {

        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("game.properties"));

            String pcA = prop.getProperty("plays");
            int pcB = Integer.parseInt(pcA);
            int pcC = pcB + 1;
            String pcD = "" + pcC;

            prop.setProperty("plays", pcD);

            prop.store(new FileOutputStream("game.properties"), null);

            if (debug) {
                System.out.println("DEBUG: [INFO] Total plays: " + prop.getProperty("plays"));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
