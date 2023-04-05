package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static constants.ProtocolConstants.QUIT_COMMAND;

/**
 * MathOperationClient class, designed to enable sending requests from the client to the server.
 * this class is the one the communicates de facto with math-app-server,
 * an object of the class should be instantiated in order to connect to the server.
 */
public class MathOperationClient {
    private Socket socket;
    private PrintWriter serverOutput;
    private ServerListeningThread serverListeningThread;
    private boolean isRunning = true;
    private boolean isWaitingForResponse = false;
    private final Scanner localInput = new Scanner(System.in);
    private final MathClientIOMediator ioMediator = new MathClientIOMediator(this);

    /**
     * Running the MathOperationClient object, establishes the connection and the i/o methods with the server
     */
    public void start(int port) {
        try {
            socket = new Socket("localhost", port);
            serverOutput = new PrintWriter(socket.getOutputStream());
            serverListeningThread = new ServerListeningThread(socket.getInputStream(), ioMediator);
            serverListeningThread.start();
            isRunning = true;
            init();
            eventLoop();
        } catch (SocketException e) {
            System.out.println("Lost connection to server");
        } catch (IOException e) {
            System.out.println("Failed to initialize client");
        }
    }

    /**
     * An init method that print instructions to the user console
     */
    private void init() {
        System.out.println("Hello! and welcome to the mathematical application service (:");
        System.out.println("You can use the following commands: 'add', 'subtract', 'multiply', 'divide'");
        System.out.println("Press 'quit' to finish");
    }

    /**
     * The eventLoop() method manage the operations that will be sent to the server:
     * The main loop of the client, keeps getting input from the user and received responses from the server.
     * NOTE: Since a command line waits for input from the user, server-responses cannot be sent until input is given, or else the input field will be overridden.
     */
    private void eventLoop() throws IOException {
        while (isRunning) {
            if (!isWaitingForResponse) {
                System.out.println("Please enter your command...");
                final String command = localInput.next();
                final double firstNum;
                final double secondNumb;

                if (QUIT_COMMAND.equals(command)) {
                    quit();
                    break;
                } else {
                    System.out.println("Enter first number:");
                    firstNum = localInput.nextDouble();
                    System.out.println("Enter second number:");
                    secondNumb = localInput.nextDouble();
                }
                callServer(command, firstNum, secondNumb);
                isWaitingForResponse = true;
            }
        }
    }

    /**
     * This method sends requests to the server.
     * There is no need for an indicator to be sent as the server handles all the requests from the client ASAP.
     */
    private void callServer(String command, Double number1, Double number2) {
        StringBuilder sb = new StringBuilder();
        sb.append(command);
        sb.append(",");
        sb.append(number1);
        sb.append(",");
        sb.append(number2);

        serverOutput.println(sb);
        serverOutput.flush();
    }

    /**
     * Close the connection to the server.
     */
    private void quit() {
        System.out.println("Good bye!");
        serverListeningThread.interrupt();
        isRunning = false;
    }

    public void proceedToNextPrompt() {
        this.isWaitingForResponse = false;
    }
}
