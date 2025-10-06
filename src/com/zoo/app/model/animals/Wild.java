package com.zoo.app.model.animals;

import com.zoo.app.model.base.Showable;

public class Wild extends Animal implements Showable {
    
    public Wild(String name, String id, String gender, String nationality,
                String nationalNumber, String insuranceSituation,
                String foodType, String department) {
        super(name, id, gender, nationality, nationalNumber,
              insuranceSituation, foodType, department);
    }

    @Override
    public void show() {
        // Implementation for showing wild animal information
        System.out.println("Wild Animal: " + getName() + " (ID: " + getId() + ")");
    }
}
