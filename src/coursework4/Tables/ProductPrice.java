package coursework4.Tables;

public class ProductPrice {
    private int id;
    private String effectiveDate;
    private double price;
    private int product_id;
    
    // Constructotr setting all class attributes
    public ProductPrice(int id, String effectiveDate, double price, int product_id) {
        this.id = id;
        this.effectiveDate = effectiveDate;
        this.price = price;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public double getPrice() {
        return price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    
    // To output every attribute of the class
    @Override
    public String toString() {
        return "ProductPrice{" + "id=" + id + ", effectiveDate=" + effectiveDate + ", price=" + price + ", product_id=" + product_id + '}';
    }
    
}// end ProductPrice class
