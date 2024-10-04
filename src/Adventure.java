
import java.util.ArrayList;

public class Adventure {


    private Map map;                           // Map instance
    private Player player;                  // Player instance

    // Constructor to initialize the game components
    public Adventure() {
        this.map = new Map();                      // Initialize the map
        Room startRoom = map.getCurrentRoom();    // Get starting room
        this.player = new Player(startRoom);    // Initialize player without a name or location and empty inventory and full health
    }

    public Player getPlayer() {
        return player;
    }

    public Room getPlayerLocation() {
        return player.getLocation();
    }

    public void setPlayerLocation(Room room) {
        player.setLocation(room);
    }

    public Map getMap() {
        return map;
    }

    public String describeCurrentRoom() {
        return getPlayerLocation().getRoomDescription();
    }

    public MoveResults movePlayerNorth() {
        return player.movePlayerNorth();
    }

    public MoveResults movePlayerSouth() {
        return player.movePlayerSouth();
    }

    public MoveResults movePlayerEast() {
        return player.movePlayerEast();
    }

    public MoveResults movePlayerWest() {
        return player.movePlayerWest();
    }

    public String getLockedMessage() {
        return player.getLocation().getLockedMessage();
    }

    public String getNullMessage() {
        return player.getLocation().getRoomNullMessage();

    }

    public FoodResults getEatFood(String actionSubject){
        return player.eatFood(actionSubject);
    }

}

