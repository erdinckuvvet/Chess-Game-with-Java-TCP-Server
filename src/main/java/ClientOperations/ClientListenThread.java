package ClientOperations;

import Commands.Command;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Commands.MoveData;
import chess_game.Boards.Board;
import chess_game.Move.Move;
import chess_game.Pieces.PieceTypes;
import chess_game.Pieces.Team;
import chess_game.Player.Player;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * This class represents a thread that continuously listens 
 * for incoming messages from the server on the client's input stream.It 
 * processes the received messages and takes appropriate actions based 
 * on the message type.
 */
public class ClientListenThread extends Thread {

    Client client;

    public ClientListenThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!this.client.socket.isClosed()) {

            try {

                Command message = (Command) (this.client.input.readObject());
                switch (message.type) {
                    case START:
                        Team serverChosenTeam = (Team) message.content;
                        this.client.setTeam(serverChosenTeam);
                        break;
                    case MATHCING:
                        this.client.isMatched = true;
                        this.client.game.getMainMenu().getPlayBTN().setEnabled(true);
                        this.client.game.getMainMenu().getPlayBTN().setText("Start");
                        this.client.game.getMainMenu().getInfoLBL().setText("Game Found. Click to Play");
                        break;
                    case MOVE:
                        //to know last enemy move.
                        MoveData movement = (MoveData) message.content;
                        Board board = this.client.game.getChessBoard();
                        Player player = board.getCurrentPlayer();
                        Move move = new Move(board, board.getSquare(movement.currCoord), board.getSquare(movement.destCoord));
                        player.makeMove(board, move);
                        this.client.game.getBoardPanel().updateBoardGUI(this.client.game.getChessBoard());
                        if (move.hasKilledPiece()) {
                            if (move.getKilledPiece().getType() == PieceTypes.KING) {
                                Team winnerTeam;
                                winnerTeam = (move.getKilledPiece().getTeam() == Team.BLACK) ? Team.WHITE : Team.BLACK;
                                JOptionPane.showMessageDialog(null, "Winner: " + winnerTeam.toString());
                                Command command = new Command(Command.CommandTypes.END);
                                command.content = null;
                                client.send(command);
                                break;
                            }
                        }
                        board.changeCurrentPlayer();
                        this.client.game.getBottomGameMenu().getTurnLBL().setText("Your Turn");
                        this.client.game.getBottomGameMenu().getTurnLBL().setForeground(Color.GREEN);
                        break;
                    case CHECK:
                        //if any check state comes to client. Write information to the connected menu object.
                        Team checkStateTeam = (Team) message.content;
                        JOptionPane.showMessageDialog(null, "Check to: " + checkStateTeam.toString());
                        break;

                    case END:
                        JOptionPane.showMessageDialog(null, "Game is over..");
                        // this.client.game.getGameFrame().remove(this.client.game.getBoardPanel());
                        // this.client.game.createMainMenu();
                        break;

                    case LEAVE:
                        JOptionPane.showMessageDialog(null, "Enemy left. Returning to the Menu.");
                        this.client.game.getGameFrame().remove(this.client.game.getBoardPanel());
                        this.client.game.createMainMenu();
                }

            } catch (IOException e) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, e);
            } catch (ClassNotFoundException ex) {
                System.out.println("error: class not found");
            }
        }
    }
}
