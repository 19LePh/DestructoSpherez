public abstract class Upgrades
{
    private double mass; //kg
    private boolean isPurchased;
    private boolean isEquipped;
    private double cost;
    private boolean isUsable; //For boosters and helis, which run out of fuel after a while
    private Animation animation;

    public Upgrades(double mass, double cost, Animation animation)
    {
        this.mass = mass;
        isPurchased = false;
        isEquipped = false;
        this.cost = cost;
        isUsable = true;
        this.animation = animation;
    }
    
    public boolean getisUsable()
    {
        return isUsable;
    }
    
    public abstract void use(GameLogic logic);
    
    public double getMass()
    {
        return mass;
    }
}
