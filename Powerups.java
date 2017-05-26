public abstract class Powerups
{
    private final double spawnDuration = 5.0; //Amount of time a powerup stays on screen
    private boolean isSpawned;

    public Powerups()
    {
        isSpawned = false;
    }

    public void countdown()
    {
        double duration = spawnDuration;
        while(duration >= 0.001)
        {
            duration -= 0.1;
            //Add a delay time here
        }
        isSpawned = false;
    }
    
    //Will later be able to spawn a powerup at a certain location on screen
    public void spawn()
    {
        isSpawned = true;
        countdown();
    }
}
