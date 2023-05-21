package ServerOperations;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//The class that represents the clients who want to connect to the server
public class ServerClient {

    public Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    public ListenThread listenThread;
    public ServerClient pair;
    public boolean isPaired;
    public boolean isSearching = false;
    public PairingManagerThread pairingThread;
    
    public ServerClient(Socket socket) {

        try {
            this.socket = socket;
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.input = new ObjectInputStream(this.socket.getInputStream());
            this.listenThread = new ListenThread(this);
            this.pairingThread = new PairingManagerThread(this);
            this.isPaired = false;
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Send(Object msg)
    {
        try {
            this.output.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Listen() {
        this.listenThread.start();
    }
}
