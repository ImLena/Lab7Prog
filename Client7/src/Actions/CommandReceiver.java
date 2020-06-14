package Actions;
import Collections.Ticket;
import Exceptions.ExecuteScriptException;
import Exceptions.InvalidFieldException;
import Other.Client;
import Other.ReadCommand;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class CommandReceiver{
    private final CommandInvoker commandInvoker;
    private Enter e = new Enter();
    private SocketChannel channel;
    int delay = 1500;
    private String login;
    private String pas;


    public CommandReceiver(CommandInvoker commandInvoker, SocketChannel socket, String login, String pas) {
        this.commandInvoker = commandInvoker;
        this.channel = socket;
        this.login = login;
        this.pas = pas;
    }

    public void help() {
        commandInvoker.getCommands().forEach((name, command) -> command.help());
    }

    public void history(){
        System.out.println(CommandInvoker.getHistory().toString());
    }


    public void clear() throws IOException, ClassNotFoundException, InterruptedException {
        ReadCommand rc = new ReadCommand("clear", null,null, login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        System.out.println(Client.getMessage(channel));

    }

    public void info() throws IOException, ClassNotFoundException, InterruptedException {
        ReadCommand rc = new ReadCommand("info", null,null, login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        System.out.println(Client.getMessage(channel));
    }

    public void insert(String[] command, Scanner in) throws IOException, InterruptedException, ClassNotFoundException {
        Ticket tic = e.enter(command, in);
        if (command.length > 1){
        ReadCommand rc = new ReadCommand("insert", tic.getId().toString(), tic, login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        System.out.println(Client.getMessage(channel));
        } else{
            System.out.println("Enter key after 'insert'");
        }
    }

    public void min_by_creation_date() throws IOException, InterruptedException, ClassNotFoundException {
        ReadCommand rc = new ReadCommand("min_by_creation_date", null,null,login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        System.out.println(Client.getMessage(channel));
    }

    public void print_descending() throws IOException, InterruptedException, ClassNotFoundException {
        ReadCommand rc = new ReadCommand("print_descending", null,null, login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        Client.getMessage(channel);
    }

    public void remove(String[] args) throws IOException {
        try{
            if (args.length > 1) {
                Long id = Long.valueOf(args[1]);
                ReadCommand rc = new ReadCommand("remove_key", String.valueOf(id), null, login, pas);
                Client.writeCommand(channel, rc);
                Thread.sleep(delay);
                System.out.println(Client.getMessage(channel));
            } else{
                System.out.println("Enter key after 'remove'");
            }
        }catch (InputMismatchException | NumberFormatException | ClassNotFoundException | InterruptedException e){
            System.out.println("Wrong type, Long id expected");
        }
    }

    public void remove_greater(String[] command, Scanner in) throws IOException {
        Ticket tic = e.enter(command, in);
        ReadCommand rc = new ReadCommand("remove_greater", null, tic, login, pas);
        Client.writeCommand(channel, rc);
    }

    public void replace_if_greater(String[] command , Scanner in) throws IOException, ClassNotFoundException, InterruptedException {
        if (command.length > 1) {
            Ticket tic = e.enter(command, in);
            ReadCommand rc = new ReadCommand("replace_if_greater", command[1], tic, login, pas);
            Client.writeCommand(channel, rc);
            Thread.sleep(delay);
            System.out.println(Client.getMessage(channel));
        }else{
            System.out.println("Enter key after 'replace_if_greater'");
        }
    }

    public void update(String[] command, Scanner in) throws IOException {
        Ticket tic = e.enter(command, in);
        ReadCommand rc = new ReadCommand("update", command[1], tic, login, pas);
        Client.writeCommand(channel, rc);
    }

    public void show() throws IOException, ClassNotFoundException, InterruptedException {
        ReadCommand rc = new ReadCommand("show", null, null, login, pas);
        Client.writeCommand(channel, rc);
        Thread.sleep(delay);
        System.out.println(Client.getMessage(channel));
    }

    public void count_greater_than_price(String[] com) throws IOException {
        try {
            if (com.length > 1) {
                float price = Float.parseFloat(com[1]);
                ReadCommand rc = new ReadCommand("count_greater_than_price", com[1], null, login, pas);
                Client.writeCommand(channel, rc);
                Thread.sleep(delay);
                System.out.println(Client.getMessage(channel));
            } else {
                System.out.println("Enter price after command");
            }
        }catch (InvalidFieldException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wrong type, of price");
        } catch (NumberFormatException e) {
            System.out.println("Incorrect enter, float price expected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void execute_script(String path) {
        Path p;
        Reader r;
        Scanner in;
        Scanner in2;
        LinkedList<Scanner> q = new LinkedList<>();
        String file = path;
        String line = "";
        String[] com;
        p = Paths.get(file);
        try {
            if (Files.isReadable(p)) {
                FileInputStream fstream = new FileInputStream(file);
                r = new BufferedReader(new InputStreamReader(fstream));
                in = new Scanner(r);
                q.addLast(in);

                while (!q.isEmpty()) {
                    in2 = q.getLast();
                    while ((in2.hasNextLine()) & (!line.equals("exit"))) {
                        if (Paths.get(path).equals(p)) {
                            line = in2.nextLine();
                            com = line.split(" ");
                            System.out.println(Arrays.toString(com));
                            commandInvoker.execute(com, in2);
                        }

                    }
                    q.pollLast();

                }

            } else {
                System.out.println("You can't read file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("This file doesn't exist");
        } catch (NullPointerException e) {
            System.out.println("Unknown command in the script");
        } catch (ExecuteScriptException e) {
            System.out.println("Recursion in the script!");
        } catch (InvalidFieldException e) {
            System.out.println("Invalid field entered in the script ");
        } catch (StackOverflowError e) {
            System.out.println("Recursion happened");
        } catch (Exception e) {
            System.out.println("Unknown error, aliens have taken over the program");
        }
    }

    public void exit() throws IOException {
        System.out.println("Channel is closed. good bye!");
        Client.closeChannel();
        System.exit(0);
    }

}
