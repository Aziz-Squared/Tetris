
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

class TetrisTest {

    private boolean ableToMove;
    private int row, col;

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

    @Test
    void testDrop() {
        Grid g = new Grid();
        LShape l = new LShape(5, 5, g);
        g.set(5, 5, Color.BLACK);
        l.move(Direction.DROP);
        assertTrue(g.isSet(5, 5));
    }
    @Test
    void testCanMove(Direction direction) {
        boolean move = true;
        if (!ableToMove){
            assertFalse(move);

        }
        switch (direction) {
            case DOWN:
                if (row == (Grid.HEIGHT - 1) || g.isSet(row + 1, col))
                    move = false;
                    assertFalse(move);
                break;
            case LEFT:
                if (col == 0 || g.isSet(row, col - 1))
                    move = false;
                    assertFalse(move);
                break;
            case RIGHT:
                if (col == Grid.WIDTH - 1 || g.isSet(row, col + 1))
                    move = false;
                    assertFalse(move);
                break;
        }
        assertTrue(move);
        
    }

}
