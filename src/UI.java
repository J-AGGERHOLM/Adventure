
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

}




