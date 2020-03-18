package app.swellmap;

import app.swellmap.handlers.ConfigHandler;
import app.swellmap.handlers.ConnectionHandler;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

public class CreateTable {

    public static void main(String args[]) {
        ConfigHandler.setConfigPath("src/test/resources/config.json");
        dropTable();
        createTable();
        createUniqueIndex();
    }

    private static void createTable() {
        try {
            ConnectionHandler ch = ConnectionHandler.getInstance();
            ch.getConnection().createStatement().execute("CREATE TABLE IF NOT EXISTS FORECASTS "
                    + "(REGION         VARCHAR (100) NOT NULL,"
                    + "LOCATION        VARCHAR (100) NOT NULL,"
                    + "DATE            DATE          NOT NULL,"
                    + "TIME            VARCHAR (10)  NOT NULL,"
                    + "RATING          INT           NOT NULL,"
                    + "SUMMARY         VARCHAR (255) NOT NULL,"
                    + "SEA_HEIGHT      DOUBLE        NOT NULL,"
                    + "SWELL_HEIGHT    DOUBLE        NOT NULL,"
                    + "CHOP_HEIGHT     DOUBLE        NOT NULL,"
                    + "PERIOD          INT           NOT NULL,"
                    + "SWELL_DIRECTION VARCHAR (5)   NOT NULL,"
                    + "SEA_DIRECTION   VARCHAR (5)   NOT NULL,"
                    + "WIND_DIRECTION  VARCHAR (5)   NOT NULL,"
                    + "WIND_SPEED      INT           NOT NULL,"
                    + "GUST            INT           NOT NULL)");
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void dropTable() {
        try {
            ConnectionHandler ch = ConnectionHandler.getInstance();
            ch.getConnection().createStatement().execute("DROP TABLE IF EXISTS FORECASTS");
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void createUniqueIndex() {
        try {
            ConnectionHandler ch = ConnectionHandler.getInstance();
            ch.getConnection().createStatement().execute("CREATE UNIQUE INDEX \"forecasts_unique_index\" ON forecasts (REGION, LOCATION, DATE, TIME)");
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
