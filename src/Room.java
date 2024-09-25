public class Room {
    String roomDescription;
    Room north;
    Room south;
    Room west;
    Room east;
    Room currentRoom;


    public Room(String roomDescription) {
        this.roomDescription = roomDescription;

    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void setNorth(Room north) {
        this.north = north;
        north.south = this;

    }
    public void setEast(Room east){
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


}
