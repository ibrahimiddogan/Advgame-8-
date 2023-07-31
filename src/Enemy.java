public class Enemy {
    private String name;
    private int damage;
    private int health;

    public Enemy(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
