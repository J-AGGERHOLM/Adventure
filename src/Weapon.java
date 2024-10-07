abstract class Weapon extends Item {

    private boolean equipped = false;

    public Weapon(String itemName, boolean equipped) {
        super(itemName);
        this.equipped = equipped;

    }

    ////mehods


    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    abstract int remainingUses();



}