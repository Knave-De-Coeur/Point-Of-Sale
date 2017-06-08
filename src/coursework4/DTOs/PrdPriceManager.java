package coursework4.DTOs;

import coursework4.ConnectionManager;
import coursework4.Tables.ProductPrice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrdPriceManager {
    
    // Gets the instance of the singlton and uses that already opened connection
    private static Connection conn = ConnectionManager.getInstance().getConnection();
    // String holds query to return a row from the database table productprice with the effective date
    private static final String selectRow = "SELECT * FROM productPrice WHERE DATE_FORMAT(effectiveDate,\"%Y-%m\") = DATE_FORMAT(NOW(), "
            + "\"%Y-%m\") AND product_id = ?; ";
    
     // will return the employee based on the credenials of the row
    public static ProductPrice getRow(int productId) throws SQLException {
        ResultSet rs = null;
        
        // uses try with resources to create a prepared statement and pass the String selectRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(selectRow);
                ){
            pstmt.setInt(1, productId); // sets the first and only parameter markers as the int passed into the function
            rs = pstmt.executeQuery(); // returns number of rows affected and stores it in the resultset
            
            /* Checks that there is indeed a row returned meaning that there was an productPrice returned from
                the database based on the productId inputted by the user
            */
            
            if( rs.next() ){
                int id = rs.getInt("id");
                String effectiveDate = rs.getString("effectiveDate");
                double priceAmount= rs.getDouble("price");
                int product_Id = rs.getInt("product_id");
                ProductPrice price = new ProductPrice(id, effectiveDate, priceAmount, product_Id);
                return price; // row is saved in object and returned
            } else {
                // nothing returned from database
                return null;
            } // end if else
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) rs.close(); // close ResultSet
        } // end try catch finally
    } // end getRow()
} // end PrdPriceManager class 