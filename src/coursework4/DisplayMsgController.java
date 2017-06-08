/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author alexanderjames
 */
public class DisplayMsgController implements Initializable{
    
    @FXML 
    private Label prompt; // will hold the message to the user
    
    @FXML
    private Button OK; // will close the window 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prompt.setText(SingltonDisplay.getInstance().getLabel()); // sets the texts as the string returned from the SingltonDisplay class
    }
    
    // Grabs the stage the ok button is located in and closes it
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) OK.getScene().getWindow();
        stage.close();
    } // end clsoWindow
    
}
