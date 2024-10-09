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

    public EquipResults getEquipResults(String actionSubject){
        return player.equipWeapon(actionSubject);
    }
    public AttackResults getAttack(){
        return player.attack();
    }



    ///combat caller methods

    public int getEnemyAttackValue(Enemy enemy){
        return enemy.enemyAttack(enemy.getAttackMin(),enemy.getAttackMax());
    }

    public void updatePlayerHealth(int damage){
        player.setHealth(player.getHealth()-damage);
    }

    public void updateEnemyHealth(Enemy enemy, int damage){
        enemy.setHealth(enemy.getHealth()-damage);
    }

    public EnemyHealthLevel checkEnemyAlive(Enemy enemy){
        return enemy.enemyAlive(enemy);
    }

    public int getPlayerAttackValue(){
        return player.damageDealt();
    }

}

