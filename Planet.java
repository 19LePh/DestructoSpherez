public class Planet
{
    private Sprite map;
    private double gravity;
    private boolean hasAirDrag;
    //Add wind in the near future?

    public Planet(Sprite map, double gravity, boolean airDrag)
    {
        this.map = map;
        this.gravity = gravity;
        this.hasAirDrag = airDrag;
    }
    
    public boolean getHasAirDrag()
    {
        return hasAirDrag;
    }
}
