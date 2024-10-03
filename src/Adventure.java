
import java.util.ArrayList;

public class Adventure {


    private Map map;                           // Map instance
    private Room currentRoom;                  // Current Room
    private Player newPlayer;                  // Player instance
    private UI ui;                             // UI instance

    // Constructor to initialize the game components
    public Adventure(UI ui) {
        this.map = new Map();                      // Initialize the map
        this.currentRoom = map.getCurrentRoom();    // Get starting room
        this.newPlayer = new Player(null, null, new ArrayList<Item>(), 100);    // Initialize player without a name or location and empty inventory and full health
        this.ui = ui;                               // Initialize UI
    }

    public void startGame() {

        ui.titleScreen();                           //gets titleScreen

        newPlayer.setName(ui.getPlayerName());
        ui.displayMessage(ui.getWelcome() + newPlayer.getName() + "!");
        ui.displayHelp();                                                   // Display available commands


        //game loop:
        String userInput = "";
        while (!userInput.equals("exit")) {                                             //Sentinel loop for "Exit"
            userInput = ui.getPlayerInput();
            map.updateDescription(map.getCurrentRoom());
            String actionSubject = "";


            //checks user input for a match
            if (userInput.contains("north")) {
                userInput = "north";
            }
            if (userInput.contains("south")) {
                userInput = "south";
            }
            if (userInput.contains("east")) {
                userInput = "east";
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


            switch (userInput) {                                                        //switch case that looks for strings


                case "north":                                                           // checks for string "north"
                    if (map.getCurrentRoom().getNorth() != null) {                           // if north is not null, we go north.
                        if (!map.getCurrentRoom().getNorth().isLocked()) {                       // checks if room's locked attribute is true.
                            map.getCurrentRoom().setLeftRoom(true);                       //marks the room as a room that has already been in.
                            ui.displayMessage("You enter into the northern room...");
                            map.setCurrentRoom(currentRoom.getNorth());

                        } else {
                            ui.displayMessage(map.getCurrentRoom().getLockedMessage());          //if locked is true, then we get this message.
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().getRoomNullMessage());          //if north is null, returns this message.
                    }
                    break;

                case "south":
                    if (map.getCurrentRoom().getSouth() != null) {
                        if (!map.getCurrentRoom().getSouth().isLocked()) {
                            map.getCurrentRoom().setLeftRoom(true);
                            ui.displayMessage("You enter into the southern room...");
                            map.setCurrentRoom(currentRoom.getSouth());
                        } else {
                            ui.displayMessage(map.getCurrentRoom().getLockedMessage());
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().getRoomNullMessage());
                    }
                    break;

                case "west":
                    if (map.getCurrentRoom().getWest() != null) {
                        if (!map.getCurrentRoom().getWest().isLocked()) {
                            map.getCurrentRoom().setLeftRoom(true);
                            ui.displayMessage("You head west, into the next room...");
                            map.setCurrentRoom(currentRoom.getWest());
                        } else {
                            System.out.println(map.getCurrentRoom().getLockedMessage());
                        }
                    } else {
                        System.out.println(map.getCurrentRoom().getRoomNullMessage());
                    }
                    break;

                case "east":
                    if (map.getCurrentRoom().getEast() != null) {
                        if (!map.getCurrentRoom().getEast().isLocked()) {
                            map.getCurrentRoom().setLeftRoom(true);
                            ui.displayMessage("You turn east, into the next room...");
                            map.setCurrentRoom(currentRoom.getEast());
                        } else {
                            ui.displayMessage(map.getCurrentRoom().getLockedMessage());
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().getRoomNullMessage());

                    }
                    break;

                case "help":                                                                      //case help prints out the input options.
                    ui.displayHelp();
                    break;

                case "look":
                    ui.displayMessage(newPlayer.getName() + " is looking around...");//gets the room description from the currentRoom object.
                    ui.displayMessage(map.getCurrentRoom().getRoomDescription());
                    ui.seeRoomItems(map.getCurrentRoom());
                    ui.getDoorOptions(map.getCurrentRoom()); //gets door options.

                    break;

                case "take":
                    if (actionSubject.isEmpty()) {
                        ui.displayMessage("please specify which item you would like to take.");
                    } else {

                        for (Item i : map.getCurrentRoom().getRoomItems()) {
                            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                newPlayer.addToInventory(i);                                            // add the item to player's inventory
                                map.getCurrentRoom().takeItem(actionSubject, map.getCurrentRoom().getRoomItems());    // removes item from room list
                                break;
                            }
                        }
                    }

                    break;

                case "drop":
                    if (!newPlayer.getInventory().isEmpty()) {
                        if (actionSubject.isEmpty()) {
                            ui.displayMessage("please specify which item you would like to drop.");
                        } else {
                            for (Item i : newPlayer.getInventory()) {
                                if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                    newPlayer.dropItem(i);
                                    ui.displayMessage("You dropped the " + i.getItemName() + ".");
                                    break;

                                } else {
                                    System.out.println("don't have that item");
                                }
                            }
                            break;
                        }
                    }

                case "inventory":
                    newPlayer.seeInventory();
                    break;

                case "health":
                    ui.seeHealth(newPlayer);
                    break;

                case "eat":
                    if (actionSubject.isEmpty()) {
                        ui.displayMessage("Please specify which item you would like to eat.");
                    } else {
                        boolean itemFound = false;

                        // Check the player's inventory first
                        for (Item i : newPlayer.getInventory()) {
                            if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                itemFound = true;
                                if (i instanceof Food) {
                                    newPlayer.eatFood((Food) i);
                                    ui.displayMessage("You ate the " + i.getItemName() + ".");
                                    newPlayer.getInventory().remove(i);
                                } else {
                                    ui.displayMessage("This item is not food, you can't eat it.");
                                }
                                break;  // Exit the loop after the item is found and handled
                            }
                        }

                        // If the item wasn't found in the inventory, check the current room's items
                        if (!itemFound) {
                            for (Item i : map.getCurrentRoom().getRoomItems()) {
                                if (i.getItemName().toLowerCase().contains(actionSubject.toLowerCase())) {
                                    itemFound = true;
                                    if (i instanceof Food) {
                                        newPlayer.eatFood((Food) i);
                                        ui.displayMessage("You ate the " + i.getItemName() + ".");
                                        map.getCurrentRoom().getRoomItems().remove(i);
                                    } else {
                                        ui.displayMessage("This item is not food, you can't eat it.");
                                    }
                                    break;  // Exit the loop after the item is found and handled
                                }
                            }
                        }

                        // If the item was not found in both inventory and room
                        if (!itemFound) {
                            ui.displayMessage("You don't have that item, and it's not in the room.");
                        }
                    }
                    break;

                default:
                    ui.displayMessage("Invalid option. Type 'help' for commands.");
                    break;


            }
        }
        currentRoom = map.getCurrentRoom();
        newPlayer.setLocation(map.getCurrentRoom());
    }
}

