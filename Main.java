//The purpose of this class is to set up a launch
public class Main
{
    public static long initialSpeed, currentSpeed, playerAngle, playerMass;
    
    //Precondition: Player must choose what planet they want to be on with a button
    //This begins the launch
    public void init(Planet world, Player player)
    {
        calculateMass(player);
        //Code that calculates initial speed based on mass and launcher's power
    }
    
    //Could be expoited if public, but crucial to the SpeedBoost class
    public static void setCurrentSpeed(double amount)
    {
        currentSpeed += amount;
    }
    
    public static double getCurrentSpeed()
    {
        return currentSpeed;
    }
    
    public void calculateMass(Player player)
    {
        double mass = 20.0; //This is the mass of the player without upgrades
        mass += player.getBoosters().getMass() + player.getMount().getMass() + player.getEnhancement().getMass();
    }
}
