import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public abstract class AbstractPiece implements Piece {

    protected boolean ableToMove; // can this piece move

    protected boolean ableToRotate; // can this piece rotate

    protected Square[] square; // the squares that make up this piece

    // Made up of PIECE_COUNT squares
    protected Grid grid; // the board this piece is on
    private int row, col;

    // number of squares in one Tetris game piece
    protected static final int PIECE_COUNT = 4;

    public AbstractPiece(int r, int c, Grid g, Color color) {

        grid = g;
        square = new Square[PIECE_COUNT];
        ableToMove = true;
        ableToRotate = true;
        row = r;
        col = c;

    }

    public void draw(Graphics g) {

        for (int i = 0; i < PIECE_COUNT; i++) {
            square[i].draw(g);
        }

    }

    public void move(Direction direction) {
        if (direction == Direction.DROP) {
            // move down as long as it is possible
            while (canMove(Direction.DOWN)) {
                for (int i = 0; i < PIECE_COUNT; i++)
                    square[i].move(Direction.DOWN);
            }
        }

        else if (canMove(direction)) {
            for (int i = 0; i < PIECE_COUNT; i++)
                square[i].move(direction);
        }
        // if we couldn't move, see if because we're at the bottom
        else if (direction == Direction.DOWN) {
            ableToMove = false;
        }
    }

    public Point[] getLocations() {
        Point[] points = new Point[PIECE_COUNT];
        for (int i = 0; i < PIECE_COUNT; i++) {
            points[i] = new Point(square[i].getRow(), square[i].getCol());
        }
        return points;
    }

    public Color getColor() {
        // all squares of this piece have the same color
        return square[0].getColor();
    }

    public boolean canMove(Direction direction) {
        if (!ableToMove)
            return false;

        // Each square must be able to move in that direction
        boolean answer = true;
        for (int i = 0; i < PIECE_COUNT; i++) {
            answer = answer && square[i].canMove(direction);
        }

        return answer;
    }

    // Checks to see if the squares can rotate
    public boolean canRotate() {
        if (!ableToRotate)
            return false;

        boolean answer = true;
        for (int i = 0; i < PIECE_COUNT; i++) {
            answer = answer && square[i].canRotate(square[1]);

        }
        return answer;
    }

    // Rotates the squares
    public void rotate() {

        for (int i = 0; i < PIECE_COUNT; i++) {
            square[i].rotate(square[1]);
        }

    }

}
