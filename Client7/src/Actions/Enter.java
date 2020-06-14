package Actions;

import Collections.*;
import Exceptions.InvalidFieldException;
import Other.Client;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс для реализации ввода данных пользователем
 */

public class Enter implements Serializable {
    private Coordinates coordinates = new Coordinates();
    private Location loc = new Location();
    private Ticket ticket = new Ticket();
    private Person person = new Person();
    private LocalDateTime creationDate = LocalDateTime.now();


    public Enter(){

    }

    public Ticket enter(String[] s, Scanner in){
        try {
            if (s.length == 1) {
                enterId(in);
            } else {
                enterId(Long.valueOf(s[1]), in);
            }
            entername(in);
            entercoordsx(in);
            entercoordsy(in);
            entercoords();
            enterprice(in);
            entertype(in);
            enterheight(in);
            enterln(in);
            enterxloc(in);
            enteryloc(in);
            enterzloc(in);
            enterloc();
            enterperson();
            enterdate();
            enterUser();
        }catch (NumberFormatException e){
            System.out.println("Long ID expected");
        }
            return ticket;

    }

    public void enterUser(){
        ticket.setUser(Client.getLogin());
    }

    public void enterId(Scanner in){
        System.out.println("Enter ID (Long, >0)");
        try {
            Long id = Long.valueOf(in.nextLine());
                enterId(id, in);
        }catch (InputMismatchException e){
            System.out.println("Wrong type, try again");
            enterId(in);
        }catch(NumberFormatException e){
            System.out.println("Enter Long");
            enterId(in);
        }
    }

    public void entername(Scanner in) {
        System.out.println("Enter name");
        try {
            String name = in.nextLine();
            ticket.setName(name);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            entername(in);
        }
    }

    public void entercoordsx(Scanner in) {
        System.out.println("Enter x coordinate (float, > -502)");
        try {
            String xx = in.nextLine();
            float x = Float.parseFloat(xx);
            coordinates.setX(x);

        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            entercoordsx(in);
        } catch (NumberFormatException e){
            System.out.println("Incorrect enter, enter float");
            entercoordsx(in);
        } catch (InputMismatchException e){
            System.out.println("Wrong type, try again");
            entercoordsx(in);
        }
    }
    public void entercoordsy(Scanner in){
        System.out.println("Enter y coordinate (Integer)");
        try{
            Integer y = Integer.valueOf(in.nextLine());
            coordinates.setY(y);

        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            entercoordsy(in);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter Integer");
            entercoordsy(in);
        }
    }
    public void entercoords(){
        ticket.setCoordinates(coordinates);
    }

    public void enterprice(Scanner in) {
        System.out.println("Enter price (float, > 0)");
        try {
            String p = in.nextLine();
            float price = Float.parseFloat(p);
            ticket.setPrice(price);

        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterprice(in);
        } catch (InputMismatchException e) {
            System.out.println("Wrong type, try again");
            enterprice(in);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter float");
            enterprice(in);
        }
    }

    public void entertype(Scanner in) {
        System.out.println("Enter ticket type: VIP, USUAL, BUDGETARY, CHEAP");
        try {
            String typeStr = in.nextLine();
            TicketType type;
            if (typeStr.isEmpty()){
                type = null;
            }else {
                type = TicketType.valueOf(typeStr);
            }
            ticket.setType(type);
        } catch (IllegalArgumentException e) {
            System.out.println("This type doesn't exist, try again");
            entertype(in);
        }

    }

    public void enterheight(Scanner in) {
        System.out.println("Enter height (double, > 0)");
        try {
            Double height = Double.valueOf(in.nextLine());
            person.setHeight(height);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterheight(in);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter double");
            enterheight(in);
        }
    }

    public void enterln(Scanner in) {
        System.out.println("Enter location name");
        try {
            String locName = in.nextLine();
            loc.setName(locName);

        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterln(in);
        }
    }

    public void enterxloc(Scanner in) {
        System.out.println("Enter x location coordinate (Long)");
        try {
            Long xx = Long.valueOf(in.nextLine());
            loc.setX(xx);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterxloc(in);
        } catch (NumberFormatException e){
            System.out.println("Incorrect enter, enter Long");
            enterxloc(in);
        }
    }

    public void enteryloc(Scanner in) {
        System.out.println("Enter y location coordinate (float)");
        try {
            Float yy = Float.valueOf(in.nextLine());
            loc.setY(yy);

        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enteryloc(in);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter float");
            enteryloc(in);
        }

    }

    public void enterzloc(Scanner in) {
        System.out.println("Enter z location coordinate (float)");
        try {
            Float zz = Float.valueOf(in.nextLine());
            loc.setZ(zz);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterzloc(in);
        }catch (InputMismatchException e){
            System.out.println("Wrong type, try again");
            enterzloc(in);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter float");
            enterzloc(in);
        }
    }
    public void enterdate(){
        try {
            ticket.setCreationDate(creationDate);
        }catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enterloc(){
        try {
            person.setLocation(loc);
        }catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
        }
    }
    public void enterperson(){
        try{
            ticket.setPerson(person);
        }catch (InvalidFieldException e){
            System.out.println(e.getMessage());
        }
    }
    public void enterId(Long id, Scanner in) {
        try {
            ticket.setId(id);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, enter Long");
            enterId(in);
        } catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
            enterId(in);
        }
    }

    public Ticket getTicket() {
        return ticket;
    }
}
