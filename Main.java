public class Main
{
    private Terrain terrain;
    private Player player = new Player();
    private Wall[] walls;
    private static double initialSpeed;
    private static double currentSpeed;
    private double playerMass; //Unsure of units
    
    //Player must choose what planet they want to be on with a button
    public void init(Planet world)
    {
        walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()}; //Needs Update
        terrain = new Terrain(world, 10, walls);
        this.calculateMass();
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
    
    public void calculateMass()
    {
        double mass = 20.0; //This is the mass of the player without upgrades
        mass += player.getBoosters().getMass() + player.getMount().getMass() + player.getEnhancement().getMass();
    }
}
