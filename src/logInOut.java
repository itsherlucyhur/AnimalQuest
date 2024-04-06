
//imported the libraries required for this class

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

/**
 * The logInOut class represents the GUI for the loggging in functionality. 
 * It allows users to enter their username and login to the system. 
 * 
 * @author Lucy Hur
 *
 */
public class logInOut extends JFrame implements ActionListener {

    // creating instance panels and fields
    JPanel panel;
    JLabel user_label, message;
    JTextField userName_text;
    JButton submit;

    /***
     * Constructor for the login interface
     */
    public logInOut() {
        // Set up the JFrame
        super("Log In"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Set window size
        setLocationRelativeTo(null); // Center the window on the screen
        setBackground(new Color(135, 206, 250));

        // Initialize components
        panel = new JPanel(new GridLayout(3, 1));
        user_label = new JLabel("User Name:");
        user_label.setHorizontalAlignment(SwingConstants.CENTER);
        userName_text = new JTextField(20); // Set preferred text field size
        submit = new JButton("SUBMIT");
        message = new JLabel();
        panel.setBackground(new Color(135, 206, 250));

        // Add components to the panel
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(message);
        panel.add(submit);

        // Add panel to the JFrame
        add(panel);

        // Register action listener for the submit button
        submit.addActionListener(this);
    }

    /***
     * The actionPerformed method called when the submit button is clicked. 
     * Implements the login functionality by checking the entered username. 
     * 
     * @param ae ActionEvent object representing the action performed 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Simulate database check (replace with your database logic)
        String userName = userName_text.getText();

        if (!userName.isEmpty()) {
            // If username is not empty, simulate successful login
            JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + userName + "!");

            // creating a username file with the user's name who logged in
            database usernameFile = new database("tempFile");
            usernameFile.addUsernameToFile(userName);

            // then display the start game window
            StartGame startGame = new StartGame();
            startGame.setVisible(true);

            // Close the login window
            this.dispose();

        } else {
            // If username is empty, show error message
            message.setText("Invalid username.");
        }
    }

    /**
     * The main method creates an instance of logInOut and displays the login window. 
     * 
     * @param args Command line arguments  
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater() to ensure thread safety when updating Swing components
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set system look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    // Create and display the login window
                    new logInOut().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}