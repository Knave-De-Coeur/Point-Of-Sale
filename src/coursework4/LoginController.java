package coursework4;

import coursework4.DTOs.EmployeeManager;
import coursework4.Tables.Employee;
import coursework4.Tables.Product;
import coursework4.Tables.ProductPrice;
import coursework4.Tables.TransactionHeader;
import coursework4.Tables.TransactionItem;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    public static Stage stage = new Stage();// stage object will be overrwritten for different windows
    public static Employee crntEmp;// will hold the employee grabbed from the database
    public static boolean onLogin = true; // condition to determine what scenes to switch
    
    @FXML
    private Label noemplabel; // will be used to display to the user that their emp id is incorrect or doesn't exit
    
    @FXML
    private Button exit; // will be used through out the application to shut it down
    
    @FXML
    private Button signout; // will sign the user out taking them back to the loggin menu
    
    @FXML
    private TextField login; // where user will enter text to check with the database to see if they exist 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } // end initialize
    
    @FXML
    private void login() throws SQLException, IOException, Exception{
        // first determines that there is some sort of input in the textfield
        if (login.getText() != null && login.getText().isEmpty() == false ) {
            int logginId;
            // sets the loggedinId only if it passes validation otherwise stops function
            if (checkIfNum(login.getText()) == true) {
                logginId = Integer.parseInt(login.getText());
            } else {
                return;
            }
            // Once all validation for the id input has been passed the connection to the database is opened
            ConnectionManager.getInstance();
            // employee object instantiated to represent to the employee successfully logged in
            crntEmp = EmployeeManager.getRow(logginId);// will return employee
            System.out.println(crntEmp);

            if (crntEmp != null) {
                // The id returns an employee and he is logged in
                switchStage(); // opens pos main menu
            } else {
                noemplabel.setText("Employee doesn't exist!");
            } // end if else
        } else {
            SingltonDisplay.getInstance().setLabel("Enter your Id!");
            // that error will be outputted to the user in another stage
            displayMessage();
        }
    } // end login()
    
    //Simply uses same stage to open a different scene depending wheather the user wants to login or out
    @FXML
    private void switchStage() {
        Parent root;
        try {
            // will set the root as the main menu
            if (onLogin == true) {
                stage = (Stage) login.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("FXMLPOSMainMenu.fxml"));
                onLogin = false;
            } else {
                // root gets set back to login fxml 
                stage = (Stage) signout.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                onLogin = true;
            } // end else if
            // sets scene depending on what root was 
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();// shows new scene
        } catch (IOException e) {
            System.err.println(e);
        }// end try catch
    }// end switchStage
    
    // Simply opens a new POS UI
    @FXML
    private void openPOS() throws Exception {
        Parent root;
        try {
            // New window with POS is opened
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("FXMLPOS.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e);
        } // end try catch
    } // end openPOS()
    
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
    
    @FXML
    private void exitSystem() {
        // Closes connection
        ConnectionManager.getInstance().close();
        // ends the program
        Platform.exit();
    } // end exitSystem()
    
} // end controller class
