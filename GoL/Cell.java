package GoL;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    boolean isAlive = true;
    public Cell (boolean living) {
        if (living) { this.live(); }
        else { this.die(); }
    }

    public void update (int neighbors, boolean lastState) {
        if (neighbors == 3) {
            this.live();
        } else if (lastState && (neighbors == 2)) {
            this.live();
        } else {
            this.die();
        }
    }

    public void draw() {
        if (isAlive){
            System.out.print('O');
            darken();

        } else {
            System.out.print('X');
            lighten();
        }
    }

    public void live() {
        this.isAlive = true;
    }
    public void die() {
        this.isAlive = false;
    }
    void lighten() {
        this.setBackground(Color.LIGHT_GRAY);
    }
    void darken() {
        this.setBackground(Color.BLACK);
    }


}
