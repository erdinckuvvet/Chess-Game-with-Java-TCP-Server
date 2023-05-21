package ServerOperations;

import chess_game.Pieces.Team;
import Commands.Command;

/**
 * This thread is responsible for pairing clients in the chess game, 
 * ensuring each client finds a suitable opponent.Since chess is a 1 vs 1 game, 
 * the pairing process occurs between two clients.When this thread is invoked 
 * by a client, it begins searching for another client interested in pairing
 * Once a successful pairing is established, the thread pauses until the current
 * match concludes or the client decides to leave and search for a new opponent.
 */

public class PairingManagerThread extends Thread {

    ServerClient client;

    public PairingManagerThread(ServerClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (this.client.socket.isConnected() && this.client.isSearching == true && this.client.isPaired == false) {

            try {
                // take one client to here ==> acquire 1 permit
                Server.lock.acquire(1);
                //matching system starts to matching clients.
                ServerClient chosenPair = null;
                //while the client is connected and not have pair try this to match him.
                while (this.client.socket.isConnected() && chosenPair == null) {
                    for (ServerClient client : Server.getClients()) {
                        if (client != this.client && client.isPaired == false && client.isSearching == true) {
                            //matching objects and making client pairs to play each other.
                            chosenPair = client;
                            this.client.pair = client;
                            client.pair = this.client;
                            this.client.isSearching = false;
                            client.isSearching = false;    
                            client.isPaired = true;
                            this.client.isPaired = true;
                            //giving information to the clients about the success on pairing3
                            Command message = new Command(Command.CommandTypes.MATHCING);
                            message.content = "Eşleştin";
                            Server.SendMessage(this.client, (message));
                            Server.SendMessage(chosenPair,  (message));
                            
                            //after succeeded pairing, determine the team of the clients which starter for the chess game(black or white)
                            Command clientStartMessage = new Command(Command.CommandTypes.START);
                            clientStartMessage.content = (Object)Team.WHITE;
                            Command pairClientStartMessage = new Command(Command.CommandTypes.START);
                            pairClientStartMessage.content = (Object)Team.BLACK;
                            Server.SendMessage(this.client, clientStartMessage);
                            Server.SendMessage(chosenPair,pairClientStartMessage);
                            break;
                        }
                    }
                    //do not try anytime this operation. Just every second is enough. Do not need to control is there a client want to pair
                    //in any time. Every second is more optimized solution.So sleep 1 second...
                    sleep(1000);
                    
                    
                }
                // after a completed pairing for a sclient. Release the lock to let other clients match with each other.
                Server.lock.release(1);
            } catch (InterruptedException ex) {
                System.out.println("Pairing thread could not been acquired 1 permit. There is an error occured there.");
            }
        }
    }
}
