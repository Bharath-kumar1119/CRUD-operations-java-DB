import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/employeedb";
        String user = "root"; // Replace with your DB username
        String password = "Bharath@123"; // Replace with your DB password

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create the statement
            Statement stmt = con.createStatement();

            // Step 4: Define the SQL query to create table
            String sql = "CREATE TABLE employee (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(100))";

            // Step 5: Execute the SQL query
            stmt.executeUpdate(sql);

            System.out.println("Table 'students' created successfully!");

            String insert1 = "INSERT INTO employee (name, email) VALUES ('BharathKumar', 'bharath9502@gmail.com')";
            String insert2 = "INSERT INTO employee (name, email) VALUES ('ManiVardhan', 'manivardhan1810@gmail.com')";
            String insert3 = "INSERT INTO employee (name, email) VALUES ('AravindSwamy', 'aravindswamy@gmail.com')";

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);
            stmt.executeUpdate(insert3);

            System.out.println("Rows inserted successfully!");

            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Step 6: Close the connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}