public class Mount extends Upgrades
{
    private double lift; //In newtons, opposes gravity. NOTE: THE NUMBERS NEED TWEAKING
    private double fuel; //Specific to Helis (Path 2) in seconds
    private double thrust; //In newtons, opposes drag

    //Umbrella
    //Code for creating animation
    public static final Mount TIER_1_PATH_1 = new Mount(0.3, 30.0, 12.0, 0.0, 0.0, null);
    //Propeller Hat
    //Code for creating animation
    public static final Mount TIER_1_PATH_2 = new Mount(0.12, 10.0, 0.0, 10.0, 2.0, null);
    //Glider
    //Code for creating animation
    public static final Mount TIER_2_PATH_1 = new Mount(0.65, 100.0, 40.0, 0.0, 0.0, null);
    //Prototype H
    //Code for creating animation
    public static final Mount TIER_2_PATH_2 = new Mount(2.0, 75.0, 10.0, 15.0, 7.0, null);
    //Super Glider
    //Code for creating animation
    public static final Mount TIER_3_PATH_1 = new Mount(1.0, 600.0, 80.0, 0.0, 0.0, null);
    //The Chopper
    //Code for creating animation
    public static final Mount TIER_3_PATH_2 = new Mount(40.0, 1500.0, 15.0, 25.0, 12.0, null);
    //One with the Wind
    //Code for creating animation
    public static final Mount TIER_4_PATH_1 = new Mount(30.0, 9000.0, 150.0, 0.0, 0.0, null);
    //Mini UFO
    //Code for creating animation
    public static final Mount TIER_4_PATH_2 = new Mount(0.0, 15000.0, 30.0, 40.0, 15.0, null);
    public Mount(double mass, double cost, double lift, double fuel, double thrust, Animation animation)
    {
        super(mass, cost, animation);
        this.lift = lift;
        this.fuel = fuel;
        this.thrust = thrust;
    }

    public double getLift()
    {
        return lift;
    }

    public double getThrust()
    {
        return thrust;
    }

    //Only Applicable to Helis (Path 2)
    @Override
    public void use(GameLogic logic)
    {
        System.out.println(""); //
    }

    @Override
    public void equip(Player player)
    {
        player.setMount(this);
        this.setIsEquipped(true);
    }

    @Override
    public String getSpecs()
    {
        return "***MOUNT*** Cost: $" + this.getCost() + ", Mass: " + this.getMass() + "kg, Lift Force: " + lift + " N," +
        " Thrust Force: " + thrust + "N, Duration: " + fuel + "s";
    }
}
