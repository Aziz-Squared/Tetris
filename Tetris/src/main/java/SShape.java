import java.awt.Color;

public class SShape extends AbstractPiece {
  
   public SShape(int r, int c, Grid g,Color color) {
      super(r,c,g,color);
      
       // Create the squares
       square[0] = new Square(g, r, c + 1, Color.green, true);
       square[1] = new Square(g, r, c, Color.green, true);
       square[2] = new Square(g, r + 1, c, Color.green, true);
       square[3] = new Square(g, r + 1, c - 1, Color.green, true);
   }

}
