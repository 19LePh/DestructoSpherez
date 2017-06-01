public class SpeedBoost extends Powerups
{
    private double accelIncrease; //Acceleration that will be added to the player's
    private final static Sprite sprite = null; //Will eventually be a pic of a speed boost
    
    public SpeedBoost(double accelIncrease)
    {
        super(0, 0, sprite);
        this.accelIncrease = accelIncrease;
    }
    
    //Only use for the player
    @Override
    public void use()
    {
        double initial = GameLogic.getAccelX();
        GameLogic.increaseAccelX(accelIncrease);
        while((initial + accelIncrease >= initial))
        {
            GameLogic.increaseAccelX(-1 * (accelIncrease / 50.0)); //May need to be adjusted
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
