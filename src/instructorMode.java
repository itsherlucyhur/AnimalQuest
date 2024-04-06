import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the instructor mode interface. 
 * It allows instructors to view profiles and interact with user names
 * @author Laith Al-Absi
 * @author Lucy Hur
 *
 */


public class instructorMode extends JFrame implements ActionListener {

    /**
     * This method calls the initializeGUI() method.
     * Initializes the GUI components 
     *
     * @author Laith Al-Absi and Lucy Hur
     * @version 4.0
     */
    public instructorMode() {
        initializeGUI();
    }

    /**
     * Main method to call the instructorMode() method.
     *
     * @param args an array of strings containing the command-line arguments passed to the program
     *
     * @author Laith Al-Absi and Lucy Hur
     * @version 4.0
     */

    public static void main(String[] args) {
        new instructorMode();
    }

    /**
     *  Displays the instructor mode interface. 
     *
     *  This method displays the instructor mode page and sets up the buttons for all the students names by accessing the database and parsing it.
     *
     * @throws IOException if an I/O error occurs while trying to access the database.
     *
     * @throws IOException if an I/O error occurs while trying to access the pictures.
     *
     * @author Laith Al-Absi and Lucy Hur
     * @version 4.0
     */
    private void initializeGUI() {
        //////////////////////setting up the frame///////////////////
        setTitle("Instructor Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        getContentPane().setBackground(new Color(135, 206, 250));
        setLayout(null);

        /////////////////////////setting up labels/////////////////////
        JLabel pageName = new JLabel("<html><u>Instructor Mode</u></html>");
        pageName.setFont(new Font("Calibri", Font.BOLD, 16));
        add(pageName);
        pageName.setBounds(10, 7, 120, 20);

        JLabel instructor = new JLabel("<html><u>Instructor Profile</u></html>");
        instructor.setFont(new Font("Calibri", Font.BOLD, 24));
        add(instructor);
        instructor.setBounds(250, 100, 200, 25);

        JLabel studentNames = new JLabel("<html><u>Student Names</u></html>");
        studentNames.setFont(new Font("Calibri", Font.BOLD, 24));
        add(studentNames);
        studentNames.setBounds(832, 100, 200, 25);

        //////////////////////////setting up back button///////////////////////
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("backButton.png"));
        add(backButton);
        backButton.setBounds(10, 35, 60, 60);

        ////////////////////////setting up the panels/////////////////////////
        JPanel panel1 = new JPanel();
        panel1.setBounds(80, 65, 530, 675);
        Border panel1border = BorderFactory.createLineBorder(Color.BLACK, 10);
        panel1.setBorder(panel1border);
        add(panel1);
        panel1.setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setBounds(640, 65, 530, 675);
        Border panel2border = BorderFactory.createLineBorder(Color.BLACK, 10);
        panel2.setBorder(panel2border);
        add(panel2);
        panel2.setLayout(null);

        //////////////////////////setting up the image//////////////////////
        try {
            BufferedImage myPicture = ImageIO.read(new File("pfp.jpg"));
            JLabel insPfp = new JLabel(new ImageIcon(myPicture));
            panel1.add(insPfp);
            insPfp.setBounds(185,75,142,145);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////////////////////////loop for student names//////////////////////////
        String filePath = "Hard.csv"; // Update this to your CSV file path

        // initialize lists to store player info
        List<String> names = new ArrayList<>();
        List<Integer> rankList = new ArrayList<>();
        List<Integer> highScores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); //Skip the first line (headers)
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming the first value is the name
                names.add(values[0]);
                rankList.add(Integer.valueOf(values[1]));
                highScores.add(Integer.valueOf(values[2]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the List to an Array
        String[] namesArray = names.toArray(new String[0]);

        int k = 0;

        int j = 0;

        for (String name : namesArray) {
            final int index = k;
            JButton button = new JButton(name);
            panel2.add(button);
            button.setBounds(20, 80 + j, 490, 20);
            j += 23;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Instantiate the desired class and pass the name to its constructor
                    // Replace "OtherClass" with the name of your class

                    // display the profile of that selected player
                    PlayerProfile newProfile = new PlayerProfile(name, highScores.get(index), rankList.get(index));

                    newProfile.setVisible(true);
                    setVisible(false);
                }
            });

            // increment counter
            k++;

        }

        //////////////////////////labels for instructor profile panel//////////////////////////
        JLabel email = new JLabel("Instructor Email:  JOHNPORK@UWO.CA");
        email.setFont(new Font("Calibri", Font.BOLD, 18));
        panel1.add(email);
        email.setBounds(15, 425, 400, 25);

        JLabel firstname = new JLabel("First Name:  JOHN");
        firstname.setFont(new Font("Calibri", Font.BOLD, 18));
        panel1.add(firstname);
        firstname.setBounds(15, 275, 300, 25);

        JLabel lastname = new JLabel("Last Name:  PORK");
        lastname.setFont(new Font("Calibri", Font.BOLD, 18));
        panel1.add(lastname);
        lastname.setBounds(15, 350, 300, 25);

        JLabel comments = new JLabel("Comments:  BLAH BLAH BLAH BLAH BLAH ");
        comments.setFont(new Font("Calibri", Font.BOLD, 18));
        panel1.add(comments);
        comments.setBounds(15, 500, 400, 25);

        backButton.addActionListener(new ActionListener() {

            // When user clicks on the back button, it will take them back to the main menu
            public void actionPerformed(ActionEvent e) {
                logInOut obj = new logInOut();
                MainPage newPage = new MainPage("Animal Quest", obj);
                setVisible(false);

            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}
