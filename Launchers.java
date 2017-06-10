
/**
 * Write a description of class Launchers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Launchers extends Upgrades
{
    private double launchAngle; //In degrees
    private double launchPower; //Force in newtons
    //Typical slinghost can exert 200 newtons 

    //Slingshot
    //Code for creating animation
    public static final Launchers TIER_1_PATH_1 = new Launchers(0.0, 45.0, 200.0, null); //FREE!
    //Dynamite
    //Code for creating animation
    public static final Launchers TIER_1_PATH_2 = new Launchers(20.0, 60.0, 500.0, null);
    //Catapult
    //Code for creating animation
    public static final Launchers TIER_2_PATH_1 = new Launchers(75.0, 45.0, 300.0, null);
    //TNT Tower
    //Code for creating animation
    public static final Launchers TIER_2_PATH_2 = new Launchers(200.0, 60.0, 750.0, null);
    //Cannon
    //Code for creating animation
    public static final Launchers TIER_3_PATH_1 = new Launchers(500.0, 45.0, 900.0, null);
    //Nuke
    //Code for creating animation
    public static final Launchers TIER_3_PATH_2 = new Launchers(13500, 60.0, 4000.0, null);
    //Alien Launch Pad
    //Code for creating animation
    public static final Launchers TIER_4_PATH_1 = new Launchers(10000, 45.0, 2500.0, null);
    //????
    //Code for creating animation
    public static final Launchers TIER_4_PATH_2 = new Launchers(30000, 60.0, 10000,  null);
    public Launchers(double cost, double launchAngle, double launchPower, Animation animation)
    {
        super(0.0, cost, animation); //Mass of launchers have no impact on the game
        this.launchAngle = launchAngle;
        this.launchPower = launchPower;
    }

    public double getAngle()
    {
        return launchAngle;
    }

    public double getPower()
    {
        return launchPower;
    }

    @Override
    public void use()
    {
        System.out.println(""); //
    }
}
