public class MoneyRing extends Powerups
{
    private double cashBonus;
    private final static Sprite sprite = null; //Will eventually be a pic of a golden ring

    public MoneyRing(int posX, int posY, double cashBonus)
    {
        super(posX, posY, sprite);
        this.cashBonus = cashBonus;
    }
    
    //Only use for shop
    @Override
    public void use()
    {
        Shop.setBalance(this.cashBonus);
    }
}
