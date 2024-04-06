
import java.io.*;
import java.io.BufferedWriter;

/**
 * The database class represents a file-based database system for storing user information. 
 * It provides methods to add usernames to the file, and read the last username from a file. 
 * 
 * @author chaejinhur
 *
 */
public class database {
    private String file;
    private String easyFile;
    private String mediumFile;
    private String hardFile;


    /**
     * Constructor for the database class. 
     * 
     * @param fileName string representation of the file name
     */
    public database (String fileName) {
        this.file = fileName;
    }


    /**
     * Adds a username to the file. 
     * @param username The username to be added. 
     */
    public void addUsernameToFile(String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a file if it doesn't exist. 
     * 
     * @param fileName The name of the file to be created. 
     */
    public void createFileIfNotExists(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the last username from the file. 
     * @param file The file from which to read the username. 
     * @return The last username found in the file. 
     */
    public String ReadUserName(File file) {

        String lastLine = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}

    