public class Main
{
    private Terrain terrain;
    private Player player = new Player();
    private Wall[] walls;
    private static double initialSpeed;
    private static double currentSpeed;

    public void init(Planet world)
    {
        walls = new Wall[]{new Wall(), new Wall(), new Wall(), new Wall()}; //Needs Update
        terrain = new Terrain(world, 10, walls);
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
}
