import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * This class is responsible for displaying the "choose difficulty and start game" page when debug mode is activated.
 * The developer / instructor will be able to choose a difficulty for the game round they are about to play
 *
 * This class creates the buttons that represent one of the three difficulty levels and also the start game button.
 *
 * @version 19.0.2
 * @author Kalpi Patel
 * @author Sehee Ryoo
 *
 */
public class ChooseDifficulty extends JFrame {

    private JLabel chooseADifficulty; // variable for the title label, a "box"


    // Create instance variable for te difficulty level of the game about to be played
    private String difficultyLevel;

    // The buttons for easy, medium, and hard mode
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;

    // Buttons to go back to main menu and set the sound on or off
    private JButton mainMenu;
    private JButton startButton;
    private JButton soundOnButton;
    private JButton soundOffButton;
    private File FILE;


    /**
     * Constructor creates the "chooseDifficulty" object
     * Responsible for creating the buttons and displaying to the user.
     */
    public ChooseDifficulty() {  // no parameter because the difficulty will be set depending on what button user clicks

        // Create the JFrame for the page. Use GridLayout to display the buttons on the window
        setTitle("Choose Difficulty");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(6, 1));
        JPanel topPanel = new JPanel(new GridLayout(1, 5));

        // Make empty panels to set margins around the window
        JPanel emptyPanel = new JPanel();
        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        JPanel emptyPanel3 = new JPanel();
        JPanel emptyPanel4 = new JPanel();
        JPanel emptyPanel5 = new JPanel();
        JPanel emptyPanel6 = new JPanel();

        mainMenu = new JButton("Main Menu");
        mainMenu.setPreferredSize(new Dimension(200, 60));

        // Add ActionListener for the main page button
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                logInOut obj = new logInOut();
                MainPage newPage = new MainPage("Animal Quest", obj);
                newPage.setVisible(true);
            }
        });
        topPanel.add(mainMenu);

        topPanel.add(emptyPanel);
        topPanel.add(emptyPanel1);
        topPanel.add(emptyPanel2);
        topPanel.add(emptyPanel3);
        add(topPanel);

        Font titleFont = new Font("Calibre", Font.PLAIN, 30);

        JLabel titleLabel = new JLabel("Animal Quest");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        add(titleLabel);

        Font diffFont = new Font("Calibre", Font.PLAIN, 20);

        // Creates a panel for the title JLabel
        JPanel subTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Creates the JLabel for the title
        chooseADifficulty = new JLabel("Choose a Difficulty Level");
        chooseADifficulty.setHorizontalAlignment(SwingConstants.CENTER);
        chooseADifficulty.setFont(diffFont);
        subTitlePanel.add(chooseADifficulty);
        add(subTitlePanel);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));

        // Create buttons for each difficulty
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");

        JPanel soundPanel = new JPanel(new GridLayout(1, 5));

        soundOnButton = new JButton("Sound On");
        soundOffButton = new JButton("Sound Off");

        soundPanel.add(emptyPanel4);
        soundPanel.add(emptyPanel5);
        soundPanel.add(emptyPanel6);
        soundPanel.add(soundOnButton);
        soundPanel.add(soundOffButton);



        // Add ActionListeners for each difficulty button, when pressed, set difficulty level accordingly
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficultyLevel("Easy");

            }
        });

        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficultyLevel("Medium");
            }
        });

        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDifficultyLevel("Hard");
            }
        });

        // Add ActionListener to activate sound on/off buttons
        soundOnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.playSound("animalquestmusic.wav");
            }
        });

        soundOffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.stopSound();
            }
        });


        startButton = new JButton("Start Game");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if statement so that startGame cannot happen unless a difficulty has been clicked
                try {
                    startGame(difficultyLevel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonsPanel.add(easyButton);
        buttonsPanel.add(mediumButton);
        buttonsPanel.add(hardButton);

        add(buttonsPanel);
        add(startButton);
        add(soundPanel);

    }


    // Create a pop-up message for feedback when difficulty is set
    public void setDifficultyLevel(String newDiff) {
        difficultyLevel = newDiff;
        JOptionPane.showMessageDialog(this, "Difficulty set to: " + newDiff);
    }


    /**
     * Method responsible for starting the game
     *
     * @param level string representation of level: "Easy", "Medium", or "Hard"
     * @throws IOException used to indicate an error that occurs during input-output operations
     */
    public void startGame(String level) throws IOException {

        DebugGameBoard startGameRound = new DebugGameBoard(level);

    }

}