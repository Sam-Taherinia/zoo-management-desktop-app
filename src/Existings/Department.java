package Existings;

public abstract class Department  {

    private String ID ;
    private String Name ;
    private String TicketPrice ;
    private String ZooKeepers ;

    Department (String ID , String Name , String TicketPrice , String ZooKeepers) {

        this.ID = ID ;
        this.Name = Name ;
        this.TicketPrice = TicketPrice ;
        this.ZooKeepers = ZooKeepers ;

    }

}
