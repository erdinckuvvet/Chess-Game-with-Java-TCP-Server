package chess_game.Pieces;

import chess_game.Move.Move;
import chess_game.Boards.Board;
import chess_game.Boards.Square;
import java.util.ArrayList;
import java.util.List;
import chess_game.Resources.PIECE_Configurations;
import chess_game.Resources.BOARD_Configurations;
import chess_game.Utilities.BoardUtilities;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team, PieceTypes.KNIGHT);
    }
    
    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {

        List<Move> possibleMoves = new ArrayList<Move>();

        Square destinationTile;

        for (Coordinate coord : PIECE_Configurations.KNIGHT_MOVES) {
 
            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue; // chech if the coord outside of board.
            }
            destinationTile = board.getSquare(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                possibleMoves.add(new Move(board,board.getSquare(currentCoord),destinationTile));
            } else {
                if (destinationTile.getPiece().getTeam() != this.getTeam()) {
                    possibleMoves.add(new Move(board,board.getSquare(currentCoord),destinationTile));
                }
            }
        }
        return possibleMoves;
    }
}
