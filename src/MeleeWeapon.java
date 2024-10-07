public class MeleeWeapon extends Weapon{


    public MeleeWeapon(String itemName, boolean equipped) {
        super(itemName, equipped);
    }

    ///methods

    @Override
    int remainingUses() {
        return 0;
    }
}
