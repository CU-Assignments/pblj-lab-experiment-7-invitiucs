

1. **Setup MySQL Database**  
   - Ensure MySQL is installed and running.  
   - Create a database and an `Employee` table with columns `EmpID`, `Name`, and `Salary`.

2. **Update Database Credentials**  
   - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

3. **Add MySQL JDBC Driver**  
   - Download and add `mysql-connector-java.jar` to your project’s classpath.

4. **Compile and Run the Program**  
   - Compile: `javac MySQLConnection.java`  
   - Run: `java MySQLConnection`

5. **Verify Output**  
   - Ensure that employee records are displayed correctly from the database.
code 

   import java.sql.*;
public class MySQLConnection {
public static void main(String[] args) {
 // Database credentials
 String url = "jdbc:mysql://localhost:3306/your_database"; // Replace with your
database name
String user = "your_username"; // Replace with your username
String password = "your_password"; // Replace with your password
 // SQL query to fetch employee records
 String query = "SELECT * FROM Employee";
 // Establish connection and retrieve data
 try {
 // Load MySQL JDBC Driver
 Class.forName("com.mysql.cj.jdbc.Driver");
 // Establish connection
 Connection conn = DriverManager.getConnection(url, user, password);
 Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);
 // Display employee records
 System.out.println("EmpID | Name | Salary");
 while (rs.next()) {
 int id = rs.getInt("EmpID");
 String name = rs.getString("Name");
 double salary = rs.getDouble("Salary");
 System.out.println(id + " | " + name + " | " + salary);
 }
 // Close resources
 rs.close();
 stmt.close();
 conn.close();
 } catch (Exception e) {
 e.printStackTrace();
 }
 }
}
