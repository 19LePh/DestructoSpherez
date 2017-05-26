public class Player
{
    private Sprite color;
    private double mass; //Unsure of units
    private Boosters boosters;
    private Launchers launchers;
    private Mounts mounts;
    private Enhancements enhancements;

    public Player()
    {
        color = null;
        mass = 20.0; //Can change based on whatever units we use
        //Everything below could be changed if we decide to add in default/"already brought" upgrades
        boosters = null;
        launchers = null;
        mounts = null;
        enhancements = null;
    }
    
    public void setColor(Sprite color)
    {
        this.color = color;
    }
}
