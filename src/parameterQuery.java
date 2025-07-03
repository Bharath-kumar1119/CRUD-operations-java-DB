import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class parameterQuery {
    public static void main(String[] args){

        String url = "jdbc:mysql://localhost:3306/parameterquery";
        String user = "root";
        String password = "Bharath@123";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "CREATE TABLE IF NOT EXISTS studentDetails (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(100))";

            PreparedStatement createTableStmt = con.prepareStatement(sql);
            createTableStmt.executeUpdate();
            System.out.println("Table created successfully!");

            // 👉 INSERT using parameters
            String insertQuery = "INSERT INTO studentDetails (name, email) VALUES (?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertQuery);

            insertStmt.setString(1, "BharathKumar");
            insertStmt.setString(2, "bharath9502@gmail.com");
            insertStmt.executeUpdate();

            insertStmt.setString(1, "ManiVardhan");
            insertStmt.setString(2, "manivardhan1810@gmail.com");
            insertStmt.executeUpdate();

            insertStmt.setString(1, "AravindSwamy");
            insertStmt.setString(2, "aravindswamy@gmail.com");
            insertStmt.executeUpdate();

            System.out.println("Rows inserted successfully using parameters!");


            // 👉 READ
            ResultSet rs = con.prepareStatement("SELECT * FROM studentDetails").executeQuery();
            System.out.println("\nCurrent Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Email: " + rs.getString("email"));
            }


            // 👉 UPDATE using parameters
            String updateQuery = "UPDATE studentDetails SET name = ? WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, "BharathKumarUpdated");
            updateStmt.setInt(2, 1);
            updateStmt.executeUpdate();
            System.out.println("\nRecord with ID 1 updated successfully using parameters!");

            // 👉 DELETE using parameters
            String deleteQuery = "DELETE FROM studentDetails WHERE id = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, 2);
            deleteStmt.executeUpdate();
            System.out.println("\nRecord with ID 2 deleted successfully using parameters!");


            // 👉 Read final records
            ResultSet rs2 = con.prepareStatement("SELECT * FROM studentDetails").executeQuery();
            System.out.println("\nUpdated Records:");
            while (rs2.next()) {
                System.out.println("ID: " + rs2.getInt("id") +
                        ", Name: " + rs2.getString("name") +
                        ", Email: " + rs2.getString("email"));
            }


            // Closing resources
            rs.close();
            rs2.close();
            insertStmt.close();
            updateStmt.close();
            deleteStmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }




        }
}
