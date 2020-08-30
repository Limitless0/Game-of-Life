package GoL;

public class Grids {
    static int size;
    static Cell[][] gridNow;
    static Cell[][] gridNext;

    static void init (int siz) {
        size = siz;
        gridNow = new Cell[size][size];
        gridNext = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridNow[i][j] = new Cell(false);
                gridNext[i][j] = new Cell(false);
            }
        }
    }

}
