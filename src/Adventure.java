public class Adventure {


    private Map map;                           // Map instance
    private Room currentRoom;                  // Current Room
    private Player newPlayer;                  // Player instance
    private UI ui;                             // UI instance

    // Constructor to initialize the game components
    public Adventure(UI ui) {
        this.map = new Map();                      // Initialize the map
        this.currentRoom = map.getCurrentRoom();    // Get starting room
        this.newPlayer = new Player(null, null);    // Initialize player without a name or location
        this.ui = ui;                               // Initialize UI
    }

    public void startGame() {

        ui.titleScreen();                           //gets titleScreen

        newPlayer.setName(ui.getPlayerName());
        ui.displayMessage(ui.getWelcome() + newPlayer.getName() + "!");
        ui.displayHelp();                                                   // Display available commands


        //game loop:
        String userchoice = "";
        while (!userchoice.equals("exit")) {                                             //Sentinel loop for "Exit"
            userchoice = ui.getPlayerInput();
            map.updateDescription(map.getCurrentRoom());


            //checks user input for a match
            if (userchoice.contains("north")) {
                userchoice = "north";
            }
            if (userchoice.contains("south")) {
                userchoice = "south";
            }
            if (userchoice.contains("east")) {
                userchoice = "east";
            }
            if (userchoice.contains("east")) {
                userchoice = "east";
            }




            switch (userchoice) {                                                        //switch case that looks for strings


                case "north":                                                           // checks for string "north"
                    if (map.getCurrentRoom().north != null) {                           // if north is not null, we go north.
                        if (!map.getCurrentRoom().north.locked) {                       // checks if room's locked attribute is true.
                            map.getCurrentRoom().leftRoom = true;                       //marks the room as a room that has already been in.
                            ui.displayMessage("You enter into the northern room...");
                            map.setCurrentRoom(currentRoom.north);

                        } else {
                            ui.displayMessage(map.getCurrentRoom().lockedMessage);          //if locked is true, then we get this message.
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().roomNullMessage);          //if north is null, returns this message.
                    }
                    break;

                case "south":
                    if (map.getCurrentRoom().south != null) {
                        if (!map.getCurrentRoom().south.locked) {
                            map.getCurrentRoom().leftRoom = true;
                            ui.displayMessage("You enter into the southern room...");
                            map.setCurrentRoom(currentRoom.south);
                        } else {
                            ui.displayMessage(map.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().roomNullMessage);
                    }
                    break;

                case "west":
                    if (map.getCurrentRoom().west != null) {
                        if (!map.getCurrentRoom().west.locked) {
                            map.getCurrentRoom().leftRoom = true;
                            ui.displayMessage("You head west, into the next room...");
                            map.setCurrentRoom(currentRoom.west);
                        } else {
                            System.out.println(map.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        System.out.println(map.getCurrentRoom().roomNullMessage);
                    }
                    break;

                case "east":
                    if (map.getCurrentRoom().east != null) {
                        if (!map.getCurrentRoom().east.locked) {
                            map.getCurrentRoom().leftRoom = true;
                            ui.displayMessage("You turn east, into the next room...");
                            map.setCurrentRoom(currentRoom.east);
                        } else {
                            ui.displayMessage(map.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        ui.displayMessage(map.getCurrentRoom().roomNullMessage);

                    }
                    break;

                case "help":                                                                      //case help prints out the input options.
                    ui.displayHelp();
                    break;

                case "look":                                                                      //gets the room description from the currentRoom object.
                    ui.displayMessage(newPlayer.getName() + " is looking around...");
                    ui.displayMessage(map.getCurrentRoom().getRoomDescription());
                    ui.getDoorOptions(map.getCurrentRoom());                                        //gets door options.

                    break;


                default:
                    ui.displayMessage("Invalid option. Type 'help' for commands.");
                    break;
            }
            currentRoom = map.getCurrentRoom();
            newPlayer.setLocation(map.getCurrentRoom());                                          //makes sure currentRoom is updated after loop.
        }
    }
}

