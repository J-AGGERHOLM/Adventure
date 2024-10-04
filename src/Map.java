

public class Map {

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
    Room room10 = new Room(null);
    Room room11 = new Room(null);
    Room room12 = new Room(null);
    Room room13 = new Room(null);
    Room room14 = new Room(null);
    Room room15 = new Room(null);
    Room room16 = new Room(null);
    Room room17 = new Room(null);
    Room room18 = new Room(null);


    //setting descriptions:

    public Map() {


        //Map setting
        room1.setEast(room2);
        room1.setSouth(room4);
        room1.setNorth(room10);
        Item spoon = new Item("a rusty spoon");
        Item candle = new Item("an unlit candle");
        Food apple = new Food("a shiny apple", 5, false);
        room1.addRoomItems(spoon);
        room1.addRoomItems(candle);
        room1.addRoomItems(apple);

        room2.setEast(room3);
        room2.setDark(true);

        room3.setSouth(room6);

        room4.setSouth(room7);
        room4.setWest(room11);
        room4.setLocked(true);

        room5.setSouth(room8);

        room6.setSouth(room9);

        room7.setEast(room8);

        room8.setEast(room9);
        room8.setNorth(room5);
        room8.setDark(true);

        room11.setSouth(room12);
        room11.setWest(room13);

        room12.setSouth(room14);

        room14.setSouth(room15);

        room15.setEast(room16);
        room14.setLocked(true);

        room16.setEast(room17);

        room17.setNorth(room9);

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

        room10.setRoomDescription("The room smells faintly of wood and sawdust, and rows of shelves line the walls, filled with mundane supplies—baskets, ropes, tools, and a few dusty bottles. \n " +
                "The floorboards creak underfoot, and a single window near the ceiling lets in a narrow beam of light, illuminating motes of dust as they lazily float through the air.");

        room11.setRoomDescription("You see an unusually tall hat stand in the corner, covered in an eclectic assortment of hats. \n" +
                "There’s a top hat, a pirate’s tricorn, and even a helmet made of cheese. \n" +
                "When you glance away for a second and look back, there’s another new hat—this time, a ridiculous jester’s cap. \n" +
                "The stand sways slightly, as if it’s alive, quietly accumulating hats for reasons only it knows.\n");

        room12.setRoomDescription("At first glance, this room is unremarkable, but your eyes are drawn to an impossibly small door set into the far wall. \n " +
                "It’s no more than a foot high and ornately carved. \n " +
                "You get the sense that opening it might lead to somewhere entirely unexpected—or to nowhere at all. \n " +
                "It’s as if the door is waiting for someone much smaller, or perhaps for you to find a way to shrink.");

        room14.setRoomDescription("The floor is covered in square tiles, each with a different symbol—a sun, a tree, a moon, and more. \n " +
                "As you step on one, the tiles around it shift and change color, like some forgotten game waiting to be played. \n " +
                "Nothing else seems out of place, though you can’t shake the feeling that something will happen if you solve the pattern… whatever it may be.");

        room15.setRoomDescription("You find yourself in a large, cavern-like room, the ceiling arching high above. \n " +
                "The stone walls are rough and damp, and the distant sound of dripping water echoes softly. Stalactites hang from the ceiling, glistening in the faint light. \n " +
                "The floor is uneven, with patches of slick moss that make each step cautious and deliberate.");

        room16.setRoomDescription("The corridor is narrow and dimly lit, the walls rough and uneven. \n " +
                "A few unlit sconces are set into the stone, though they offer little warmth or light. \n " +
                "The floor is damp, and the smell of mildew fills the air. \n " +
                "A few stray cobwebs hang from the corners, swaying slightly in a draft that you can’t quite place.");

        room17.setRoomDescription("You step into what looks like an old kitchen, though everything seems long unused. \n " +
                "Pots and pans hang from the ceiling, covered in a fine layer of dust. \n " +
                "The hearth is cold, with ash scattered across the floor. \n " +
                "A wooden table stands in the center, and on it sits a bowl of dried herbs, long since forgotten. \n " +
                "The air smells faintly of stale bread and wood smoke.");

    }


    // getter and setter for currentroom
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }


    String beenThereDescription = "this room seems familiar, you've been here before.";

    public void updateDescription(Room room) {
        if (this.currentRoom.playerGotDescription() && this.currentRoom.playerLeftRoom()) {
            this.currentRoom.setRoomDescription(beenThereDescription);
        }
    }


}
