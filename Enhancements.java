public class Enhancements extends Upgrades
{
    private double pierceFactor; //Higher value = more likely to pierce wall. Unitless

    //Fireball
    //Code for creating animation
    public static final Enhancements TIER_1_PATH_1 = new Enhancements("Fireball", 0.0, 5.0, 2.0, null);
    //Iron Spikeball
    //Code for creating animation
    public static final Enhancements TIER_1_PATH_2 = new Enhancements("Iron Spikeball", 1.0, 20.0, 5.0, null);
    //Hot Lead
    //Code for creating animation
    public static final Enhancements TIER_2_PATH_1 = new Enhancements("Hot Lead", 0.5, 40.0, 20.0, null);
    //Steel Spikeball
    //Code for creating animation
    public static final Enhancements TIER_2_PATH_2 = new Enhancements("Steel Spikeball", 20.0, 80, 10.0, null);
    //Radioactive!
    //Code for creating animation
    public static final Enhancements TIER_3_PATH_1 = new Enhancements("Radioactive", 0.75, 400.0, 40.0, null);
    //Titanium Spikeball
    //Code for creating animation
    public static final Enhancements TIER_3_PATH_2 = new Enhancements("Titanium Spikeball", 250.0, 450.0, 15.0, null);
    //The Sun
    //Code for creating animation
    public static final Enhancements TIER_4_PATH_1 = new Enhancements("The Sun", 420.0, 17500.0, 80.0, null);
    //Shield of Gods
    //Code for creating animation
    public static final Enhancements TIER_4_PATH_2 = new Enhancements("Shield of Gods", 300.0, 12500.0, 120.0, null);

    public Enhancements(String name, double mass, double cost, double pierceFactor, Animation animation)
    {
        super(name, mass, cost, animation);
        this.pierceFactor = pierceFactor;
    }

    public double getPierceFactor()
    {
        return pierceFactor;
    }

    @Override
    public void use(GameLogic logic)
    {
        System.out.println(""); //
    }

    @Override
    public String getSpecs()
    {
        return name.toUpperCase() + "   " + "***ENHANCEMENTS*** Cost: $" + this.getCost() + ", Mass: " + this.getMass() + "kg, Pierce Factor: " +
        pierceFactor;
    }
}
