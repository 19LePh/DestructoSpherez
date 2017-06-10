public class SpeedBoost extends Powerups
{
    private double boostForce; //In newtons
    private final static Sprite sprite = null; //Will eventually be a pic of a speed boost
    
    public SpeedBoost(double boostForce)
    {
        super(0, 0, sprite);
        this.boostForce = boostForce;
    }
    
    @Override
    public void use(GameLogic logic)
    {
        double temp = boostForce;
        logic.boost(boostForce);
        while(temp >= 0.0)
        {
            logic.boost(-1 * (boostForce / 50.0)); //every .1 seconds it removes 1/50th of boostForce
            temp -= -1 * boostForce / 50.0;
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
