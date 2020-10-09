
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
    void testCheckRows() {
        Grid g = new Grid();
        for (int row = 0; row < Grid.HEIGHT; row++) {
            for (int col = 0; col < Grid.WIDTH; col++) {
                g.set(row, col, Color.MAGENTA);
            }
        }
        g.checkRows();
        for (int row = 0; row < Grid.HEIGHT; row++) {
            for (int col = 0; col < Grid.WIDTH; col++) {
                assertFalse(g.isSet(row, col));
            }
        }
    }

    void testDrop() {
        Grid g = new Grid();
        LShape l = new LShape(5, 5, g);
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
