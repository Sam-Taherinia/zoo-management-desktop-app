package com.zoo.app.model.animals;

import com.zoo.app.model.base.Showable;

public class Bird extends Animal implements Showable {
    
    public Bird(String name, String id, String gender, String nationality, 
                String nationalNumber, String insuranceSituation, 
                String foodType, String department) {
        super(name, id, gender, nationality, nationalNumber, 
              insuranceSituation, foodType, department);
    }

    @Override
    public void show() {
        // Implementation for showing bird information
        System.out.println("Bird: " + getName() + " (ID: " + getId() + ")");
    }
}
