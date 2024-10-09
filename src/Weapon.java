abstract class Weapon extends Item {

    private boolean equipped = false;
    private int attackModifier;

    public Weapon(String itemName, boolean equipped) {
        super(itemName);
        this.equipped = equipped;
        int attackModifier = 0;

    }

    ////mehods


    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public void setAttackModifier(int modifier){
        this.attackModifier = modifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    abstract int remainingUses();



}