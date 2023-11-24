package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Citations:
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter
// https://docs.oracle.com/javase/8/docs/api/javax/swing/Box.html

public class OptionsPanel extends JPanel {
    private Game game;
    private JButton save;
    private JButton load;
    private JButton buyHelper;
    private JButton removeHelper;

    // EFFECTS: creates a side panel with options for user
    public OptionsPanel(Game g) {
        setPreferredSize(new Dimension(105, 800));
        game = g;
        setBackground(new Color(180, 180, 180));
        buyHelperButton();
        add(Box.createVerticalStrut(690));
        removeHelperButton();
        saveButton();
        loadButton();
    }

    // MODIFIES: this
    // EFFECTS: adds JButton that saves game when pressed
    private void saveButton() {
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.save();
            }
        });

        add(save);
    }

    // MODIFIES: this
    // EFFECTS: adds JButton that loads game when pressed
    private void loadButton() {
        load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.load();
            }
        });

        add(load);
    }

    // MODIFIES: this
    // EFFECTS: adds JButton that buys helper when pressed
    private void buyHelperButton() {
        buyHelper = new JButton("Buy helper");
        buyHelper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.buyHelper();
            }
        });

        add(buyHelper);
    }

    // MODIFIES: this
    // EFFECTS: adds JButton that removes helper when pressed
    private void removeHelperButton() {
        removeHelper = new JButton("Remove helper");
        removeHelper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.removeHelper();
            }
        });

        add(removeHelper);
    }
}
