
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;

/**
 * This class is responsible for setting up the game board of the game Animal Quest in debug mode.
 *
 * @version 19.0.2
 * @author Kalpi Patel
 * @author Sehee Ryoo
 * @author Vismaya Theertha
 */
public class DebugGameBoard extends JFrame {

    public ArrayList<GridCoordinates> selectedButtons = new ArrayList<GridCoordinates>(); // Initialize the list for selected buttons
    public Timer timer1;


    // The difficulty being played
    public String difficulty;


    // The score of the game being played and the target sum user has to add to
    private int score;


    // Variable that represents the target sum user has to add to

    private int targetSum;


    // The user playing the game
    private Player player;


    // The variable representing the size of the grid (width and height)
    private int gridSize = 6;

    public int[][] arr = new int[gridSize][gridSize];
    public JButton[][] gridButtons;


    // Button that will allow user to reshuffle the board

    private JButton reshuffleButton = new JButton("Reshuffle");




    // Creating the labels to be added to a panel (mainPanel) that will be used to organize the UI formatting
    public JLabel animalQuest = new JLabel("Animal Quest");
    public JLabel sum = new JLabel("Target Sum: " + targetSum);
    public JLabel scoreLabel = new JLabel("Score: " + score);
    public JLabel timer = new JLabel("Time: ");

    private ArrayList<ArrayList<GridCoordinates>> solutions = new ArrayList<>();

    /**
     * Constructor responsible for creating the DebugGameBoard object
     *
     * @param level represents the difficultyLevel of the game being played
     */
    public DebugGameBoard(String level) {

        Random random = new Random();
        targetSum = random.nextInt(17) + 2;
        sum.setText("Target Sum: " + targetSum);

        difficulty = level;

        setTitle("GameBoard");  // Sets title of window the DebugGameBoard
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ensures the window frame will close when closed

        // Creates main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1200, 1200));
        mainPanel.setBackground(new Color(135, 206, 250));
        mainPanel.setLayout(null); // Panel layout is set to null so objects added to the panel can be arranged by grid coordinates

        // Create grid layout
        JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
        gridPanel.setBounds(350, 100, 760, 650);  // the position for the panel for the grid

        gridButtons = new JButton[gridSize][gridSize];

        // Creates a font object representing the font we are using in the game
        Font font = new Font("Calibre", Font.PLAIN, 30);


        ActionListener gridButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                int x = -1, y = -1, numVal = 0;
                // Find the grid coordinates of the button the user clicked
                outerloop:
                for (int i = 0; i < gridSize; i++) {
                    for (int j = 0; j < gridSize; j++) {
                        if (gridButtons[i][j] == button) {
                            x = i;
                            y = j;
                            numVal = Integer.parseInt(button.getText());
                            break outerloop;
                        }
                    }
                }
                GridCoordinates coordinate = new GridCoordinates(x, y, numVal, button);
                if (!selectedButtons.contains(coordinate)) {
                    selectedButtons.add(coordinate);
                    button.setBackground(Color.BLUE);
                } else {
                    selectedButtons.remove(coordinate);
                    button.setBackground(Color.LIGHT_GRAY);
                }
            }
        };


        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                arr[i][j] = (int) ((Math.random() * (10 - 1)) + 1);
                JButton button = new JButton(String.valueOf(arr[i][j]));
                button.setFont(font);

                gridButtons[i][j] = button;
                button.setBackground(Color.LIGHT_GRAY);
                gridPanel.add(button);
                gridButtons[i][j].addActionListener(gridButtonListener);


            }
        }


        // Setting fonts for the buttons
        animalQuest.setFont(font);
        sum.setFont(font);
        scoreLabel.setFont(font);
        timer.setFont(font);


        // Setting the position of the labels at the top of the screen
        animalQuest.setBounds(50, 0, 350, 100);
        sum.setBounds(360, 0, 300, 100);
        scoreLabel.setBounds(650, 0, 300, 100);
        timer.setBounds(900, 0, 300, 100);

        // Creating the buttons to be added to the left side of the screen
        JButton hintButton = new JButton("Hint");
        JButton instructButton = new JButton("Instructions");

        // Creating font object representing the font used on the buttons created above
        Font buttonFont = new Font("Calibri", Font.PLAIN, 20);

        // Sets fonts for text on buttons
        hintButton.setFont(buttonFont);
        reshuffleButton.setFont(buttonFont);
        instructButton.setFont(buttonFont);

        // Setting the position of the buttons on screen
        hintButton.setBounds(70, 300, 150, 100);
        reshuffleButton.setBounds(70, 450, 150, 100);
        instructButton.setBounds(70, 600, 150, 100);

        // If the user clicks on hints, display a message telling the user
        // about a number on the grid that can be used to reach the target sum
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Attain all the valid paths on the grid that will add up to target sum
                searchForCombinations();

                // If there is no solution
                if (solutions.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Shake the board!");
                }
                // If there is a solution
                else {
                    JOptionPane.showMessageDialog(null, "Try "+solutions.get(0).get(0)+" in your answer");
                }

            }
        });

        // Adding labels to the main panel (that will be on top of the screen)
        mainPanel.add(animalQuest);
        mainPanel.add(sum);
        mainPanel.add(scoreLabel);
        mainPanel.add(timer);

        // Adding buttons to the panel (on the left side of the screen)
        mainPanel.add(hintButton);
        mainPanel.add(reshuffleButton);
        mainPanel.add(instructButton);

        // Adding the game grid to the panel
        mainPanel.add(gridPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        // If user clicks reshuffle board, call the reshuffle method (which reshuffles the board)
        reshuffleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reshuffleBoard();
            }
        });


        // If user clicks on instructions, show instructions again
        instructButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "How to Play Animal Quest!\n\nYou will see a game board with boxes. \nEach box will have a number on it. \nYou will also see a 'target sum' " +
                        "displayed on top of the game board. \nClick on 2 or more boxes that are next to each other that you think add up to the target sum. \nOnce you click the boxes," +
                        " you have to type 'a' to check if your answer is correct. \nIf your answer is correct you will get points. \nIf not, then you have to try again. \nIf you cannot " +
                        "find a right answer, you can click on the 'Reshuffle' button or 'Hint' button. \nKeep in mind, there will be a timer at the top. \nYou have to answer as many " +
                        "questions in this time.");

            }
        });


        /* setup keyboard inputs to the main panel */
        // Make sure the panel can receive focus, then request focus for the panel to receive keyboard input
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        getContentPane().add(mainPanel);
        setVisible(true);

        setSize(1200, 800);

        // Timers for different modes
        switch (level) {
            case "Easy":
                // Set the timer to 3 minutes
                timer1 = new Timer(1000, new ActionListener() {
                    int count = 180;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display time left in game
                        int minutes = count / 60;
                        int seconds = count % 60;
                        if (seconds / 10 == 0) {
                            timer.setText("Timer: " + minutes+":0"+seconds);
                        }
                        else {
                            timer.setText("Timer: " + minutes+":"+seconds);
                        }
                        count--;
                        // When 3 minutes are over
                        if (count < 1) {
                            timer1.stop();
                            timer.setText("GAME OVER!");
                            dispose();

                        }
                    }
                });
                break;
            case "Medium":
                // Set the timer to 2 minutes
                timer1 = new Timer(1000, new ActionListener() {
                    int count = 120;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display time left in game
                        int minutes = count / 60;
                        int seconds = count % 60;
                        if (seconds / 10 == 0) {
                            timer.setText("Timer: " + minutes+":0"+seconds);
                        }
                        else {
                            timer.setText("Timer: " + minutes+":"+seconds);
                        }
                        count--;
                        // When 2 minutes are over
                        if (count < 1) {
                            timer1.stop();
                            timer.setText("GAME OVER!");
                            dispose();

                        }
                    }
                });
                break;
            case "Hard":
                // Set the timer to 1 minutes
                timer1 = new Timer(1000, new ActionListener() {
                    int count = 60;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display time left in game
                        int minutes = count / 60;
                        int seconds = count % 60;
                        if (seconds / 10 == 0) {
                            timer.setText("Timer: " + minutes+":0"+seconds);
                        }
                        else {
                            timer.setText("Timer: " + minutes+":"+seconds);
                        }
                        count--;

                        // When 1 minute is over
                        if (count < 1) {
                            timer1.stop();
                            timer.setText("GAME OVER!");
                            dispose();

                        }
                    }
                });
                break;
            default:
                // Default timer setup if level is unknown
                timer1 = new Timer(1000, new ActionListener() { // 1 minute as default
                    int count = 60;

                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
        }
        // Start the timer based on the level
        timer1.start();


        /* Set up all in-game keyboard inputs */
        // Register KeyEventDispatcher to intercept all key events while the DebugGameBoard runs
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                // Handle key events globally
                switch (e.getID()) {
                    case KeyEvent.KEY_TYPED:
                        char keyChar = e.getKeyChar();
                        switch (keyChar) {
                            case 'a':
                                // Handle 'a' key
                                System.out.println("Pressed 'a'");

                                // Check if valid sum
                                if (validPath()) {
                                    pointsCalc();
                                } else {
                                    // Throw warning and clear the list
                                    JOptionPane.showMessageDialog(null, "Not a valid path! Try again.");
                                    selectedButtons.clear();
                                }

                                break;

                            case 'h':
                                // Handle 'h' key
                                System.out.println("Pressed 'h'");

                                // Find possible paths that add up to target sum
                                searchForCombinations();

                                // Show messages if feedback is needed
                                // If no solution is found, suggest to shake the board
                                if (solutions.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Shake the board!");
                                }
                                // Otherwise suggest the hint found
                                else {
                                    JOptionPane.showMessageDialog(null, "Try "+solutions.get(0).get(0)+" in your answer");
                                }

                                break;

                            case 'i':
                                // Handle 'i' key
                                System.out.println("Pressed 'i'");

                                // Display instructions
                                JOptionPane.showMessageDialog(null, "How to Play Animal Quest: You will see a game board with boxes. Each box will have a number on it. You will also see a 'target sum' displayed on top of the game board. Click on 2 or more boxes that are next to each other that you think add up to the target sum. Once you click the boxes, you have to type 'a' to check if your answer is correct. If your answer is correct you will get points. If not, then you have to try again. If you cannot find a right answer, you can click on the 'Reshuffle' button or 'Hint' button. Keep in mind, there will be a timer at the top. You have to answer as many questions in this time.");
                                break;
                        }
                        break;
                    case KeyEvent.KEY_PRESSED:
                        // Handle key pressed events globally
                        break;
                    case KeyEvent.KEY_RELEASED:
                        // Handle key released events globally
                        break;
                }
                // Allow the event to continue dispatching to other components
                return false;
            }
        });

    }

    /**
     * validPath is used to find whether the buttons selected by the user are connected adjacently
     *
     * @return whether the path is connected or not
     */
    public boolean validPath() {
        boolean temp = true;
        // In the selectedButtons list, check if each preceding button is adjacent to the current button
        for (int i = 1; i < selectedButtons.size(); i++) {
            if (selectedButtons.get(i-1).getX() == selectedButtons.get(i).getX() && selectedButtons.get(i-1).getY()-1 == selectedButtons.get(i).getY()) {
                temp = true;
            }
            else if (selectedButtons.get(i-1).getX() == selectedButtons.get(i).getX() && selectedButtons.get(i-1).getY()+1 == selectedButtons.get(i).getY()) {
                temp = true;
            }
            else if (selectedButtons.get(i-1).getX()-1 == selectedButtons.get(i).getX() && selectedButtons.get(i-1).getY() == selectedButtons.get(i).getY()) {
                temp = true;
            }
            else if (selectedButtons.get(i-1).getX()+1 == selectedButtons.get(i).getX() && selectedButtons.get(i-1).getY() == selectedButtons.get(i).getY()) {
                temp = true;
            }
            else {
                temp = false;
                break;
            }

        }
        return temp;
    }

    /**
     * reshuffleBoard() generates new numbers on the grid displayed in the game
     */
    public void reshuffleBoard() {
        // updating the values in the already existing grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int randomNum = (int) ((Math.random() * (10-1)) + 1);
                gridButtons[i][j].setText(String.valueOf(randomNum));
            }
        }
    }

    /**
     * pointsCalc calculates how many points are earned by the user for the buttons they input. This
     * method is only called if validPath() returns true
     */
    public void pointsCalc() {


        int sumCalc = 0;

        // Check if buttons entered by user add up to the target sum
        for (int i = 0; i < selectedButtons.size(); i++) {
            sumCalc += selectedButtons.get(i).getNumVal();
            selectedButtons.get(i).getButton().setBackground(Color.LIGHT_GRAY);
        }
        // If user deserves points
        if (sumCalc == targetSum && validPath()) {
            // Add points to the score
            if (selectedButtons.size() == 2) {
                score += 1;
            } else if (selectedButtons.size() == 3) {
                score += 2;
            } else if (selectedButtons.size() > 3) {
                score += 3;
            }

            SoundPlayer.playSound("correct.wav");
            scoreLabel.setText("Score: " + score);

            // Change the target sum to a random number
            Random random = new Random();
            targetSum = random.nextInt(17) + 2;
            sum.setText("Target Sum: " + targetSum);

        } else if (sumCalc != targetSum && validPath()) {
            JOptionPane.showMessageDialog(null, "Valid path, but your numbers don't add up to " + targetSum + ". Try again!");

        }
        // Remove selected buttons from list
        selectedButtons.clear();
    }

    /**
     * searchForCombinations finds all the possible paths in the board that add up to targetSum and stores
     * this path in solutions using the search() helper method.
     */
    public void searchForCombinations() {
        solutions.clear();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                ArrayList<GridCoordinates> path = new ArrayList<>();
                search(i, j, path, 0);
            }
        }
    }

    /**
     * search() uses DFS to find all the paths from a given cell that add up to targetSum
     * @param row row of the cell
     * @param col column of the cell
     * @param path path that adds up to targetSum
     * @param sumSoFar current sum of numbers in path
     */
    private void search(int row, int col, ArrayList<GridCoordinates> path, int sumSoFar) {
        // Check if cell is in bounds
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return;
        }
        // Add cell to path and the number in cell to sumSoFar
        path.add(new GridCoordinates(row, col, arr[row][col], gridButtons[row][col]));
        sumSoFar += arr[row][col];
        // If path is longer than cell and it's equal to targetSum
        if (sumSoFar == targetSum && path.size() > 1) {
            solutions.add(new ArrayList<>(path));
        }
        // Check adjacent cells of the current cell being looked at
        else if (sumSoFar < targetSum) {
            search(row + 1, col, path, sumSoFar);
            search(row, col + 1, path, sumSoFar);
            search(row, col - 1, path, sumSoFar);
            search(row - 1, col, path, sumSoFar);

        }
        // Remove last element in path to traverse other possible paths
        path.remove(path.size() - 1);
    }

}