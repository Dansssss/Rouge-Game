/**
 * Form the map, put player, monsters and items.
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */

import java.util.ArrayList;

public class Map {

    // map[y][x]
    public String[][] map;
    String DOT = ".";

    private int depth;
    private int width;

    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public Map(int width, int depth) {
        this.width=width;
        this.depth=depth;
    }
    /*
     *  Output the current map
     */
    public void map() {
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    /*
     *  Search for Warp Stone in map.
     *  Same as the following
     */
    public boolean ifWarpStone() {
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                String warpStone = "@";
                if (map[i][j].equals(warpStone)) {
                    return true;
                }
            }
        }
        // if there is no warp stone, return false.
        return false;
    }

    public boolean ifHealingItem() {
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                String healingItem = "+";
                if (map[i][j].equals(healingItem)) {
                    return true;
                }
            }
        }
        // if there is no healing item, return false.
        return false;
    }

    public boolean ifDamagePerk() {
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                String damagePerk = "^";
                if (map[i][j].equals(damagePerk)) {
                    return true;
                }
            }
        }
        // if there is no damage perk, return false.
        return false;
    }

    // Both player and monster can use the same boolean
    public boolean ifInside(int x, int y) {
        //check whether the player or monster is inside the map
        return (x>=0 && x<width && y>=0 && y < depth);

    }

    /*
     *  return false if the target location is # or ~
     */
    public boolean ifTraversable(String[][]map, int x, int y){
        String currentLocation=map[y][x];
        String mountain = "#";
        String water = "~";
        return !currentLocation.equals(mountain) && !currentLocation.equals(water);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void putPlayer(int x, int y, String p) {
        map[y][x] = p;
    }

    public ArrayList<Monster> getMonsterArray(){
        return monsters;
    }

    public void setArraySize(int depth,int width){
        map = new String[depth][width];
    }

    public String[][] getMap(){
        return this.map;
    }

    public void setMap(int x, int y, String s){
        map[y][x] = s;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /*
     * Fill the map with dot
     */
    public void fillBackground(int depth,int width) {
        map = new String[depth][width];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = DOT;
            }
        }
    }

    /*
     * Put monsters into the map.
     */
    public void putAllMonster(Map map){
        ArrayList<Monster>monsters=map.getMonsterArray();
        for(Monster monster:monsters){
            monster.putAllMonster(map);
        }
    }
}

