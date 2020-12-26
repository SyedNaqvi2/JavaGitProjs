import java.sql.*;
import java.util.Scanner;

public class JDBCExample {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	// Database credentials
	static final String USER = "SyedBaqir";
	static final String PASS = "SyedBaqir123";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			//System.out.println("Creating database...");
			//stmt = conn.createStatement();

			//String sql = "CREATE DATABASE STUDENTS";
			String sql;
			//stmt.executeUpdate(sql);
			//System.out.println("Database created successfully...");
			
			//STEP 5: Execute a query
		      //System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      /*
		      String sql = "CREATE TABLE REGISTRATION " +
	                   "(id INTEGER not NULL, " +
	                   " first VARCHAR(255), " + 
	                   " last VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " PRIMARY KEY ( id ))";  
		       sql = "CREATE TABLE student " +
		                   "(name VARCHAR(255) not NULL, " + 
		                   " grade VARCHAR(255), " + 
		                   " attendance INTEGER, " + 
		                   " score FLOAT(4), " + 
		                   " PRIMARY KEY ( name ))"; 
       */
		      int flag = 1;
		      String name = null;
		      String score = null;
		      int attendance;
		      String grade = null;
		      int count = 0;
	          int batchSize = 5;
		        while(flag > 0)
		        {
		        	
		        	conn.setAutoCommit(false);
		        	Scanner input = new Scanner(System.in);	
		            System.out.println("Enter student's name");
		            name = input.next();
		            System.out.println("Enter student's score");
		            score = input.next();
		           System.out.println("Enter student's attendance");
		           attendance = input.nextInt();
		           System.out.println("Enter student's grade");
		           grade = input.next();
		          
		           sql = "INSERT INTO student (name,score,attendance,grade) " +
		                   "VALUES (" + "'" + name + "'" + ", " +  "'" + score + "'" + "," + "'" + attendance + "'" + "," + "'" + grade + "'" + ")";
		           stmt.addBatch(sql);
		           count++;
		           if(count % batchSize == 0){
		                  System.out.println("Commit the batch");
		                  int[] result = stmt.executeBatch();
		                  System.out.println("Number of rows inserted: "+ result.length);
		                  conn.commit();
		           }
		           //stmt.executeUpdate(sql);
		           System.out.println("Student " + name + " added in the database ");
		          // sql = "DELETE FROM student WHERE name =" + "\"" + "Anjum" + "\"";
		          //  stmt.executeUpdate(sql);
		            System.out.println("Please enter -1 to Exit or a positive input to continue");
		            flag = input.nextInt();
		            
		        }
		     
		      //sql = "INSERT INTO student (name,grade,attendance,score) " +
	                  // " (VALUES ("Raza","A","10","90.50" ) )"; 
		      
		      
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end main
}// end JDBCExample
