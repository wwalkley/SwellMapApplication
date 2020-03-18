package app.swellmap.dao;

import app.swellmap.models.Forecast;
import app.swellmap.handlers.ConnectionHandler;
import java.io.IOException;
import java.sql.*;
import org.json.simple.parser.ParseException;

public class ForecastDAO {

    public void delete(Forecast forecast) {
        try {
            ConnectionHandler connHandler = ConnectionHandler.getInstance();
            String sql = "DELETE FROM FORECASTS\n"
                    + "      WHERE REGION = ? AND \n"
                    + "            LOCATION = ? AND \n"
                    + "            DATE = ? AND \n"
                    + "            TIME = ?";
            PreparedStatement statement = connHandler.getConnection().prepareStatement(sql);
            statement.setString(1, forecast.getRegion());
            statement.setString(2, forecast.getLocation());
            statement.setString(3, forecast.getDate());
            statement.setString(4, forecast.getTime());
            statement.execute();
            statement.close();
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void insert(Forecast forecast) {
        try {
            ConnectionHandler connHandler = ConnectionHandler.getInstance();
            String sql = "INSERT INTO forecasts (REGION,LOCATION,DATE,TIME,RATING,SUMMARY,SEA_HEIGHT,SWELL_HEIGHT,CHOP_HEIGHT,PERIOD,SWELL_DIRECTION,SEA_DIRECTION,WIND_DIRECTION,WIND_SPEED,GUST) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement = connHandler.getConnection().prepareStatement(sql);
            statement.setString(1, forecast.getRegion());
            statement.setString(2, forecast.getLocation());
            statement.setString(3, forecast.getDate());
            statement.setString(4, forecast.getTime());
            statement.setInt(5, forecast.getRating());
            statement.setString(6, forecast.getSummary());
            statement.setDouble(7, forecast.getSeaHeight());
            statement.setDouble(8, forecast.getSwellHeight());
            statement.setDouble(9, forecast.getChopHeight());
            statement.setInt(10, forecast.getPeriod());
            statement.setString(11, forecast.getSwellDirection());
            statement.setString(12, forecast.getSeaDirection());
            statement.setString(13, forecast.getWindDirection());
            statement.setInt(14, forecast.getWindSpeed());
            statement.setInt(15, forecast.getGust());
            statement.execute();
            statement.close();
            System.out.println(String.format("Inserted record for [%s] [%s] [%s]", forecast.getRegion(), forecast.getLocation(), forecast.getTime()));
        } catch (IOException | ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }
    }
}
