package Hello;

import java.sql.*;

public class Insert {

   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:database.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO FORECAST (REGION,LOCATION,TIME,RATING,SUMMARY,SEA_HEGIHT,SWELL_HEGIHT,CHOP_HEIGHT,PERIOD,SWELL_DIRECTION,SEA_DIRECTION,WIND_DIRECTION,WIND_SPEED,GUST) " +
                        "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
         stmt.executeUpdate(sql);

         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}