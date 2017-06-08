package coursework4.DTOs;

import coursework4.ConnectionManager;
import coursework4.Tables.TransactionItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrnscItemManager {
    
    // Gets the instance of the singlton and uses that already opened connection
    private static final Connection conn = ConnectionManager.getInstance().getConnection();
    
    /* Query to be sent to the database that will insert a new TransactionItem into the table with its relations to 
        productprice and TransactionHeader respectfully
    */
    private static final String insertRow = "INSERT into transactionitem (transactionheader_id, productprice_id, quantity) " +
                                            "VALUES (?,?,?); ";
    
    // string holds query to delete a transaction item based on its id
    private static final String deleteRow = "DELETE FROM transactionitem WHERE id = ?";
    
    // inserts a new row based on the object passed as args
    public static boolean insertRow(TransactionItem trnsItm) throws Exception {
        ResultSet keys = null;
        // uses try with resources to create a prepared statement and pass the String inserRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(insertRow, Statement.RETURN_GENERATED_KEYS)
            ){
            pstmt.setInt(1, trnsItm.getTrnsheaderId()); // First placeholder that sets the reation to Transaction Header
            pstmt.setDouble(2, trnsItm.getPrdpriceId()); // Second placeholder that sets the relation to the Product Price
            pstmt.setInt(3, trnsItm.getQuantity()); // Thrd placeholder that sets the quantity
            
            int affected = pstmt.executeUpdate();
            
             // Checks that a row was indeed inserted grabs that row's id and sets it in the object's id
            if (affected == 1){
                keys = pstmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                trnsItm.setId(newKey);
            } else {
                System.err.println("No rows were added");
                return false;
            } // end if else
            
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            conn.rollback(); // transaction rolled back
            return false;
        } finally {
            if(keys != null) keys.close();
        }
        //conn.commit(); // commits the transaction
        return true;
    } // end insertRow()
    
    // will remove a row in the Transaction Item table based on its id 
    public static boolean deleteRow(int itemId) throws Exception {
        ResultSet keys = null;
        // uses try with resources to create a prepared statement and pass the String selectRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(deleteRow, Statement.RETURN_GENERATED_KEYS)
            ){
            pstmt.setInt(1, itemId); // sets first parameter as the id passed as args
            
            int affected = pstmt.executeUpdate(); // returns number of rows affected 
            
            // as long a number has been returned then the deletion was completed & transaction commited
            if (affected == 1){
                //conn.commit();
                return true;
            } else {
                //conn.rollback();
                return false;
            } // end if else 
            
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            //conn.rollback();
            return false;
        } finally {
            if(keys != null) keys.close(); // closes ResultSet
        } // end finally
    }// end deleteRow()
} // end TrascItemManager class
