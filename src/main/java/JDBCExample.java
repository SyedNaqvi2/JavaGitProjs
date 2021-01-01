import java.sql.*;
import java.util.Scanner;

public class JDBCExample {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost";
	//static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	// Database credentials
	static final String USER = "SyedBaqir";
	static final String PASS = "SyedBaqir123";

	public static void main(String[] args) {
		Connection conn = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection without a database name to create a new database. It will connect to database system with Admin permissions
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);//DB_URL = "jdbc:mysql://localhost"
			String dataBaseName = "ShoppingCart";
			createDatabase(conn, dataBaseName);
			// closing the connection so that we can open a new connection by including the database name in the db_url
			conn.close();
			// STEP 4: Open a connection with a database name to create a new table in the database (ShoppingCart) we created in the previous step
			String NEW_DB_URL_FOR_TBL = "jdbc:mysql://localhost/ShoppingCart";
			conn = DriverManager.getConnection(NEW_DB_URL_FOR_TBL, USER, PASS);
			String tableName = "Products";
			// STEP 5: create a new table "Products" by calling the createTable method
			//String sql = "CREATE TABLE EMPLOYEE " + "(name VARCHAR(255) not NULL, " + " grade VARCHAR(255), "
					//+ " attendance INTEGER, " + " score FLOAT(4), " + " PRIMARY KEY ( name ))";
			String sql = " CREATE TABLE " + tableName + " (id INTEGER not NULL, prodName VARCHAR(255) not NULL, Description VARCHAR(255), "
					+ " price FLOAT(4), " + " PRIMARY KEY ( id ))";
			createTable(conn, tableName, sql);
			conn = DriverManager.getConnection(NEW_DB_URL_FOR_TBL, USER, PASS);
			populateProductDataInBatch(conn);
			//populateStudentDataInBatch(conn);
			//String stdName = "Trump";
			//deleteStudent(conn, stdName);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try

		System.out.println("Goodbye!");

	}// main method ends

	private static void populateProductDataInBatch(Connection conn) {
		int flag = 1;
		String prodName = null;
		float price;
		int id;
		String Description = null;
		int count = 0;
		int batchSize = 2;
		Statement stmt = null;		
		try {
			stmt = conn.createStatement();
			while (flag > 0) {
				conn.setAutoCommit(false);				
				Scanner input = new Scanner(System.in);
				System.out.println("Enter product's name");
				prodName = input.next();
				System.out.println("Enter product id");
				id = input.nextInt();
				System.out.println("Enter product's price");
				price = input.nextFloat();
				System.out.println("Enter product's desc");
				Description = input.next();
				String sql = null;
				count++;
				sql = "INSERT INTO Products (id,prodName,price,Description) " + "VALUES (" + "'" + id + "'" + ", " + "'"
						+ prodName + "'" + "," + "'" + price + "'" + "," + "'" + Description + "'" + ")";

				stmt.addBatch(sql);
				
				if (count % batchSize == 0) {
					System.out.println("Commit the batch");
					int[] result = stmt.executeBatch();

					System.out.println("Number of rows inserted: " + result.length);
					System.out.println("Number of products inserted so far: " + count);
					conn.commit();
					stmt.clearBatch();
				}

				System.out.println("Please enter -1 to Exit or a positive input to continue");

				flag = input.nextInt();
			} // while loop ends

			/////////////

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try
		finally {
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
		}
		
	}

	public static void createDatabase(Connection conn, String dataBaseName) {
		Statement stmt = null;
		try {

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE " + dataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try
		finally {
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
		}
		System.out.println("Goodbye!");

	}

	public static void createTable(Connection conn, String tableName, String sql) {
		Statement stmt = null;
		// STEP 5: Execute a query
		try {
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try
		finally {
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
		}
		System.out.println("Goodbye!");

	}

	public static void populateStudentDataInBatch(Connection conn) {
		int flag = 1;
		String name = null;
		String score = null;
		int attendance;
		String grade = null;
		int count = 0;
		int batchSize = 2;
		Statement stmt = null;
		try {
			while (flag > 0) {

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
				String sql = null;
				sql = "INSERT INTO student (name,score,attendance,grade) " + "VALUES (" + "'" + name + "'" + ", " + "'"
						+ score + "'" + "," + "'" + attendance + "'" + "," + "'" + grade + "'" + ")";

				stmt.addBatch(sql);
				count++;
				if (count % batchSize == 0) {
					System.out.println("Commit the batch");
					int[] result = stmt.executeBatch();

					System.out.println("Number of rows inserted: " + result.length);
					System.out.println("Number of students inserted so far: " + count);
					conn.commit();
					stmt.clearBatch();
				}

				System.out.println("Please enter -1 to Exit or a positive input to continue");

				flag = input.nextInt();
			} // while loop ends

			/////////////

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try
		finally {
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
		}

	}// method ends

	// stmt.clearBatch();

	public static void deleteStudent(Connection conn, String stdName) {
		Statement stmt = null;

		try {
			System.out.println("Student " + stdName + " Deleted in the database ");
			String sql = null;
			sql = "DELETE FROM student WHERE name =" + "\"" + stdName + "\"";
			// sql = "DELETE FROM student WHERE name like " + "'" + "Ali%" + "'";

			stmt.executeUpdate(sql);
			conn.commit();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} // end try
		finally {
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
		}

		// sql = "INSERT INTO student (name,grade,attendance,score) " +
		// " (VALUES ("Raza","A","10","90.50" ) )";
	}

}
// end JDBCExample
