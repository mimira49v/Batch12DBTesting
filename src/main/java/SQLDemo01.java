
import java.sql.*;

public class SQLDemo01 {
    public static void main(String[] args) {
        String DbUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String userName = "syntax_hrm";
        String password = "syntaxhrm123";
        Connection connection = null;
//      variable to hold the conection
        Statement statment = null;
//      helps us execute the queries on the database
        ResultSet result = null;
//      helps us store the result

        try {
            connection = DriverManager.getConnection(DbUrl,userName,password);
            statment = connection.createStatement();
            result = statment.executeQuery("select * from person");
            while (result.next()){
                System.out.println(result.getString("id")+" "+result.getString("FirstName")
                +" "+result.getString("LastName")+" "+result.getString("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           DBUtils.closeResultSet(result);
           DBUtils.closeStatement(statment);
           DBUtils.closeConnection(connection);
        }
    }
}
// what happens when you call a method on a null object - nullPointerException