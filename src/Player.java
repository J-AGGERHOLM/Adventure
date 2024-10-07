
import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    private String name;
    private Room location;
    private ArrayList<Item> inventory;
    private int health;
    private ArrayList<Weapon> equipment;


    public Player(Room location) {
        this.location = location;
        this.inventory = new ArrayList<Item>();
        this.health = 100;
        this.equipment = new ArrayList<Weapon>();
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
        System.out.println("------EQUIPMENT-----");
        for (Item i : equipment) {
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

    public boolean takeItem(String input) {
        boolean itemFound = false;

        for (int i = 0; i < location.getRoomItems().size(); i++) {
            Item item = location.getRoomItems().get(i);

            // Check if the item name contains the input (case-insensitive match)
            if (item.getItemName().toLowerCase().contains(input.toLowerCase())) {
                location.getRoomItems().remove(i);  // remove the item from the room
                itemFound = true;
            }
        }
        return itemFound;
    }


    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public int getHealth() {
        return health;
    }


    public EquipResults equipWeapon(String actionSubject) {
        boolean itemFound = false;
        Iterator<Item> inventoryIterator = inventory.iterator();  ///creating an iterator to avoid concurrent modification error

        while (inventoryIterator.hasNext()) {
            Item i = inventoryIterator.next();

            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                itemFound = true;
                if (i instanceof Weapon) {
                    ((Weapon) i).setEquipped(true);
                    inventoryIterator.remove(); // Safely remove the item during iteration
                    equipment.add((Weapon) i);

                } else {
                    return EquipResults.NOT_WEAPON;
                }
            }

        }
        if (itemFound) {
            return EquipResults.FOUND;
        } else {
            return EquipResults.NOT_FOUND;
        }
    }


    public FoodResults eatFood(String actionSubject) {

        boolean itemFound = false;

        // Check the player's inventory first
        for (Item i : inventory) {
            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                itemFound = true;
                if (i instanceof Food) {
                    Food foodItem = (Food) i;

                    // Handle tastiness and health adjustment
                    int howTasty = foodItem.getTastiness();
                    if (howTasty >= 10) {
                        this.health += 50;
                    } else if (howTasty >= 5) {
                        this.health += 25;
                    } else if (howTasty >= 2) {
                        this.health += 10;
                    }

                    // If the food is poisoned
                    if (foodItem.isPoisoned()) {
                        this.health -= 30;  // Apply health penalty for poisoned food
                        inventory.remove(i); // Remove the item from inventory
                        return FoodResults.POISONED;
                    }

                    inventory.remove(i); // Remove the food after eating
                    return FoodResults.EDIBLE;

                } else {
                    return FoodResults.INEDIBLE; // Item is not food
                }
            }
        }

        // Check the room for the item if not found in inventory
        for (Item i : location.getRoomItems()) {
            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                itemFound = true;
                if (i instanceof Food) {
                    Food foodItem = (Food) i;

                    // Handle tastiness and health adjustment
                    int howTasty = foodItem.getTastiness();
                    if (howTasty >= 10) {
                        this.health += 50;
                    } else if (howTasty >= 5) {
                        this.health += 25;
                    } else if (howTasty >= 2) {
                        this.health += 10;
                    }

                    // If the food is poisoned
                    if (foodItem.isPoisoned()) {
                        this.health -= 30;  // Apply health penalty for poisoned food
                        location.getRoomItems().remove(i); // Remove the item from room
                        return FoodResults.POISONED;
                    }

                    location.getRoomItems().remove(i); // Remove the food after eating
                    return FoodResults.EDIBLE;

                } else {
                    return FoodResults.INEDIBLE; // Item is not food
                }
            }
        }

        // If the item is not found in inventory or room
        if (!itemFound) {
            return FoodResults.NULL; // No item found
        }

        return FoodResults.NULL; // Default return in case of unexpected situation
    }


    public Room getLocation() {
        return location;
    }


    public AttackResults attack() {
        boolean hasMeleeWeapon = false;
        boolean hasRangedWeapon = false;
        boolean hasAmmo = false;

        for (Weapon weapon : equipment) {
            if (weapon instanceof MeleeWeapon) {
                hasMeleeWeapon = true;
            }
            if (weapon instanceof RangedWeapons) {
                hasRangedWeapon = true;
                if (weapon.remainingUses() > 0) {
                    hasAmmo = true;
                }

            }
        }

        if (hasMeleeWeapon) {
            return AttackResults.MELEE;
        } else if (hasRangedWeapon && hasAmmo) {
            return AttackResults.RANGED;
        } else if (hasRangedWeapon && !hasAmmo) {
            return AttackResults.NO_AMMO;
        } else {
            return AttackResults.UNARMED;
        }
    }


///Move Logic

    public MoveResults movePlayerNorth() {
        if (location.getNorth() != null) {                           // if north is not null, we go north.
            if (!location.getNorth().isLocked()) {
                location.setLeftRoom(true);                         //marks the room as a room that has already been in.
                setLocation(location.getNorth());
                return MoveResults.SUCCESS;

            } else {
                return MoveResults.LOCKED;
            }
        } else {
            return MoveResults.NULL;
        }

    }

    public MoveResults movePlayerSouth() {
        if (location.getSouth() != null) {                           // if north is not null, we go north.
            if (!location.getSouth().isLocked()) {
                location.setLeftRoom(true);                         //marks the room as a room that has already been in.
                setLocation(location.getSouth());
                return MoveResults.SUCCESS;

            } else {
                return MoveResults.LOCKED;
            }
        } else {
            return MoveResults.NULL;
        }

    }

    public MoveResults movePlayerEast() {
        if (location.getEast() != null) {                           // if north is not null, we go north.
            if (!location.getEast().isLocked()) {
                location.setLeftRoom(true);                         //marks the room as a room that has already been in.
                setLocation(location.getEast());
                return MoveResults.SUCCESS;

            } else {
                return MoveResults.LOCKED;
            }
        } else {
            return MoveResults.NULL;
        }

    }

    public MoveResults movePlayerWest() {
        if (location.getWest() != null) {                           // if north is not null, we go north.
            if (!location.getWest().isLocked()) {
                location.setLeftRoom(true);
                setLocation(location.getWest());
                return MoveResults.SUCCESS;

            } else {
                return MoveResults.LOCKED;
            }


        } else {
            return MoveResults.NULL;

        }


    }
}

