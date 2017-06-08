package coursework4.DTOs;

import coursework4.ConnectionManager;
import coursework4.Tables.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManager {
    
    // Gets the instance of the singlton and uses that already opened connection
    private static final Connection conn = ConnectionManager.getInstance().getConnection();
    
    // String simply holds query to return product from database depending on its id
    private static final String selectRow = "SELECT * FROM product WHERE id = ?; ";
    
    // will return the employee based on the credenials of the row
    public static Product getRow(int id) throws SQLException {
        ResultSet rs = null;
        // uses try with resources to create a prepared statement and pass the String selectRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(selectRow);
            ){
            pstmt.setInt(1, id); // sets the first and only parameter markers as the int passed into the function
            rs = pstmt.executeQuery(); // returns number of rows affected and stores it in the resultset
            
            /* Checks that there is indeed a row returned meaning that there was an product returned from
                the database based on the number inputted by the user
            */
            if( rs.next() ){
                String name = rs.getString("name");
                String desc = rs.getString("description");
                Product prd = new Product(id, name, desc);
                return prd; // row is saved in object and returned
            } else {
                // // nothing returned from database
                return null;
            } // end if else
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) rs.close(); // closes resultset
        } // end try catch finally
    } // end getRow()
}
