package coursework4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Coursework4 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Chooses what FXML file to load up forst
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        
        Scene scene = new Scene(root); // sets the FXML file as the scene
        stage.setTitle("Point of Sale");// sets title of window
        stage.setScene(scene); // give the window the fxml files format
        stage.show(); // displays the window
    } // end start
    
    public static void main(String[] args) {
        launch(args);
    }// end main
    
}// end Coursework4
