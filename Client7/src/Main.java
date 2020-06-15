import Other.Client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("Enter port:");
        int port = Integer.valueOf(new Scanner(System.in).nextLine());
        Client.loginClient(port);
    }

}
