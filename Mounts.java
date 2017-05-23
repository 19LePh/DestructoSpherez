public class Mounts extends Upgrades
{
    /*private double gliding; for gliding, change name later*/
    private double duration;
    private double speedIncrease;

    public Mounts(double mass, double cost, int tier, int path, /*double gliding*/ double duration, double speedIncrease)
    {
        super(mass, cost, tier, path);
        /*this.gliding = gliding*/
        this.duration = duration;
        this.speedIncrease = speedIncrease;
    }
    
    @Override
    public void use(double duration)
    {
        System.out.println("");
    }
}
