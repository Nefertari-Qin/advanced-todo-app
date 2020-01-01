package ui;

import model.AccountManager;
import model.exceptions.NoSuchAccount;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class WelcomeFrame extends JFrame {
    public static final int WF_WIDTH = 800;
    public static final int WF_HEIGHT = 600;
    AccountManager accountManager;
    private JPanel welcomePanel;
    JTextField userName;
    JPasswordField password;

    public WelcomeFrame(AccountManager accountManager) {
        this.accountManager = accountManager;
        initializeWelcomePanel();
        add(welcomePanel, BorderLayout.WEST);
    }

    private void initializeWelcomePanel() {
        welcomePanel = new JPanel();
        welcomePanel.setSize(WF_WIDTH, WF_HEIGHT);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel login = createLoginLabel();
        initializeUserNameTextField();
        initializePasswordField();
        JButton done = createDoneButton();
    }

    private JButton createDoneButton() {
        JButton done = new JButton("DONE");
        done.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameStr = userName.getText();
                String passwordStr = new String(password.getPassword());
                try {
                    if (accountManager.signIn(usernameStr, passwordStr)) {
                        //  Close WelcomeFrame, open AccountFrame
                        setVisible(false);
                        new WelcomeFrame(accountManager);
                    } else {
                        errorSignIn("Incorrect password for: " + usernameStr);
                    }
                } catch (NoSuchAccount noSuchAccount) {
                    errorSignIn("Username doesn't exist. Create a new account for: " + usernameStr);
                }
            }

            private void errorSignIn(String errorMessage) {
                JOptionPane.showMessageDialog(null, errorMessage,
                        "System Error", JOptionPane.ERROR_MESSAGE);
                //  Reset both text fields
                userName.setText("");
                password.setText("");
            }
        });
        return done;
    }

    private void initializePasswordField() {
        password = new JPasswordField(20);
        password.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        // Set label for password text field.
        JLabel textLabel = new JLabel("Username: ");
        textLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        textLabel.setLabelFor(password);

        // Constrain password length to be <= 12.
        PlainDocument pDoc = (PlainDocument) password.getDocument();
        pDoc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (string.length() <= 12) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void initializeUserNameTextField() {
        String promptText = "alphanumeric";
        userName = new JTextField(20);
        userName.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        // Set label for username text field.
        JLabel textLabel = new JLabel("Username: ");
        textLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        textLabel.setLabelFor(userName);

        // Create prompt message for username text field.
        userName.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (userName.getText().isEmpty()) {
                    userName.setText(promptText);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (userName.getText().equals(promptText)) {
                    userName.setText("");
                }
            }
        });
    }

    // Helper: create an accustomed JLabel for "LOGIN" title
    // if no other extra setting is need, merge this little helper
    // with its caller. TODO
    private JLabel createLoginLabel() {
        JLabel login = new JLabel("<html><font face=\"Arial Black\" size=\"24\" color=\"#999999\">LOGIN</font></html>");
        login.setOpaque(false);
        return login;
    }
}
