public class Food extends Item {

    private int tastiness;
    private boolean goneBad;


    public Food(String itemName, int tastiness, boolean goneBad) {
        super(itemName);
        this.tastiness=tastiness;
        this.goneBad = goneBad;

    }


    ////methods


    public void setTastiness(int tastiness) {
        this.tastiness = tastiness;
    }

    public int getTastiness() {
        return tastiness;
    }

    public boolean isGoneBad() {
        return goneBad;
    }

    public void setGoneBad(boolean goneBad) {
        this.goneBad = goneBad;
    }
}
