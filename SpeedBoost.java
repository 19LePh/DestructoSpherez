public class SpeedBoost extends Powerups
{
    private double speedIncrease; //Speed that will be added to the player's
    private final static Sprite sprite = null; //Will eventually be a pic of a speed boost
    
    public SpeedBoost(int posX, int posY, double speedIncrease)
    {
        super(posX, posY, sprite);
        this.speedIncrease = speedIncrease;
    }
    
    //Only use for the player
    @Override
    public void use()
    {
        double initial = Main.getCurrentSpeed();
        Main.setCurrentSpeed(speedIncrease);
        while((initial + speedIncrease >= initial))
        {
            Main.setCurrentSpeed(-1 * (speedIncrease / 50.0)); //May need to be adjusted
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
