package com.zoo.app.model.animals;

import com.zoo.app.model.base.Showable;

public class Aquatic extends Animal implements Showable {

    public Aquatic(String name, String id, String gender, String nationality,
                   String nationalNumber, String insuranceSituation,
                   String foodType, String department) {
        super(name, id, gender, nationality, nationalNumber,
              insuranceSituation, foodType, department);
    }

    @Override
    public void show() {
        // Implementation for showing aquatic animal information
        System.out.println("Aquatic Animal: " + getName() + " (ID: " + getId() + ")");
    }
}
