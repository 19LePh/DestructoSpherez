public class MoneyRing extends Powerups
{
    private double cashBonus;
    private final static Sprite sprite = null; //Will eventually be a pic of a golden ring

    public MoneyRing(double cashBonus)
    {
        super(0, 0, sprite);
        this.cashBonus = cashBonus;
    }
    
    //Only use for shop
    @Override
    public void use(GameLogic logic)
    {
        Shop.setBalance(this.cashBonus);
    }
}
