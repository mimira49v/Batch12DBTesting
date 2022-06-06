import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLDemo03 {
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


        ResultSetMetaData resultSetMetaData = null;
//      object to give the information about table and its data

        try {       // needs to start from 1

            connection = DriverManager.getConnection(DbUrl, userName, password);
            // getting the database connection from driverManager class
            statment = connection.createStatement();
            // creating a statement to execute the queries
            result = statment.executeQuery("select * from person");
            // executing the queries and storing the results in ResultSet
            resultSetMetaData=result.getMetaData();
            // getting the resultSet object so that we fetch the column names and other info related to table
            List<Map<String,String>> tableData = new ArrayList <>();
            for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
                // gets the column name passes it to the getString method to get the data for that column using loops
                System.out.print(resultSetMetaData.getColumnName(i)+" ");
            }
            System.out.println();
            while (result.next()) {
                Map<String, String> row =  new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    // gets the column name passes it to the getString method to get the data for that column using loops
                    row.put(resultSetMetaData.getColumnName(i),result.getString(resultSetMetaData.getColumnName(i)));
                }
                tableData.add(row);
            }
            System.out.println(tableData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(result);
            DBUtils.closeStatement(statment);
            DBUtils.closeConnection(connection);
        }
    }
}

