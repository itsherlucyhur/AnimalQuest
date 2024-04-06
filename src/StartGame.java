import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


/**
 * This class is responsible for displaying the "start game" page to the user.
 * The user will be able to choose whether they want to play the game with the sound on or off.
 * This class also creates a button that returns the user to main page and the start game button.
 *
 * @version 2.0.0
 * @author Vismaya Theertha
 */
public class StartGame extends JFrame {



    private JFrame frame;



    private String difficultyLevel;




    private JButton mainMenu;

    private JButton startButton;

    private JButton soundOnButton;

    private JButton soundOffButton;



    private File FILE;


    /**
     * Constructor creates the "StartGame" object
     * Responsible for creating the buttons and displaying to the user.
     *
     */
    public StartGame() {


        setTitle("Game Round Settings");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(6, 1));

        JPanel topPanel = new JPanel(new GridLayout(1, 5));



        JPanel emptyPanel = new JPanel();
        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        JPanel emptyPanel3 = new JPanel();
        JPanel emptyPanel4 = new JPanel();
        JPanel emptyPanel5 = new JPanel();
        JPanel emptyPanel6 = new JPanel();



        // Create main menu button
        mainMenu = new JButton("Main Menu");
        mainMenu.setPreferredSize(new Dimension(200, 60));

        // If the main menu button is clicked, return the user to the main page
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




        // Creates a panel for the title JLabel
        JPanel subTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Creates the JLabel for the title
      
        add(subTitlePanel);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3));
        // Creates each button
     
        JPanel soundPanel = new JPanel(new GridLayout(1, 5));

        soundOnButton = new JButton("Sound On");
        soundOffButton = new JButton("Sound Off");

        soundPanel.add(emptyPanel4);
        soundPanel.add(emptyPanel5);
        soundPanel.add(emptyPanel6);
        soundPanel.add(soundOnButton);
        soundPanel.add(soundOffButton);

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


        // If the startButton is clicked then start the game
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If statement so that startGame cannot happen unless a difficulty has been clicked
                try {
                    startGame(difficultyLevel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

       

        add(buttonsPanel);
        add(startButton);
        add(soundPanel);

    }

    /**
     * Sets the difficulty of the game round that will be played
     *
     * @param newDiff the difficulty of the game
     */
    public void setDifficultyLevel(String newDiff) {

        difficultyLevel = newDiff;
        JOptionPane.showMessageDialog(this, "Difficulty set to: " + newDiff);
    }


    /**
     * Returns the difficulty of the game round that will be played
     *
     * @return the difficulty of the game
     */
    public String getDifficultyLevel() {

        return difficultyLevel;
    }

    /**
     * Method responsible for starting the game
     *
     * @param level is the difficulty level of the game
     * @throws java.io.IOException thrown when there is a failure to read from or write to a file
     */
    public void startGame(String level) throws IOException {
        // Getting username from tempFile

        File USERNAMEFILE = new File("userNameFile");

        // Store the username of player in a variable
        if (Files.exists(Paths.get("tempFile"))) {
            Path userFilePath = Paths.get("tempFile");

            USERNAMEFILE = userFilePath.toFile();
        }

        String playerName;

        database dbObj = new database("user name file");
        playerName = dbObj.ReadUserName(USERNAMEFILE);

        // A boolean variable that will be set to true if the username is found in the file storing easy mode's leaderboard
        boolean easyFlag = false;
        // A boolean variable that will be set to true if the username is found in the file storing medium mode's leaderboard
        boolean mediumFlag = false;
        // A boolean variable that will be set to true if the username is found in the file storing hard mode's leaderboard
        boolean hardFlag = false;

        Player player = null;
        if (Files.exists(Paths.get("Easy.csv"))) {

            String fileName = "Easy.csv";

            // Assigns that file to the FILE (by converting file path)
            Path filePath = Paths.get(fileName);
            FILE = filePath.toFile();

            int userRank = 0;
            int userScore = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
                String line;

               


// If user already exists in Easy.csv, find the player's rank and score
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");

                    if (parts.length >= 1 && parts[0].equals(playerName)) {
                        
                        easyFlag = true;
                        userRank = Integer.parseInt(parts[1]);
                        userScore = Integer.parseInt(parts[2]);
                      
                        
                        break;
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            if (Files.exists(Paths.get("Medium.csv"))) {
            	fileName = "Medium.csv";

                // Assigns that file to the FILE (by converting file path)
                filePath = Paths.get(fileName);
                FILE = filePath.toFile();

               

                try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
                    String line;

                    // If user already exists in Medium.csv, find the player's rank and score
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");

                        if (parts.length >= 1 && parts[0].equals(playerName)) {
                          
                            mediumFlag = true;
                            userRank = Integer.parseInt(parts[1]);
                            userScore = Integer.parseInt(parts[2]);
                           
                            break;
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (Files.exists(Paths.get("Hard.csv"))) {
                   	fileName = "Hard.csv";

                    // Assigns that file to the FILE (by converting file path)
                    filePath = Paths.get(fileName);
                    FILE = filePath.toFile();

                   

                    try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
                        String line;

                        // if user already exists in Hard.csv, find the player's rank and score
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");

                            if (parts.length >= 1 && parts[0].equals(playerName)) {
                                
                                hardFlag = true;
                                userRank = Integer.parseInt(parts[1]);
                                userScore = Integer.parseInt(parts[2]);
                                
                                break;
                            }
                        }
       

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            if (easyFlag == true && mediumFlag == false && hardFlag == false) {
            	level = "Easy";
            	player = new Player(playerName, userScore, userRank);
            }
            else if (easyFlag == true && mediumFlag == true && hardFlag == false) {
            	level = "Medium";
            	player = new Player(playerName, userScore, userRank);
            }
            else if (easyFlag == true && mediumFlag == true && hardFlag == true) {
            	level = "Hard";
            	player = new Player(playerName, userScore, userRank);
            }
            else if (easyFlag == true && mediumFlag == false && hardFlag == true) {
            	level = "Hard";
            	player = new Player(playerName, userScore, userRank);
            }
            // If the user hasn't played before
            else {

             	int totalLines = 0;
                try (BufferedReader countReader = new BufferedReader(new FileReader(Paths.get("Easy.csv").toFile()))) {
                    while (countReader.readLine() != null) {
                        totalLines++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
               
                userRank = totalLines;
                // Set the user's rank to the number of lines in the file
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Paths.get("Easy.csv").toFile(), true))) {
                    bufferedWriter.write(playerName + "," + userRank + ",0");
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                level = "Easy";
                player = new Player(playerName, 0, userRank);
            }
            dispose();
            

           
            
            

        } 
    // If no one has played before
        else {
        	level = "Easy";
            try {
                // create the file

                String fileName = "Easy.csv";

                File file = new File(fileName);

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("Name,Rank,Highscore");
                bufferedWriter.newLine();
                bufferedWriter.write(playerName +",1,0");  // rank is the number of lines
                bufferedWriter.newLine();
                bufferedWriter.close();

                
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
          


        }


//      Start the game
        GameBoard startGameRound = new GameBoard(level, player);
    }

}