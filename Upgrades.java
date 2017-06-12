public abstract class Upgrades
{
    private double mass; //kg
    private boolean isPurchased;
    private boolean isEquipped;
    private double cost;
    private boolean isUsable; //For boosters and helis, which run out of fuel after a while
    private Animation animation;
    protected String name;

    public Upgrades(String name, double mass, double cost, Animation animation)
    {
        this.mass = mass;
        isPurchased = false;
        isEquipped = false;
        this.cost = cost;
        isUsable = true;
        this.animation = animation;
        this.name = name;
    }
    
    public boolean getIsEquipped()
    {
        return isEquipped;
    }
    
    public void setIsPurchased(boolean brought)
    {
        isPurchased = brought;
    }
    
    public boolean getIsPurchased()
    {
        return isPurchased;
    }
    
    public boolean setIsEquipped(Player player)
    {
        isEquipped = player.equip(this);
        return isEquipped;
    }
    
    public boolean getisUsable()
    {
        return isUsable;
    }
    
    public double getCost()
    {
        return cost;
    }
    
    public abstract void use(GameLogic logic);
    
    public abstract String getSpecs();
    
    public double getMass()
    {
        return mass;
    }
}
