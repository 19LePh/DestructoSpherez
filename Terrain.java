import java.util.ArrayList;
public class Terrain
{
    // instance variables
    private Planet planet;
    private ArrayList<Powerups> powerups; //There can be a lot of powerups at a time or a few
    private Wall[] walls; //Always a set number of walls

    public Terrain(Planet world, int numPowerups, Wall[] walls)
    {
        planet = world;
        this.walls = walls;
        //Below will add random Powerups to the powerups list
        for(int i = 0; i < numPowerups; i++)
        {
            int random = (int)(Math.random() * 2 + 1);
            if(random == 1)
            {
                powerups.add(null); //Will add a certain type of powerup
            } else {
                powerups.add(null); //Will add another type of powerup instead
            }
        }
    }
    
    public void setPlanet(Planet newPlanet)
    {
        planet = newPlanet;
    }
    
    public boolean checkWalls()
    {
        return true; //Will eventually invoke a walls method to see if it is destroyed or not
    }
}
