import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author lucyhur
 *
 */
class GridCoordinatesTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass()"); 
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown()"); 
	}

	
	/**
	 * Test of getX method, of class GridCoordinates. 
	 */
	@Test
	void testGetX() {
        System.out.println("testGetX");

        JButton button = new JButton();
        GridCoordinates grid = new GridCoordinates(1, 2, 3, button);

        assertEquals(1, grid.getX());
    }
	
	/**
	 * Test of getY method, of class GridCoordinates. 
	 */
	@Test
	void testGetY() {
		System.out.println("testGetY"); 
		
		JButton button = new JButton(); 
		GridCoordinates grid = new GridCoordinates(1, 2, 3, button); 
		
        assertEquals(2, grid.getY());

	}
	
	/**
	 * Test of getButton method, of class GridCoordinates. 
	 */
	@Test
	void testGetButton() {
		System.out.println("testGetButton"); 
		
		JButton button = new JButton(); 
		GridCoordinates grid = new GridCoordinates(1, 2, 3, button); 
		
        assertEquals(button, grid.getButton());
	}
	

}
