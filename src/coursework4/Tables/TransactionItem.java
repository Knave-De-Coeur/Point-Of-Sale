package coursework4.Tables;


public class TransactionItem {
    
    private int id; // id in row
    private int trnsheaderId; // tranasction header related to
    private int prdpriceId; // productprice related to
    private int quantity; // quantity of item
    private double subTotal; // multiplies by price from related productprice
    
    // Constructotr setting all class attributes
    public TransactionItem(int trnsheaderId, int prdpriceId, int quantity, double subTotal) {
        this.trnsheaderId = trnsheaderId;
        this.prdpriceId = prdpriceId;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }
    
    // Getters and Setters od class attributes

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTtotal) {
        this.subTotal = subTtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrnsheaderId() {
        return trnsheaderId;
    }

    public void setTrnsheaderId(int trnsheaderId) {
        this.trnsheaderId = trnsheaderId;
    }

    public double getPrdpriceId() {
        return prdpriceId;
    }

    public void setPrdpriceId(int prdpriceId) {
        this.prdpriceId = prdpriceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // To output every attribute of the class
    @Override
    public String toString() {
        return "TransactionItem{" + "id=" + id + ", trnsheaderId=" + trnsheaderId + ", prdpriceId=" + prdpriceId + ", quantity=" + quantity + ", subTotal=" + subTotal + '}';
    }
    
} // end TransactionItem class
