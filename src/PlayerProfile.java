import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PlayerProfile class represents each individual player/student's profile which contains the player's name,
 * high score, and rank. This information is accessed by the instructor, through the Instructor Mode.
 *
 * @author Sehee Ryoo
 * @version 19.0.2
 */
public class PlayerProfile extends JFrame {
    private JLabel nameLabel;
    private JLabel highscoreLabel;
    private JLabel rankLabel;
    private JButton backButton;

    /**
     * Constructor method to create a player profile
     *
     * @param playerName name of the player
     * @param playerHighScore high score of the player
     * @param playerRank rank of the player
     */
    public PlayerProfile(String playerName, int playerHighScore, int playerRank) {
        // Set window title and size
        super("Player Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Create and put text into the labels
        nameLabel = new JLabel("Player Name: " + playerName);
        highscoreLabel = new JLabel("Highscore: " + playerHighScore);

        // Determine text output message depending on the player's rank
        if (playerRank == 1) {
            rankLabel = new JLabel("You are in " + playerRank + "st place! Congratulations!");
        } else if (playerRank == 2) {
            rankLabel = new JLabel("You are in " + playerRank + "nd place! Amazing!");
        } else if (playerRank == 3) {
            rankLabel = new JLabel("You are in " + playerRank + "rd place! Good job!");
        } else {
            rankLabel = new JLabel("You are in " + playerRank + "th place! Awesome!");
        }

        // Set font and alignment for labels
        Font font = new Font("Calibri", Font.PLAIN, 20);
        nameLabel.setFont(font);
        highscoreLabel.setFont(font);
        rankLabel.setFont(font);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highscoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rankLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Use FlowLayout for the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Click to see other student's profile");

        // Set size of the button and add to panel, then add button and labels to the main content pane
        backButton.setPreferredSize(new Dimension(600, 50));
        buttonPanel.add(backButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 1));
        contentPane.add(nameLabel);
        contentPane.add(highscoreLabel);
        contentPane.add(rankLabel);
        contentPane.add(buttonPanel);

        // set colour of the background
        Color bgColour = new Color(135, 206, 250);
        contentPane.setBackground(bgColour); // Set color of the background
        buttonPanel.setBackground(bgColour);

        // Make the window visible
        setVisible(true);

        // Add ActionListener to the back button to go back to the instructor's page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructorMode backPage = new instructorMode();
                backPage.setVisible(true);
                setVisible(false);
            }
        });
    }

}
