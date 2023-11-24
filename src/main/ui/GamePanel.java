package ui;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

// The panel where the game is rendered
public class GamePanel extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Game game;
    private JButton addCookie;
    private JLabel cookies;

    // EFFECTS: sets size and background colour of panel
    public GamePanel(Game g) throws IOException {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game = g;
        setBackground(Color.GRAY);

        cookies = new JLabel("Cookies: " + game.getNumCookies());
        cookies.setPreferredSize(new Dimension(100, 30));
        add(cookies);

        add(Box.createHorizontalStrut(10));

        JLabel cookieLabel = new JLabel(new ImageIcon("./data/cookie.jpg"));
        cookieLabel.setVisible(true);
        add(cookieLabel);

        addCookieButton();
    }

    // MODIFIES: g
    // EFFECTS: draws the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the game onto g
    private void drawGame(Graphics g) {
        drawHelpers(g);
    }


    // MODIFIES: g
    // EFFECTS: draws the helpers onto g
    private void drawHelpers(Graphics g) {
        int addX = 0;
        int addY = 0;
        for (Helper helper : game.getHelpers()) {
            if (addX >= WIDTH) {
                addX = 0;
                addY += helper.SIZE_Y;
            }
            drawHelper(g, addX, addY);
            addX += helper.SIZE_X;
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the helper onto g, but with each additional helper, the x position is increased
    private void drawHelper(Graphics g, int addX, int addY) {
        g.setColor(Helper.COLOR);
        g.fillOval(addX, 600 + addY,
                Helper.SIZE_X, Helper.SIZE_Y);
    }

    // MODIFIES: this
    // EFFECTS: creates JButton that adds a cookie when pressed
    private void addCookieButton() {
        addCookie = new JButton("Click me");
        addCookie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.addCookie();
            }
        });

        add(addCookie);
    }

    // MODIFIES: this
    // EFFECTS: updates number of cookies
    public void update() {
        cookies.setText("Cookies: " + game.getNumCookies());
    }
}
