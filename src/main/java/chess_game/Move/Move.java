package chess_game.Move;

import chess_game.Boards.Board;
import chess_game.Boards.Square;
import chess_game.Pieces.Piece;

public class Move implements java.io.Serializable{

    Board board;
    Square currentTile;
    Square destinationTile;
    Piece movedPiece;
    Piece killedPiece;

    public Move(Board board, Square currentTile, Square destinationTile) {
        this.board = board;
        this.currentTile = currentTile;
        this.destinationTile = destinationTile;
        this.movedPiece = currentTile.getPiece();
        if (destinationTile.hasPiece()) {
            killedPiece = destinationTile.getPiece();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Square getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Square currentTile) {
        this.currentTile = currentTile;
    }

    public Square getDestinationTile() {
        return destinationTile;
    }

    public void setDestinationTile(Square destinationTile) {
        this.destinationTile = destinationTile;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public void setKilledPiece(Piece killedPiece) {
        this.killedPiece = killedPiece;
    }
    
    public boolean hasKilledPiece()
    {
        return this.killedPiece != null;
    }
}
