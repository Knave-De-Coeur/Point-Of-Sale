package coursework4.Tables;


public class TransactionHeader {
    
    private int id; // new id when inserted into table
    private double totalPrice; // totalPrice when transaction has ended
    private int employeeId; // employee that opened the transaction
    
    // Constructor will only take employee id since that will be the only data available upon instantiation
    public TransactionHeader(int employeeId) {
        this.employeeId = employeeId;
    }
    
    // Getters and Setters of class attributes

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    // To output every attribute of the class
    @Override
    public String toString() {
        return "TransactionHeader{" + "id=" + id + ", totalPrice=" + totalPrice + ", employeeId=" + employeeId + '}';
    }
    
}// end TransactionHeader class
