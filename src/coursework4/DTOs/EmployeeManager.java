package coursework4.DTOs;

import coursework4.ConnectionManager;
import coursework4.Tables.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManager {
    
    // Gets the instance of the singlton and uses that already opened connection
    private static Connection conn = ConnectionManager.getInstance().getConnection();
    
    // Query to be sent to the database
    private static final String selectRow = "SELECT * FROM employees WHERE id = ?; ";
    
    // will return the employee based on the credenials of the row
    public static Employee getRow(int id) throws SQLException {
        ResultSet rs = null;
        // uses try with resources to create a prepared statement and pass the String selectRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(selectRow);
            ){
            pstmt.setInt(1, id); // sets the first and only parameter markers as the int passed into the function
            rs = pstmt.executeQuery(); // returns number of rows affected and stores it in the resultset
            
            /* Checks that there is indeed a row returned meaning that there was an employee returned from
                the database based on the number inputted by the user
            */
            if( rs.next() ) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phone = rs.getString("phone");
                int company_Id = rs.getInt("company_id");
                Employee emp = new Employee(id, name, surname, phone, company_Id);
                return emp; // row is saved in object and returned
            } else {
                // nothing returned from database
                return null;
            } // end else if
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) rs.close(); // closes resultset
        } // end try catch finally
    } // end getRow()
}// end EmployeeManager Class
