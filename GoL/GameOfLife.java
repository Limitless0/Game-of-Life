package GoL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    static JLabel GenerationLabel = new JLabel("1");
    static JLabel AliveLabel = new JLabel("0");
    JPanel gamePanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JLabel generations = new JLabel("Generation: ");
    JLabel alive = new JLabel("Alive: ");
    JButton PlayToggleButton = new JButton("Play/Pause");
    JButton ResetButton = new JButton("Reset");

    public GameOfLife() {
        super("Game of Life");
        Main.init();
        AliveLabel.setText(String.valueOf(Main.countAlive()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
        draw();
        while (true) {
            play();
        }
    }
    void play() {

        Main.run();
        Main.mutations++;

        while (!Main.playing) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void resetGamePanel() {
        gamePanel = new JPanel();
        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                gamePanel.add(Grids.gridNow[ii][jj]);
            }
        }
    }
    void initComponents() {
        //JLabel playing = new JLabel("false");

        PlayToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Main.playing) {
                    Main.playing = true;
                } else {
                    Main.playing = false;
                }
                //playing.setText(String.valueOf(Main.playing));
            }
        });

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mutations = 0;
                Main.init();
                try {
                    remove(gamePanel);
                } catch (Exception d) {
                    // do nothing
                }
                draw();
            }
        });

        infoPanel.add(PlayToggleButton);
        //infoPanel.add(ResetButton);
        infoPanel.add(generations); infoPanel.add(GenerationLabel);
        infoPanel.add(alive); infoPanel.add(AliveLabel);
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
        setLayout(new GridLayout(2, 1, 20, 3));
        add(infoPanel);
        add(gamePanel);

    }

    static void draw() {

        for (int ii = 0; ii < Main.size; ii++) {
            for (int jj = 0; jj < Main.size; jj++) {
                Grids.gridNow[ii][jj].draw();
            }
            System.out.println();
        }
        try {
            Thread.sleep(Main.speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
