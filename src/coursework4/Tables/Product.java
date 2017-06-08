package coursework4.Tables;

public class Product {
    
    private int id; // id in row
    private String name; 
    private String description;
    
    // Constructotr setting all class attributes
    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters of class attributes
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // // To output every attribute of the class
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
} // end Product class
