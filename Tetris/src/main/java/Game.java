import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	private Piece piece; // the current piece that is dropping

	private boolean isOver; // has the game finished?

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
        
        
//         Piece [] p = {new TShape(1, Grid.WIDTH / 2, grid, Color.YELLOW), new ZShape(1,Grid.WIDTH / 2, grid, Color.RED),
// 				new SquareShape(1, Grid.WIDTH / 2, grid, Color.GRAY),new BarShape(1,Grid.WIDTH / 2 - 1, grid, Color.CYAN),
// 				new SShape(1, Grid.WIDTH / 2, grid, Color.GREEN), new JShape(1, Grid.WIDTH / 2, grid, Color.BLUE),
// 				new LShape(1, Grid.WIDTH / 2 - 1, grid, Color.MAGENTA)};
	
// 		Random rand = new Random();
// 		int randomShape = rand.nextInt(7);
		
		piece = new LShape(1, Grid.WIDTH / 2 - 1, grid, Color.MAGENTA);
        
		isOver = false;
	}


	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			piece.move(direction);
		}
		updatePiece();
		display.update();
		grid.checkRows();
	}
    
    /**
    * Rotates the piece
    */
    public void rotatePiece() {
		if (piece != null) {
			piece.rotate();
		}
		updatePiece();
		display.update();
		grid.checkRows();
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			// CREATE A NEW PIECE HERE
            
			// Array of all the shapes
        Piece [] p = {new TShape(1, Grid.WIDTH / 2, grid, Color.YELLOW), new ZShape(1,Grid.WIDTH / 2, grid, Color.RED),
				new SquareShape(1, Grid.WIDTH / 2, grid, Color.GRAY),new BarShape(1,Grid.WIDTH / 2 - 1, grid, Color.CYAN),
				new SShape(1, Grid.WIDTH / 2, grid, Color.GREEN), new JShape(1, Grid.WIDTH / 2, grid, Color.BLUE),
				new LShape(1, Grid.WIDTH / 2 - 1, grid, Color.MAGENTA)};
            
		// Choose a random number for the shape
		Random rand = new Random();
		int randomShape = rand.nextInt(7);
            
		// Get the piece
		piece = p[randomShape];

		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

	}

}
