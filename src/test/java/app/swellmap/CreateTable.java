package app.swellmap;

import app.swellmap.ConfigHandler;
import java.sql.*;

public class CreateTable {

    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;

        try {
            String dbPath = ConfigHandler.getInstance().getDbPath();
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbPath));
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS forecasts "
                    + "(REGION TEXT NOT NULL,"
                    + " LOCATION TEXT NOT NULL, "
                    + " DATE TEXT NOT NULL, "
                    + " TIME TEXT NOT NULL, "
                    + " RATING INT NOT NULL, "
                    + " SUMMARY TEXT NOT NULL,"
                    + " SEA_HEIGHT DOUBLE NOT NULL, "
                    + " SWELL_HEIGHT DOUBLE NOT NULL, "
                    + " CHOP_HEIGHT DOUBLE NOT NULL, "
                    + " PERIOD INT NOT NULL, "
                    + " SWELL_DIRECTION TEXT NOT NULL, "
                    + " SEA_DIRECTION TEXT NOT NULL, "
                    + " WIND_DIRECTION TEXT NOT NULL, "
                    + " WIND_SPEED INT NOT NULL, "
                    + " GUST INT NOT NULL) ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
