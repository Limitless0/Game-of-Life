package GoL;

import javax.swing.*;
import java.awt.*;

public class GameOfLife implements Runnable {
    static JLabel GenerationLabel = new JLabel("1");
    static JLabel AliveLabel = new JLabel("0");
    JPanel gamePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JLabel generations = new JLabel("Generation: ");
    JLabel alive = new JLabel("Alive: ");
    JButton PlayToggleButton = new JButton("Play/Pause");
    JButton ResetButton = new JButton("Reset");
    JFrame masterFrame = new JFrame("Game of Life");
    private int runCounter = 0;

    public GameOfLife() {
        Main.init();
        AliveLabel.setText(String.valueOf(Main.countAlive()));
        masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterFrame.setSize(500, 500);
        masterFrame.setLocationRelativeTo(null);
        initComponents();
        masterFrame.setVisible(true);
        draw();
    }
    public void run() {
        while (Main.running.get()) {
            while (Main.playing.get()) {
                updateAllNext();
                boldlyGoIntoTheFuture();
                System.out.println("Generation: " + (runCounter + 1));
                GenerationLabel.setText(String.valueOf(runCounter + 1));
                System.out.println("Alive " + Main.countAlive());
                AliveLabel.setText(String.valueOf(Main.countAlive()));
                draw();
                System.out.println();
                runCounter++;
                pause(Main.speed);
            }
        }
    }

    void initComponents() {

        PlayToggleButton.addActionListener(e -> {
            Main.playing.set(!Main.playing.get());
        });

        ResetButton.addActionListener(e -> {
            Main.setGridInitValues();
            Main.mutations = 0;
            runCounter = 0;
            draw();
        });

        infoPanel.add(PlayToggleButton);
        infoPanel.add(ResetButton);
        infoPanel.add(generations); infoPanel.add(GenerationLabel);
        infoPanel.add(alive); infoPanel.add(AliveLabel);
        //infoPanel.add(new JLabel(String.valueOf(Main.playing)));
        GenerationLabel.setName("GenerationLabel");
        AliveLabel.setName("AliveLabel");
        infoPanel.setName("infoPanel");
        gamePanel.setName("gamePanel");

        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                gamePanel.add(Grids.gridNow[ii][jj]);
            }
        }

        infoPanel.setLayout(new GridLayout());
        gamePanel.setLayout(new GridLayout(Main.size, Main.size, 1, 1));
        masterFrame.setLayout(new GridLayout(2, 1, 20, 3));
        masterFrame.add(infoPanel);
        masterFrame.add(gamePanel);

    }

    static void draw() {
        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                Grids.gridNow[ii][jj].draw();
            }
            System.out.println();
        }
    }

    void checkNeighborsAndUpdateNext(int ii, int jj) {

        int counter = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (Grids.gridNow[((ii + i + Main.size) % Main.size)][((jj + j + Main.size) % Main.size)].isAlive) {
                    counter++;
                }
            }
        }
        if (Grids.gridNow[ii][jj].isAlive) {
            counter--;
        }
        Grids.gridNext[ii][jj].update(counter, Grids.gridNow[ii][jj].isAlive);
    }

    private void updateAllNext() {
        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                checkNeighborsAndUpdateNext(ii, jj);
            }
        }
    }

    private void boldlyGoIntoTheFuture() {
        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                Grids.gridNow[ii][jj].isAlive = Grids.gridNext[ii][jj].isAlive;
            }
        }
    }

    private void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }
}
