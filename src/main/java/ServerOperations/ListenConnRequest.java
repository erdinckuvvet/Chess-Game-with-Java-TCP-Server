package ServerOperations;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This thread is responsible for listening to incoming client connection
 * requests and accepting them to establish a communication channel with the
 * server.It runs continuously until the server socket is closed.
 */
public class ListenConnRequest extends Thread {

    private Server server;
    private ServerSocket serverSocket;

    public ListenConnRequest(Server server) {
        this.server = server;
        this.serverSocket = this.server.getServerSocket();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            try {
                Socket newSocket = serverSocket.accept();
                ServerClient newClient = new ServerClient(newSocket);
                newClient.Listen();
                server.addClient(newClient);

            } catch (IOException e) {
                System.out.println("new client adding error." + e);
            }
        }
    }
}
