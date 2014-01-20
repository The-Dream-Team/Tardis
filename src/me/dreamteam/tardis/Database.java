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
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SSA.db");

            if (GProperties.debug) {
                System.out.println("DEBUG: [INFO] Connected to SQLite database sucessfully");
            }

            stmt = c.createStatement();

            // Work in progress


            c.close();
        } catch (Exception e) {
            System.err.println("DEBUG: [ERROR]" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}