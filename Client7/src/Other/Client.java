package Other;

import Actions.*;

import javax.sound.midi.Soundbank;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private static final String localhost = "127.0.0.1";
    private static SocketChannel channel;
    private static String login;

    public Client(String login) {
        this.login = login;
    }


    public static String getLogin() {
        return login;
    }

    public static void loginClient(int serverPort) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            InetSocketAddress ip = new InetSocketAddress(localhost, serverPort);
            channel = SocketChannel.open(ip);
            channel.configureBlocking(false);
            Login.registerUser(channel);
        } catch(ConnectException ex) {
            System.out.println("No connection");
        }
    }

    public static void client(String login, String pas) throws InterruptedException {
        CommandInvoker commandInvoker = new CommandInvoker();

        try{

            CommandReceiver cr = new CommandReceiver(commandInvoker, channel, login, pas);
            Command clear = new Clear(cr);
            Command cgtp = new CountGreaterThanPrice(cr);
            Command es = new ExecuteScript(cr);
            Command help = new Help(cr);
            Command history = new History(cr);
            Command info = new Info(cr);
            Command insert = new Insert(cr);
            Command mbcd = new MinByCreationDate(cr);
            Command pd = new PrintDescending(cr);
            Command remove = new Remove(cr);
            Command rg = new RemoveGreater(cr);
            Command rig = new ReplaceIfGreater(cr);
            Command show = new Show(cr);
            Command update = new Update(cr);
            Command exit = new Exit(cr);

            commandInvoker.addCom("clear", clear);
            commandInvoker.addCom("count_greater_than_price", cgtp);
            commandInvoker.addCom("execute_script", es);
            commandInvoker.addCom("help", help);
            commandInvoker.addCom("history", history);
            commandInvoker.addCom("info", info);
            commandInvoker.addCom("insert", insert);
            commandInvoker.addCom("min_by_creation_date", mbcd);
            commandInvoker.addCom("print_descending", pd);
            commandInvoker.addCom("remove_key", remove);
            commandInvoker.addCom("remove_greater", rg);
            commandInvoker.addCom("replace_if_greater", rig);
            commandInvoker.addCom("show", show);
            commandInvoker.addCom("update", update);
            commandInvoker.addCom("exit", exit);
            Reader r = new BufferedReader(new InputStreamReader(System.in));
            Scanner in = new Scanner(r);
           // writeMessage(channel, args[0]);\
            while (true) {
                System.out.print("Enter command\n");
                commandInvoker.execute(in.nextLine().split(" "), in);
            }

            }catch (NoSuchElementException e){
                System.out.println("What a shame! Are you trying to break my code? Enter correct commands next time, please!\nDisappointed client disconnecting, start client again!");

        }catch (StackOverflowError e){
            System.out.println("If you're trying to break my code be ready, that it will break you!");
            client(login, pas);

            }catch (Exception exc) {
                exc.printStackTrace();
            } finally {
            try {
                if (channel != null)
                    channel.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getMessage(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            socketChannel.read(byteBuffer);


            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object o = objectInputStream.readObject();

            return (String) o;
        } catch (StreamCorruptedException e) {
            System.out.println("Congrats! You broke server!");
            System.exit(0);
            return null;
        }

    }

    public static void writeCommand(SocketChannel channel, ReadCommand com) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(com);
        byte[] data = baos.toByteArray();
        channel.write(ByteBuffer.wrap(data));
    }

    public static void writeMessage(SocketChannel channel, String str) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(str);
        byte[] data = baos.toByteArray();
        channel.write(ByteBuffer.wrap(data));
    }

    public static void closeChannel() throws IOException {
        channel.close();
    }

    public static String[] register(){
        String[] logPas = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your login");
        logPas[0] = in.nextLine();
        System.out.println("Enter yor password");
        logPas[1] = in.nextLine();
        return logPas;
    }
}