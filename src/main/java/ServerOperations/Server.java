package ServerOperations;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

//TCP server.
public class Server {

    private ServerSocket serverSocket;
    private int port;
    private static ArrayList<ServerClient> clients;
    private ListenConnRequest listenRequest;
    private ClientCleanupThread cleanupThread;
    public static Semaphore lock = new Semaphore(1, true); // Semaphore to control the pairing of clients.

    public Server(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(this.port);
            this.listenRequest = new ListenConnRequest(this);
            cleanupThread = new ClientCleanupThread(this);
            Server.clients = new ArrayList<>();

        } catch (IOException e) {
            System.out.println("Server starting error port no:" + this.port);

        }
    }
    
     public static ArrayList<ServerClient> getClients() {
        return clients;
    }
     
    public static void addClient(ServerClient client){
        Server.clients.add(client);
    } 

    public static void removeClient(ServerClient client) {
        Server.clients.remove(client);
    }
    
    
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort() {
        return port;
    }
     
     
     
    //Starts listening for client connection requests. 
    public void ListenConnRequest() {
        this.listenRequest.start();
    }

    //Sends an object to the specified client.
    public static void SendMessage(ServerClient client, Object message) {
        try {
            client.output.writeObject(message);
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
/*    public static void SendMessage(ServerClient client, Command message) {
       try {
            client.output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void SendMessage(ServerClient client, String message) {
        try {
            client.output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 */
