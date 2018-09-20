import java.sql.*;
import java.io.*;

public class Employee {
   public static void main(String[] args) {
       System.out.println("Inserting values in derby  database employee table!");
       String driver = "org.apache.derby.jdbc.ClientDriver";
       // the database name
       String dbName="Q31_DB";
       // define the Derby connection URL to use
       String protocol = "jdbc:derby://localhost:1527/";
       String connectionURL = protocol + dbName + ";";
       Connection conn = null;
       try{
           Class.forName(driver);
           conn = DriverManager.getConnection(connectionURL);
           System.out.println("Connected to database " + dbName);

           try {
               conn.setAutoCommit(false);
               Statement st = conn.createStatement();
               String lname = getEmployeeLastName();
               String fname = getEmployeeFirstName();
               String initial = fname.substring(0,1) + lname.substring(0,1);
               String startDate = getEmployeeStartDate();
               int jobCode = getJobCode();
               //int val = st.executeUpdate("INSERT INTO JOB VALUES("+1+","+"'newjob'"+","+300+")");
               int val1 = st.executeUpdate("INSERT INTO EMPLOYEE VALUES("+"DEFAULT,'"+lname+"','"+fname+"','"+initial+"','"+startDate+"',"+jobCode+")");
               //- Below is a query that will fail and role back as the job_code does not exist. Uncomment to test
               //int val2 = st.executeUpdate("INSERT INTO EMPLOYEE VALUES("+"DEFAULT,'Fail,"+"'Demonstrate RollBack',"+"'DF',"+"'04.06.2015',"+6666+")");
               //commit this addition
               conn.commit();
               System.out.println("1 row affected");
           } catch (SQLException sqle) {
               String theError = (sqle).getSQLState();
               if (theError.equals("40XL1"))
               { conn.rollback();
                  System.out.println(" *** employee *** NOT *** Inserted !!!");
               } // error with another transac
               else
               throw sqle;
           }
           conn.setAutoCommit(true);
           conn.close();
           System.out.println("Closed connection");
       }
       catch (Exception e){
           e.printStackTrace();
       }
   }

    private static String getEmployeeFirstName() {
    BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    String ans = "";
    try {
      while ( ans.length() == 0 ) {
        System.out.println("Please Enter the Employee's first name.");
        ans = br.readLine();
        if ( ans.length() == 0 )
          System.out.print("Nothing entered: ");
      }
    } catch (java.io.IOException e) {
      System.out.println("Could not read response from stdin");
    }
    return ans;
  }

  private static String getEmployeeLastName() {
    BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    String ans = "";
    try {
      while ( ans.length() == 0 ) {
        System.out.println("Please Enter the Employee's Last name.");
        ans = br.readLine();
        if ( ans.length() == 0 )
          System.out.print("Nothing entered: ");
      }
    } catch (java.io.IOException e) {
      System.out.println("Could not read response from stdin");
    }
    return ans;
  }

  private static String getEmployeeStartDate() {
    BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    String ans = "";
    try {
      while ( ans.length() == 0 ) {
        System.out.println("Please Enter the Employee's Start Date.");
        System.out.println("Epexted Date Format: dd.mm.yyyy");
        ans = br.readLine();
        if ( ans.length() == 0 )
          System.out.print("Nothing entered: ");
      }
    } catch (java.io.IOException e) {
      System.out.println("Could not read response from stdin");
    }
    return ans;
  }

  private static int getJobCode() {
    BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
    String ans = "";
    int result =-1;
    try {
      while ( ans.length() == 0 ) {
        System.out.println("Please Enter the Employee's JobCode.");
        System.out.println("Enter one of the valid job_code: 6587 or 4523");
        System.out.println("Enter in a differnt number, other then the two listed above to see a rollback occur.");
        ans = br.readLine();
        if ( ans.length() == 0 )
          System.out.print("Nothing entered: ");
      }
      result =Integer.parseInt(ans);
    } catch (java.io.IOException e) {
      System.out.println("Could not read response from stdin");
    }
    return result;
  }
}
