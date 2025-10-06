package Existings;

public class ZooKeeper extends Person implements Showable {

    private String Department ;
    private String income ;

    public ZooKeeper(String Name, String ID, String LastName, String username, String password, String EmailAddress, String PhoneNumber , String Department , String income) {
        super(Name, ID, LastName, username, password, EmailAddress, PhoneNumber);
        this.Department = Department ;
        this.income = income ;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
