import java.util.ArrayList;

public class Player {
    private String name;
    private Room location;
    private ArrayList<Item> inventory = new ArrayList<>();


    public Player(String name, Room location, ArrayList<Item> inventory) {
        this.name = name;
        this.location = location;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public void addToInventory(Item item) {
        inventory.add(item);// Add the item to player's inventory
        System.out.println("You added " + item.getItemName() + " to your inventory.");
    }

    public void seeInventory() {
        System.out.println("------INVENTORY-----");
        for (Item i : inventory) {
            System.out.println("|   " + i.getItemName()+ "  |");
        }
        System.out.println("--------------------");
    }


    public void dropItem(Item item) {
        if (!this.inventory.isEmpty()) {
            this.inventory.remove(item);
            this.location.roomItems.add(item);
        }
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
}
