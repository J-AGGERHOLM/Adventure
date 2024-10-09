import java.util.Random;

public class Enemy {

    private String name;
    private String description;
    private int health;
    private Item weapon;
    private int attackMin;
    private int attackMax;


    public Enemy(String name, String description, int health, Item weapon, int attackMin, int attackMax) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.attackMin = attackMin;
        this.attackMax = attackMax;
    }


    public int enemyAttack(int min, int max) {
        Random attack = new Random();
        return attack.nextInt(max - min) + ((Weapon) weapon).getAttackModifier();
    }

    public EnemyHealthLevel enemyAlive(Enemy enemy) {
        while (enemy.getHealth() > 0) {
            if (enemy.getHealth() > (enemy.getHealth()/2)){
                return EnemyHealthLevel.GOOD;
            }
            if ((enemy.getHealth() < (enemy.getHealth()/2))){
                return EnemyHealthLevel.BAD;
            }
        }
        return EnemyHealthLevel.DEAD;
    }


    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item getWeapon() {
        return weapon;
    }

    @Override
    public String toString(){
        return " " + getName();
    }

}
