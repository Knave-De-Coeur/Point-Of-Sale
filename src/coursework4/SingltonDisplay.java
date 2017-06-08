package coursework4;

public class SingltonDisplay {
    private static SingltonDisplay instance = null;// will hold this class's only instance  
    
    // sets the varaible as the instance otherwise returns the same instance
    public static SingltonDisplay getInstance() {
        if (instance == null) {
            instance = new SingltonDisplay(); 
        }
        return instance;
    }// end getInstance
    
    private String label;// will contain what needs to be outputted on the label
    
    // Getters and setter for the String label: 
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}// end SingltonDisplay
