import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class to test the Player class.
 * 
 * This class tests the various methods of the Player class.
 * 
 * @author lucyhur
 */
class PlayerTest {
	
	private Player player;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass()");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass()");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("setUp()");
		player = new Player("John", 1000, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown()");
	}

	/**
	 * Test of getName method, of class Player.
	 */
	@Test
	void testGetName() {
		System.out.println("testGetName()");
		assertEquals("John", player.getName());
	}

	/**
	 * Test of getHighScore method, of class Player.
	 */
	@Test
	void testGetHighScore() {
		System.out.println("testGetHighScore()");
		assertEquals(1000, player.getHighScore());
	}

	/**
	 * Test of getRank method, of class Player.
	 */
	@Test
	void testGetRank() {
		System.out.println("testGetRank()");
		assertEquals(1, player.getRank());
	}

	/**
	 * Test of setName method, of class Player.
	 */
	@Test
	void testSetName() {
		System.out.println("testSetName()");
		player.setName("Danny");
		assertEquals("Danny", player.getName());
	}

	/**
	 * Test of setHighScore method, of class Player.
	 */
	@Test
	void testSetHighScore() {
		System.out.println("testSetHighScore()");
		player.setHighScore(800);
		assertEquals(800, player.getHighScore());
	}

	/**
	 * Test of setRank method, of class Player.
	 */
	@Test
	void testSetRank() {
		System.out.println("testSetRank()");
		player.setRank(2);
		assertEquals(2, player.getRank());
	}
}
