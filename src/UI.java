
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
        System.out.println("You can travel in the following directions: north, south, east, west");
        System.out.println("You can also type 'look' to look around or 'exit' to quit the game.");
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

            // If there's only one direction, just append it
            if (directionList.size() == 1) {
                message.append(directionList.get(0));
            } else {
                // For multiple directions, append all but the last with commas
                for (int i = 0; i < directionList.size() - 1; i++) {
                    message.append(directionList.get(i)).append(", ");
                }
                // Append the last direction with "and"
                message.append("and ").append(directionList.get(directionList.size() - 1));
            }

            message.append(".");
            System.out.println(message.toString()); // Return the formatted message
        }

        System.out.println(""); // Return an empty string if there are no visible doors
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
            System.out.println(message.toString()); // Return the formatted message
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
            System.out.println("Your breath is ragged and you're covered in cuts and bruises.\n" +
                    "It's becoming hard to move... you need to tend to your injuries as soon as possible.");
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
                    actionSubject = inputParts[1].trim();  // Get the item keyword (e.g., "candle")
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
                                case POISONED -> displayMessage("The " + actionSubject + " was poisoned! \n" +
                                        "Your stomach turns and empties itself on the floor. You feel sick.");
                                case NULL -> displayMessage("There's nothing to eat...");
                            }
                        }
                        break;



                default:
                    displayMessage("Invalid option. Type 'help' for commands.");
                    break;


            }
        }
    }
}








