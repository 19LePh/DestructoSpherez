import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;
public class Terrain
{
    public static final ImageView space = new ImageView(new Image("Space.png"));
    public static ImageView currImage = new ImageView(new Image("Dawn.png"));
    public final static double gravity = 9.80665;
    public static int time = 0;
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

    //For Title Screen, Launch Screen (Ground)
    public static void getTime()
    {
        switch(time)
        {
            case 0: currImage = new ImageView(new Image("Dawn.png")); time++; break;
            case 1: currImage = new ImageView(new Image("Day.png")); time++; break;
            case 2: currImage = new ImageView(new Image("Sunset.png")); time++; break;
            case 3: currImage = new ImageView(new Image("Night.png")); time = 0; break;
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

    public static ImageView getSpace()
    {
        return new ImageView(new Image("Space.png"));
    }

    public static ImageView getCurrCopy()
    {
        switch(time)
        {
            case 0: return new ImageView(new Image("Dawn.png"));
            case 1: return new ImageView(new Image("Day.png"));
            case 2: return new ImageView(new Image("Sunset.png"));
            case 3: return new ImageView(new Image("Night.png"));
        }
        System.out.println("Time check failed");
        return new ImageView(new Image("Night.png"));
    }
}
