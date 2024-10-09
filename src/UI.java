import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Adventure adventure;
    private Scanner input = new Scanner(System.in);           //input created


    // Gets player name
    public String getPlayerName() {
        System.out.println("Please enter your name:");
        return input.nextLine();
    }

    // Gets player input for the game
    public String getPlayerInput() {
        System.out.println("What would you like to do?");
        return input.nextLine();
    }

    // Displays message to the player
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Displays help menu
    public void displayHelp() {
        displayMessage("Here are the commands available to you::");
        displayMessage("You can 'go' or 'travel' in the directions, 'north', 'south' 'east' and 'west'");
        displayMessage("further commands include: " +
                "\n 'look' to look around. " +
                "\n 'take' no take an item. " +
                "\n 'drop' to drop an item." +
                "\n 'eat' to eat food. " +
                "\n 'equip' to equip a weapon. " +
                "\n 'see health', or 'health' to see your health. " +
                "\n 'see inventory', or 'inventory' to view your inventory or equipment." +
                "\n 'attack' to attack" +
                "\n 'exit' to exit the game.");


    }

    public void displayExit() {
        displayMessage("Thank you for playing Peril!");
    }

    public String getWelcome() {
        return "Welcome to the dungeon, ";
    }

    public String titleScreen() {
        System.out.println("""
                                "
                ╔════════════════════════════════════════════════════════════════════════════╗
                ║                                                            |               ║
                ║                      ██████╗ ███████╗██████╗ ██████╗██╗   -+-          +   ║
                ║   +     |            ██╔══██╗██╔════╝██╔══██╗  ██╔═╝██║    |               ║
                ║        -+-           ██████╔╝█████╗  ██████╔╝  ██║  ██║                    ║
                ║         |    +       ██╔═══╝ ██╔══╝  ██╔═██║   ██║  ██║         +          ║
                ║    +                 ██║     ███████╗██║  ██╗██████╗███████╗       |       ║
                ║                      ╚═╝     ╚══════╝╚═╝  ╚═╝╚═════╝╚══════╝      -+-      ║
                ║       +               +                                            |       ║
                ║                           D U N G E O N   D E L V E R     +                ║
                ╠════════════════════════════════════════════════════════════════════════════╣
                ║                                                                            ║
                ║                     Press [ENTER] to Begin Your Descent...                 ║
                ║                                                                            ║
                ╚════════════════════════════════════════════════════════════════════════════╝
                """);
        return input.nextLine();

    }

    public void getDoorOptions(Room room) {


        ArrayList<String> directionList = new ArrayList<>();

        if (room.getNorth() != null) {
            directionList.add("North");
        }
        if (room.getSouth() != null) {
            directionList.add("South");
        }
        if (room.getEast() != null) {
            directionList.add("East");
        }
        if (room.getWest() != null) {
            directionList.add("West");

        }
        // Only if the room is not dark
        if (!room.isDark() && !directionList.isEmpty()) {
            StringBuilder message = new StringBuilder("You see doors leading in the directions of ");

            // If there's only one direction, we append it
            if (directionList.size() == 1) {
                message.append(directionList.get(0));
            } else {
                // For multiple directions, append all but the last on e with commas
                for (int i = 0; i < directionList.size() - 1; i++) {
                    message.append(directionList.get(i)).append(", ");
                }
                // Append the last direction with "and"
                message.append("and ").append(directionList.get(directionList.size() - 1));
            }

            message.append(".");
            System.out.println(message.toString());
        }

        System.out.println("");
    }


    public void seeRoomItems(Room room) {
        if (!(room.getRoomItems().isEmpty()) && !room.isDark()) {
            StringBuilder message = new StringBuilder("In this room, you notice ");

            if (room.getRoomItems().size() == 1) {
                message.append(room.getRoomItems().get(0));
            }
            if (room.getRoomItems().size() > 1) {
                // For items, append all but the last with commas
                for (int i = 0; i < room.getRoomItems().size() - 1; i++) {
                    message.append(room.getRoomItems().get(i)).append(", ");
                }
                // Append the last item with "and "
                message.append("and ").append(room.getRoomItems().get(room.getRoomItems().size() - 1));
            }

            message.append(".");
            System.out.println(message.toString()); // Returns new message

        } else {

            System.out.println("");

        }
    }

    public void seeRoomEnemies(Room room) {
        if (!room.roomEnemies.isEmpty() && !room.isDark()) {
            StringBuilder message = new StringBuilder("You see a figure in the room! it's a  ");
            if (room.getEnemyList().size() == 1) {
                message.append(room.getEnemyList().get(0));
            }
            if (room.getEnemyList().size() > 1) {
                for (int i = 0; i < room.getEnemyList().size() - 1; i++) {
                    message.append(room.getEnemyList().get(i)).append(", ");
                }
                message.append("and ").append(room.getEnemyList().get(room.getEnemyList().size() - 1));

            }
            message.append(".");
            System.out.println(message.toString());
            System.out.println(room.getRoomEnemies().getDescription());
        } else {

            System.out.println("");
        }
    }

    public void seeHealth(Player player) {
        int healthPoints = player.getHealth();

        if (healthPoints >= 100) {
            System.out.println("Not a scratch!");
        } else if (healthPoints >= 75) {
            System.out.println("You're weary from the day's adventure... maybe a snack would be nice.");
        } else if (healthPoints > 50) {
            System.out.println("You've sustained minor injuries, you need to rest soon.");
        } else if (healthPoints > 25) {
            System.out.println("Your breath is ragged and you're covered in cuts and bruises.\n" + "It's becoming hard to move... you need to tend to your injuries as soon as possible.");
        } else {
            System.out.println("You're on the brink of collapse! Immediate care is necessary.");
        }
    }


    public UI(Adventure adventure) {
        this.adventure = adventure;
    }

    public void startGame() {

        titleScreen();                           //gets titleScreen

        adventure.getPlayer().setName(getPlayerName());
        displayMessage(getWelcome() + adventure.getPlayer().getName() + "!");
        displayHelp();                                                   // Display available commands


        //game loop:
        String userInput = "";
        while (!userInput.equals("exit")) {                                             //Sentinel loop for "Exit"
            userInput = getPlayerInput();
            adventure.getMap().updateDescription(adventure.getPlayerLocation());
            String actionSubject = "";


            //Input parser, checks user input for a match
            if (userInput.contains("north")) {
                userInput = "north";
            }
            if (userInput.contains("south")) {
                userInput = "south";
            }
            if (userInput.contains("west")) {
                userInput = "west";
            }
            if (userInput.contains("east")) {
                userInput = "east";
            }
            if (userInput.contains("take")) {
                // Extracting the keyword from the user input after "take"
                String[] inputParts = userInput.split(" ", 2);  // Split into "take" and item name
                if (inputParts.length > 1) {
                    actionSubject = inputParts[1].trim();  // Get the item keyword (ex. "candle")
                    userInput = "take";  // change userInput to "take" for switch-case
                }
            }
            if (userInput.contains("drop")) {
                String[] inputPart = userInput.split(" ", 2);
                if (inputPart.length > 1) {
                    actionSubject = inputPart[1].trim();
                    userInput = "drop";
                }
            }
            if (userInput.contains("health")) {
                userInput = "health";
            }
            if (userInput.contains("eat")) {
                String[] inputPart = userInput.split(" ", 2);
                if (inputPart.length > 1) {
                    actionSubject = inputPart[1].trim();
                    userInput = "eat";
                }
            }
            if (userInput.contains("equip")) {
                String[] inputPart = userInput.split(" ", 2);
                if (inputPart.length > 1) {
                    actionSubject = inputPart[1].trim();
                    userInput = "equip";
                }
            }

            MoveResults moveResults = null;
            switch (userInput) {                                                        //switch case that looks for strings
                case "north":                                                           // checks for string "north"
                    moveResults = adventure.movePlayerNorth();
                    switch (moveResults) {
                        case SUCCESS -> displayMessage("You enter into the Northern room");
                        case LOCKED -> displayMessage(adventure.getLockedMessage());
                        case NULL -> displayMessage(adventure.getNullMessage());

                    }

                    break;

                case "south":
                    moveResults = adventure.movePlayerSouth();
                    switch (moveResults) {
                        case SUCCESS -> displayMessage("You enter into the Southern room");
                        case LOCKED -> displayMessage(adventure.getLockedMessage());
                        case NULL -> displayMessage(adventure.getNullMessage());
                    }
                    break;

                case "west":
                    moveResults = adventure.movePlayerWest();
                    switch (moveResults) {
                        case SUCCESS -> displayMessage("You turn to and enter the Western room");
                        case LOCKED -> displayMessage(adventure.getLockedMessage());
                        case NULL -> displayMessage(adventure.getNullMessage());
                    }
                    break;

                case "east":
                    moveResults = adventure.movePlayerEast();
                    switch (moveResults) {
                        case SUCCESS -> displayMessage("You turn to and enter the eastern room");
                        case LOCKED -> displayMessage(adventure.getLockedMessage());
                        case NULL -> displayMessage(adventure.getNullMessage());
                    }
                    break;

                case "help":                                                                      //case help prints out the input options.
                    displayHelp();
                    break;

                case "look":
                    displayMessage(adventure.getPlayer().getName() + " is looking around...");//gets the room description from the currentRoom object.
                    displayMessage(adventure.getPlayerLocation().getRoomDescription());
                    seeRoomEnemies(adventure.getPlayerLocation());
                    seeRoomItems(adventure.getPlayerLocation());
                    getDoorOptions(adventure.getPlayerLocation()); //gets door options.

                    break;

                case "take":
                    if (actionSubject.isEmpty()) {
                        displayMessage("please specify which item you would like to take.");
                    } else {

                        for (Item i : adventure.getPlayerLocation().getRoomItems()) {
                            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                adventure.getPlayer().addToInventory(i);                                            // add the item to player's inventory
                                boolean itemTaken = adventure.getPlayer().takeItem(actionSubject);
                                // removes item from room list
                                if (itemTaken) {
                                    displayMessage("the " + actionSubject + " was removed from the room.");
                                } else {
                                    displayMessage("that item isn't in the room.");
                                }

                                break;
                            }
                        }
                    }

                    break;

                case "drop":
                    if (!adventure.getPlayer().getInventory().isEmpty()) {
                        if (actionSubject.isEmpty()) {
                            displayMessage("please specify which item you would like to drop.");
                        } else {
                            for (Item i : adventure.getPlayer().getInventory()) {
                                if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                    adventure.getPlayer().dropItem(i);
                                    displayMessage("You dropped the " + i.getItemName() + ".");
                                    break;

                                } else {
                                    System.out.println("don't have that item");
                                }
                            }
                            break;
                        }
                    }

                case "inventory":
                    adventure.getPlayer().seeInventory();
                    break;

                case "health":
                    seeHealth(adventure.getPlayer());
                    break;

                case "eat":
                    if (actionSubject.isEmpty()) {
                        displayMessage("Please specify which item you would like to eat.");
                    } else {
                        FoodResults foodResults = adventure.getEatFood(actionSubject);
                        switch (foodResults) {
                            case EDIBLE -> displayMessage("You ate the " + actionSubject + ".");
                            case INEDIBLE -> displayMessage("That's not food, you can't eat that.");
                            case POISONED ->
                                    displayMessage("The " + actionSubject + " was poisoned! \n" + "Your stomach turns and empties itself on the floor. You feel sick.");
                            case NULL -> displayMessage("There's nothing to eat...");
                        }
                    }
                    break;

                case "equip":
                    if (actionSubject.isEmpty()) {
                        displayMessage("Please specify which item you would like to equip.");
                    } else {
                        EquipResults equipResults = adventure.getEquipResults(actionSubject);
                        switch (equipResults) {
                            case FOUND -> displayMessage("You've equipped the " + actionSubject + ".");

                            case NOT_FOUND ->
                                    displayMessage("You rummage through your inventory, but find no weapon like that.");
                            case NOT_WEAPON ->
                                    displayMessage("You hold up" + actionSubject + ". \n While trying to discern it's deadliness, you realize that this won't help you at all. you put it back in your knapsack.");
                            case ALREADY_EQUIPPED ->
                                    displayMessage("You take your current weapon and stuff it in your bag, switching it out with a " + actionSubject + ".");
                        }

                    }
                    break;

                case "attack":
                    AttackResults attackResults = adventure.getAttack();

                    switch (attackResults) {
                        case MELEE -> displayMessage("You move in to attack with your weapon!");
                        case RANGED -> displayMessage("You fire off a shot with your weapon!");
                        case NO_AMMO ->
                                displayMessage("You get ready to fire off your weapon, but realize you're out of ammo!");
                        case UNARMED -> displayMessage("You throw a weak punch!");
                    }

                    Enemy thisEnemy = adventure.getPlayerLocation().getRoomEnemies();
                    // Apply damage to the enemy
                    if (!adventure.getPlayerLocation().getEnemyList().isEmpty()) {
                        if (adventure.checkEnemyAlive(thisEnemy) != EnemyHealthLevel.DEAD) {
                            int damageDealt = adventure.getPlayerAttackValue();
                            displayMessage("You deal " + damageDealt + " damage to the enemy!");

                            // Update enemy health
                            adventure.updateEnemyHealth(thisEnemy, damageDealt);

                            // Check if enemy is still alive
                            if (adventure.checkEnemyAlive(thisEnemy) == EnemyHealthLevel.DEAD) {
                                displayMessage("The enemy dies! Its weapon drops to the ground");
                                adventure.getPlayerLocation().roomEnemies.remove(thisEnemy);
                                adventure.getPlayerLocation().roomItems.add(thisEnemy.getWeapon());
                            } else {
                                // If the enemy is still alive, it retaliates
                                if (adventure.checkEnemyAlive(thisEnemy) != EnemyHealthLevel.DEAD) {
                                    int thisEnemyAttack = adventure.getEnemyAttackValue(thisEnemy);
                                    displayMessage("The enemy retaliates, attacking with their weapon, dealing " + thisEnemyAttack + " damage!");
                                    adventure.updatePlayerHealth(thisEnemyAttack);
                                } else {
                                    displayMessage("The enemy dies!");
                                    adventure.getPlayerLocation().roomEnemies.remove(thisEnemy);
                                }
                            }
                        }
                    }
                    break;
                case "exit": {
                    displayExit();
                }

                default:
                    displayMessage("Invalid option. Type 'help' for commands.");
                    break;


            }

        }

    }
}









