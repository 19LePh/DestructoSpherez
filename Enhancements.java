public class Enhancements extends Upgrades
{
    private double pierceFactor; //Higher value = more likely to pierce wall

    public Enhancements(double mass, double cost, int tier, int path, double pierceFactor)
    {
        super(mass, cost, tier, path);
        this.pierceFactor = pierceFactor;
    }

    @Override
    public void use(double duration)
    {
        System.out.println("");
    }
}
