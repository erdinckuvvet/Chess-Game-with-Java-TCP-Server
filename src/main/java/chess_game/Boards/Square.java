package chess_game.Boards;

import chess_game.Pieces.Coordinate;
import chess_game.Pieces.Piece;

/**
*This class represents a square on the chessboard.It holds the coord of 
 the square and information about the piece in it.
*/
public class Square implements java.io.Serializable {

    private Piece piece;
    private Coordinate coord;

    public Square(Coordinate coord, Piece piece) {
        this.coord = coord;
        this.piece = piece;
    }

    public Piece getPiece() {

        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public Coordinate getCoordinate() {

        return this.coord;
    }

    public void setCoordinate(int x, int y) {
        this.coord.setX(x);
        this.coord.setY(y);
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coord = coordinate;
    }

    public boolean hasPiece() {
        return this.piece != null;
    }

    @Override
    public String toString() {
        return coord.toString() + " Piece " + ((hasPiece() ? piece.toString() : "Empty"));
    }

}
