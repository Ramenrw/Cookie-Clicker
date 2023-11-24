package ui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// runs Game
public class RunGame extends JFrame {
    private Game game;
    private GamePanel gp;
    private OptionsPanel op;

    public RunGame() throws FileNotFoundException {
        super("A Budget Cookie Clicker Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        game = new Game();
        gp = new GamePanel(game);
        op = new OptionsPanel(game);
        add(gp, BorderLayout.CENTER);
        add(op, BorderLayout.EAST);
        // do something with mouse here
    }

    public static void main(String[] args) {
        try {
            new RunGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}