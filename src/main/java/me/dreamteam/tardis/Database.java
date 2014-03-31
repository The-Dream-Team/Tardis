package me.dreamteam.tardis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    // Update high scores by connecting to the website
    public void dbUpdateScore() throws Exception {
        String dbDistance = Integer.toString(GProperties.gameTime) + "0";
        String dbUsername = GProperties.username;
        try {
            // Send data
            URL dbConnect = new URL("http://37.187.75.63/includes/highscores/update.php?username=" + dbUsername + "&distance=" + dbDistance);
            URLConnection conn = dbConnect.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream())
            );
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                if (GProperties.debug) {
                    System.out.println("DEBUG: [INFO] Retrieved the following from server:" + inputLine);
                }
            in.close();
        } catch (Exception ex) {
            if (GProperties.debug) {
                System.out.println("DEBUG: [WARNING] " + ex.toString());
            }
        }
    }
    
    public void dbUpdateDistance() throws Exception {

        String dbDistance = Integer.toString(GProperties.gameTime) + "0";
        int getDistance = Integer.parseInt(dbDistance);

        int setDistance = GProperties.achDistance + getDistance;

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");

            System.out.println("DEBUG: [INFO] Opened database successfully");

        stmt = c.createStatement();
        String sql = "UPDATE data set distance = " + setDistance + ";";
        stmt.executeUpdate(sql);
        c.setAutoCommit(false);
        c.commit();
        stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
    }

    public void dbGetDistance() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");

            System.out.println("DEBUG: [INFO] Opened data database successfully");

            GProperties.achFalcon = false;
            GProperties.achMoth = false;

            //Check Distance
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distance FROM data;");
            while (rs.next()) {
                int distance = rs.getInt("distance");
                GProperties.achDistance = distance;
                
                if (GProperties.achDistance >= 1000){
                	GProperties.ach1Distance = 100;
                    GProperties.achFalcon = true;
                } else {
                	GProperties.ach1Distance = GProperties.achDistance / 10;
                }
                
                if (GProperties.achDistance >= 5000){
                	GProperties.ach2Distance = 100;
                    GProperties.achMoth = true;
                } else if (GProperties.achDistance < 5000 && GProperties.achDistance > 1000) {
                	GProperties.ach2Distance = GProperties.achDistance / 50;
                }   
                
                if (GProperties.achDistance >= 100000){
                	GProperties.ach3Distance = 100;
                } else if (GProperties.achDistance < 100000 && GProperties.achDistance > 5000) {
                	GProperties.ach3Distance = GProperties.achDistance / 10000;
                }   
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    

    // Update achievements if the player has made the successful criteria
    public void dbUpdateAchievements() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");
            c.setAutoCommit(false);
            System.out.println("DEBUG: [INFO] (dbUpdateAchievements) Opened database successfully");


            //Check Achievements
            if (GProperties.gameTime + 0 >= 1000) {
                stmt = c.createStatement();
                String sql = "UPDATE achievements set unlocked = 1 where id=1;";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();
            }
            if (GProperties.gameTime + 0 >= 5000) {
                stmt = c.createStatement();
                String sql = "UPDATE achievements set unlocked = 1 where id=2;";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();
            }


        } catch (Exception e) {
            System.err.println("DEBUG: [ERROR]" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }

    // Reset the database, for testing purposes
    public void dbReset() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");
            c.setAutoCommit(false);
            System.out.println("DEBUG: [INFO] Opened database successfully");


            stmt = c.createStatement();
            String sql = "UPDATE achievements set unlocked = 0 where id=1; ";
            String sql2 = "UPDATE achievements set unlocked = 0 where id=2; ";
            String sql3 = "UPDATE data set distance = 0;";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            c.commit();
            stmt.close();
            System.out.println("DEBUG: [INFO] Successfully reset database and achievements");


        } catch (Exception e) {
            System.err.println("DEBUG: [ERROR]" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }


}
