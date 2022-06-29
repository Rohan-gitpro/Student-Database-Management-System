package connectivity;
import java.sql.*;
public class ConnectionClass {
    public Connection connection;
    public Connection getConnection(){

//        String dbname="empData";
//        String username="root";
//        String password="mittal";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/My Database","root","jb1965");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
