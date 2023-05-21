package chess_game.Utilities;

import chess_game.Boards.Square;
import chess_game.Pieces.*;
import chess_game.Resources.BOARD_Configurations;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BoardUtilities {

    public static boolean isValidCoordinate(Coordinate coord) {
        return (coord.getX() >= BOARD_Configurations.BOARD_LOWER_BOUND && coord.getX() <= BOARD_Configurations.BOARD_UPPER_BOUND && coord.getY() >= BOARD_Configurations.BOARD_LOWER_BOUND && coord.getY() <= BOARD_Configurations.BOARD_UPPER_BOUND);
    }

    public static ImageIcon getImageOfTeamPiece(Team team, PieceTypes pieceType) {

        String imagePath = "Img\\";
        if (team == null || pieceType == null) {
            imagePath += "transparent.png";
        } else {
            if (team == Team.BLACK) {
                imagePath += "black";
            } else {
                imagePath += "white";
            }
            switch (pieceType) {
                case BISHOP:
                    imagePath += "_bishop.png";
                    break;
                case KING:
                    imagePath += "_king.png";
                    break;
                case QUEEN:
                    imagePath += "_queen.png";
                    break;
                case KNIGHT:
                    imagePath += "_knight.png";
                    break;
                case PAWN:
                    imagePath += "_pawn.png";
                    break;
                case ROOK:
                    imagePath += "_rook.png";
                    break;
                default:
                    break;
            }
        }
        try {
            File img = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(img);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            return imageIcon;

        } catch (IOException ex) {
            Logger.getLogger(BOARD_Configurations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Square[][] createStandartBoardTiles() {
        Square[][] tiles = new Square[BOARD_Configurations.ROW_COUNT][BOARD_Configurations.ROW_TILE_COUNT];
        tiles[0][0] = new Square(new Coordinate(0, 0), new Rook(Team.BLACK));
        tiles[1][0] = new Square(new Coordinate(1, 0), new Knight(Team.BLACK));
        tiles[2][0] = new Square(new Coordinate(2, 0), new Bishop(Team.BLACK));
        tiles[3][0] = new Square(new Coordinate(3, 0), new Queen(Team.BLACK));
        tiles[4][0] = new Square(new Coordinate(4, 0), new King(Team.BLACK));
        tiles[5][0] = new Square(new Coordinate(5, 0), new Bishop(Team.BLACK));
        tiles[6][0] = new Square(new Coordinate(6, 0), new Knight(Team.BLACK));
        tiles[7][0] = new Square(new Coordinate(7, 0), new Rook(Team.BLACK));

        for (int i = 0; i < 8; i++) {
            tiles[i][1] = new Square(new Coordinate(i, 1), new Pawn(Team.BLACK));
            tiles[i][6] = new Square(new Coordinate(i, 6), new Pawn(Team.WHITE));
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[j][i] = new Square(new Coordinate(j, i), null);
            }
        }
        //white
        tiles[0][7] = new Square(new Coordinate(0, 7), new Rook(Team.WHITE));
        tiles[1][7] = new Square(new Coordinate(1, 7), new Knight(Team.WHITE));
        tiles[2][7] = new Square(new Coordinate(2, 7), new Bishop(Team.WHITE));
        tiles[3][7] = new Square(new Coordinate(3, 7), new King(Team.WHITE));
        tiles[4][7] = new Square(new Coordinate(4, 7), new Queen(Team.WHITE));
        tiles[5][7] = new Square(new Coordinate(5, 7), new Bishop(Team.WHITE));
        tiles[6][7] = new Square(new Coordinate(6, 7), new Knight(Team.WHITE));
        tiles[7][7] = new Square(new Coordinate(7, 7), new Rook(Team.WHITE));
        return tiles;
    }
}
