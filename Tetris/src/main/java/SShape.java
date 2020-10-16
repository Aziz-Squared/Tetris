import java.awt.Color;

public class SShape extends AbstractPiece {
  
   public SShape(int r, int c, Grid g,Color color) {
	   super(r,c,g,color);
       grid = g;
       square = new Square[PIECE_COUNT];
       ableToMove = true;

       // Create the squares
       square[0] = new Square(g, r, c + 1, Color.green, true);
       square[1] = new Square(g, r, c, Color.green, true);
       square[2] = new Square(g, r + 1, c, Color.green, true);
       square[3] = new Square(g, r + 1, c - 1, Color.green, true);
   }

   @Override
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
