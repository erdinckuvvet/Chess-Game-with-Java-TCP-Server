package chess_game.Resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class GUI_Configurations {

    public static int SCREEN_WIDTH = 700;
    public static int SCREEN_HEIGHT = 800;
    public static int TILE_HEIGHT = 64;
    public static int TILE_WIDTH = 64;
    public static Dimension TILE_DIMENSION = new Dimension(TILE_WIDTH, TILE_HEIGHT);
    public static Dimension OUTER_FRAME_DIMENSION = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    public static Color whiteColor = new Color(255, 255, 255);
    public static Color greyColor = new Color(155, 164, 181);
    public static Color greenColor = new Color(251, 255, 177);
    public static int MainMenuAlignment = FlowLayout.CENTER;
    public static int MainMenuHorizantalGAP = SCREEN_WIDTH;
    public static int MainMenuVerticalGAP = 50;
    public static int BottomGameMenuHorizontalGap = 50;
    public static int BottomGameMenuVerticalGap = SCREEN_HEIGHT;
    public static int BottomGameMenuAlignment = FlowLayout.LEADING;

}
