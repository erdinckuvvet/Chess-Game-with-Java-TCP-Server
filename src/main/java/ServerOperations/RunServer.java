package ServerOperations;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server starting class.
 */
public class RunServer {

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.ListenConnRequest();

        while (!server.getServerSocket().isClosed()) {

            try {
                System.out.println("Server waiting with " + Server.getClients().size() + " client..");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.getLogger(RunServer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
