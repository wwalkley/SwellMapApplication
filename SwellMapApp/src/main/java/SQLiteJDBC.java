import java.sql.*;

public class SQLiteJDBC {

   public static void main(String args[]) {
      Connection c = null;
      Statement stmt = null;

      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:database.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE forecasts " 
               + "(REGION INT NOT NULL," 
               + " LOCATION TEXT NOT NULL, "
               + " RATING INT NOT NULL, " 
               + " ADDRESS CHAR(50), " 
               + " SALARY         REAL)";
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