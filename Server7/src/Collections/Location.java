package Collections;

import Exceptions.InvalidFieldException;

import java.io.Serializable;

/**
 * Класс для установки локации
 */


public class Location implements Serializable {
    private Long x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private Float z; //Поле не может быть null
    private String name; //Поле не может быть null
    private static final long serialVersionUID = 32L;

    public Location(Long x, Float y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }


    @Override
    public String toString() {
        return (" x = " + x + ", y = " + y + ", z = " + z + ", name = " + name);
    }

    public Long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    private void checkLocName() {
        if (name == null) {
            throw new InvalidFieldException("Error in field name (location)");
        }
    }
    private void checkLocx() {
        if (x == null) {
            throw new InvalidFieldException("Error in field x (location)");
        }
    }
    private void checkLocy() {
        if (y == null) {
            throw new InvalidFieldException("Error in field y (location)");
        }
    }
    private void checkLocz(){
        if (z == null) {
            throw new InvalidFieldException("Error in field z (location)");
        }
    }
}
