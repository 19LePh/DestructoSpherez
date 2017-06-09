public class Player
{
    private Sprite color;
    private Boosters boosters;
    private Launchers launcher;
    private Mounts mount;
    private Enhancements enhancement;
    private double mass; //Default is 10.0 kg
    private double diameter; //Default is 2 meters, or the length of a piano
    private double crossSectionalArea; //Important for air drag. For spheres, this is pi*r^2. R is in meters

    public Player()
    {
        color = null;
        mass = 10.0;
        crossSectionalArea = Math.PI * Math.pow((diameter / 2.0), 2.0);
        //Everything below could be changed if we decide to add in default/"already brought" upgrades
        boosters = null;
        launcher = null;
        mount = null;
        enhancement = null;
    }
    
        public double calculateMass()
    {
        double totalMass = this.mass;
        if(this.getBoosters() != null)
        {
            mass += this.getBoosters().getMass();
        }
        if(this.getLauncher() != null)
        {
            mass += this.getLauncher().getMass();
        }
        if(this.getMount() != null)
        {
            mass += this.getMount().getMass();
        }
        if(this.getEnhancement() != null)
        {
            mass += this.getEnhancement().getMass();
        }
        return totalMass;
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
