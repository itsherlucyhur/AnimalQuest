# Animal Quest
***
## Gameplay

Animal Quest is a desktop application that users can play an educational math game on. This source code contains a mode in the application that allows instructors to track their students' progress and there's a mode that
allows the students to practice their addition skills by playing the game. This is done by finding adjacent cells in 
a 6 by 6 grid that adds up to a certain sum. Once they find some cells that do, the target sum will change. 
This process repeats itself until the timer goes off. There are 3 different levels that the user can play this game in: 
Easy, Medium and Hard mode. In Easy mode, the timer goes off in three minutes. In Medium mode, the timer goes off in 
two minutes. In Hard mode, the timer goes off in 1 minute.

When the software is run, you will land on the main page. On the main page you can either choose to see the instructions,
log in as a player, or enter instructor mode. If you choose to see the instructions, you can click the back button to 
return to the main page. If you click on instructor mode, you can click on different student names to see their profiles
and click on the back button to go back to instructor mode. Note that there is also a back button on instructor mode to
take the user back to the main page. If you click on log in, enter your name. Then you will be taken to the start game
to choose whether you want to play with the sound on or off, then click start game. You can still return to the main 
page by clicking on the main menu on this page. If you choose to click start game, you will start playing the game. 
In the Gameboard, to see hints you can type 'h' or click 'Hints', to see instructions you can either type 'i' or click 
'Instructions', to reshuffle the board you click 'Reshuffle', or type 'a' to add the clicked buttons. At the end of the 
game, the leaderboard will be displayed. From here you will have the option to either play again or log out. To enter 
debug mode from you can press d. From here you can not only click main menu, Sound On and Sound Off, you can also 
choose the difficulty you want to debug. Then you will be taken to the Gameboard with corresponding difficulty level.


## Prerequisites
- JDK version 19.0.2
- Intellij IDEA version 2023.3.6

The libraries used in this project include:
- `<java.io>`, `<javax.swing>`, `<java.awt>`, `<java.util>`, `<java.nio>`, `<org.junit.jupiter.api>`, `<javax.sound.sampled>`

## Included files
- MainPage.java
- ChooseDifficulty.java
- DebugGameBoard.java
- GameBoard.java
- GridCoordinates.java
- instructionsPage.java
- instructionsMode.java
- Leaderboard.java
- logInOut.java
- Player.java
- PlayerProfile.java
- SoundPlayer.java
- StartGame.java

Miscellaneous files:
- maintitle.png
- backButton.png
- animalquestmusic.wav
- correct.wav
- easy.jpg, medium.jpg, hard.jpg
- Javadoc html files 
- AnimalQuest.jar - executable file

## Compile Instructions
This program was compiled through the Intellij IDEA IDE. To get this IDE, go to Jetbrains at https://www.jetbrains.com/idea/ to download version 2023.3.6.

If you do not already have JDK installed, download any version past 19.0.2 at
https://www.oracle.com/java/technologies/downloads/.
The JDK includes the JRE, which is the environment needed to run Java applications for compiling and debugging the source code.

To build and compile the software, do the following:
1. Open Intellij IDEA 
2. Extract the **AnimalQuest.zip** file 
3. Go to **File** > **Open** > select **AnimalQuest**
4. Go to **Build** > **Build Project**
5. To run the program, go to **Run** and choose **MainPage.java** to use main() as executable.
6. Run MainPage.java to start the software.

To run the software without using an IDE, do the following:
1. Extract **AnimalQuest.zip**
2. Under the root directory, find **AnimalQuest.jar**
3. Click on this JAR file to start the game

*Optional*: to create an executable JAR file through Intellij, do the following: 
1. Go to **File** > **Project Structure**
2. Select **Artifacts** from the left dropdown menu
3. Click on the **+** icon and select **JAR** > **From modules with dependencies**
4. Navigate the AnimalQuest directory to choose **MainPage.java** as the main class 
5. Click **OK** to save the artifact configuration
6. Go to **Build** > **Build Artifacts** > **Build** to build the JAR file
7. Once the JAR file is created, run it to start the game


