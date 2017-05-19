import java.util.ArrayList;
public class Terrain
{
    // instance variables
    private Planet planet;
    private ArrayList<Powerups> powerups;
    private Wall[] walls;

    /**
     * Constructor for objects of class Terrain
     */
    public Terrain(Planet a, int numPowerups)
    {
        planet = a;
        walls = null;
        //Below will add random Powerups to the powerups list
        for(int i = 0; i < numPowerups; i++)
        {
            powerups.add(null);
        }
        
    }
}
