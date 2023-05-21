package chess_game.Boards;

import chess_game.Pieces.*;
import chess_game.Player.Player;
import chess_game.Utilities.BoardUtilities;

/**
 * The Board class represents the state of the chessboard and keeps track of the
 * moves made by the players.It is responsible for managing the progress of the 
 * game and performing the movements on the board.Other classes interact with 
 * the Board class to control the game and access the board's state.
 */
public class Board implements java.io.Serializable {

    private final Square[][] squares;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private Square chosenSquare = null;
    
    public Board() {
        whitePlayer = new Player(Team.WHITE);
        blackPlayer = new Player(Team.BLACK);
        currentPlayer = whitePlayer;
        squares = BoardUtilities.createStandartBoardTiles();

    }
    
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Square getChosenSquare() {
        return chosenSquare;
    }

    public boolean hasChosenSquare() {
        if (chosenSquare == null) {
            return false;
        }

        if (chosenSquare.getPiece() == null) {
            return false;
        }
        return true;
    }
    
    public void changeCurrentPlayer() {
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    public void setChosenSquare(Square chosenTile) {
        if (!chosenTile.hasPiece()) {
            this.chosenSquare = null;
        } else {
            this.chosenSquare = chosenTile;
        }
    }

    public Square getSquare(Coordinate coord) {
        return Board.this.getSquare(coord.getX(), coord.getY());
    }


    public Square getSquare(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("index exceeds board size");
            return null;
        }
        return squares[x][y];
    }

    
    
    public Coordinate getCoord(Team team, PieceTypes pieceType) {
    for (Square[] row : squares) {
        for (Square square : row) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getTeam() == team && piece.getType() == pieceType) {
                return square.getCoordinate();
            }
        }
    }
    return null;
}

    public Square getSquare(Team team, PieceTypes pieceType) {
    for (Square[] row : squares) {
        for (Square square : row) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getTeam() == team && piece.getType() == pieceType) {
                return square;
            }
        }
    }
    return null;
}
}
