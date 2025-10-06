package com.zoo.app.model.animals;

import com.zoo.app.model.base.Living;

public abstract class Animal extends Living {

    private String gender;
    private String nationality;
    private String nationalNumber;
    private String insuranceSituation;
    private String foodType;
    private String department;

    public Animal(String name, String id, String gender, String nationality, 
                  String nationalNumber, String insuranceSituation, 
                  String foodType, String department) {
        super(name, id);
        this.gender = gender;
        this.nationality = nationality;
        this.nationalNumber = nationalNumber;
        this.insuranceSituation = insuranceSituation;
        this.foodType = foodType;
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public String getInsuranceSituation() {
        return insuranceSituation;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getDepartment() {
        return department;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public void setInsuranceSituation(String insuranceSituation) {
        this.insuranceSituation = insuranceSituation;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
