package coursework4.Tables;

public class Employee {
    
    private int Id; // holds id in the employee table
    private String name;
    private String surname;
    private String phoneNumber; // String because of +356
    private int company_Id; // copmany employee belongs to
    
    // Constructotr setting all class attributes
    public Employee(int Id, String name, String surname, String phoneNumber, int company_Id) {
        this.Id = Id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.company_Id = company_Id;
    }
    
    // Getter and setters for class attributes

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCompany_Id() {
        return company_Id;
    }

    public void setId(int empId) {
        this.Id = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }
    
    // To output every attribute of the class
    @Override
    public String toString() {
        return "Employee{" + "Id=" + Id + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", company_Id=" + company_Id + '}';
    }
    
    
}// end Employee class
