public class RangedWeapons extends Weapon{
private int ammoCount;

    public RangedWeapons(String itemName,int ammoCount, boolean equipped) {
        super(itemName, equipped);
        this.ammoCount = ammoCount;
    }

    @Override
    public int remainingUses() {
        return ammoCount;
    }
}
