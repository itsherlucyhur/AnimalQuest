import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
class SoundPlayerTest {
    private static final String TEST_FILE_PATH = "animalquestmusic.wav"; 


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
	 * Test of playSound method, of class SoundPlayer. 
	 */
	@Test
	void testPlaySound() {
		// Test playing sound from the file 
        assertDoesNotThrow(() -> SoundPlayer.playSound(TEST_FILE_PATH));

        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Test of stopSound method, of class SoundPlayer. 
	 */
	@Test
	void testStopSound() {
		// Test stopping sound
        assertDoesNotThrow(SoundPlayer::stopSound);
	}
	
	/**
     * Test to verify that playing an invalid sound file throws UnsupportedAudioFileException.
     */
	@Test
    void testPlayInvalidSoundFile() {
        // Test playing invalid sound file
	    assertThrows(UnsupportedAudioFileException.class, () -> SoundPlayer.playSound("invalidSoundFile.wav"));
    }
	
	/**
     * Test to verify that playing a sound file not found throws IOException.
     */
	@Test
    void testPlaySoundFileNotFound() {
        // Test playing sound file not found
        assertThrows(IOException.class, () -> SoundPlayer.playSound("nonExistentSoundFile.wav"));
    }
	
	/**
     * Test to verify that playing sound when the line is unavailable throws LineUnavailableException.
     */
    @Test
    void testPlaySoundLineUnavailable() {
        // Test playing sound when line is unavailable
        assertThrows(LineUnavailableException.class, () -> {
            SoundPlayer.playSound("unavailableSoundFile.wav");
            // Let the sound play for a short duration
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
