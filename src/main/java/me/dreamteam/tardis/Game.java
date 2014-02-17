package me.dreamteam.tardis;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main Class
 */

public class Game extends Canvas {
    GProperties properties = new GProperties();
    Sound sound;
    public Game() {
        JFrame container = new JFrame(Utils.gameName + "- " + Utils.build + Utils.version);

        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(500, 650));
        panel.setLayout(null);

        panel.setBackground(Color.black);
        setBounds(0, 0, 500, 650);
        panel.add(this);

        setIgnoreRepaint(true);
        container.setResizable(false);
        container.pack();

        // Set up elements colours
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", new ColorUIResource(0, 0, 0));
        UI.put("Panel.background", new ColorUIResource(0, 0, 0));
        UI.put("OptionPane.messageForeground", new ColorUIResource(255, 255, 255));

        // Window setup
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = container.getSize().width;
        int h = container.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        container.setLocation(x, y);
        container.setBackground(Color.black);
        container.setVisible(true);

        properties.sX = x;
        properties.sY = y;

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
        properties.strategy = getBufferStrategy();

        Graphics2D gix = (Graphics2D) properties.strategy.getDrawGraphics();

        ImageIcon splashbgicon = new ImageIcon(Utils.splashURL);
        properties.splashbg = splashbgicon.getImage();

        gix.drawImage(properties.splashbg, 0, 0, null);

        gix.setColor(Color.DARK_GRAY);
        gix.setFont(new Font("Century Gothic", Font.BOLD + Font.ITALIC, Utils.splashFS));
        gix.drawString(Utils.build + Utils.version, (1070 - gix.getFontMetrics().stringWidth(Utils.gameName)) / 2, 648);
        properties.strategy.show();
        gix.dispose();

        // Register Font
        try {
            URL fontUrl = me.dreamteam.tardis.Game.class.getResource("/fonts/Volter.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            gix.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UI.put("OptionPane.messageFont", new Font ("Volter (Goldfish)",Font.BOLD, 14));

        requestFocus();
        initPlayer();
        titleScreen();

        Graphics2D gi = (Graphics2D) properties.strategy.getDrawGraphics();

        gi.setColor(Color.red);
        gi.setFont(new Font("Volter (Goldfish)", Font.BOLD + Font.ITALIC, Utils.splashFS));
        gi.drawString(Utils.txtLoad, (500 - gi.getFontMetrics().stringWidth(Utils.txtLoad)) / 2, 248);
        properties.strategy.show();
        gi.dispose();

    }

    /**
     * Create our ship
     */

    private void initPlayer() {

        if (properties.shipS == 0) {
            properties.ship = new Player(this, "sprites/ship.png", 220, 568);
            properties.entities.add(properties.ship);
        }

        if (properties.shipS == 1) {
            properties.ship = new Player(this, "sprites/ship2.png", 220, 568);
            properties.entities.add(properties.ship);
        }

        if (properties.shipS == 2) {
            properties.ship = new Player(this, "sprites/ship3.png", 220, 568);
            properties.entities.add(properties.ship);
        }
    }

    private void updateEnt() {
        properties.moveSpeed = 180 + (properties.tWait * 0.7);
        properties.spriteLoc = properties.rSpriteLoc.nextInt(200);
        properties.spriteLoc2 = 200 + properties.rSpriteLoc.nextInt(250);
        if (properties.spriteLoc2 < properties.spriteLoc + 63) {
            if (properties.spriteLoc2 > properties.spriteLoc - 63) {
                properties.spriteLoc2 = properties.spriteLoc - 63;
                if (properties.spriteLoc2 > 450)
                    properties.spriteLoc2 = properties.spriteLoc - 63;
            }
        }
        if (properties.tWait != properties.gameTime) {
            int FinalLoc;
            if (properties.gameTime >= properties.tWait + 2 && properties.advanceLevel == false) {
                properties.tWait = properties.gameTime;
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        FinalLoc = properties.spriteLoc;
                    } else {
                        FinalLoc = properties.spriteLoc2;
                    }
                    Entity Enemies = new Enemy(this, "sprites/enemies/0" + properties.curSprite + ".png", FinalLoc, -50);
                    properties.entities.add(Enemies);
                    properties.curSprite += 1;
                    if (properties.curSprite > 5)
                        properties.curSprite = 1;
                }
            } else if (properties.advanceLevel == true) {
                if (properties.gameTime >= properties.tWait + 2 && properties.level == 2) {
                    properties.tWait = properties.gameTime;
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            FinalLoc = properties.spriteLoc;
                        } else {
                            FinalLoc = properties.spriteLoc2;
                        }
                        Entity Enemies = new Enemy(this, "sprites/enemies/0" + properties.curSprite + ".png", FinalLoc, -50, (properties.tWait + (100 * 0.45) - 30));
                        properties.entities.add(Enemies);
                        properties.curSprite += 1;
                        if (properties.curSprite > 5)
                            properties.curSprite = 1;
                    }
                } else if (properties.gameTime >= properties.tWait && properties.level == 3) {
                    properties.tWait = properties.gameTime;
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            FinalLoc = properties.spriteLoc;
                        } else {
                            FinalLoc = properties.spriteLoc2;
                        }
                        Entity Enemies = new Enemy(this, "sprites/enemies/0" + properties.curSprite + ".png", FinalLoc, -50, (properties.tWait + (100 * 0.45) - 30));
                        properties.entities.add(Enemies);
                        properties.curSprite += 1;
                        if (properties.curSprite > 5)
                            properties.curSprite = 1;
                    }
                } else if (properties.gameTime >= properties.tWait && properties.level == 4) {
                    properties.tWait = properties.gameTime;
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            FinalLoc = properties.spriteLoc;
                        } else {
                            FinalLoc = properties.spriteLoc2;
                        }
                        Entity Enemies = new Enemy(this, "sprites/enemies/0" + properties.curSprite + ".png", FinalLoc, -50, (150 + (115 * 0.45) - 30));
                        properties.entities.add(Enemies);
                        properties.curSprite += 1;
                        if (properties.curSprite > 5)
                        	properties.curSprite = 1;
                    	}
                } else if (properties.gameTime >= properties.tWait && properties.level == 5) {
                    properties.tWait = properties.gameTime;
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            FinalLoc = properties.spriteLoc;
                        } else {
                            FinalLoc = properties.spriteLoc2;
                        }
                        Entity Enemies = new Enemy(this, "sprites/enemies/0" + properties.curSprite + ".png", FinalLoc, -50, (115 + (100 * 0.45) - 30));
                        properties.entities.add(Enemies);
                        properties.curSprite += 1;
                        if (properties.curSprite > 5)
                            properties.curSprite = 1;
                    }
                }
            }
        }
    }


    private void startGame() {
        properties.entities.clear();
        properties.Background = new Background(this, "sprites/background.png", 0, 0);
        properties.backgroundImages.add(properties.Background);
        properties.Background2 = new Background(this, "sprites/background.png", 0, -650);
        properties.backgroundImages.add(properties.Background2);
        initPlayer();

        // reset key presses
        properties.leftPressed = false;
        properties.rightPressed = false;

        //reset time
        properties.timeMil = 0;
        //reset lives
        GProperties.gameLives = 3;
        properties.level = 1;
        properties.gameStart = true;
        properties.advanceLevel = false;
        properties.tWait = 0;
        properties.gameTime = 0;
        properties.finalTime = 0;
        properties.lastLoopTime = System.currentTimeMillis();
        properties.firing = false;
        properties.curWeapon = 1;
        properties.setWeapon(1);
        GProperties.debugHits = 0;
        
        Sound.soundTheme1.play();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();

        if (GProperties.debug) {
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
            properties.shipS = 2;
            startGame();
        }

        if (characterS == 1) {
            properties.shipS = 1;
            startGame();
        }

        if (characterS == 0) {
            properties.shipS = 0;
            startGame();
        }
    }

    public void titleScreen() {
        ImageIcon icon = new ImageIcon(Utils.iconURL);
        Utils.systemLF();

        Object[] options = {Utils.bPlay, Utils.bHowTo, Utils.bQuit};
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

        if (startG == 2) {
            System.exit(0);
        }

        if (startG == 1) {
            howToPlay();
        }

        if (startG == 0) {
            characterSelect();
        }

    }

    public void howToPlay() {

        HowToPlay HTP = new HowToPlay();
        HTP.setVisible(true);

        while (GProperties.wake == 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }

        if (GProperties.wake == 1) {
            GProperties.wake--;
            titleScreen();
        }

    }

    public void gameOver() {

        Sound.soundGameOver.play();
        new GProperties().propertiesFile();

        try {
            new Database().updateDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GProperties.debug) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("GAME OVER DISPLAYED AFTER " + properties.gameTime + " SECONDS");
            System.out.println("HITS:" + GProperties.debugHits + "/3" + " | " + "LEVEL: " + properties.level);
        }

        //Game over display
        Graphics2D g2 = (Graphics2D) properties.strategy.getDrawGraphics();
        g2.setColor(Color.RED);
        g2.setFont(new Font("Volter (Goldfish)", Font.BOLD, 32));
        g2.drawString(Utils.txtGO, (500 - g2.getFontMetrics().stringWidth(Utils.txtGO)) / 2, 80);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Volter (Goldfish)", Font.BOLD, 33));
        g2.drawString(Utils.txtGO, (500 - g2.getFontMetrics().stringWidth(Utils.txtGO)) / 2, 80);
        properties.strategy.show();
        g2.dispose();

        ImageIcon icon = new ImageIcon(Utils.iconURL);
        Utils.systemLF();

        Object[] options = {Utils.bPlayAgain, Utils.bQuit};
        int goG = JOptionPane.showOptionDialog(null,
                Utils.txtTravelled + properties.gameTime + Utils.txtMetre, Utils.goDialogTitle,
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
        properties.gameStart = false;
        long LoopTempTime = System.currentTimeMillis();
        Object[] options = {Utils.bReturn, Utils.bRestart, Utils.bQuit};
        int pauseG = JOptionPane.showOptionDialog(null,
                Utils.txtPS, Utils.tsDialogTitle,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                options[0]);
        double[] entCurYLoc = new double[properties.entities.size()];
        for (int i = 0; i < properties.entities.size(); i++) {
            Entity entity = (Entity) properties.entities.get(i);
            entCurYLoc[i] = entity.getVerticalMovement();
            entity.setVerticalMovement(0);
        }
        if (pauseG != 1 && pauseG != 2) {
            for (int i = 0; i < properties.entities.size(); i++) {
                Entity entity = (Entity) properties.entities.get(i);
                entity.setVerticalMovement(entCurYLoc[i]);
            }
            properties.finalTime = System.currentTimeMillis() - LoopTempTime;
            properties.gameStart = true;
        }

        if (pauseG == 2) {
            System.exit(0);
        }

        if (pauseG == 1) {
            properties.gameTime = 0;
            characterSelect();
        }

    }

    public void gameLoop() {
        properties.lastLoopTime = System.currentTimeMillis();
        long bgLoop = System.currentTimeMillis();

        while (properties.Running) {
            if (properties.gameStart == true) {

                long delta = (System.currentTimeMillis() - properties.finalTime) - properties.lastLoopTime;
                properties.finalTime = 0;
                properties.lastLoopTime = System.currentTimeMillis();
                properties.lastTime = getTime();
                updateTime();

                // Colour in background
                Graphics2D g = (Graphics2D) properties.strategy.getDrawGraphics();
                g.setColor(Color.black);
                g.fillRect(0, 0, 500, 650);

                for (int i = 0; i < properties.backgroundImages.size(); i++) {
                    Entity entity = (Entity) properties.backgroundImages.get(i);
                    entity.move(delta);
                }

                for (int i = 0; i < properties.entities.size(); i++) {
                    Entity entity = (Entity) properties.entities.get(i);
                    entity.move(delta);
                }

                long bgLoopCheck = System.currentTimeMillis();
                for (int i = 1; i < 3; i++) {
                    if (i == 2) {
                        properties.Background2.setVerticalMovement(10);
                    } else {
                        properties.Background.setVerticalMovement(10);
                    }
                    if ((bgLoopCheck - bgLoop) > 63000) {
                        properties.Background = new Background(this, "sprites/background.png", 0, -650);
                        properties.backgroundImages.add(properties.Background);
                        bgLoop = System.currentTimeMillis();
                    }

                }

                //testing for collision of player and enemy
                // p = ship, s = enemy

                for (int p = 0; p < properties.entities.size(); p++) {
                    for (int s = p + 1; s < properties.entities.size(); s++) {
                        Entity me = (Entity) properties.entities.get(p);
                        Entity him = (Entity) properties.entities.get(s);

                        if (me.collidesWith(him)) {
                            me.collidedWith(him);
                            him.collidedWith(me);
                        }
                    }
                }
                properties.entities.removeAll(properties.removeList);
                properties.removeList.clear();

                for (int i = 0; i < properties.backgroundImages.size(); i++) {
                    Entity entity = (Entity) properties.backgroundImages.get(i);
                    entity.draw(g);
                }
                for (int i = 0; i < properties.entities.size(); i++) {
                    Entity entity = (Entity) properties.entities.get(i);
                    entity.draw(g);
                }

                if (properties.gameTime > 59) {
                    properties.advanceLevel = true;
                    if (properties.gameTime < 119) {
                        properties.level = 2;
                    } else if (properties.gameTime < 179) {
                        properties.level = 3;
                    } else if (properties.gameTime < 239) {
                        properties.level = 4;
                    } else if (properties.gameTime >= 240) {
                        properties.level = 5;
                    }
                }

				/* 
				 * Game Text
				 */

                //Level
                g.setColor(Color.RED);
                g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.levelFS));
                g.drawString(Utils.txtLevel + properties.level, (500 - g.getFontMetrics().stringWidth(Utils.txtLevel + properties.level)) / 2, 18);

                // Distance
                g.setColor(Color.WHITE);
                g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.timeFS));
                g.drawString(properties.timeDisplay, (125 - g.getFontMetrics().stringWidth(properties.timeDisplay)) / 2, 18);
                g.drawString(Utils.txtTime, (125 - g.getFontMetrics().stringWidth(Utils.txtTime)) / 2, 18);

                if (properties.timeMil > 99) {
                    properties.gameTime = properties.timeMil / 100;
                }
                String convtime = String.valueOf(properties.gameTime*10);
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Volter (Goldfish)", Font.ITALIC, Utils.timeIFS));
                g.drawString(properties.timeDisplay, (275 - g.getFontMetrics().stringWidth(properties.timeDisplay)) / 2, 18);
                g.drawString(convtime, (275 - g.getFontMetrics().stringWidth(convtime)) / 2, 18);

                //Lives
                g.setColor(Color.WHITE);
                g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.livesFS));
                g.drawString(properties.livesDisplay, (870 - g.getFontMetrics().stringWidth(properties.livesDisplay)) / 2, 18);
                g.drawString(Utils.txtLives, (870 - g.getFontMetrics().stringWidth(Utils.txtLives)) / 2, 18);

                String convlives = String.valueOf(GProperties.gameLives);
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Volter (Goldfish)", Font.ITALIC, Utils.livesIFS));
                g.drawString(properties.timeDisplay, (965 - g.getFontMetrics().stringWidth(properties.timeDisplay)) / 2, 18);
                g.drawString(convlives, (965 - g.getFontMetrics().stringWidth(convlives)) / 2, 18);

                //Weapon
                g.setColor(Color.WHITE);
                g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.weaponFS));
                g.drawString(Utils.txtWeapon, (110 - g.getFontMetrics().stringWidth(Utils.txtWeapon)) / 2, 645);
                if (properties.curWeapon == 1) {
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon1), (205 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon1))) / 2, 645);
                } else {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Volter (Goldfish)", Font.PLAIN, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon1), (205 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon1))) / 2, 645);
                }

                g.setColor(Color.WHITE);
                g.setFont(new Font("Volter (Goldfish)", Font.PLAIN, Utils.weaponIFS));
                g.drawString("/", (225 - g.getFontMetrics().stringWidth("/")) / 2, 645);

                if (properties.curWeapon == 2) {
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon2), (245 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon2))) / 2, 645);
                } else {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Volter (Goldfish)", Font.PLAIN, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon2), (245 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon2))) / 2, 645);
                }
                g.setColor(Color.WHITE);
                g.setFont(new Font("Volter (Goldfish)", Font.PLAIN, Utils.weaponIFS));
                g.drawString("/", (265 - g.getFontMetrics().stringWidth("/")) / 2, 645);

                if (properties.curWeapon == 3) {
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("Volter (Goldfish)", Font.BOLD, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon3), (285 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon3))) / 2, 645);
                } else {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Volter (Goldfish)", Font.PLAIN, Utils.weaponIFS));
                    g.drawString(String.valueOf(properties.weapon3), (285 - g.getFontMetrics().stringWidth(String.valueOf(properties.weapon3))) / 2, 645);
                }
                // Clear Graphics
                g.dispose();
                properties.strategy.show();
                updateEnt();

                if (GProperties.gameLives == 0) {
                    gameOver();
                }

                properties.ship.setHorizontalMovement(0);
                // Ship movement
                if ((properties.leftPressed) && (!properties.rightPressed)) {
                    properties.ship.setHorizontalMovement(-properties.moveSpeed);
                } else if ((properties.rightPressed) && (!properties.leftPressed)) {
                    properties.ship.setHorizontalMovement(properties.moveSpeed);
                }

                if (properties.firing && properties.weapon1 > 0) {
                    if (properties.curWeapon == 1) {
                        useWeapon();
                        properties.weaponLoopTime = System.currentTimeMillis();
                        properties.setWeapon(0);
                    }
                    if (properties.curWeapon != 1) {
                        properties.firing = false;
                    }
                } else if (properties.firing && System.currentTimeMillis() < properties.weaponLoopTime + 4421) {
                    useWeapon();
                } else {
                    if (System.currentTimeMillis() > properties.weaponLoopTime + 4421) {
                        properties.firing = false;
                    }
                }

                properties.weaponPressed = false;
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
            }
        }
    }

    public void useWeapon() {
        if (properties.curWeapon == 1) {
            if (System.currentTimeMillis() - properties.lastFire < properties.timeBetweenShots) {
                return;
            }
            properties.lastFire = System.currentTimeMillis();
            if (properties.shipS == 0) {
                Weapon shot = new Weapon(this, "sprites/Shot1.png", properties.ship.getX() + 13, properties.ship.getY() - 15);
                properties.entities.add(shot);
                Weapon shot2 = new Weapon(this, "sprites/Shot1.png", properties.ship.getX() + 23, properties.ship.getY() - 15);
                properties.entities.add(shot2);
                Sound.soundShoot.play();
            } else if (properties.shipS == 1) {
                Weapon shot = new Weapon(this, "sprites/Shot2.png", properties.ship.getX() + 0, properties.ship.getY() - -12);
                properties.entities.add(shot);
                Weapon shot2 = new Weapon(this, "sprites/Shot2.png", properties.ship.getX() + 35, properties.ship.getY() - -12);
                properties.entities.add(shot2);
                Sound.soundShoot.play();
            } else if (properties.shipS == 2) {
                Weapon shot = new Weapon(this, "sprites/Shot3.png", properties.ship.getX() + 13, properties.ship.getY() - 15);
                properties.entities.add(shot);
                Weapon shot2 = new Weapon(this, "sprites/Shot3.png", properties.ship.getX() + 23, properties.ship.getY() - 15);
                properties.entities.add(shot2);
                Sound.soundShoot.play();
            }
        }
    }
    
    /**
     * Update the game time
     */

    public void updateTime() {
        if (getTime() - properties.lastTime > 1000) {
            properties.timeMil = 0; //reset the timer counter
            properties.lastTime += 1000; //add one second
        }
        properties.timeMil++;
    }

    public long getTime() {
        return ((System.nanoTime()/1000000)+552);
    }

    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - properties.lastFrame);
        properties.lastFrame = time;

        return delta;
    }

    public void removeEntity(Entity entity) {
        properties.removeList.add(entity);
    }

    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                properties.leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                properties.rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_1) {
                properties.curWeapon = 1;
                if (properties.debug) {
                    System.out.println("DEBUG: [INFO] Current weapon set to weapon " + properties.curWeapon);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_2) {
                properties.curWeapon = 2;
                if (properties.debug) {
                    System.out.println("DEBUG: [INFO] Current weapon set to weapon " + properties.curWeapon);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_3) {
                properties.curWeapon = 3;
                if (properties.debug) {
                    System.out.println("DEBUG: [INFO] Current weapon set to weapon " + properties.curWeapon);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                properties.firing = true;
                properties.weaponPressed = true;
            }
            if (e.getKeyChar() == 27 || e.getKeyCode() == KeyEvent.VK_PAUSE || e.getKeyCode() == KeyEvent.VK_P) {
                pauseGame();
            }
            if (properties.debug) {
            	if (e.getKeyCode() == KeyEvent.VK_F7) {
                	properties.advanceLevel = true;
                    properties.timeMil = 18000;
                    for (int r = 0; r < properties.entities.size(); r++) {
                    	properties.removeList.add(properties.entities.get(r));
                    }  
                    initPlayer();
            	}
            }
            if (properties.debug) {
                if (e.getKeyCode() == KeyEvent.VK_F6) {
                    GProperties.gameLives = 0;
                }
            }
            
        }

        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                properties.leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                properties.rightPressed = false;
            }
        }
    }

    public static void main(String argv[]) {
        Game g = new Game();

        // Start the main game loop
        g.gameLoop();
    }

}
