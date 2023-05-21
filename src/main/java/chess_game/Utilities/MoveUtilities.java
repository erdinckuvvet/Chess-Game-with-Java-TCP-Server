package chess_game.Utilities;

import chess_game.Boards.Board;
import chess_game.Boards.Square;
import chess_game.Pieces.Coordinate;
import chess_game.Move.Move;
import chess_game.Pieces.PieceTypes;
import chess_game.Pieces.Team;
import chess_game.Resources.PIECE_Configurations;

public class MoveUtilities {

    public static boolean isValidMove(Board board, Square destinationTile) {
        if (!board.hasChosenSquare()) {
            return false;
        }
        for (Move m : board.getChosenSquare().getPiece().availableMoves(board, board.getChosenSquare().getCoordinate())) {
            if (m.getDestinationTile().getCoordinate().equals(destinationTile.getCoordinate())) {
                return true;
            }
        }
        return false;
    }

    public static boolean controlCheckState(Board board, Team team) {

        Square destinationTile;
        Coordinate currentCoord = board.getCoord(team, PieceTypes.KING);
        //control is there a knight as a danger for king ( check state),
        for (Coordinate coord : PIECE_Configurations.KNIGHT_MOVES) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue; // chech if the coord outside of board.
            }
            destinationTile = board.getSquare(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                continue;
            } else {
                if (destinationTile.getPiece().getTeam() != team && destinationTile.getPiece().getType() == PieceTypes.KNIGHT) {
                    return true;
                }
            }
        }
        
        //control is there a rooks, queen etc. (check danger from straight way)
        
        Square currentTile = board.getSquare(currentCoord);
        Coordinate destinationCoordinate;
        for (Coordinate coord : PIECE_Configurations.ROOK_MOVES) {
            destinationCoordinate = currentCoord;
            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {
                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getSquare(destinationCoordinate);
                if (!destinationTile.hasPiece()) {
                    continue;
                } else {
                    if (destinationTile.getPiece().getTeam() == team) {
                        break;
                    }
                    if (destinationTile.getPiece().getTeam() != team && (destinationTile.getPiece().getType() == PieceTypes.ROOK || destinationTile.getPiece().getType() == PieceTypes.QUEEN)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        //control is there a bishop, queen ( check danger from cross way)
        for (Coordinate coord : PIECE_Configurations.BISHOP_MOVES) {
            destinationCoordinate = currentCoord;
            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {
                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getSquare(destinationCoordinate);
                if (!destinationTile.hasPiece()) {
                    continue;
                } else {
                    if (destinationTile.getPiece().getTeam() == team) {
                        break;
                    }
                    if (destinationTile.getPiece().getTeam() != team && (destinationTile.getPiece().getType() == PieceTypes.BISHOP || destinationTile.getPiece().getType() == PieceTypes.QUEEN)) {
                        return true;

                    } else {
                        break;
                    }
                }
            }
        }
        
        // control is there a pawn that able to attack king nearby. 
        for (Coordinate coord : (Coordinate[]) PIECE_Configurations.PAWN_MOVES.get(team).get("Attack")) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }
            destinationTile = board.getSquare(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                continue;
            } else {
                if (destinationTile.getPiece().getTeam() != team && destinationTile.getPiece().getType() == PieceTypes.PAWN) {
                    return true;
                }
            }
        }
        return false;
    }

}
