package view;

import logic.Events;
import logic.MainFieldManager;
import logic.vo.Cell;
import util.EventTrans;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MainScreenManager implements ActionListener, EventListenerEx {

    MainScreenViewLogic view;
    MainFieldManager field;

    public  MainScreenManager() {

        view = new MainScreenViewLogic();
        field = new MainFieldManager();

        addListeners();
    }

    private void addListeners() {
        for (JButton button : view.fieldButtons.values()) {

            button.addActionListener(this);
        }

        view.restart.addActionListener(this);

        field.addListener(Events.FIELD_UPDATED, this);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton button = (JButton)evt.getSource();
        switch (button.getText()){
            case "RESTART":
                System.out.println("Button " + button.getName() + " is pressed");
                view.reset();
                field.reset();
                break;
            default: {
                System.out.println("Button " + button.getName() + " is pressed");
                if (field.isMoveAllowed(button.getName()))
                {
                    field.actionPerformed(button.getName());
                }
            }
        }

    }

    @Override
    public void eventHandler(EventTrans evt) {

        switch (evt.getEventName()){
            case Events.FIELD_UPDATED:
                view.updateField((HashMap<String, Cell>)evt.getData());
                break;
        }
    }
}
