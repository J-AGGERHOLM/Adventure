public class Adventure {


    private Room currentRoom;
    Room room1 = new Room(null);
    Room room2 = new Room(null);
    Room room3 = new Room(null);
    Room room4 = new Room(null);
    Room room5 = new Room(null);
    Room room6 = new Room(null);
    Room room7 = new Room(null);
    Room room8 = new Room(null);
    Room room9 = new Room(null);


    //setting descriptions:

    public Adventure() {


        //Map setting
        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setEast(room3);
        room2.dark = true;

        room3.setSouth(room6);

        room4.setSouth(room7);
        room4.locked=true;

        room5.setSouth(room8);

        room6.setSouth(room9);

        room7.setEast(room8);

        room8.setEast(room9);
        room8.setNorth(room5);

        currentRoom = room1;



        room1.setRoomDescription("The air is thick with the scent of damp earth. \n" +
                            "Walls of worn stone are lined with thick patches of moss that glow faintly in the dark. \n" +
                            "A few scattered roots hang from the ceiling, whispering secrets as they sway.\n");

        room2.setRoomDescription("A solitary lantern swings from a rusted chain, casting unsteady shadows across the narrow hall. \n" +
                    "Faded tapestries hang on the walls, their designs long worn away. \n" +
                    "The floor creaks underfoot as if hiding a story untold.\n");

        room3.setRoomDescription("Dust clings to rows of forgotten bookshelves, each crammed with crumbling tomes. \n" +
                "A small table in the corner is lit by a weak, magical glow, illuminating a single, unmarked book left open, as if someone was just here.");

        room4.setRoomDescription("You step into a room that’s somehow warmer than the others, filled with the faint smell of cheese and… fresh laundry? \n " +
                "Small, mismatched furniture fills the space, though it’s all clearly sized for creatures far smaller than humans. \n" +
                "In the corner, a tiny chandelier—made from what looks like bent spoons and beads—casts a gentle glow over a small, bustling rat family. \n" +
                "As you stand there, you get the distinct feeling that this is no ordinary rat family, but they don’t seem bothered by your presence. \n" +
                "In fact, one of the rats pulls out a fourth tiny chair, as if inviting you to stay for a cup of tea… \n" +
                "if you can find a way to shrink down to their size, that is.");

        room5.setRoomDescription("Your footsteps seem unnaturally loud as you step into this long corridor. \n" +
                "The walls are smooth and cold, with small, irregular indentations that catch the eye. \n" +
                "A faint, distant sound echoes through the space, like a voice calling from far away.");

        room6.setRoomDescription("In the center of this small, circular room is a pedestal, cracked and worn with age. \n" +
                "The ceiling has caved in slightly, letting in a sliver of moonlight that bathes the space in an ethereal glow. \n" +
                "The air feels thick with forgotten magic.");

        room7.setRoomDescription("Mirrors line the walls, but none of them reflect you or the room. \n" +
                "Instead, they show other strange places—some filled with odd creatures, others with peaceful glades. \n " +
                "Every now and then, you catch a glimpse of a figure in the distance of the reflections, waving as though they know you're watching.\n");


        room8.setRoomDescription("Shelves are haphazardly mounted on the ceiling, filled with jars of pickled... something. \n " +
                "Oddly enough, gravity seems to have forgotten this room, as the floor remains suspiciously free of any falling items. \n" +
                "A faint hum fills the air, and you can’t help but feel like the room is watching you back.\n ");


        room9.setRoomDescription("Vines and leaves spill over ancient stone carvings, almost concealing the small shrine at the back of the room. \n" +
                "The faint scent of herbs and earth fills the air, and tiny flowers grow in the cracks between the stones. \n" +
                "A quiet hum seems to vibrate through the ground beneath you. \n");



    }



    // getter and setter for currentroom
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }


    String beenThereDescription = "this room seems familiar, you've been here before.";

    public void updateDescription(Room room){
        if (this.currentRoom.gotDescription == true && this.currentRoom.leftRoom == true) {
            this.currentRoom.setRoomDescription(beenThereDescription);
        }
    }


}
