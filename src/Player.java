
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

    public void setHealth(int health) {
        this.health = health;
    }

    ///inventory

    public void seeInventory() {
        // Finds  the longest item name in both inventory and equipment
        int maxLength = 0;
        for (Item i : inventory) {
            if (i.getItemName().length() > maxLength) {
                maxLength = i.getItemName().length();
            }
        }
        for (Item i : equipment) {
            if (i.getItemName().length() > maxLength) {
                maxLength = i.getItemName().length();
            }
        }

///add space for padding
        int paddingSize = 3;
        int totalLength = maxLength + (paddingSize * 2); // Total length for each line
        String horizontalLine = "═".repeat(totalLength + 6); // number accounts for the side ║ and padding

        // inventory header with aligned top ╔═...═╗
        System.out.println("╔" + horizontalLine + "╗");
        String paddedInventoryTitle = padItemName("---INVENTORY---", totalLength);
        System.out.println("║   " + paddedInventoryTitle + "   ║");

        // Prints each item in the inventory with padded item names
        for (Item i : inventory) {
            String paddedItemName = padItemName(i.getItemName(), totalLength);
            System.out.println("║   " + paddedItemName + "   ║");
        }

        // Print the equipment header with aligned middle ╠═...═╣
        System.out.println("╠" + horizontalLine + "╣");
        String paddedEquipmentTitle = padItemName("---EQUIPMENT---", totalLength);
        System.out.println("║   " + paddedEquipmentTitle + "   ║");

        // Print each item in the equipment with padded item names
        for (Item i : equipment) {
            String paddedItemName = padItemName(i.getItemName(), totalLength);
            System.out.println("║   " + paddedItemName + "   ║");
        }

        // Print the aligned bottom ╚═...═╝
        System.out.println("╚" + horizontalLine + "╝");
    }

    // Helper method to pad item names with spaces
    private String padItemName(String itemName, int totalLength) {
        int paddingSize = (totalLength - itemName.length()) / 2;
        String paddedItemName = " ".repeat(paddingSize) + itemName + " ".repeat(totalLength - itemName.length() - paddingSize);
        return paddedItemName;
    }



    ///metjos to add and remove from ItemList
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
        Weapon newWeapon = null;
        boolean alreadyEquipped = false;


        for (Item i : new ArrayList<>(inventory)) {
            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                itemFound = true;


                if (i instanceof Weapon) {
                    newWeapon = (Weapon) i;


                    if (!equipment.isEmpty()) {// checks for equipped weapon, move it to the inventory
                        alreadyEquipped = true;
                        // Move the currently equipped weapon back to inventory
                        Weapon currentlyEquipped = equipment.get(0);
                        currentlyEquipped.setEquipped(false); // Un-equips the currently equipped weapon
                        inventory.add(currentlyEquipped); // Add it back to the inventory
                        equipment.remove(0); // Remove it from the equipped list
                    }

                    // Equipping new weapon
                    newWeapon.setEquipped(true);
                    inventory.remove(i); // Remove the item from inventory directly
                    equipment.add(newWeapon); // Add the new weapon to the equipment list
                    return alreadyEquipped ? EquipResults.ALREADY_EQUIPPED : EquipResults.FOUND;
                } else {
                    return EquipResults.NOT_WEAPON; // if item is not a weapon
                }
            }
        }

        return itemFound ? EquipResults.FOUND : EquipResults.NOT_FOUND;
    }


    public FoodResults eatFood(String actionSubject) {

        boolean itemFound = false;

        // Check the player's inventory first
        for (Item i : inventory) {
            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                itemFound = true;
                if (i instanceof Food) {
                    Food foodItem = (Food) i;


                    //check how tasty an item is, and restores HP depending on it.
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

    Weapon currentWeapon;

    public AttackResults attack() {
        boolean hasMeleeWeapon = false;
        boolean hasRangedWeapon = false;
        boolean hasAmmo = false;


        for (Weapon weapon : equipment) {
            if (weapon instanceof MeleeWeapon) {
                hasMeleeWeapon = true;
                currentWeapon = weapon;

            }
            if (weapon instanceof RangedWeapons) {
                hasRangedWeapon = true;
                if (weapon.remainingUses() > 0) {
                    hasAmmo = true;
                    currentWeapon = weapon;
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

    public int damageDealt() {
        Random attack = new Random();
        int weaponModifier = 0;
        if (!equipment.isEmpty()) {
            weaponModifier = currentWeapon.getAttackModifier();
        }

        if (currentWeapon instanceof RangedWeapons) {
            // Generate random damage between 1 and 6 for ranged weapons
            return attack.nextInt(6) + 1 + weaponModifier;
        } else if (currentWeapon instanceof MeleeWeapon) {
            // Generate random damage between 1 and 8 for melee weapons
            return attack.nextInt(8) + 1 + weaponModifier;
        } else {
            // if no weapon we only get this value:
            return 1;
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
        if (location.getWest() != null) {
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

