package app.swellmap;

import java.sql.*;

public class Database {

    public void insert(Forecast forecast) {
        try {
            ConnectionHandler connHandler = ConnectionHandler.getInstance();
            Statement stmt = connHandler.getConnection().createStatement();
            String sql = "INSERT INTO forecasts (REGION,LOCATION,DATE,TIME,RATING,SUMMARY,SEA_HEIGHT,SWELL_HEIGHT,CHOP_HEIGHT,PERIOD,SWELL_DIRECTION,SEA_DIRECTION,WIND_DIRECTION,WIND_SPEED,GUST) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pstmt = connHandler.getConnection().prepareStatement(sql);
            pstmt.setString(1, forecast.getRegion());
            pstmt.setString(2, forecast.getLocation());
            pstmt.setString(3, forecast.getDate());
            pstmt.setString(4, forecast.getTime());
            pstmt.setInt(5, forecast.getRating());
            pstmt.setString(6, forecast.getSummary());
            pstmt.setDouble(7, forecast.getSeaHeight());
            pstmt.setDouble(8, forecast.getSwellHeight());
            pstmt.setDouble(9, forecast.getChopHeight());
            pstmt.setInt(10, forecast.getPeriod());
            pstmt.setString(11, forecast.getSwellDirection());
            pstmt.setString(12, forecast.getSeaDirection());
            pstmt.setString(13, forecast.getWindDirection());
            pstmt.setInt(14, forecast.getWindSpeed());
            pstmt.setInt(15, forecast.getGust());
            pstmt.executeUpdate();
            System.out.println(String.format("Inserted record for [%s] [%s] [%s]", forecast.getRegion(), forecast.getLocation(), forecast.getTime()));
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
