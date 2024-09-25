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
        room1.setRoomDescription(
                "The air is thick with the scent of damp earth. \n" +
                        "Walls of worn stone are lined with thick patches of moss that glow faintly in the dark. \n" +
                        "A few scattered roots hang from the ceiling, whispering secrets as they sway.\n");


        room2.setRoomDescription("A solitary lantern swings from a rusted chain, casting unsteady shadows across the narrow hall. \n" +
                "Faded tapestries hang on the walls, their designs long worn away. \n" +
                "The floor creaks underfoot as if hiding a story untold.\n");

        room3.setRoomDescription("Dust clings to rows of forgotten bookshelves, each crammed with crumbling tomes. \n" +
                "A small table in the corner is lit by a weak, magical glow, illuminating a single, unmarked book left open, as if someone was just here.");

        room4.setRoomDescription("In the center of this quiet room lies a shallow pool, its water shimmering with an unnatural light. \n" +
                "The sound of a gentle drip echoes softly, as if time slows the closer you get. \n" +
                "Small carvings of mythical creatures line the walls, watching in silence.");

        room5.setRoomDescription("Your footsteps seem unnaturally loud as you step into this long corridor. \n" +
                "The walls are smooth and cold, with small, irregular indentations that catch the eye. \n" +
                "A faint, distant sound echoes through the space, like a voice calling from far away.");

        room6.setRoomDescription("In the center of this small, circular room is a pedestal, cracked and worn with age. \n" +
                "The ceiling has caved in slightly, letting in a sliver of moonlight that bathes the space in an ethereal glow. \n" +
                "The air feels thick with forgotten magic.");

        room7.setRoomDescription("Vines and leaves spill over ancient stone carvings, almost concealing the small shrine at the back of the room. \n" +
                "The faint scent of herbs and earth fills the air, and tiny flowers grow in the cracks between the stones. \n" +
                "A quiet hum seems to vibrate through the ground beneath you.");


        room8.setRoomDescription("Vines and leaves spill over ancient stone carvings, almost concealing the small shrine at the back of the room. \n" +
                "The faint scent of herbs and earth fills the air, and tiny flowers grow in the cracks between the stones. \n" +
                "A quiet hum seems to vibrate through the ground beneath you.");


        room9.setRoomDescription("Vines and leaves spill over ancient stone carvings, almost concealing the small shrine at the back of the room. \n" +
                "The faint scent of herbs and earth fills the air, and tiny flowers grow in the cracks between the stones. \n" +
                "A quiet hum seems to vibrate through the ground beneath you.");



        //Map setting
        room1.setEast(room2);
        room1.setSouth(room4);

        room2.setEast(room3);

        room3.setSouth(room6);

        room4.setSouth(room7);

        room5.setSouth(room8);

        room6.setSouth(room9);

        room7.setEast(room8);

        currentRoom=room1;
    }

// getter and setter for currentroom
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
}
