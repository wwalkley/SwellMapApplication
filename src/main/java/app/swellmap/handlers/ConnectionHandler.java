package app.swellmap.handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

public class ConnectionHandler {

    private static ConnectionHandler connectionHandler;
    final private Connection connection;

    private ConnectionHandler() throws IOException, FileNotFoundException, ParseException, ClassNotFoundException, SQLException {
        String dbPath = ConfigHandler.getInstance().getDbPath();
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbPath));
    }

    public static ConnectionHandler getInstance() throws IOException, FileNotFoundException, ParseException, ClassNotFoundException, SQLException {
        if (connectionHandler == null) {
            connectionHandler = new ConnectionHandler();
        }
        return connectionHandler;
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}
