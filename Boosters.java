public class Boosters extends Upgrades
{
    private double boostPower;
    private double boostDuration;
    private int numUses;

    public Boosters(double mass, double cost, int tier, int path, double bp, double bd, int numUses)
    {
        super(mass, cost, tier, path);
        boostPower = bp;
        boostDuration = bd;
        this.numUses = numUses;
    }

    public double getBoostPower()
    {
        return boostPower;
    }
    
    public double getboostDuration()
    {
        return boostDuration;
    }
    
    public int getnumUses()
    {
        return numUses;
    }
    
    @Override
    public void use()
    {
        System.out.println(""); //
    }
}
