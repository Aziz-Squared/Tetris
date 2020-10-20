
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class TetrisTest implements ParameterResolver {

    private boolean ableToMove;
    private int row, col;
    private static int PIECE_COUNT = 4;
    private Square[] square;
    Tetris tetris = new Tetris();
    Game game = new Game(tetris);
    EventController ev = new EventController(game);
    Grid g = new Grid();


    @Test
    public void testRotate(){
        int r = 5, c = 5;
        Piece[] pieces = {new BarShape(r, c, g, Color.CYAN),
                            new LShape(r, c, g, Color.YELLOW),
                            new ZShape(r, c, g, Color.MAGENTA),
                            new TShape(r, c, g, Color.RED),
                            new JShape(r, c, g, Color.GRAY),
                            new SShape(r, c, g, Color.BLUE)};

        for (Piece p : pieces){
            System.out.println(p.getClass());

            // Piece should be able to rotate
            assertTrue(p.canRotate());

            // Please a square next tothe piece to prevent it from rotating
            int setRow, setCol;
            if (p.getClass() == BarShape.class){
                setRow = r;
                setCol = c;
            } else if (p.getClass() == LShape.class){
                setRow = r;
                setCol = c - 1;
            } else if (p.getClass() == ZShape.class){
                setRow = r + 1;
                setCol = c - 1;
            } else if (p.getClass() == TShape.class){
                setRow = r + 1;
                setCol = c - 1;
            } else if (p.getClass() == JShape.class){
                setRow = r;
                setCol = c - 1;
            } else { //SShape
                setRow = r;
                setCol = c - 1;
            }

            g.set(setRow, setCol, Color.GREEN);
            assertFalse(p.canRotate());
            g.set(setRow, setCol, Grid.EMPTY);


        }
    }


    @Test
    void testCheckRows() {
        for (int row = 0; row < Grid.HEIGHT; row++) {
            for (int col = 0; col < Grid.WIDTH; col++) {
                g.set(row, col, Color.MAGENTA);
            }
        }
        g.checkRows();
        for (int row = 0; row < Grid.HEIGHT; row++) {
            for (int col = 0; col < Grid.WIDTH; col++) {
                assertTrue(g.isSet(row, col));
            }
        }
    }
    @ Test
    void testDrop() {
        Grid g = new Grid();
        LShape l = new LShape(5, 5, g, Color.magenta);
        g.set(5, 5, Color.BLACK);
        l.move(Direction.DROP);
        assertTrue(g.isSet(5, 5));
    }

    public boolean testCanMove(Direction direction) {
        if (!ableToMove)
            return false;

        boolean move = true;
        switch (direction) {
            case DOWN:
                if (row == (Grid.HEIGHT - 1) || g.isSet(row + 1, col))
                    move = false;
                break;
            case LEFT:
                if (col == 0 || g.isSet(row, col - 1))
                    move = false;
                break;
            case RIGHT:
                if (col == Grid.WIDTH - 1 || g.isSet(row, col + 1))
                    move = false;
                break;
            case DROP:
                move = false;
                break;
        }
        return move;
    }

    /**
     * 
     * @param direction
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    void testMove(Direction direction) {
        if (direction == Direction.DROP) {
            // move down as long as it is possible
            while (testCanMove(Direction.DOWN)) {
                for (int i = 0; i < PIECE_COUNT; i++)
                    square[i].move(Direction.DOWN);
                assertTrue(ableToMove);
            }
        }

        else if (testCanMove(direction)) {
            for (int i = 0; i < PIECE_COUNT; i++)
                square[i].move(direction);
            assertTrue(ableToMove);
        }
        // if we couldn't move, see if because we're at the bottom
        else if (direction == Direction.DOWN) {
            ableToMove = false;
            assertFalse(ableToMove);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        // TODO Auto-generated method stub
        return null;
    }

}
