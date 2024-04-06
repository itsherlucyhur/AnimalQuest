
import java.util.Objects;
import javax.swing.JButton;

/**
 * This class represents the coordinates and buttons of a grid cell 
 * Provides methods to access and manipulate the coordinates 
 * @author Vismaya Theertha
 *
 */
public class GridCoordinates {
    private  int x;
    private int y;
    private int numVal;
    private JButton button;

    /**
     * Constructor for the GridCoordinates 
     * @param x The x-coordinate of the grid cell 
     * @param y The y-coordinate of the grid cell 
     * @param numVal The value associated with the grid cell 
     * @param button The JButton associated with the grid cell 
     */
    public GridCoordinates(int x, int y, int numVal, JButton button) {
        this.x = x;
        this.y = y;
        this.numVal = numVal;
        this.button = button;
    }

    /**
     * Returns the x coordinate of the grid cell 
     * @return x coordinate 
     */
    public int getX() {
        return x;
    }

    /**
     * returns the y-coordinate of the grid cell 
     * @return y coordinate 
     */
    public int getY() {
        return y;
    }

    /**
     * returns the numerical value of the grid cell 
     * @return the value associated with the grid cell 
     */
    public int getNumVal() {
        return numVal;
    }

    /**
     * returns the button of the grid cell 
     * @return the button 
     */
    public JButton getButton() {
        return button;
    }
    
    /**
     * Indicates whether some other object is "equal to" this one. 
     * 
     * @param obj The reference object that's being compared
     *
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GridCoordinates)) return false;
        GridCoordinates other = (GridCoordinates) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns a hash code value 
     * @return A hash code value for this object. 
     */
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    /**
     * returns a string representation of the object. 
     * @return A string representation of the value. 
     */
    public String toString() {
        return "" +numVal;
    }
}
