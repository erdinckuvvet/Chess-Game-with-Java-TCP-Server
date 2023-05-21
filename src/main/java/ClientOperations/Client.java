package ClientOperations;

import chess_game.Pieces.Team;
import chess_game.gui.Table;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages the client's connection to the server, creates the input
 * and output streams, and handles sending messages.
 */
public class Client {

    public Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    private Team team = Team.NOCOLOR;
    public boolean isMatched = false;
    public String serverIP;
    public int serverPort;
    public ClientListenThread listenThread;
    public Table game;

    public Client(Table game) {
        this.game = game;
    }

    public void Connect(String serverIP, int port) {
        try {
            System.out.println("Connecting..");
            this.serverIP = serverIP;
            this.serverPort = port;
            this.socket = new Socket(this.serverIP, this.serverPort);
            output = new ObjectOutputStream(this.socket.getOutputStream());
            input = new ObjectInputStream(this.socket.getInputStream());
            listenThread = new ClientListenThread(this);
            this.listenThread.start();
        } catch (IOException e) {
            System.out.println("Connection failed. " + e);
        }
    }

    public void closeStreams() throws IOException {
        if (output != null) {
            output.flush();
            output.close();
        }
        if (input != null) {
            input.close();
        }
    }

    public void closeSocket() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public void stop() {
        try {
            closeStreams();
            closeSocket();
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void send(Object message) {
        try {
            this.output.writeObject(message);
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
