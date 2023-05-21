package chess_game.Player;

import chess_game.Move.Move;
import chess_game.Boards.Board;
import chess_game.Pieces.*;

//The player which is making moves on board. They have team also black or white.
public class Player implements java.io.Serializable{
    
    private Team team;
    
    public Player(Team team)
    {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    public void makeMove(Board board, Move move)
    {
        board.getSquare(move.getDestinationTile().getCoordinate()).setPiece(move.getCurrentTile().getPiece());
        board.getSquare(move.getCurrentTile().getCoordinate()).setPiece(null);
   
    }
}
