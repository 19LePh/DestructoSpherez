public class Player
{
    private Sprite color;
    private Boosters boosters;
    private Launchers launcher;
    private Mount mount;
    private Enhancements enhancements;
    private double mass; //Default is 30.0 kg; an average human is 70 kg
    private final double crossSectionalArea = Math.PI * Math.pow(1.0, 2.0); //Important for air drag. For spheres, this is pi*r^2. Radius is 1.0 by default

    public Player()
    {
        color = null;
        mass = 30.0;
        //Everything below could be changed if we decide to add in default/"already brought" upgrades
        boosters = null;
        launcher = null;
        mount = null;
        enhancements = null;
    }

    public double calculateMass()
    {
        double totalMass = this.mass;
        if(this.getBoosters() != null)
        {
            mass += this.getBoosters().getMass();
        }
        if(this.getMount() != null)
        {
            mass += this.getMount().getMass();
        }
        if(this.getEnhancements() != null)
        {
            mass += this.getEnhancements().getMass();
        }
        return totalMass;
    }

    //below is bad practice
    public boolean equip(Upgrades u)
    {
        if(!u.getIsPurchased())
        {
            return false;
        }
        if(u instanceof Boosters)
        {
            boosters = (Boosters)u;
            return true;
        }
        if(u instanceof Launchers)
        {
            launcher = (Launchers)u;
            return true;
        }
        if(u instanceof Mount)
        {
            mount = (Mount)u;
            return true;
        }
        if(u instanceof Enhancements)
        {
            enhancements = (Enhancements)u;
            return true;
        }
        return false;
    }

    //below is bad practice
    public boolean checkIsEquipped(Upgrades u)
    {
        if(!u.getIsPurchased())
        {
            return false;
        }
        if(u instanceof Boosters)
        {
            if(boosters != (Boosters)u)
            {
                return false;
            }
        }
        if(u instanceof Launchers)
        {
            if(launcher != (Launchers)u)
            {
                return false;
            }
        }
        if(u instanceof Mount)
        {
            if(mount != (Mount)u)
            {
                return false;
            }
        }
        if(u instanceof Enhancements)
        {
            if(enhancements != (Enhancements)u)
            {
                return false;
            }
        }
        return true;
    }

    public double getCrossSectionalArea()
    {
        return crossSectionalArea;
    }

    public void setBoosters(Boosters b)
    {
        boosters = b;
    }

    public void setEnhancements(Enhancements e)
    {
        enhancements = e;
    }

    public void setLauncher(Launchers l)
    {
        launcher = l;
    }

    public void setMount(Mount m)
    {
        mount = m;
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

    public Mount getMount()
    {
        return mount;
    }

    public Enhancements getEnhancements()
    {
        return enhancements;
    }
}
