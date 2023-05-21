package chess_game.Pieces;

import chess_game.Move.Move;
import chess_game.Boards.Board;
import chess_game.Boards.Square;
import java.util.ArrayList;
import java.util.List;
import chess_game.Resources.PIECE_Configurations;
import java.util.Set;
import chess_game.Utilities.BoardUtilities;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team, PieceTypes.BISHOP);
    }

    public List<Move> availableMoves(Board board, Coordinate currentCoord) {
        List<Move> possibleMoves = new ArrayList<Move>();
        Square currentTile = board.getSquare(currentCoord);
        Square destinationTile;
        Coordinate destinationCoordinate;
        for (Coordinate coord : PIECE_Configurations.BISHOP_MOVES) {
            destinationCoordinate = currentCoord;
            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {
                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getSquare(destinationCoordinate);
                if (!destinationTile.hasPiece()) {
                    possibleMoves.add(new Move(board, currentTile, destinationTile));
                } else {
                    if (destinationTile.getPiece().getTeam() != this.getTeam()) {
                        possibleMoves.add(new Move(board, currentTile, destinationTile));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
        return possibleMoves;
    }

}
