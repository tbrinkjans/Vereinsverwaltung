package application.util;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class Dialog {

    public static void showInfoDialog(String message, Component parent) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorDialog(String message, Component parent) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(parent, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showConfirmDialog(String message, Component parent) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Bestätigen", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

}
