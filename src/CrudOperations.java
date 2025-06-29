import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrudOperations {
    public static void main(String[] args){

//        Connect DataBase

        String url = "jdbc:mysql://localhost:3306/students";
        String user = "root"; // Replace with your DB username
        String password = "Bharath@123";

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            Connection con = DriverManager.getConnection(url, user, password);

            // Step 3: Create the statement
            Statement stmt = con.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS studentDetails (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(100))";

            stmt.executeUpdate(sql);

            System.out.println("Table 'student' created successfully!");

            //          CREATE OPERATIONS

            String insert1 = "INSERT INTO studentDetails (name, email) VALUES ('BharathKumar', 'bharath9502@gmail.com')";
            String insert2 = "INSERT INTO studentDetails (name, email) VALUES ('ManiVardhan', 'manivardhan1810@gmail.com')";
            String insert3 = "INSERT INTO studentDetails (name, email) VALUES ('AravindSwamy', 'aravindswamy@gmail.com')";

            stmt.executeUpdate(insert1);
            stmt.executeUpdate(insert2);
            stmt.executeUpdate(insert3);

            System.out.println("Rows inserted successfully!");

//            READ OPERATIONS

            ResultSet rs = stmt.executeQuery("SELECT * FROM studentDetails");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // UPDATE Record
            String updateQuery = "UPDATE studentDetails SET name = 'BharathKumarUpdated' WHERE id = 1";
            stmt.executeUpdate(updateQuery);
            System.out.println("\nRecord with ID 1 updated successfully!");

            // DELETE Record
            String deleteQuery = "DELETE FROM studentDetails WHERE id = 2";
            stmt.executeUpdate(deleteQuery);
            System.out.println("\nRecord with ID 2 deleted successfully!");

            // READ Records Again to show changes
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM studentDetails");

            System.out.println("\nUpdated studentDetails Records:");
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String name = rs2.getString("name");
                String email = rs2.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Step 6: Close the connection
            // Close resources
            rs.close();
            rs2.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
