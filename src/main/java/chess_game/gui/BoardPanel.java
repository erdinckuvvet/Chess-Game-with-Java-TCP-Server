package chess_game.gui;

import ClientOperations.Client;
import chess_game.Boards.Board;
import chess_game.Pieces.Coordinate;
import javax.swing.JPanel;
import chess_game.Resources.BOARD_Configurations;
import java.awt.GridLayout;

// This class is the visual version of the Board object.
public class BoardPanel extends JPanel {

    private SquarePanel boardTiles[][];

    public BoardPanel(Board chessBoard,Client client) {
        super(new GridLayout(BOARD_Configurations.ROW_COUNT, BOARD_Configurations.ROW_TILE_COUNT));
        this.boardTiles = new SquarePanel[BOARD_Configurations.ROW_COUNT][BOARD_Configurations.ROW_TILE_COUNT];
        for (int i = 0; i < BOARD_Configurations.ROW_COUNT; i++) {
            for (int j = 0; j < BOARD_Configurations.ROW_TILE_COUNT; j++) {
                SquarePanel tilePanel = new SquarePanel(this, new Coordinate(j, i), chessBoard,client);
                this.boardTiles[i][j] = tilePanel;
                add(tilePanel);
            }

        }
    }

    public SquarePanel[][] getBoardTiles() {
        return boardTiles;
    }

    public void setBoardTiles(SquarePanel[][] boardTiles) {
        this.boardTiles = boardTiles;
    }

    public void updateBoardGUI(Board board) {
        for (int i = 0; i < BOARD_Configurations.ROW_COUNT; i++) {
            for (int j = 0; j < BOARD_Configurations.ROW_TILE_COUNT; j++) {
                boardTiles[i][j].assignTileColor(board);
                boardTiles[i][j].assignTilePieceIcon(board);
                
            }

        }
    }
}
