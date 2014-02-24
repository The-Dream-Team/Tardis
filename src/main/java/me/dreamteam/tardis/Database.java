package me.dreamteam.tardis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

public class Database {

    public void updateDb() throws Exception {
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
    public void SQLConnect(){
    	Connection c = null;
    	Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:game.db");

          System.out.println("Opened database successfully");
          
          
        //Check Achievements
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM achievements;" );
          while(rs.next()){
        	  int id = rs.getInt("id");
        	  String name = rs.getString("name");
        	  String desc = rs.getString("desc");
        	  int unlocked = rs.getInt("unlocked");
        	  System.out.println("ID = " + id);
        	  System.out.println("Name = " + name );
        	  System.out.println("Desc = " + desc);
        	  System.out.println("Unlocked = " + unlocked);
        	  
        	  if (id == 1 && unlocked == 1) {
        		  System.out.println("HIP HIP HOORAY");
        	  }
          }
          
          
          rs.close();
          stmt.close();
          
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        
        
    }

}
