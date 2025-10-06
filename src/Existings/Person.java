package Existings;

public abstract class Person extends Living {

    private String LastName ;
    private String username ;
    private String password ;
    private String EmailAddress ;
    private String PhoneNumber ;

    Person (String Name,
            String ID ,
            String LastName ,
            String username ,
            String password ,
            String EmailAddress ,
            String PhoneNumber ){

        super(Name, ID);
        this.LastName = LastName ;
        this.username = username ;
        this.password = password ;
        this.EmailAddress = EmailAddress ;
        this.PhoneNumber = PhoneNumber ;

    }

//    protected Person() {
//    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getLastName() {
        return LastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    @Override
    public void setID(String ID) {
        super.setID(ID);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
