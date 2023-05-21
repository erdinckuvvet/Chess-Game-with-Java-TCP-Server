package Commands;

import chess_game.Pieces.Coordinate;

/**
 * This class is designed to represent movement information in the chess game It
 * contains the necessary data, such as the destination coordinate, current
 * coordinate, and whether a piece was killed, to convey the details of a move
 * made by a player.It serves as a lightweight and optimized data structure or
 * transmitting move information efficiently.
 */
public class MoveData implements java.io.Serializable {

    public boolean isPieceKilled;
    public Coordinate destCoord;
    public Coordinate currCoord;
}
