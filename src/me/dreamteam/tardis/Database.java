package me.dreamteam.tardis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static Connection con;

    public void updateDb() throws Exception {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://37.187.75.63:3306/SSA?user=root&password=Lunar172");

            if (GProperties.debug) {
                System.out.println("DEBUG: [INFO] Connected to MySQL database successfully");
            }

            stmt = c.createStatement();

            // Work in progress


            c.close();
        } catch (Exception e) {
            System.err.println("DEBUG: [ERROR]" + e.getClass().getName() + ": " + e.getMessage());
            return;
        }
    }
}