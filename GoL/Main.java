package GoL;

import java.util.Random;

public class Main {

    public static final long speed = 500;
    static Random rand = new Random();
    static final int size = 10;
    static int mutations = 0;
    static boolean playing = false;

    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //size = //scan.nextInt();
        GameOfLife frame = new GameOfLife();
        //long seed = rand.nextLong();


    }


    static void run() {

        for (int kk = 0; kk < mutations; kk++) {
            for (int ii = 0; ii < size; ii++) {
                for (int jj = 0; jj < size; jj++) {
                    checkNeighborsAndUpdateNext(ii, jj);
                }
                //System.out.println(); // here
            }
            for (int ii = 0; ii < size; ii++) {
                for (int jj = 0; jj < size; jj++) {
                    Grids.gridNow[ii][jj].isAlive = Grids.gridNext[ii][jj].isAlive;

                }
            }
            System.out.println("Generation: " + (kk + 2));
            GameOfLife.GenerationLabel.setText(String.valueOf(kk + 2));
            System.out.println("Alive " + countAlive());
            GameOfLife.AliveLabel.setText(String.valueOf(countAlive()));
            GameOfLife.draw();
            System.out.println();
            while (!playing) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mutations++;
        }
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
        for (int ii = 0; ii < size; ii++) {
            for (int jj = 0; jj < size; jj++) {
                Grids.gridNow[ii][jj].isAlive = rand.nextBoolean();
                Grids.gridNext[ii][jj].isAlive = Grids.gridNow[ii][jj].isAlive;
            }
            System.out.println();
        }
    }

    static void checkNeighborsAndUpdateNext(int ii, int jj) {

        int counter = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (Grids.gridNow[((ii + i + size) % size)][((jj + j + size) % size)].isAlive) {
                    counter++;
                }
            }
        }
        if (Grids.gridNow[ii][jj].isAlive) {
            counter--;
        }
        Grids.gridNext[ii][jj].update(counter, Grids.gridNow[ii][jj].isAlive);
        //System.out.print(Grids.gridNow[ii][jj].isAlive + " " + Grids.gridNext[ii][jj].isAlive + counter +"   "); //here

    }
}

