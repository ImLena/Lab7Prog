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



    public void setX(Long x) throws InvalidFieldException {
        this.x = x;
        checkLocx();
    }

    public void setY(Float y) throws InvalidFieldException {
        this.y = y;
        checkLocy();
    }

    public void setZ(Float z) throws InvalidFieldException {
        this.z = z;
        checkLocz();
    }

    public void setName(String name) throws InvalidFieldException{
        this.name = name;
        checkLocName();
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
