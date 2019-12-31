package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class WelcomeFrame extends JFrame {
    public static final int WF_WIDTH = 800;
    public static final int WF_HEIGHT = 600;
    private JPanel welcomePanel;

    public WelcomeFrame() {
        initializeWelcomePanel();
        add(welcomePanel, BorderLayout.WEST);
    }

    private void initializeWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setSize(WF_WIDTH, WF_HEIGHT);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel login = createLoginLabel();
        JTextField userName = createUserNameTextField();
        JPasswordField password = createPasswordField();
    }

    private JPasswordField createPasswordField() {
        return null;
    }

    private JTextField createUserNameTextField() {
        String promptText = "Enter user name here:";
        JTextField useName = new JTextField(promptText);
        useName.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (useName.getText().isEmpty()) {
                    useName.setText(promptText);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (useName.getText().equals(promptText)) {
                    useName.setText("");
                }
            }
        });
        return useName;
    }

    // Helper: create an accustomed JLabel for "LOGIN" title
    // if no other extra setting is need, merge this little helper
    // with its caller. TODO
    private JLabel createLoginLabel() {
        JLabel login = new JLabel("<html><font face=\"Arial Black\" size=\"20\" color=\"#999999\">LOGIN</font></html>");
        login.setOpaque(false);
        return login;
    }
}
