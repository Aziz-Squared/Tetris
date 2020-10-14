import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public abstract class AbstractPiece implements Piece {

    protected boolean ableToMove; // can this piece move

    protected Square[] square; // the squares that make up this piece

    // Made up of PIECE_COUNT squares
    protected Grid grid; // the board this piece is on

    // number of squares in one Tetris game piece
    protected static final int PIECE_COUNT = 4;

    public AbstractPiece(int r, int c, Grid g, Color color) {

        grid = g;
        square = new Square[PIECE_COUNT];
        ableToMove = true;

        square[0] = new Square(g, r - 1, c, color, true);
        square[1] = new Square(g, r, c, color, true);
        square[2] = new Square(g, r + 1, c, color, true);
        square[3] = new Square(g, r + 1, c + 1, color, true);

    }

    public void draw(Graphics g) {

        for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}

    }

    public void move(Direction direction) {
		if (direction == Direction.DROP){
			// move down as long as it is possible
			while (canMove(Direction.DOWN)){
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
    
    /** This rotates the Piece*/
    for(int i = 0; i<PIECE_COUNT; i++)
    { if(i==1)
    {
        ;
    }
     else {
         int py = square[1].getRow();
         int px = square[1].getCol();
         int y1 = square[i].getRow();
         int x1 = square[i].getCol();
         int x2 = y1+px-py;
         int y2 = x1+px-px;
         if ((0<=x2)&&(x2<=Grid.WIDTH)&&(0<=y2)&&(y2<=Grid.HEIGHT))
         {
             continue;
         }
         else
             check = false;
     }
    }
    if(check==true)
    {
        for(int i=0;i<PIECE_COUNT;i++)
        {
            if(i==1)
            {
                ;
            }
            else {
                int py = square[1].getRow();
                int px = square[1].getCol();
                int y1 = square[i].getRow();
                int x1 = square[i].getCol();
                int x2 = px+py-y1;
                int y2 = x1+py-px;
                square[i].setCol(x2);
                square[i].setRow(y2);
            }
        }
    }
}
}
