package me.dreamteam.tardis;

import java.io.*;
import java.net.*;

public class Database {

    public void updateDb() throws Exception {
        String dbDistance = Integer.toString(GProperties.gameTime);
        String dbUsername = GProperties.username;
        try {
            // Send data
            URL dbConnect = new URL("http://37.187.75.63/includes/highscores/update.php?username=" +dbUsername + "&distance=" + dbDistance);
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
        }
        catch(Exception ex) {
            if (GProperties.debug) {
            System.out.println("DEBUG: [WARNING] " + ex.toString());
            }
        }
    }

}
