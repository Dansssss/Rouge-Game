/**
 * Store attributes for monster.
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */
public class Monster extends Unit {
    private String name;    //Monster's name
    private final String DOT = ".";

    private int maxHealth;  //The maximum of health
    private int health; //Health the monster holds
    private int damage; //Damage the monster deals

    private int monPreviousX;
    private int monPreviousY;

    final static int ZERO = 0;
    final static int ONE = 1;
    final static int TWO = 2;

    Monster() {
        this(ZERO,ZERO,"0-inCaseSomeoneNameLikeThis", ZERO, ZERO);
    }

    Monster(int x, int y, String name, int health, int damage) {
        super(x,y,name,health);

        int[]monsterPreviousPosition = new int[TWO];
        monsterPreviousPosition[ZERO]=x;
        monsterPreviousPosition[ONE]=y;
        setMonPreviousX(x);
        setMonPreviousY(y);

        setHealth(health);
        setMaxHealth(health);
        //setName(name);
        this.damage=damage;
    }

    /*
     * Calculate player's health after the attack by monster
     */
    public void monsterAttackPlayer(Player player) {
        int healthBeforeAttack = player.getMaxHealth();
        int healthAfterAttack = healthBeforeAttack - getDamage();

        player.setHealth(healthAfterAttack);
    }

    /*
     * Put monster into the map
     */
    public void putMonster(Map map) {
        String m = String.valueOf(getName().toLowerCase().charAt(ZERO));
        map.setMap(getX(),getY(),m);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMonPreviousX() {
        return monPreviousX;
    }

    public void setMonPreviousX(int monPreviousX) {
        this.monPreviousX = monPreviousX;
    }

    public int getMonPreviousY() {
        return monPreviousY;
    }

    public void setMonPreviousY(int monPreviousY) {
        this.monPreviousY = monPreviousY;
    }

    public void setPrevPosX(int posX){
        this.monPreviousX=posX;
    }
    public void setPrevPosY(int posY){
        this.monPreviousY=posY;
    }
    public int getPrevPosX(){
        return this.monPreviousX;
    }
    public int getPrevPosY(){
        return this.monPreviousY;
    }

    /*
     * Put dot into the map after monster leaves.
     */
    public void putAllMonster(Map map){
        map.setMap(getPrevPosX(),getPrevPosY(),DOT);
        putMonster(map);
    }

    /*
     * The way monster moves.
     * Monster moves to East or West rather than North or South.
     */
    public void monsterMovement(Player player,Map map){
        int xDiff=getX()-player.getX();
        int yDiff=getY()-player.getY();
        setPrevPosX(getX());
        setPrevPosY(getY());
        if(xDiff>=ZERO){
            if (xDiff==ZERO){
                if(yDiff>=ZERO){
                    setY(getY()-ONE);
                    if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                        setY(getY()+ONE);
                    }
                } else {
                    setY(getY()+ONE);
                    if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                        setY(getY()-ONE);
                    }
                }
            } else{
                setX(getX()-ONE);
                if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                    setX(getX()+ONE);

                    setPrevPosX(getX());
                    setPrevPosY(getY());
                    if(yDiff>=ZERO){
                        setY(getY()-ONE);
                        if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                            setY(getY()+ONE);
                        }
                    } else {
                        setY(getY()+ONE);
                        if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                            setY(getY()-ONE);
                        }
                    }
                }
            }
        } else {
            setX(getX()+ONE);
            if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                setX(getX()-ONE);
                setPrevPosX(getX());
                setPrevPosY(getY());
                if(yDiff>=ZERO){
                    setY(getY()-ONE);
                    if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                        setY(getY()+ONE);
                    }
                } else {
                    setY(getY()+ONE);
                    if(!(map.ifInside(getX(),getY()) && map.ifTraversable(map.getMap(),getX(),getY()))){
                        setY(getY()-ONE);
                    }
                }
            }
        }
    }
}