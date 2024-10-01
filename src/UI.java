import java.util.ArrayList;
import java.util.Scanner;

public class UI {


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

        if (room.north != null) {
            directionList.add("North");
        }
        if (room.south != null) {
            directionList.add("South");
        }
        if (room.east != null) {
            directionList.add("East");
        }
        if (room.west != null) {
            directionList.add("West");

        }
        // Only if the room is not dark
        if (!room.dark && !directionList.isEmpty()) {
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
        if (!(room.roomItems.isEmpty()) && !room.dark) {
            StringBuilder message = new StringBuilder("In this room, you notice ");

            if (room.roomItems.size() == 1) {
                message.append(room.roomItems.get(0));
            }
            if (room.roomItems.size() > 1) {
                // For items, append all but the last with commas
                for (int i = 0; i < room.roomItems.size() - 1; i++) {
                    message.append(room.roomItems.get(i)).append(", ");
                }
                // Append the last item with "and "
                message.append("and ").append(room.roomItems.get(room.roomItems.size() - 1));
            }

            message.append(".");
            System.out.println(message.toString()); // Return the formatted message
        } else {

            System.out.println("");

        }
    }

}
