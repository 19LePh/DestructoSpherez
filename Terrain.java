import java.util.ArrayList;
import javafx.scene.image.Image;
public class Terrain
{
    public static Image background = new Image("Dawn.png");
    public static final Image space = new Image("Space.png");
    public static int time = 1; //Numbers 1 - 4, 1 is Dawn, 2 is day, 3 is dusk, 4 is night
    public final static double gravity = 9.80665;
    private ArrayList<Powerups> powerups; //There can be a lot of powerups at a time or a few
    private Wall[] walls;
    
    public Terrain(ArrayList<Powerups> powerups, Wall[] walls)
    {
        this.walls = walls;
        this.powerups = powerups;
    }

    //Below: do we even need this method?
    public boolean checkWalls()
    {
        return true; //Will eventually invoke a walls method to see if it is destroyed or not
    }

    public static void updateBackground()
    {
        if(time == 1)
        {
            time ++;
            background = new Image("Day.png");
        } else if(time == 2)
        {
            time ++;
            background = new Image("Sunset.png");
        } else if(time == 3)
        {
            time ++;
            background = new Image("Night.png");
        } else if(time == 4)
        {
            time = 1;
            background = new Image("Dawn.png");
        }
    }
    
    //Randomly adds "amount" amount of powerups to the list
    public void randomPowerups(int amount)
    {
        powerups.clear();
        for(int i = 0; i < amount; i++)
        {
            int random = (int)(Math.random() + 1);
            if(random == 1)
            {
                powerups.add(new MoneyRing(500)); //Value not finalized
            } else {
                powerups.add(new SpeedBoost(20)); //Value not finalized
            }
        }
    }
}
