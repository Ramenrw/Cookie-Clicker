package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

// Main class
public class RunGame extends JFrame {
    private static final int INTERVAL = 10;

    private Game game;
    private GamePanel gp;
    private OptionsPanel op;
    private Graphics graphics;

    // EFFECTS: sets up window in which game will be played
    public RunGame() throws FileNotFoundException, IOException {
        super("A Budget Cookie Clicker Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        game = new Game();
        gp = new GamePanel(game);
        op = new OptionsPanel(game);
        add(gp, BorderLayout.CENTER);
        add(op, BorderLayout.EAST);

        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gp.update();
                gp.repaint();
            }
        });
        t.start();
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // plays the game
    public static void main(String[] args) {
        try {
            new RunGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException i) {
            System.out.println("Unable to find image");
        }
    }
}