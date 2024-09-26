import java.util.Scanner;

public class UI {


    Scanner input = new Scanner(System.in);                                         //input created
    String userchoice = "";                                                         //userChoice initialized
    Adventure adventure = new Adventure();                                          //Adventure objekt initialized
    Player newPlayer = new Player(null);                                            //player initialized

    public String getDisplay() {
//        System.out.println("please name your character:");
//        newPlayer.setName(input.nextLine());

        System.out.println(newPlayer.getName() + "what would you like to do?");             //this is UI
        System.out.print("you can travel: \n north \n south \n east \n west \n");
        System.out.println("You can also: \n Look around");


        //this controls the behaviour to move around the rooms:

        Room currentRoom = adventure.getCurrentRoom();                                   //Access the current room


        while (!userchoice.equals("exit")) {                                             //Sentinel loop for "Exit"
            userchoice = input.nextLine();
            adventure.updateDescription(adventure.getCurrentRoom());

            switch (userchoice) {                                                        //switch case that looks for strings

                case String userchoice when userchoice.contains("north"):                // checks if room's locked attribute is true.
                    if (adventure.getCurrentRoom().north != null) {                     // checks for string "north"
                        if (!adventure.getCurrentRoom().north.locked) {                  // if north is not null, we go north.
                            adventure.getCurrentRoom().leftRoom = true;                  //marks the room as a room that has already been in.
                            System.out.println("You enter into the northern room...");
                            adventure.setCurrentRoom(currentRoom.north);

                        } else {
                            System.out.println(adventure.getCurrentRoom().lockedMessage);          //if locked is true, then we get this message.
                        }
                    } else {
                        System.out.println(adventure.getCurrentRoom().roomNullMessage);          //if north is null, returns this message.
                    }
                    break;

                case String userchoice when userchoice.contains("south"):
                    if (adventure.getCurrentRoom().south != null) {
                        if (!adventure.getCurrentRoom().south.locked) {
                            adventure.getCurrentRoom().leftRoom = true;
                            System.out.println("You enter into the southern room...");
                            adventure.setCurrentRoom(currentRoom.south);
                        } else {
                            System.out.println(adventure.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        System.out.println(adventure.getCurrentRoom().roomNullMessage);
                    }
                    break;

                case String userchoice when userchoice.contains("west"):
                    if (adventure.getCurrentRoom().west != null) {
                        if (!adventure.getCurrentRoom().west.locked) {
                            adventure.getCurrentRoom().leftRoom = true;
                            System.out.println("You head west, into the next room...");
                            adventure.setCurrentRoom(currentRoom.west);
                        } else {
                            System.out.println(adventure.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        System.out.println(adventure.getCurrentRoom().roomNullMessage);
                    }
                    break;

                case String userchoice when userchoice.contains("east"):
                    if (adventure.getCurrentRoom().east != null) {
                        if (!adventure.getCurrentRoom().east.locked) {
                            adventure.getCurrentRoom().leftRoom = true;
                            System.out.println("You turn east, into the next room...");
                            adventure.setCurrentRoom(currentRoom.east);
                        } else {
                            System.out.println(adventure.getCurrentRoom().lockedMessage);
                        }
                    } else {
                        System.out.println(adventure.getCurrentRoom().roomNullMessage);

                    }
                    break;

                case "help":                                                                      //case help prints out the input options.
                    System.out.println("Your options are:");
                    System.out.print("you can travel: \n north \n south \n east \n west \n");
                    System.out.println("You can also: \n Look around");
                    break;

                case "look":                                                                      //gets the room description from the currentRoom object.
                    System.out.println(newPlayer.getName() + " is looking around...");
                    System.out.println(adventure.getCurrentRoom().getRoomDescription());

                    String doorN = "";
                    String doorS = "";
                    String doorE = "";
                    String doorW = "";

                    if (adventure.getCurrentRoom().north != null) {
                        doorN = ", north";
                    }
                    if (adventure.getCurrentRoom().south != null) {
                        doorS = ", south";
                    }
                    if (adventure.getCurrentRoom().east != null) {
                        doorE = ", east";
                    }
                    if (adventure.getCurrentRoom().west != null) {
                        doorW = ", west";
                }
                    if (!currentRoom.dark) {
                        System.out.println("You see doors leading in the directios of" + doorN + doorS + doorE + doorW + ".");
                    }

                    break;


                case null, default:
                    break;
            }
            currentRoom = adventure.getCurrentRoom();                                           //makes sure currentRoom is updated after loop.
        }
        return "";
    }


}
