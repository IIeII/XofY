package view;

import logic.CombinationAnalyzer;
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

        field.addListener(Events.FIELD_UPDATED, this);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton button = (JButton)evt.getSource();
        switch (button.getText()){
            case "RESTART":
                view.reset();
                field.reset();
                break;
            default: {
                if (isActionAllowed())
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

    public boolean isActionAllowed() {
        return true;
    }
}
