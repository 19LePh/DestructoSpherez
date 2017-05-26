
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

    public Launchers(double mass, double cost, int tier, int path, double launchAngle, double launchPower)
    {
        super(mass, cost, tier, path);
        this.launchAngle = launchAngle;
        this.launchPower = launchPower;
    }

    @Override
    public void use()
    {
        System.out.println(""); //
    }
}
