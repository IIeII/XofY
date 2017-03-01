package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenManager implements ActionListener {

    MainScreenViewLogic main;

    public  MainScreenManager() {

        main = new MainScreenViewLogic();
        addListeners();
    }

    private void addListeners() {
        for (JButton button : main.fieldButtons.values()) {

            button.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton source = (JButton)evt.getSource();
        source.setText("X");
        source.setForeground(Color.RED);
    }
}
