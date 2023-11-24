package ui;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

// The panel where the game is rendered
public class GamePanel extends JPanel {
    private Game game;
    private JButton addCookie;
    private JLabel cookies;

    // EFFECTS: sets size and background colour of panel
    public GamePanel(Game g) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.GRAY);
        cookies = new JLabel("" + game.getNumCookies());
        game = g;
        add(cookies);
        add(Box.createHorizontalStrut(10));
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
        drawCookie(g);
        drawHelpers(g);
    }

    // MODIFIES: g
    // EFFECTS: draws a cookie onto g
    private void drawCookie(Graphics g) {
        Cookie c = new Cookie();
        Color savedCol = g.getColor();
        g.setColor(Cookie.COLOR);
        g.fillOval(c.X_POS, c.Y_POS, c.SIZE_X, c.SIZE_Y);
    }

    // MODIFIES: g
    // EFFECTS: draws the helpers onto g
    private void drawHelpers(Graphics g) {
        int addX = 0;
        for (Helper helper : game.getHelpers()) {
            drawHelper(g, addX);
            addX += helper.SIZE_X;
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the helper onto g, but with each additional helper, the x position is increased
    private void drawHelper(Graphics g, int addX) {
        Color savedCol = g.getColor();
        g.setColor(Helper.COLOR);
        g.fillOval(game.WIDTH + Helper.SIZE_X / 2 + addX, game.HEIGHT + Helper.SIZE_Y / 2,
                Helper.SIZE_X, Helper.SIZE_Y);
    }

//    // MODIFIES: g
//    // EFFECTS:  draws "Save"
//    private void save(Graphics g) {
//        Color saved = g.getColor();
//        g.setColor(new Color(255, 255, 255));
//        g.setFont(new Font("Arial", 20, 20));
//        FontMetrics fm = g.getFontMetrics();
//        g.drawString("Save", Game.WIDTH, Game.HEIGHT - 50);
//        g.setColor(saved);
//    }
//
//    // MODIFIES: g
//    // EFFECTS:  draws "Load"
//    private void load(Graphics g) {
//        Color saved = g.getColor();
//        g.setColor(new Color(255, 255, 255));
//        g.setFont(new Font("Arial", 20, 20));
//        FontMetrics fm = g.getFontMetrics();
//        g.drawString("Load", Game.WIDTH, Game.HEIGHT);
//        g.setColor(saved);
//    }

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
