import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq <br>
 * Sq <br>
 * Sq Sq <br>
 * 
 * The game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author CSC 143
 */
public class LShape extends AbstractPiece {

    private int row;
    private int col;
    private Grid g;
    private Color Col;

    public LShape(int r, int c, Grid g, Color color) {
        super(r, c, g, color);
        grid = g;
        row = r;
        col = c;
        Col = color;
        square = new Square[PIECE_COUNT];
        ableToMove = true;

        square[0] = new Square(g, r - 1, c, color, true);
        square[1] = new Square(g, r, c, color, true);
        square[2] = new Square(g, r + 1, c, color, true);
        square[3] = new Square(g, r + 1, c + 1, color, true);

    }

    /** This rotates the Piece */
    public void rotate() {

        for (int i = 0; i < PIECE_COUNT; i++) {

            int py = square[1].getRow();
            int px = square[1].getCol();
            int y1 = square[i].getRow();
            int x1 = square[i].getCol();
            int x2 = px + py - y1;
            int y2 = x1 + py - px;
            square[i].setCol(x2);
            square[i].setRow(y2);

        }

    }

}
