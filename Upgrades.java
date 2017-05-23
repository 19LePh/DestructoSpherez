public abstract class Upgrades
{
    private double mass;
    private boolean isPurchased;
    private boolean isEquipped;
    private double cost;
    private int tier; //1 is the lowest, 4 is the highest
    private int path; //Either path 1 or 2 
    private boolean isUsable; //For boosters and helis, which run out of fuel after a while

    public Upgrades(double mass, double cost, int tier, int path)
    {
        this.mass = mass;
        isPurchased = false;
        isEquipped = false;
        this.cost = cost;
        this.tier = tier;
        this.path = path;
        isUsable = true;
    }
    
    public boolean getisUsable()
    {
        return isUsable;
    }
}
