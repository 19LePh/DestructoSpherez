public class Player
{
    private String color;
    private double mass; //Unsure of units
    private double initialSpeed;
    private Boosters boosters;
    private Launchers launchers;
    private Mounts mounts;
    private Enhancements enhancements;

    public Player()
    {
        color = "Red";
        mass = 20.0; //Can change based on whatever units we use
        initialSpeed = 5.0; //Can change
        //Everything below could be changed if we decide to add in default/"already brought" upgrades
        boosters = null;
        launchers = null;
        mounts = null;
        enhancements = null;
    }
    
    //color must be a single word in ALL CAPS
    public void setColor(String color)
    {
        this.color = color;
    }
}
