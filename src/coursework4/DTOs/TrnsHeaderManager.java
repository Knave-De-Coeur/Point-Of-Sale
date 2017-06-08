package coursework4.DTOs;

import coursework4.ConnectionManager;
import coursework4.Tables.TransactionHeader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrnsHeaderManager {
    
    // Gets the instance of the singlton and uses that already opened connection
    private static final Connection conn = ConnectionManager.getInstance().getConnection();
    
    // String holds query to insert row based on object data
    private static final String insertRow = "INSERT into transactionheader (date, totalprice, employee_id) VALUES (NOW(),?,?); ";
    // String holds query that search for a row based on its id and will then, update the row with the current time index and sets the complete totalPrice
    private static final String updateRow = "UPDATE transactionheader Set "+
                                            "date = NOW(), totalprice = ? " +
                                            "WHERE Id = ?";
    
    // string holds query to delete a transaction based on its id
    private static final String deleteRow = "DELETE FROM transactionheader WHERE id = ?";
    
    // Inserts new Transaction header into databse with only current time and employee number
    public static boolean insertRow(TransactionHeader trnsHdr) throws Exception {
        ResultSet keys = null;
        // uses try with resources to create a prepared statement and pass the String inserRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(insertRow, Statement.RETURN_GENERATED_KEYS)
            ){
            pstmt.setDouble(1, trnsHdr.getTotalPrice()); // First placeholder inserts totalPrice which at this point is null
            pstmt.setInt(2, trnsHdr.getEmployeeId()); // second placeholder inserts employee id

            int affected = pstmt.executeUpdate();// returns rows affected

             // Checks that a row was indeed inserted grabs that row's id and sets it in the object's id
            if (affected == 1){
                keys = pstmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                trnsHdr.setId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            } // end else if

        } catch (SQLException e) {
            System.err.println("Error: " + e);
            //conn.rollback(); // rolls back transaction
            return false;
        } finally {
            if(keys != null) keys.close(); // closes reulstet
        }
        //conn.commit(); // commits the transaction if there are no errors 
        return true;
    } // end insertRow()
     
    // updates the row to fill in the mising data based on object passed as args
    public static boolean updateRow(TransactionHeader complete) throws Exception {
        ResultSet keys = null;
        // uses try with resources to create a prepared statement and pass the String deleteRow into it
        try(
                PreparedStatement pstmt = conn.prepareStatement(updateRow, Statement.RETURN_GENERATED_KEYS)
            ){
            pstmt.setDouble(1, complete.getTotalPrice()); // first placeholder sets the total price
            pstmt.setInt(2, complete.getId());// second placeholder sets the id to query
            
            int affected = pstmt.executeUpdate(); // returns rows affected
            
            // Checks that a row was indeed updated 
            if (affected == 1) {
                conn.commit(); // commits the transaction when there 
                return true;
            } else {
                conn.rollback(); // rolls back transaction in case of errors
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            conn.rollback(); // rolls back transaction in case of errors
            return false;
        } finally {
            if(keys != null) keys.close(); // clsoes resultset
        }
    } // end 
    
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
