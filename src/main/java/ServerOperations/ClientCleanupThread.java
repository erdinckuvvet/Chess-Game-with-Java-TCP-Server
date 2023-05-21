package ServerOperations;

/**
 * This class provides control over the clients by ensuring that disconnected
 * clients are removed from the server's list of clients.It ensures the list
 * remains up-to-date by removing clients that have lost their connection to the
 * server.
 */
public class ClientCleanupThread extends Thread {

    private Server server;

    public ClientCleanupThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {

        while (!this.server.getServerSocket().isClosed()) {
            Server.getClients().stream().filter(client -> (client.socket.isClosed())).forEachOrdered(client -> {
                Server.removeClient(client);
            });
        }
    }

}
