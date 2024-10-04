public class Food extends Item {

    private int tastiness;
    private boolean poisoned;


    public Food(String itemName, int tastiness, boolean poisoned) {
        super(itemName);
        this.tastiness=tastiness;
        this.poisoned = poisoned;

    }


    ////methods


    public void setTastiness(int tastiness) {
        this.tastiness = tastiness;
    }

    public int getTastiness() {
        return tastiness;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }
}
