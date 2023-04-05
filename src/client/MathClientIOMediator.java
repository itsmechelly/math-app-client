package client;

/**
 * A class implements the Mediator pattern, synchronizes the IO operations between logging threads and MathOperationClient.
 */
public class MathClientIOMediator {
    private MathOperationClient mathOperationClient;

    public MathClientIOMediator(MathOperationClient mathOperationClient) {
        this.mathOperationClient = mathOperationClient;
    }

    public void notifyMathClient() {
        mathOperationClient.proceedToNextPrompt();
    }
}
