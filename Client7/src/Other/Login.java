package Other;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class Login {
    private static boolean available = false;
    public static ReadCommand rc;
    public static void registerUser(SocketChannel channel) throws InterruptedException, IOException, ClassNotFoundException {
        String[] logPas;
        while (available != true) {
            String s = registration();
            logPas = insertlogPas(channel);
            rc = new ReadCommand(s, null, null, logPas[0], logPas[1]);
            Client.writeCommand(channel, rc);
            Thread.sleep(1500);
            String answer = Client.getMessage(channel);
            System.out.println(answer);
            String[] answ = answer.split(" ");
            if (answ.length == 2) {
                if (answ[0].equals("user")){
                    if (answ[1].equals("logged") | answ[1].equals("registered")) {
                        Client client = new Client(logPas[0]);
                        client.client(logPas[0], logPas[1]);
                        available = true;
                    }
                }
            }
        }

    }

    public static String registration(){
        Scanner scanner = new Scanner(System.in);
        String s = null;
        System.out.println("Do you want to login or to register? Please, choose one:");
        switch (scanner.nextLine()){
            case "login":
                s = "login";
                break;
            case "register":
                s = "regist";
                break;
            default:
                registration();
        }
        return s;
    }

    public static String[] insertlogPas(SocketChannel channel) throws InterruptedException, IOException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);
        String[] logPas = new String[2];
        System.out.println("Enter your login");
        logPas[0] = in.next();
        checkExit(logPas[0]);
        checkNull(logPas[0], channel);
        System.out.println("Enter your password");
        logPas[1] = in.next();
        checkExit(logPas[1]);
        checkNull(logPas[1], channel);
        return logPas;
    }

    public static void checkExit(String s){
        if (s.equals("exit")){
            System.out.println("Good bye!");
            System.exit(0);
        }
    }

    public static void checkNull(String s, SocketChannel channel) throws InterruptedException, IOException, ClassNotFoundException {
        if (s.equals("")){
            System.out.println("Can't be empty");
            registerUser(channel);
        }
    }/*

    public static String getLogin() {
        return logPas[0];
    }

    public static String getPas() {
        return logPas[1];
    }*/
}
