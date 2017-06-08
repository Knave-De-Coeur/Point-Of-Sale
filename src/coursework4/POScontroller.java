
package coursework4;

import coursework4.DTOs.PrdPriceManager;
import coursework4.DTOs.ProductManager;
import coursework4.DTOs.TrnsHeaderManager;
import coursework4.DTOs.TrnscItemManager;
import static coursework4.LoginController.stage;
import coursework4.Tables.Employee;
import coursework4.Tables.Product;
import coursework4.Tables.ProductPrice;
import coursework4.Tables.TransactionHeader;
import coursework4.Tables.TransactionItem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class POScontroller implements Initializable {
    public static Employee crntEmp;// will hold the employee grabbed from the database
    public Product product;// will hold the product grabbed from the database
    public ProductPrice prdPrice; // will hold the productprice grabbed from the database
    public TransactionHeader crnTrnsHdr; // will hold the transaction header of the current pos
    public double totalPrice; // total price of sale needs to be static so it can be updated by the number of transaction items
    public static double subTotal; // total price of transaction items that are of the same type 
    public TransactionItem trnItem; // will
    private static final Connection conn = ConnectionManager.getInstance().getConnection();
    
    @FXML
    private Label title; // simply displays the pos and whose logged in
    
    @FXML
    private Button endTransac; // will end pos displaying the total price of items
    
    @FXML
    private Button addItem; // button in fxml file that will start a pos
    
    @FXML 
    private Button rmvItem; // button to remove item from list
    
    @FXML
    private Button removeItem; // button to remove unwanted item from transaction
    
    @FXML
    private TextField prdIDField; // wher user will enter to find product and hence add it to the transaction if exists
    
    @FXML
    private TextField qtyField; // where user will enter quantity of the item
    
    @FXML
    private TableView<TransactionItem> pos; // the table to display all the items and data on the transaction
    
    public static void setEmployee(Employee emp) {
        POScontroller.crntEmp = emp;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        /* New TransactionHeader Object is created to represent the current transaction setting its employee id to the 
                currently logged in employee
            */
            try {
                crntEmp = LoginController.crntEmp;
                System.out.println(crntEmp);
                crnTrnsHdr = new TransactionHeader(crntEmp.getId()); 
                TrnsHeaderManager.insertRow(crnTrnsHdr);// New row is added to database with date and total price left blank for now
                title.setText("POS " + crnTrnsHdr.getId() + " logged in as " + crntEmp.getName());
                System.out.println(crnTrnsHdr);
            } catch (Exception e) {
                System.err.println(e.getMessage());
        }
    } // end initialize
    
    // Simply opens the window with a prompt to the user
    @FXML
    private void displayMessage() {
        Parent root;
        try {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("FXMLUserPrompt.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e);
        } // end try catch
    } // end displayMessage()
    
    // Function to enter an item in the pos transaction list
    @FXML
    private void enterItem() throws SQLException, Exception {
        int prodQuantity;
        // First validation makes sure productId texfield isn't empty
        if (prdIDField.getText() != null && prdIDField.getText().isEmpty() == false ) {
            int product_Id;
            // then checks if what the user enter is a number and assigns it
            if (checkIfNum(prdIDField.getText()) == true ) {
                product_Id = Integer.parseInt(prdIDField.getText());
            } else { 
                return;
            }
            // gets Product and product price depending on the id entered in database and stores it as an object
            product = ProductManager.getRow(product_Id);
            prdPrice = PrdPriceManager.getRow(product_Id);
            
        } else {
            SingltonDisplay.getInstance().setLabel("ProductId Field is empty!");
            displayMessage();
            return;
        }// end elseif
        
        // First validation makes sure quantity texfield isn't empty
        if (qtyField.getText() != null && qtyField.getText().isEmpty() == false ) {
            // then checks if what the user enter as qunatity is a number and assigns it
            if (checkIfNum(qtyField.getText()) == true ) {
                prodQuantity = Integer.parseInt(qtyField.getText());
            } else {
                return;
            }
            // the price of the prdPrice object is multiplied by the the validated quantity entered by the user
            subTotal = prdPrice.getPrice() * prodQuantity;
            System.out.println(subTotal);
            totalPrice += subTotal;
            
        } else {
            SingltonDisplay.getInstance().setLabel("Quantity Field is empty!");
            displayMessage();
            return;
        }
        int transacHeaderId = crnTrnsHdr.getId(); // will hold the TransactionHeaderID for this row 
        int productPriceId = prdPrice.getId(); // will hold theproductPriceID for this row
        // object will store the item based on the details created & validated
        trnItem = new TransactionItem(transacHeaderId, productPriceId, prodQuantity, subTotal); 

        TrnscItemManager.insertRow(trnItem);// the object is stored in the database as a TransactionItem
        pos.getItems().add(trnItem);
        prdIDField.clear();
        qtyField.clear();
    }// end enterItem
    
    @FXML
    private void removeItems() throws Exception {
        // instatiates two different observable lists
        ObservableList<TransactionItem> itemsSelected, allItems;
        allItems = pos.getItems(); // one holds all items in table
        itemsSelected = pos.getSelectionModel().getSelectedItems(); // one holds user selected
        
        // list is created to loop and remove from in the database
        List<TransactionItem> showing = itemsSelected;
        
        for (TransactionItem removes : showing) {
            totalPrice -= removes.getSubTotal(); // total price decreases depening on transaction item
            TrnscItemManager.deleteRow(removes.getId()); // item removed from databse
        }
        itemsSelected.forEach(allItems::remove); // items removed from table
    }
    // Simply validates that what the user entered is a number when parsed
    private boolean checkIfNum(String input) {
        // try catch makes sure that user entered a number and nothing else
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            // if its not a number singlton display will store the error in the form of a string
            SingltonDisplay.getInstance().setLabel("Enter a number not a character!");
            // that error will be outputted to the user in another stage
            displayMessage();
            // function will hault here
            return false;
        } // end try catch
    }// end check num
    
    // Claoses the transaction with the items linked to the header in the database,
    @FXML
    private void endTransaction() throws IOException, Exception {
        ObservableList<TransactionItem> items = pos.getItems();
        // first checks that there are items in the transaction
        if ( !items.isEmpty() ) {
            // sets the total price of the Transaction headers totalPrice Attribute, which up till now was null
            crnTrnsHdr.setTotalPrice(totalPrice);
            //Transaction Header is updated 
            TrnsHeaderManager.updateRow(crnTrnsHdr);
            // Outputs the total price in a new window
            conn.commit(); // commits the transactions made
            String totPString = String.valueOf(totalPrice);
            String displayMsg = "Total price of Transaction is: €" + totPString;
            SingltonDisplay.getInstance().setLabel(displayMsg);
            displayMessage();
        } else {
            conn.rollback(); // rolls back transaction since there aren't any items no need for a header
        }
        stage = (Stage) endTransac.getScene().getWindow();
        stage.close(); // closes pos window
    } // end endTransaction()
    
} // end POScontroller class
