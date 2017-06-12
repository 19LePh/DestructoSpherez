public class Boosters extends Upgrades
{
    private double boostPower; //Newtons
    private double boostDuration; //seconds
    private int numUses;

    //Higher tier = higher value
    
    //Multi Bottle Rocket
    //Code for creating animation
    public static final Boosters TIER_1_PATH_1 = new Boosters("Multi Bottle Rocket", 0.10, 15.0, 10.0, 0.5, 3, null);
    //Giant Bottle Rocket
    //Code for creating animation
    public static final Boosters TIER_1_PATH_2 = new Boosters("Giant Bottle Rocket", 0.15, 30.0, 60.0, 1.5, 1, null);
    //Capsule Rockets
    //Code for creating animation
    public static final Boosters TIER_2_PATH_1 = new Boosters("Capsule Rockets", 2.0, 200.0, 20.0, 0.7, 5, null);
    //Twin Rockets
    //Code for creating animation
    public static final Boosters TIER_2_PATH_2 = new Boosters("Twin Rockets", 10.0, 400.0, 100.0, 1.65, 2, null);
    //Bootlet Fireworks
    //Code for creating animation
    public static final Boosters TIER_3_PATH_1 = new Boosters("Bootleg Fireworks", 50.0, 1200.0, 25.0, 0.7, 10, null);
    //Really Big Rocket
    //Code for creating animation
    public static final Boosters TIER_3_PATH_2 = new Boosters("Really Big Rocket", 210.0, 2500.0, 175.0, 2.5, 1, null);
    //Rechargeable Boosters
    //Code for creating animation
    public static final Boosters TIER_4_PATH_1 = new Boosters("Rechargeable Boosters", 150.0, 10000.0, 40.0, 1.0, 15, null);
    //The One
    //Code for creating animation
    public static final Boosters TIER_4_PATH_2 = new Boosters("The One", 250.0, 12500.0, 7777.7, 0.2, 1, null);

    public Boosters(String name, double mass, double cost, double bp, double bd, int numUses, Animation animation)
    {
        super(name, mass, cost, animation);
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
    public void use(GameLogic logic)
    {
        System.out.println(""); //
    }
    
    @Override
    public String getSpecs()
    {
        return name.toUpperCase() + "   " + "***BOOSTERS*** Cost: $" + this.getCost() + ", Mass: " + this.getMass() + " kg, Boost Power: " + boostPower + " N," +
        " Duration: " + boostDuration + "s, Number of Uses: " + numUses;
    }
}
