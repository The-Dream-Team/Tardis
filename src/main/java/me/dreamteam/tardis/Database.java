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

    public void dbUpdate() throws Exception {
        String dbDistance = Integer.toString(GProperties.gameTime) + "0";
        String dbUsername = GProperties.username;
        try {
            // Send data
            URL dbConnect = new URL("http://37.187.75.63/includes/highscores/update.php?username=" + dbUsername + "&distance=" + dbDistance);
            URLConnection conn = dbConnect.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));
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

    //sql connection
    public void dbConnect() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");

            System.out.println("DEBUG: [INFO] Opened database successfully");


            //Check Achievements
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM achievements;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("desc");
                int unlocked = rs.getInt("unlocked");

                if (id == 1 && unlocked == 0) {
                    GProperties.achFalcon = false;
                }

                if (id == 2 && unlocked == 0) {
                    GProperties.achMoth = false;
                }

                if (id == 1 && unlocked == 1) {
                    GProperties.achFalcon = true;
                }

                if (id == 2 && unlocked == 1) {
                    GProperties.achMoth = true;
                }
            }


            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //Update SQL
    public void dbUpdateAchievements() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");
            c.setAutoCommit(false);
            System.out.println("DEBUG: [INFO] Opened database successfully");


            //Check Achievements
            if (GProperties.gameTime + 0 >= 150) {
                stmt = c.createStatement();
                String sql = "UPDATE achievements set unlocked = 1 where id=1;";
                stmt.executeUpdate(sql);
                c.commit();
                stmt.close();
            }
            if (GProperties.gameTime + 0 >= 300) {
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
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            c.commit();
            stmt.close();
            System.out.println("DEBUG: [INFO] Successfully reset database and achievements");


        } catch (Exception e) {
            System.err.println("DEBUG: [ERROR]" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


    }


}
