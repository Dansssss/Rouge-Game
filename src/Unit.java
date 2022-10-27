/**
 * Movement are similar for player and monster. They are all put here.
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */
public abstract class Unit extends Entity {

    private int health;
    private int maxHealth;
    private String name;

    private final String DOT = ".";

    public Unit(int x, int y, String name, int health){
        super(x, y);
        setName(name);
        setHealth(health);
        setMaxHealth(health);
    }

    public void moveEast(Map map) {
        map.setMap(getX(),getY(),DOT);
        setX(getX()+1);
    }

    public void moveWest(Map map) {
        map.setMap(getX(),getY(),DOT);
        setX(getX()-1);
    }

    public void moveNorth(Map map) {
        map.setMap(getX(),getY(),DOT);
//        int newY=getY()-1;
        setY(getY()-1);
    }

    public void moveSouth(Map map) {
        map.setMap(getX(),getY(),DOT);
        setY(getY()+1);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void putDot(Map map, int x, int y){
        String s = DOT;
        map.map[y][x] = s;
    }
}
