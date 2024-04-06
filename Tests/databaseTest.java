import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author lucyhur
 *
 */
class databaseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUppBeforeClass()"); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass()"); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setUp()"); 
		database db = new database("testFile"); 
		db.createFileIfNotExists("testFile");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown()");
		File file = new File("testFile"); 
		if (file.exists()) {
			file.delete(); 
		}
	}

	/**
	 * Test of addUserNameToFile method, of class database. 
	 */
	@Test
	public void testAddUsernameToFile() {
		System.out.println("AddUserNameToFile"); 
		database db = new database("testFile");
        db.addUsernameToFile("user1");

        String lastLine = db.ReadUserName(new File("testFile"));
        Assertions.assertEquals("user1", lastLine.trim());
		
	}

	@Test
	void testCreateFileIfNotExists() {
	    System.out.println("CreateFileIfNotExists"); 
	    
	    String fileName = "testFile.txt"; // Correct file name related to the database instance
	    File file = new File(fileName); // File related to the database instance

	    Assertions.assertFalse(file.exists());

	    database db = new database("testFile"); // Database instance created with the correct file name
	    db.createFileIfNotExists(fileName); // Creating the file with the correct file name

	    Assertions.assertTrue(file.exists());
	}



	/**
	 * Test of readUserName method, of class database. 
	 */
    @Test
    void testReadUserName() {
    	System.out.println("TestReadUserName"); 
        database db = new database("testFile");
        db.addUsernameToFile("user1");
        db.addUsernameToFile("user2");

        String lastLine = db.ReadUserName(new File("testFile"));
        Assertions.assertEquals("user2", lastLine.trim());
    }
}
