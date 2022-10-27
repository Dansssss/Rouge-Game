/**
 * Player attributes and movements are stored in this class
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */
public class Player extends Unit {

    private String name;    //Player's name
    private int maxHealth;  //The maximum of health
    private int damage; //Damage the player deals
    private int health; //Health the player holds
    private int level; //Player's level

    private int x;
    private int y;

    final static int INITIAL_LEVEL = 1;
    final static int TIMES = 3;
    final static int INITIAL_HEALTH = 17;


    Player(){this(0,0,"0-inCaseSomeoneNameLikeThis",0,0);}

    Player(int x, int y, String name, int health, int damage){
        super(x,y,name,INITIAL_HEALTH + INITIAL_LEVEL*TIMES );

        this.maxHealth = INITIAL_HEALTH + INITIAL_LEVEL * TIMES;    // max health = 17 + level * 3
        this.health = maxHealth;
        this.damage = 1 + INITIAL_LEVEL; // damage = 1 + level

        setLevel(INITIAL_LEVEL);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setDamage() {
        this.damage = getLevel() + 1;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setDamage();
        int newMaxHealth = INITIAL_HEALTH + getLevel() * TIMES;
        setMaxHealth(newMaxHealth);
        setHealth(newMaxHealth);
    }

    /*
     * Calculate monster's health after the attack by player
     */
    public void playerAttackMonster(Monster monster) {
        int healthBeforeAttack = monster.getHealth();
        int healthAfterAttack = healthBeforeAttack - this.damage;

        monster.setHealth(healthAfterAttack);
    }

    public void putPlayer(Map map) {
        String p = String.valueOf(getName().toUpperCase().charAt(0));
        map.putPlayer(getX(),getY(),p);

    }


}
