
import java.util.ArrayList;

public class Player {
    private String name;
    private Room location;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int health;


    public Player(String name, Room location, ArrayList<Item> inventory, int health) {
        this.name = name;
        this.location = location;
        this.inventory = inventory;
        this.health = health;
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
            System.out.println("|   " + i.getItemName() + "  |");
        }
        System.out.println("--------------------");
    }


    public void dropItem(Item item) {
        if (!this.inventory.isEmpty()) {
            this.inventory.remove(item);
            this.location.getRoomItems().add(item);
        }
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public int getHealth() {
        return health;
    }


    public void eatFood(Food food) {
        int howTasty = food.getTastiness();

        if (howTasty >= 10) {
            this.health += 50;
        }
        else if (howTasty >= 5) {
            this.health += 25;
        }
        else if (howTasty >= 2) {
            this.health += 10;
        }
    }


}
