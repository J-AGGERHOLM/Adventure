import java.util.ArrayList;

public class Room {
    String roomDescription;
    boolean gotDescription = false;
    boolean leftRoom = false;
    Room north;
    Room south;
    Room west;
    Room east;
    Room currentRoom;
    boolean locked = false;
    String lockedMessage = "This door is locked!";
    boolean dark = false;
    String roomNullMessage = "Only brick walls this way...";
    ArrayList<Item> roomItems = new ArrayList<Item>();


    public Room(String roomDescription) {
        this.roomDescription = roomDescription;

    }

    public Room(String roomDescription, boolean gotDescription, boolean leftRoom) {
        this.roomDescription = roomDescription;
        this.gotDescription = gotDescription;
        this.leftRoom = leftRoom;

    }


    public Room(String roomDescription, boolean gotDescription, boolean leftRoom, boolean locked) {
        this.roomDescription = roomDescription;
        this.gotDescription = gotDescription;
        this.leftRoom = leftRoom;
        this.locked = locked;

    }

    public Room(String roomDescription, boolean gotDescription, boolean leftRoom, boolean locked, boolean dark) {
        this.roomDescription = roomDescription;
        this.gotDescription = gotDescription;
        this.leftRoom = leftRoom;
        this.locked = locked;

    }

    public Room(String roomDescription, boolean gotDescription, boolean leftRoom, boolean locked, boolean dark, ArrayList<Item> roomItems) {
        this.roomDescription = roomDescription;
        this.gotDescription = gotDescription;
        this.leftRoom = leftRoom;
        this.locked = locked;
        this.roomItems = roomItems;

    }


    public String getRoomDescription() {
        this.gotDescription = true;
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        if (this.dark == true) {
            this.roomDescription = "this room is dark...";
        } else {
            this.roomDescription = roomDescription;
        }
    }

    public void setNorth(Room north) {
        this.north = north;
        north.south = this;

    }

    public void setEast(Room east) {
        this.east = east;
        east.west = this;
    }

    public void setWest(Room west) {
        this.west = west;
        west.east = this;
    }

    public void setSouth(Room south) {
        this.south = south;
        south.north = this;
    }

    public void setLocked(Room room) {
        this.locked = true;
    }


    public String getRoomItems() {

        for (Item i : roomItems) {
            return i.toString();
        }
        return "";
    }

    public void addRoomItems(Item item) {
        roomItems.add(item);
    }


    public void takeItem(String input, ArrayList<Item> roomItems) {
        boolean itemFound = false;

        for (int i = 0; i < roomItems.size(); i++) {
            Item item = roomItems.get(i);

            // Check if the item name contains the input (case-insensitive match)
            if (item.getItemName().toLowerCase().contains(input.toLowerCase())) {
                roomItems.remove(i);  // remove the item from the room
                itemFound = true;
                break;  // Exit the loop after removing the item
            }
        }

        if (!itemFound) {
            System.out.println("Can't find that item!");
        }
    }
}