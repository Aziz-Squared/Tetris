import java.awt.Color;
import java.awt.Graphics;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * @author CSC 143
 */
public class Square {
	private Grid grid; // the environment where this Square is

	private int row, col; // the grid location of this Square

	private boolean ableToMove; // true if this Square can move
	private boolean ableToRotate;

	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;

	public static final int HEIGHT = 20;

	/**
	 * Creates a square
	 * 
	 * @param g      the Grid for this Square
	 * @param row    the row of this Square in the Grid
	 * @param col    the column of this Square in the Grid
	 * @param c      the Color of this Square
	 * @param mobile true if this Square can move
	 * 
	 * @throws IllegalArgumentException if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;
		ableToRotate = mobile;
	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}

	public void setRow(int newRow) {
		this.row = newRow;
	}

	public void setCol(int newCol) {
		this.col = newCol;
	}

	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * 
	 * @param direction the direction to test for possible move
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
			case DOWN:
				if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
					move = false;
				break;

			// currently doesn't support checking LEFT or RIGHT
			// MODIFY so that it correctly returns if it can move left or right
			case LEFT:
				if (col == 0 || grid.isSet(row, col - 1))
					move = false;
				break;
			case RIGHT:
				if (col == Grid.WIDTH - 1 || grid.isSet(row, col + 1))
					move = false;
				break;
		}
		return move;
	}

	/**
	 * Does a check to see if the squares are able to rotate
	 * 
	 * @return
	 */
	public boolean canRotate(Square c) {

		boolean rotate = true;

		// get destination
		int r2 = c.row + (this.col - c.col);
		int c2 = c.col + (c.row - this.row);

		if (r2 >= Grid.HEIGHT || r2 < 0){
			return false;
		}
		if (c2 < 0 || c2 >= Grid.WIDTH){
			return false;
		}

		if (c2 > this.col) {
			for (int i = this.col; i <= c2; i++) {
				if (grid.isSet(this.row, i)) {
					rotate = false;
				}
			}
		}
		if (c2 < this.col) {
			for (int i = this.col; i >= c2; i--) {
				if (grid.isSet(this.row, i)) {
					rotate = false;
				}
			}
		}
		if (r2 > this.row) {
			for (int i = this.row; i <= r2; i++) {
				if (grid.isSet(i, c2)) {
					rotate = false;
				}
			}
		}
		if (r2 < this.row) {
			for (int i = this.row; i >= c2; i--) {
				if (grid.isSet(i, c2)) {
					rotate = false;
				}
			}
		
		
		}
		return rotate;
	}

	public void rotate(Square center) {

		if (canRotate(center)) {
			int r2 = center.row + (this.col - center.col);
			int c2 = center.col + (center.row - this.row);

			this.row = r2;
			this.col = c2;
			
		}

	}

	/**
	 * moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
				case DOWN:
					row++;
					break;
				case LEFT:
					col--;
					break;
				case RIGHT:
					col++;
					break;
			}
		}
	}

	/**
	 * Changes the color of this square
	 * 
	 * @param c the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = Grid.LEFT + col * WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
	}
}
