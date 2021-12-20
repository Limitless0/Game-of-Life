package GoL;

import java.util.Random;

public class Main {

    public static final long speed = 500;
    static Random rand = new Random();
    static final int size = 10;
    static int mutations = 0;
    static boolean playing = false;
    static boolean running = true;
    static Thread thread = new Thread(new GameOfLife());

    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //size = //scan.nextInt();
        //GameOfLife frame = new GameOfLife();
        //long seed = rand.nextLong();
        thread.start();
    }

    static int countAlive() {
        int alive = 0;
        for (int ii = 0; ii < size; ii++) {
            for (int jj = 0; jj < size; jj++) {
                if(Grids.gridNow[ii][jj].isAlive) {
                    alive++;
                }
            }
        }
        return alive;
    }

    static void init() {
        Grids.init(size);
        setGridInitValues();
        System.out.println("Grids initialised");
    }

    static void setGridInitValues() {
        for (int ii = 0; ii < size; ii++) {
            for (int jj = 0; jj < size; jj++) {
                Grids.gridNow[ii][jj].isAlive = rand.nextBoolean();
                Grids.gridNext[ii][jj].isAlive = Grids.gridNow[ii][jj].isAlive;
            }
        }
    }
}

