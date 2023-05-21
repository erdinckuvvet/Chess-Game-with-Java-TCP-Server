package chess_game.Pieces;


import chess_game.Boards.Board;
import chess_game.Move.Move;
import chess_game.Boards.Square;
import chess_game.Resources.PIECE_Configurations;
import chess_game.Utilities.BoardUtilities;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean castlingDone = false;

    public boolean isCastlingDone() {
        return castlingDone;
    }

    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    public King(Team team) {
        super(team, PieceTypes.KING);
    }

    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {
        List<Move> possibleMoves = new ArrayList<Move>();
        Square currentTile = board.getSquare(currentCoord);
        Square destinationTile;
        Coordinate destinationCoordinate;
        for (Coordinate coord : PIECE_Configurations.QUUEN_MOVES) {
            destinationCoordinate = currentCoord.plus(coord);
            if(!BoardUtilities.isValidCoordinate(destinationCoordinate))
            {
                continue;
            }
            destinationTile = board.getSquare(destinationCoordinate);
            if (!destinationTile.hasPiece()) {
                possibleMoves.add(new Move(board, currentTile, destinationTile));
            } else {
                if (destinationTile.getPiece().getTeam() != this.getTeam()) {
                    possibleMoves.add(new Move(board, currentTile, destinationTile));
                }
            }
        }

        return possibleMoves;

    }

}
