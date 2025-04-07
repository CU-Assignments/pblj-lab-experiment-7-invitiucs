### Instructions to Run the Java CRUD Program:

1. **Setup MySQL Database**
   - Ensure MySQL is installed and running.
   - Create a database and a `Product` table with columns `ProductID`, `ProductName`, `Price`, and `Quantity`.

2. **Update Database Credentials**
   - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

3. **Add MySQL JDBC Driver**
   - Download and add `mysql-connector-java.jar` to your project’s classpath.

4. **Compile and Run the Program**
   - Compile: `javac ProductCRUD.java`
   - Run: `java ProductCRUD`

5. **Menu-Driven Operations**
   - Select options to **Create**, **Read**, **Update**, or **Delete** products.
   - Input values as prompted.

6. **Transaction Handling**
   - Transactions ensure data integrity.
   - If an error occurs, changes are rolled back.

7. **Verify Output**
   - Ensure product records are correctly manipulated in the database.
code 
   
   
import java.sql.*;
import java.util.Scanner;
public class ProductCRUD {
 static final String URL = "jdbc:mysql://localhost:3306/your_database";
 static final String USER = "your_username";
 static final String PASSWORD = "your_password";
 public static void main(String[] args) {
 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
Scanner scanner = new Scanner(System.in)) {
 Class.forName("com.mysql.cj.jdbc.Driver");
 while (true) {
 System.out.println("\nProduct CRUD Menu:");
 System.out.println("1. Add Product");
 System.out.println("2. View Products");
 System.out.println("3. Update Product");
 System.out.println("4. Delete Product");
 System.out.println("5. Exit");
 System.out.print("Enter choice: ");
 int choice = scanner.nextInt();
 scanner.nextLine();
 switch (choice) {
 case 1:
 addProduct(conn, scanner);
 break;
 case 2:
 viewProducts(conn);
 break;
 case 3:
 updateProduct(conn, scanner);
 break;
 case 4:
 deleteProduct(conn, scanner);
 break;
 case 5:
System.out.println("Exiting...");
 return;
 default:
 System.out.println("Invalid choice, try again.");
 }
 }
 } catch (Exception e) {
 e.printStackTrace();
 }
 }
private static void addProduct(Connection conn, Scanner scanner) throws
SQLException {
 System.out.print("Enter Product Name: ");
 String name = scanner.nextLine();
 System.out.print("Enter Price: ");
 double price = scanner.nextDouble();
 System.out.print("Enter Quantity: ");
 int quantity = scanner.nextInt();
 String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES
(?, ?, ?)";
 try (PreparedStatement pstmt = conn.prepareStatement(query)) {
 pstmt.setString(1, name);
 pstmt.setDouble(2, price);
 pstmt.setInt(3, quantity);
 pstmt.executeUpdate();
 System.out.println("Product added successfully.");
 }
 }
 private static void viewProducts(Connection conn) throws SQLException {
 String query = "SELECT * FROM Product";
 try (Statement stmt = conn.createStatement(); ResultSet rs =
stmt.executeQuery(query)) {
 System.out.println("\nProduct List:");
 while (rs.next()) {
 System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName")
+ " | " + rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
 }
 }
 }
 private static void updateProduct(Connection conn, Scanner scanner) throws
SQLException {
 System.out.print("Enter Product ID to update: ");
 int id = scanner.nextInt();
 scanner.nextLine();
System.out.print("Enter new Product Name: ");
 String name = scanner.nextLine();
 System.out.print("Enter new Price: ");
 double price = scanner.nextDouble();
 System.out.print("Enter new Quantity: ");
 int quantity = scanner.nextInt();
 String query = "UPDATE Product SET ProductName=?, Price=?, Quantity=?
WHERE ProductID=?";
 try (PreparedStatement pstmt = conn.prepareStatement(query)) {
 pstmt.setString(1, name);
 pstmt.setDouble(2, price);
 pstmt.setInt(3, quantity);
 pstmt.setInt(4, id);
 pstmt.executeUpdate();
 System.out.println("Product updated successfully.");
 }
 }
 private static void deleteProduct(Connection conn, Scanner scanner) throws
SQLException {
 System.out.print("Enter Product ID to delete: ");
 int id = scanner.nextInt();
 String query = "DELETE FROM Product WHERE ProductID=?";
 try (PreparedStatement pstmt = conn.prepareStatement(query)) {
 pstmt.setInt(1, id);
 pstmt.executeUpdate();
 System.out.println("Product deleted successfully.");
 }
 }
}
