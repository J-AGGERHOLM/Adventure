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


        Room currentRoom = adventure.getCurrentRoom();                              //Access the current room


        while (!userchoice.equals("exit")) {                                        //Sentinel loop for "Exit"
            userchoice = input.nextLine();
            switch (userchoice) {                                                   //switch case that looks for strings

                case String userchoice when userchoice.contains("north"):           // checks for string "north"
                    if(adventure.getCurrentRoom().north != null) {                  // if north is not null, we go north.
                        System.out.println("You enter into the northern room...");
                        adventure.setCurrentRoom(currentRoom.north);
                    } else {
                        System.out.println("Only brick walls that way...");
                    }
                    break;

                case String userchoice when userchoice.contains("south"):
                    if(adventure.getCurrentRoom().south != null) {
                        System.out.println("You enter into the southern room...");
                        adventure.setCurrentRoom(currentRoom.south);
                    } else {
                        System.out.println("Only brick walls that way...");
                    }
                    break;

                case String userchoice when userchoice.contains("west"):
                    if(adventure.getCurrentRoom().west != null) {
                        System.out.println("You head west, into the next room...");
                        adventure.setCurrentRoom(currentRoom.west);
                    } else {
                        System.out.println("Only brick walls that way...");
                    }
                    break;

                case String userchoice when userchoice.contains("east"):
                    if(adventure.getCurrentRoom().east != null) {
                        System.out.println("You turn east, into the next room...");
                        adventure.setCurrentRoom(currentRoom.east);
                    } else {
                        System.out.println("Only brick walls that way...");
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
                    break;



                case null, default:
                    break;
            }
            currentRoom = adventure.getCurrentRoom();                                           //makes sure currentRoom is updated after loop.
        }
        return "";
    }



}
