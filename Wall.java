public class Wall
{
    private double breakingPoint; //Unitless, represents the max value before wall is destroyed
    private double mass; //In kg
    private double thickness; //Relevant to pierceFactor, meters, not related to physics in any way
    private Animation animation; //Broken wall v unbroken wall
    private boolean isDestroyed;
    private double rewardMoney;

    //The Higher the Wall #, the stronger it is

    //Code for creating animation
    public static final Wall WALL_1 = new Wall(20.0, 30.0, 1.0, 50.0, null);
    //Code for creating animation
    public static final Wall WALL_2 = new Wall(100.0, 100.0, 5.0, 100.0, null);
    //Code for creating animation
    public static final Wall WALL_3 = new Wall(1000.0, 800.0, 10.0, 500.0, null);
    //Code for creating animation
    public static final Wall WALL_4 = new Wall(5000.0, 2000.0, 20.0, 1500.0, null);
    //Code for creating animation
    public static final Wall WALL_5 = new Wall(20000.0, 10000.0, 50.0, 5000.0, null);

    public Wall(double breakingPoint, double mass, double thickness, double money, Animation animation)
    {
        isDestroyed = false;
        rewardMoney = money;
        this.breakingPoint = breakingPoint;
        this.mass = mass;
        this.thickness = thickness;
    }
    
    public double getRewardMoney()
    {
        return rewardMoney;
    }

    //Checks if the player is touching the wall
    public boolean checkCollision()
    {
        return true;
    }

    public void update(GameLogic logic, Player player)
    {
        if(this.checkCollision() && !isDestroyed)
        {
            //Use formula for elastic collision below (returns m/s)
            double ballVF = (logic.get_vx()) * (logic.getPlayerMass() - this.mass) /(this.mass + logic.getPlayerMass());
            double wallVF = 2.0 * (logic.getPlayerMass() * logic.get_vx()) / (this.mass + logic.getPlayerMass());
            logic.set_vx(ballVF); //Represents the elastic collision between the ball and the wall
            
            //TotalPower combines the wall velocity and the player's pierce factor. It is compared to breakingPoint
            double totalPower = wallVF + 1000.0 * (player.getEnhancements().getPierceFactor() / thickness);
            if(totalPower >= breakingPoint)
            {
                isDestroyed = true;
                //Code that changes the wall to broken
                Shop.setBalance(rewardMoney);
            }
        }
    }
}
