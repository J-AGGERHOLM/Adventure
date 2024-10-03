
import java.util.ArrayList;

public class Room {
    private String roomDescription;
    private boolean gotDescription = false;
    private boolean leftRoom = false;
    private Room north;
    private Room south;
    private Room west;
    private Room east;
    private Room currentRoom;
    private boolean locked = false;
    private String lockedMessage = "This door is locked!";
    private boolean dark = false;
    private String roomNullMessage = "Only brick walls this way...";
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

    public boolean playerGotDescription(){
        return this.gotDescription;
    }

    public void setNorth(Room north) {
        this.north = north;
        north.south = this;

    }

    public boolean playerLeftRoom() {
        return leftRoom;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String getLockedMessage() {
        return lockedMessage;
    }

    public String getRoomNullMessage() {
        return roomNullMessage;
    }

    ///direction setters

    public void setLeftRoom(boolean leftRoom) {
        this.leftRoom = leftRoom;
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


    ////direction getters


    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public Room getEast() {
        return east;
    }

    public String writeRoomItems() {

        for (Item i : roomItems) {
            return i.toString();
        }
        return "";
    }

    public ArrayList<Item> getRoomItems(){
        return roomItems;
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
