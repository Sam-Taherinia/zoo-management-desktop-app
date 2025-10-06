package Existings;

public class Aquatic extends Animal implements Showable {

    public Aquatic(String Name,
                   String ID,
                   String Gender,
                   String Nationality,
                   String NationalNumber,
                   String InsuranceSituation,
                   String FoodType,
                   String Department){

        super(Name ,
                ID ,
                Gender ,
                Nationality ,
                NationalNumber ,
                InsuranceSituation ,
                FoodType ,
                Department);

    }

    @Override
    public String getID() {
        return super.getID();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getGender() {
        return super.getGender();
    }
}
