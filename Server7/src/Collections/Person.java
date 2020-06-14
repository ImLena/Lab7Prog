package Collections;

import Exceptions.InvalidFieldException;

import java.io.Serializable;

/**
 * Класс для создания person
 */

public class Person implements Serializable {
    private double height; //Значение поля должно быть больше 0
    private Location location; //Поле не может быть null
    private static final long serialVersionUID = 32L;


    @Override
    public String toString() {
        return (" height = " + height + ", location: " + location.toString());
    }

    public Person(double height, Location location) {
        this.height = height;
        this.location = location;
    }

    public Location getLoc() {
        return location;
    }

    public double getHeight() {
        return height;
    }

}

