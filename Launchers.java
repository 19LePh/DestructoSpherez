
/**
 * Write a description of class Launchers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Launchers extends Upgrades
{
    private double launchAngle;
    private double launchPower;

    public Launchers(double cost, int tier, int path, double launchAngle, double launchPower)
    {
        super(0.0, cost, tier, path); //Mass of launchers have no impact on the game
        this.launchAngle = launchAngle;
        this.launchPower = launchPower;
    }

    @Override
    public void use()
    {
        System.out.println(""); //
    }
}
