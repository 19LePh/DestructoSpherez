import java.util.ArrayList;
import javafx.scene.image.Image;
public class Terrain
{
    // instance variables
    private Image background;
    public final static double gravity = 9.80665;
    private ArrayList<Powerups> powerups; //There can be a lot of powerups at a time or a few
    private Wall[] walls; //Always a set number of walls

    public Terrain(int numPowerups, Wall[] walls)
    {
        this.walls = walls;
        //Below will add random Powerups to the powerups list
        for(int i = 0; i < numPowerups; i++)
        {
            int random = (int)(Math.random() * 2 + 1);
            if(random == 1)
            {
                powerups.add(new MoneyRing(500)); //Value not finalized
            } else {
                powerups.add(new SpeedBoost(20)); //Value not finalized
            }
        }
    }
    
    //Below: do we even need this method?
    public boolean checkWalls()
    {
        return true; //Will eventually invoke a walls method to see if it is destroyed or not
    }
}
