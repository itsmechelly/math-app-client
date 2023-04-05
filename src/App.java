import client.MathOperationClient;

import static constants.ProtocolConstants.PORT;

/**
 * This class is the starting point of the math-app-client,
 * this class contains a method with a reference to MathOperationClient class
 * that will generate a Socket.
 */
public class App {
    public static void main(String[] args) {
        new MathOperationClient().start(PORT);
    }
}
