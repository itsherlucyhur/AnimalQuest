import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * This class represents the main user interface of the AnimalQuest application. 
 * It provides buttons for log in, instructions, and entering the instructor mode. 
 * 
 * @author Lucy Hur
 * @author Kalpi Patel
 *
 */
public class MainPage extends javax.swing.JFrame {
    private JFrame frame;
    private String mainTitle;
    private logInOut logInOut;

    /**
     * Constructor for the MainPage class. 
     * 
     * @param mainTitle The title to be displayed on the main page. 
     * @param logInOut An instance of the logInOut class. 
     */
    public MainPage(String mainTitle, logInOut logInOut) {
        this.mainTitle = mainTitle;
        this.logInOut = logInOut;
        initializeGUI();
    }

    /**
     * Initializes the graphical user interface of the main page. 
     */

    private void initializeGUI() {

        setTitle("Animal Quest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The panel that all UI components will be placed in
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(1200, 800));
        mainPanel.setBackground(new Color(135, 206, 250));

        // The image to be on the main page
        // The image is added to a label and the label is added to a panel.
        // The panel is later added to the mainPanel which is displayed to users
        ImageIcon icon = new ImageIcon("maintitle.png");

        Image originalImage = icon.getImage();
        int width = 420;
        int height = 250;
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);  // Resizes image to desired dimensions

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel photoLabel = new JLabel();

        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setBounds(375, 80, 420, 300);

        photoLabel.setIcon(resizedIcon);

        JPanel photoPanel = new JPanel();
        photoPanel.add(photoLabel);
        photoPanel.setBounds(400, 110, 420, 270);
        photoPanel.setBackground(new Color (135, 206, 250));


        // Creates a font object representing the font for the title
        Font font = new Font("Calibri", Font.PLAIN, 55);

        // Creates the label for the title and aligns it appropriately on the page
        JLabel titleLabel = new JLabel("Animal Quest");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(font);
        titleLabel.setBounds(375, 40, 500, 60);

        // Creates a font object representing the font for the buttons
        Font buttonsFont = new Font("Calibri", Font.PLAIN, 30);

        // Creates a buttons panel for the buttons to be on and aligns it on the page
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel.setBounds(370, 390, 500, 300);

        // Creates the buttons to be displayed on the main page
        JButton loginButton = new JButton("Log In");
        loginButton.setFont(buttonsFont);
        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(buttonsFont);
        JButton instructorModeButton = new JButton("Instructor Mode");
        instructorModeButton.setFont(buttonsFont);

        // Adds the buttons to the panel
        buttonsPanel.setBackground(new Color(135, 206, 250));
        buttonsPanel.add(loginButton);
        buttonsPanel.add(instructionsButton);
        buttonsPanel.add(instructorModeButton);

        // Create a panel for centering the titleLabel horizontally
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(new Color (135, 206, 250));
        textPanel.setBounds(0, 700, 700, 75);

        // Creates the informational and disclaimer texts to be displayed at the bottom of the screen
        JLabel groupMembers = new JLabel("Developed by: Kalpi Patel, Sehee Ryoo, Lucy Hur, Laith Mohammed Al Absi and Vismaya Theertha");
        JLabel developedFor = new JLabel("Group 27 - Winter 2024");
        JLabel group = new JLabel("This application was created as part of CS2212 at Western University");

        // Adds the text to the panel
        textPanel.add(groupMembers);
        textPanel.add(developedFor);
        textPanel.add(group);

        // Adds all the panels used for formatting to the mainPanel
        mainPanel.add(photoPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(titleLabel);
        mainPanel.add(textPanel);

        getContentPane().add(mainPanel);
        pack();
        setVisible(true);

        // Add action listeners to buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login button action
                logInOut.setVisible(true);		// make the login page visible
                setVisible(false);		// hide the main page frame
            }
        });

        // Creates ActionListener so that when user clicks instructions button, the instructions page is displayed
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle instructions button action
              instructionsPage instructionsPage = new instructionsPage();
              instructionsPage.setVisible(true);
              setVisible(false);
            }
        });

        // Creates an ActionListener so that when user clicks instructor mode they are taken to the instructor page
        instructorModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructorMode instructorMode = new instructorMode();
                // Handle instructor mode button action
                instructorMode.setVisible(true);
                setVisible(false);
            }
        });

        // Registers KeyEventDispatcher to intercept all key events
        // If user clicks "d" it takes them to developer and debugging mode
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                // Handle key events globally
                switch (e.getID()) {
                    case KeyEvent.KEY_TYPED:
                        char keyChar = e.getKeyChar();
                        if (keyChar == 'd') {// Handle 'a' key
                            System.out.println("Debug mode key is pressed");
                            ChooseDifficulty newDebugMode = new ChooseDifficulty();
                            newDebugMode.setVisible(true);
                            frame.setVisible(false);
                        }
                        break;
                    case KeyEvent.KEY_PRESSED:
                        // Handle key pressed events globally
                        break;
                    case KeyEvent.KEY_RELEASED:
                        // Handle key released events globally
                        break;
                }
                return false;
            }
        });

    }

    public static void main(String[] args) {
        // Initialize and display the main page
        logInOut logInOut = new logInOut(); // Replace YourLogInOutImplementation with your actual implementation
        MainPage mainPage = new MainPage("Animal Quest", logInOut);
    }

}


