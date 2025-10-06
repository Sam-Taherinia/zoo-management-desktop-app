package Existings;

public abstract class Living {

    private String Name ;
    private String ID ;

    Living(String Name ,
           String ID){

        this.Name = Name ;
        this.ID = ID ;

    }

    public String getName(){

        return Name ;

    }

    public String getID() {

        return ID;

    }

    public void setName(String name) {

        Name = name;

    }

    public void setID(String ID) {

        this.ID = ID;

    }
}
