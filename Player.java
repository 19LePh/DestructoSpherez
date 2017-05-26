public class Player
{
    private Sprite color;
    private Boosters boosters;
    private Launchers launcher;
    private Mounts mount;
    private Enhancements enhancement;

    public Player()
    {
        color = null;
        //Everything below could be changed if we decide to add in default/"already brought" upgrades
        boosters = null;
        launcher = null;
        mount = null;
        enhancement = null;
    }
    
    public void setColor(Sprite color)
    {
        this.color = color;
    }
    
    public Boosters getBoosters()
    {
        return boosters;
    }
    
    public Launchers getLauncher()
    {
        return launcher;
    }
    
    public Mounts getMount()
    {
        return mount;
    }
    
    public Enhancements getEnhancement()
    {
        return enhancement;
    }
}
