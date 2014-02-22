package me.dreamteam.tardis;

import java.io.*;
import java.net.*;
import java.net.HttpURLConnection;

public class Database {

    public void updateDb() throws Exception {
        String distance = Integer.toString(GProperties.gameTime);
        String username = GProperties.username;

        try {
            // open a connection to the site
            URL url = new URL("http://www.the-dreamteam.co.uk/includes/highscores/update.php");
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("username=" + username);
            ps.print("&distance" + distance);

            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            // close the print stream
            ps.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
