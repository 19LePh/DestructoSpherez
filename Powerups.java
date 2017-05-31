public abstract class Powerups
{
    private final static double spawnDuration = 5.0; //Amount of time a powerup stays on screen
    private int posX;
    private int posY;
    private Sprite sprite;

    public Powerups(int posX, int posY, Sprite sprite)
    {
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
    }

    public final boolean countdown()
    {
        double duration = spawnDuration;
        while(duration >= 0.001)
        {
            duration -= 1;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }
    
    public int[] getCoordinates()
    {
        return new int[]{posX, posY};
    }
    
    //Always returns a numerical value for things like velocity, money, etc.
    public abstract void use();
}
