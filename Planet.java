public class Planet
{
    private Sprite map;
    private double gravity;
    private boolean hasAirFriction;
    //Add wind in the near future?

    public Planet(Sprite map, double gravity, boolean airFriction)
    {
        this.map = map;
        this.gravity = gravity;
        this.hasAirFriction = airFriction;
    }
}
