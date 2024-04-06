
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * This class is responsible for displaying the leaderboard page to the user.
 * The user will be able to choose whether they want to return to log out or play again.
 *
 * @version 2.0.0
 * @author Vismaya Theertha
 */
public class Leaderboard extends JFrame {
    private JTable leaderboardTable;
    private JLabel userRankLabel;

    /**
     * Constructor responsible for creating Leaderboard objects
     * @param difficulty the difficulty level the user is has either leveled up to or has currently been playing
     * @param score the score the user attained in the round
     * @param player the information of about the user
     * @throws IOException if any IO error occurs
     */
    public Leaderboard(String difficulty, int score, Player player) throws IOException {
        String newDifficulty;

        // set newDifficulty according to the following conditions:
        if (score < 5 && player.getHighScore() < 5) {
            newDifficulty = "Easy";
        }
        else if (score < 5 && (player.getHighScore() >= 5 && player.getHighScore() <= 10)) {
            newDifficulty = "Medium";
        }
        else if (score < 5 && (player.getHighScore() > 10)) {
            newDifficulty = "Hard";
        }
        else if (score <= 10 && score >= 5 && player.getHighScore() < 5) {
            newDifficulty = "Medium";
        }
        else if (score <= 10 && score >= 5 && (player.getHighScore() >= 5 && player.getHighScore() <= 10)) {
            newDifficulty = "Medium";
        }
        else if (score <= 10 && score >= 5 && player.getHighScore() > 10) {
            newDifficulty = "Hard";
        }
        else {
            newDifficulty = "Hard";
        }

    // Update user high score and rank if necessary in newDifficulty mode
        dbEdits(newDifficulty, score, player);
        setTitle("Leaderboard -"+newDifficulty);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Initialize the JTable to display leaderboard
        leaderboardTable = new JTable(new DefaultTableModel(
                new Object[]{"Rank", "Name", "Score"}, 0));

        // Initialize the JLabel for user's rank
        userRankLabel = new JLabel("Your Rank: ");

        // Populate the leaderboard data
        try {
            populateLeaderboard(newDifficulty, player);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the leaderboard table and user rank label to the frame
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        add(scrollPane, BorderLayout.NORTH);
        add(userRankLabel, BorderLayout.SOUTH);
    // If user leveled up
        if (newDifficulty != difficulty) {
            JOptionPane.showMessageDialog(this, "You've leveled up to "+newDifficulty+" mode!");
        }

        JButton logoutButton = new JButton("Logout");
        JButton playAgainButton = new JButton("Play Again");
//  If user clicks log out, take them to the main page
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                logInOut obj = new logInOut();
                MainPage newPage = new MainPage("Animal Quest", obj);
                newPage.setVisible(true);

            }
        });
// If user want to play another round, display the startGame window
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartGame newGame = new StartGame();
                newGame.setVisible(true);

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(logoutButton);
        buttonPanel.add(playAgainButton);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    /**
     * populateLeaderboard() reads data from the csv file of the difficulty level that player has reached,
     * and populates the table displayed to the user with this data.
     * @param difficulty the difficulty level the user reached
     * @param player the player's information
     * @throws IOException if and IO error happens
     */
    private void populateLeaderboard(String difficulty, Player player) throws IOException {
        // Read the leaderboard data from the CSV file
        String fileName = difficulty + ".csv";
        Path filePath = Paths.get(fileName);
        File file = filePath.toFile();

        int userRank = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Read all the lines in the csv file and store all the data from the csv file in the same
            // format in the table in the UI.
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    // Skip invalid lines
                    continue;
                }
                try {
                    int rank = Integer.parseInt(parts[1]);
                    String name = parts[0];
                    int score = Integer.parseInt(parts[2]);

                    // Add the data to the JTable
                    DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();
                    model.addRow(new Object[]{rank, name, score});

                    // Check if the current line belongs to the user
                    if (name.equals(player.getName())) {
                        userRank = rank;
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid integer parsing
                    System.err.println("Invalid integer format in the CSV file: " + e.getMessage());
                }
            }
        }

        // Update the user rank label
        userRankLabel.setText("Your Rank: " + userRank);
    }

    /**
     * dbEdits() changes the information stored in the csv files, if the user's high score changes
     * @param difficulty the difficulty level the user has reached
     * @param score the score the user attained in the last round
     * @param player the user's information
     * @throws FileNotFoundException if a file is not found
     * @throws IOException if there is an IO error
     */
    public void dbEdits(String difficulty, int score, Player player) throws FileNotFoundException, IOException {
        // If the user attains a new high score
        if (score > player.getHighScore()) {
            String fileName = difficulty + ".csv";
            Path filePath;
            File FILE;
            // If the file for the difficulty level the user is at does not exist, create it
            if (!Files.exists(Paths.get(fileName))) {
                FILE = new File(fileName);

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE));
                bufferedWriter.write("Name,Rank,Highscore");
                bufferedWriter.newLine();
            }
            // If the file exists
            else {
                filePath = Paths.get(fileName);
                FILE = filePath.toFile();
            }


            String playerName = player.getName();
            // List of all information in the csv file
            List<Player> scores = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {

                String line;
                reader.readLine();
                // store all the csv data in scores and update the users highScore to their latest score in the scores list
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int rank = Integer.parseInt(parts[1]);
                    int highScore = Integer.parseInt(parts[2]);


                    if (name.equals(playerName)) {

                        scores.add(new Player(playerName, score, rank));
                    } else {
                        scores.add(new Player(name, highScore, rank));
                    }
                }
                // if the player's name is not in the file
                if (!scores.contains(player)) {
                    scores.add(new Player(playerName, score, scores.size()+1));
                }
            }

            // Sort the scores list based on high scores from highest to lowest
            Collections.sort(scores, Comparator.comparing(Player::getHighScore).reversed());

            // Reassign ranks based on the sorted order
            for (int i = 0; i < scores.size(); i++) {
                scores.get(i).setRank(i + 1);
            }

            // Write the updated leaderboard back to the CSV file
            try (FileWriter writer = new FileWriter(FILE)) {
                writer.write("Name,Rank,Highscore" + "\n");
                for (Player ps : scores) {
                    writer.write(ps.getName() + "," + ps.getRank() + "," + ps.getHighScore() + "\n");
                }
            }
        }
    }
}