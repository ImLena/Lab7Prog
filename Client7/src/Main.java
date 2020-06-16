import Other.Client;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        try {
            System.out.println("Enter port:");
            int port = Integer.valueOf(new Scanner(System.in).nextLine());
            try {
                Client.loginClient(port);
            } catch (IllegalArgumentException e) {
                System.out.println("wrong port");
                main(args);

            }

        } catch (NoSuchElementException e) {
            System.out.println("What a shame! Are you trying to break my code? Enter correct commands next time, please!\nDisappointed client disconnecting, start client again!");
            System.exit(0);
        }
    }

}
