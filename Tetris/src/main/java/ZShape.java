import java.awt.Color;

public class ZShape extends AbstractPiece {

   public ZShape(int r, int c, Grid g, Color color) {
      super(r, c, g, color);
      
      // Create the squares
      square[0] = new Square(g, r, c - 1, Color.red, true);
      square[1] = new Square(g, r, c, Color.red, true);
      square[2] = new Square(g, r + 1, c, Color.red, true);
      square[3] = new Square(g, r + 1, c + 1, Color.red, true);
   }
}
