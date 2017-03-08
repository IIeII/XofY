import view.MainScreenManager;
import view.MainScreenViewLogic;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {

    public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        EventQueue.invokeLater(new Main());
    }

    @Override
    public void run() {
       new MainScreenManager();
    }
}
