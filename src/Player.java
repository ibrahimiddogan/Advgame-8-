public class Player {
    private int health;
    private int food;
    private int firewood;
    private int water;
    private int money;
    private int weapon;
    private int armor;

    public Player() {
        this.health = 100;
        this.food = 0;
        this.firewood = 0;
        this.water = 0;
        this.money = 0;
        this.weapon = 0;
        this.armor = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFirewood() {
        return firewood;
    }

    public void setFirewood(int firewood) {
        this.firewood = firewood;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean hasWon() {
        return food > 0 && firewood > 0 && water > 0;
    }
}
