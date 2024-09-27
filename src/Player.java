public class Player {
    private String name;
    private Room location;

    public Player(String name, Room location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Room location) {
        this.location = location;
    }
}
