package Existings;

public class Ticket {

    private String Price ;
    private String Owner ;
    private String Department ;

    private String left ;

    public Ticket(String Price,
                  String  Owner,
                  String Department,
                  String left){

        this.Price = Price ;
        this.Owner = Owner ;
        this.Department = Department ;
        this.left = left ;

    }

    public String getPrice(){

        return Price ;

    }

    public String getOwner() {

        return Owner ;

    }

    public String getDepartment () {

        return Department ;

    }

    public void setPrice(String Price) {

        this.Price = Price;

    }

    public void setOwner(String Owner) {

        this.Owner = Owner;
    }

    public void setDepartment (String Department) {

        this.Department = Department ;

    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

}
