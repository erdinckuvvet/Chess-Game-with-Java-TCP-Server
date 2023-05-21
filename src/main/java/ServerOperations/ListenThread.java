package ServerOperations;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Commands.Command;

/**
 * This class facilitates the listening and processing of incoming messages from
 * the client, taking appropriate actions based on the message type.It enables
 * communication and coordination between the server and the client, ensuring
 * smooth interaction during the chess game or any other related functionality.
 */
public class ListenThread extends Thread {

    ServerClient client;

    public ListenThread(ServerClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!client.socket.isClosed()) {

            try {
                Command message = (Command) (this.client.input.readObject());
                switch (message.type) {
                    case MATHCING:
                        this.client.isSearching = true;
                        this.client.pairingThread.start();
                        break;
                    case MOVE:
                        this.client.pair.Send(message);
                        break;
                    case CHECK:
                        this.client.pair.Send(message);
                        break;
                    case END:
                        this.client.isPaired = false;
                        this.client.isSearching = false;
                        this.client.pair = null;

                    case LEAVE:
                        this.client.isPaired = false;
                        this.client.isSearching = false;
                        this.client.pair.isPaired = false;
                        this.client.pair.isSearching = false;
                        this.client.pair.pair = null;
                        this.client.pair = null;

                }
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(ListenThread.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
