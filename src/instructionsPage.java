import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for displaying the instructions to the user.
 * The user will be able to view these instructions when the user can click on the "Instructions" button on the main page, or
 *
 * This class creates JLabels that are used to display the instructions to the user
 *
 * @version 2.0.1
 * @author Laith Al Absi
 * @author Kalpi Patel
 */

public class instructionsPage {

    private JFrame frame;

    public instructionsPage() {

        // Creating the frame for UI component
        frame = new JFrame("Instructions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(135, 206, 250));

        // Creating back button that will take user back to the main menu once they have read instructions
        JButton backButton = new JButton();
        backButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButton.setIcon(new ImageIcon("backButton.png"));
        backButton.setBackground(new Color(135, 206, 250));
        backButton.setBounds(10, 10, 60, 60);

        // Creating the panel that will display the instructions
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new GridLayout(15, 1));
        instructionPanel.setBackground(new Color(135, 206, 250));


        frame.add(backButton);

        // The following labels created to display instructions to suer, the label is added to the frame
        JLabel titleLabel = new JLabel("How to Play Animal Quest!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 43));
        instructionPanel.add(titleLabel);

        JLabel step1 = new JLabel("* You will see a 6x6 game board with boxes. Each box will have a number");
        step1.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step1);

        JLabel step_1 = new JLabel("on it.");
        step_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step_1);

        JLabel step2 = new JLabel("* You will also see a 'target sum' displayed on top of the game board.");
        step2.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step2);

        JLabel step3 = new JLabel("* Your job is to click on 2 or more boxes that are next to each other so that");
        step3.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step3);

        JLabel step3_1 = new JLabel("when you add the numbers on them, they equal to the target sum.");
        step3_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step3_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step3_1);

        JLabel step4= new JLabel("* Once you click the boxes, you have to type 'a' to check if your answer is");
        step4.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step4);

        JLabel step4_1= new JLabel("correct.");
        step4_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step4_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step4_1);


        JLabel step5= new JLabel("* If your answer is correct you will get points. If not, then you have to try");
        step5.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step5);

        JLabel step5_1= new JLabel("again.");
        step5_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step5_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step5_1);

        JLabel step6= new JLabel("* If you cannot find a right answer, you can click on the 'Reshuffle' button");
        step6.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step6);

        JLabel step6_1= new JLabel("on the left side to mix up the board, or you can hit the 'HINT' button on");
        step6_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step6_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step6_1);

        JLabel step6_2= new JLabel("the left side to get a hint.");
        step6_2.setFont(new Font("Calibri", Font.BOLD, 30));
        step6_2.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step6_2);

        JLabel step7= new JLabel("* Keep in mind, there will be a timer at the top. You have to answer as");
        step7.setFont(new Font("Calibri", Font.BOLD, 30));
        instructionPanel.add(step7);

        JLabel step7_1= new JLabel("many questions as you can within the given time.");
        step7_1.setFont(new Font("Calibri", Font.BOLD, 30));
        step7_1.setHorizontalAlignment(SwingConstants.CENTER);
        instructionPanel.add(step7_1);

        instructionPanel.setBounds(12, 12, 1185, 750);


        frame.add(instructionPanel);

        backButton.addActionListener(new ActionListener() {

            // When user clicks on the back button, it will take them back to the main menu
            public void actionPerformed(ActionEvent e) {
                logInOut obj = new logInOut();
                MainPage newPage = new MainPage("Animal Quest", obj);
                setVisible(false);
            }
        });

    }

    /**
     * Sets the visibility of the instructions page to false
     *
     * @param b boolean value for setting visibility
     */
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}

