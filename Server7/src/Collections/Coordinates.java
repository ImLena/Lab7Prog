package Collections;

import Exceptions.InvalidFieldException;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Класс для установки координат
 */


public class Coordinates implements Serializable {
    private float x; //Значение поля должно быть больше -502
    private Integer y; //Поле не может быть null
    private static final long serialVersionUID = 32L;

    public Coordinates(float x, Integer y){
        this.x = x;
        this.y = y;
    }

    public void setX(float x) throws InvalidFieldException {
        this.x = x;
        checkCoordsx();
    }


    public void setY(Integer y) throws InvalidFieldException {
        this.y = y;
        checkCoordsy();
    }

    public float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    private void checkCoordsx() {
        if (x <= -502) {
            throw new InvalidFieldException("Error in field x");
        }
    }
    private void checkCoordsy(){
        if (y == null) {
            throw new InvalidFieldException("Error in field y");
        }
    }

    @Override
    public String toString() {
        return ("x = " + x + ", y = " + y);
    }

}
