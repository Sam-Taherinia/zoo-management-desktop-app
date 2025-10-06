package Existings;

public abstract class Animal extends Living {

    private String Gender ;
    private String Nationality ;
    private String NationalNumber ;
    private String InsuranceSituation ;
    private String FoodType ;
    private String Department ;

    Animal(String Name ,
           String ID ,
           String Gender ,
           String Nationality ,
           String NationalNumber ,
           String InsuranceSituation ,
           String FoodType ,
           String Department){

        super(Name , ID);
        this.Gender = Gender ;
        this.Nationality = Nationality ;
        this.NationalNumber = NationalNumber ;
        this.InsuranceSituation = InsuranceSituation ;
        this.FoodType = FoodType ;
        this.Department = Department ;

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getID() {
        return super.getID();
    }

    public String getGender() {
        return Gender;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getNationalNumber() {
        return NationalNumber;
    }

    public String getInsuranceSituation() {
        return InsuranceSituation;
    }

    public String getFoodType() {
        return FoodType;
    }

    public String getDepartment() {
        return Department;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setID(String ID) {
        super.setID(ID);
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setNationalNumber(String nationalNumber) {
        NationalNumber = nationalNumber;
    }

    public void setInsuranceSituation(String insuranceSituation) {
        InsuranceSituation = insuranceSituation;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
