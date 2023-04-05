package client;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static constants.ProtocolConstants.INFO_INDICATOR;
import static constants.ProtocolConstants.RESPONSE_END_INDICATOR;

/**
 * A thread listening to all input from the server, logs INFO messages and writes responses to console
 */
public class ServerListeningThread extends Thread {
    private BufferedWriter writer;
    private final BufferedReader serverInput;
    private final MathClientIOMediator ioMediator;

    @Override
    public void run() {
        eventLoop();
    }

    private void eventLoop() {
        handleServerMessage();
    }

    /**
     * Handles responses from the server, waits for the indicator '<END>' to signify the response end.
     */
    private void handleServerMessage() {
        String line;
        try {
            while (true) {
                line = serverInput.readLine();
                if (!line.equals(RESPONSE_END_INDICATOR)) {
                    if (line.contains(INFO_INDICATOR)) {
                        writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")) + " " + line + "\n");
                        writer.flush();
                    } else {
                        System.out.println(line);
                        ioMediator.notifyMathClient();
                    }
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerListeningThread(InputStream inputStream, MathClientIOMediator ioMediator) {
        this.ioMediator = ioMediator;
        this.serverInput = new BufferedReader(new InputStreamReader(inputStream));
        File logsDir = new File("logs");
        logsDir.mkdir();
        try {
            File logsFile = new File(logsDir + "/logs.txt");
            logsFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(logsFile));
        } catch (IOException e) {
            System.out.println("Buffered writer failed to initialize");
        }
    }
}
