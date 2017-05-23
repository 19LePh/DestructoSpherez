public class Launchers extends Upgrades
{
    private double launchAngle;
    private double launchPower; //Also known as initial velocity?

    public Launchers(double mass, double cost, int tier, int path, double launchAngle, double launchPower)
    {
        super(mass, cost, tier, path);
        this.launchAngle = launchAngle;
        this.launchPower = launchPower;
    }

    @Override
    public void use(double duration)
    {
        System.out.println("");
    }
}
