/**
 * Movement of player is put in this class
 * Battle round is here as well
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */

import java.util.ArrayList;

public class World {

    final static int ONE_STEP = 1;

    final static int ZERO = 0;
    final static int ONE = 1;
    final static int TWO = 2;

    /*
     *  Use "wsad" command to move the player, this is only used in Rogue 1
     */
    public void movement(String move, Player player, Monster monster, Map map) {

        if (move.length()==1 && "wsad".contains(move)) {

            if (move.equalsIgnoreCase("w")) {
                player.moveNorth(map);

                if (map.ifInside(player.getX(),player.getY())) {
                    player.putPlayer(map);
                    if ( !ifTheyMeet(player, monster)) {
                        map.map();
                    }
                } else {
                    /*
                     *  if the movement is invalid (i.e. outside the map & meet the monster)
                     *  the position should restore
                     *  same below
                     */
                    player.moveSouth(map);
                    player.putPlayer(map);
                    map.map();
                }
            }

            if (move.equalsIgnoreCase("s")) {
                player.moveSouth(map);

                if (map.ifInside(player.getX(),player.getY())) {
                    player.putPlayer(map);
                    if ( !ifTheyMeet(player, monster)) {
                        map.map();
                    }
                } else {
                    player.moveNorth(map);
                    player.putPlayer(map);
                    map.map();
                }
            }

            if (move.equalsIgnoreCase("a")) {
                player.moveWest(map);

                if (map.ifInside(player.getX(),player.getY())){
                    player.putPlayer(map);
                    if ( !ifTheyMeet(player, monster)) {
                        map.map();
                    }
                } else {
                    player.moveEast(map);
                    player.putPlayer(map);
                    map.map();
                }
            }

            if (move.equalsIgnoreCase("d")) {
                player.moveEast(map);

                if (map.ifInside(player.getX(),player.getY())) {
                    player.putPlayer(map);
                    if( !ifTheyMeet(player, monster)) {
                        map.map();
                    }
                } else {
                    player.moveWest(map);
                    player.putPlayer(map);
                    map.map();
                }
            }
        }
    }
    /*
     *  Since there are more requirements like items in Assignment 2,
     *  player does not share the same movement function.
     *  But they will work in similar ways.
     */
    public void movementFile(String move, Player player, Monster monster, Map map, GameEngine gameEngine){

        if (move.length()==1 && "wsad".contains(move)) {
            if (move.equalsIgnoreCase("w")) {
                String startStatement = checkStatement(map);
                int[]plyPreviousPos=getPlayerPrevPos(player);
                player.setY(player.getY()-ONE_STEP);
                if(map.ifInside(player.getX(),player.getY()) &&
                        map.ifTraversable(map.getMap(),player.getX(),player.getY())) {
                    player.putDot(map,plyPreviousPos[ZERO],plyPreviousPos[ONE]);

                    map.putAllMonster(map);
                    player.putPlayer(map);
                    String endStatement = checkStatement(map);
                    ifBattle(player,monster,map,gameEngine);
                    checkPrint(startStatement, endStatement);
                    if(player.getHealth()>=0){
                        map.map();
                    }
                    allMonstersCheckTwo(map,player);
                } else {
                    player.setY(player.getY()+ONE_STEP);

                    map.putAllMonster(map);
                    player.putPlayer(map);
                    map.map();
                }
            }
            else if (move.equalsIgnoreCase("s")) {
                String startStatement = checkStatement(map);
                int[]plyPreviousPos=getPlayerPrevPos(player);
                player.setY(player.getY()+ONE_STEP);
                if(map.ifInside(player.getX(),player.getY()) &&
                        map.ifTraversable(map.getMap(),player.getX(),player.getY())) {
                    player.putDot(map,plyPreviousPos[0],plyPreviousPos[ONE_STEP]);

                    map.putAllMonster(map);
                    player.putPlayer(map);
                    String endStatement = checkStatement(map);
                    ifBattle(player,monster,map,gameEngine);
                    checkPrint(startStatement, endStatement);
                    if(player.getHealth()>=0){
                        map.map();
                    }
                    allMonstersCheckTwo(map,player);
                } else {
                    player.setY(player.getY()-ONE_STEP);
                    map.putAllMonster(map);
                    player.putPlayer(map);
                    map.map();
                }
            }

            else if (move.equalsIgnoreCase("a")) {
                String startStatement = checkStatement(map);
                int[]plyPreviousPos=getPlayerPrevPos(player);
                player.setX(player.getX()-ONE_STEP);
                if(map.ifInside(player.getX(),player.getY()) &&
                        map.ifTraversable(map.getMap(),player.getX(),player.getY())) {
                    player.putDot(map,plyPreviousPos[ZERO],plyPreviousPos[ONE]);
                    map.putAllMonster(map);
                    player.putPlayer(map);
                    String endStatement = checkStatement(map);
                    ifBattle(player,monster,map,gameEngine);
                    checkPrint(startStatement, endStatement);
                    if(player.getHealth()>=0){
                        map.map();
                    }
                    allMonstersCheckTwo(map,player);
                } else {
                    player.setX(player.getX()+ONE_STEP);
                    map.putAllMonster(map);
                    player.putPlayer(map);
                    map.map();
                }
            }

            else if (move.equalsIgnoreCase("d")) {
                String startStatement = checkStatement(map);
                int[]plyPreviousPos=getPlayerPrevPos(player);
                player.setX(player.getX()+ONE_STEP);
                if(map.ifInside(player.getX(),player.getY()) &&
                        map.ifTraversable(map.getMap(),player.getX(),player.getY())) {
                    player.putDot(map,plyPreviousPos[ZERO],plyPreviousPos[ONE]);
                    map.putAllMonster(map);
                    player.putPlayer(map);
                    String endStatement = checkStatement(map);
                    ifBattle(player,monster,map,gameEngine);
                    checkPrint(startStatement, endStatement);
                    if(player.getHealth()>=0){
                        map.map();
                    }
                    allMonstersCheckTwo(map,player);
                } else {
                    player.setX(player.getX()-ONE_STEP);
                    map.putAllMonster(map);
                    player.putPlayer(map);
                    map.map();
                }
            } else {
                map.putAllMonster(map);
                allMonstersCheckTwo(map,player);
                player.putPlayer(map);
                map.map();
            }
        } else {
            int[]plyPreviousPos=getPlayerPrevPos(player);
            if(map.ifInside(player.getX(),player.getY()) &&
                    map.ifTraversable(map.getMap(),player.getX(),player.getY())) {
                map.putAllMonster(map);
                player.putPlayer(map);
                ifBattle(player, monster, map, gameEngine);
                if(player.getHealth()>=0){
                    map.map();
                }
                allMonstersCheckTwo(map, player);
            }
        }
    }
    /*
     * This is used to get item statements at the beginning and at the end.
     * detect if ^ or + is picked by player
     */
    public String checkStatement(Map map){
        boolean withHD = map.ifHealingItem() && map.ifDamagePerk();
        boolean withH = map.ifHealingItem() && !map.ifDamagePerk();
        boolean withD = !map.ifHealingItem() && map.ifDamagePerk();
        boolean withNothing = !map.ifHealingItem() && !map.ifDamagePerk();

        String statement = null;
        if(withHD) statement = "HD";
        else if(withH) statement = "H";
        else if(withD) statement = "D";
        else if(withNothing) statement = "N";

        return statement;
    }
    /*
     * If ^ or + is picked by player,
     * print relevant effect
     */
    public void checkPrint(String startStatement, String endStatement){

        if(startStatement.equals("HD")) {
            if (endStatement.equals("D")) System.out.println("Healed!");
            if (endStatement.equals("H")) System.out.println("Attack up!");
        }
        if(startStatement.equals("D")) {
            if (endStatement.equals("N")) System.out.println("Attack up!");
        }
        if(startStatement.equals("H")) {
            if (endStatement.equals("N")) System.out.println("Healed!");
        }

    }

    /*
     *  For every monster, check if they are in two units with player.
     *  if so, monster moves.
     */
    public void allMonstersCheckTwo(Map map,Player player){
        int TWOMORE = 2;
        int TWOLESS = -2;
        int ZERO = 0;
        ArrayList<Monster>monsters=map.getMonsterArray();
        for(Monster monster:monsters){

            int xDifference = monster.getX()-player.getX();
            int yDifference = monster.getY()-player.getY();

            boolean xDiff = (xDifference>=TWOLESS && xDifference<=ZERO) || (xDifference<=TWOMORE && xDifference>=ZERO);
            boolean yDiff = (yDifference>=TWOLESS && yDifference<=ZERO) || (yDifference<=TWOMORE && yDifference>=ZERO);

            if(xDiff && yDiff){
                monster.monsterMovement(player,map);
            }
        }
    }

    /*
     * This is to get player's previous position.
     * Used to put a dot instead after player leaves.
     */
    public int[] getPlayerPrevPos(Player player){
        int[] plyPreviousPos=new int[TWO];
        plyPreviousPos[ZERO] = player.getX();
        plyPreviousPos[ONE] = player.getY();
        return plyPreviousPos;
    }

    // only used in Rogue 1
    public boolean ifTheyMeet(Player player, Monster monster){
        return player.getX() == monster.getX() &&
                player.getY() == monster.getY();
    }

    /*
     *  Show the battle information
     */
    public void battleRound(Player player, Monster monster){
        System.out.printf("%s encountered a %s!\n", player.getName(), monster.getName());

        // if player/monster dies the loop stops
        while (player.getHealth()>0 && monster.getHealth()>0){
            System.out.println();
            System.out.printf("%s |", player.getName() + " " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.printf(" %s", monster.getName() + " " + monster.getHealth() + "/" + monster.getMaxHealth());

            player.playerAttackMonster(monster);

            System.out.println();
            System.out.printf("%s attacks %s for %d damage.\n", player.getName(), monster.getName(), player.getDamage());

            if(monster.getHealth()>0){
                monster.monsterAttackPlayer(player);
                System.out.printf("%s attacks %s for %d damage.\n", monster.getName(), player.getName(), monster.getDamage());
            }
            //System.out.println();
        }
        if(player.getHealth()>0){
            System.out.println(player.getName()+" wins!");
            System.out.println();
        }
        else if(monster.getHealth()>0){
            System.out.println(monster.getName()+" wins!");
        }
    }
    /*
     * This is used to battle.
     * If player wins, take off the monster.
     */
    private void ifBattle(Player player,Monster monster, Map map, GameEngine gameEngine) {
        ArrayList<Monster>monsters = map.getMonsterArray();
        for (int i = 0;i<monsters.size();i++){
            monster = monsters.get(i);
            if (player.getX() == monster.getX() && player.getY() == monster.getY()) {
                battleRound(player, monster);
                if(player.getHealth()>=0){
                    monsters.remove(monster);
                } else {
                    //System.out.println("(Press enter key to return to main menu)");
                    //gameEngine.returnMainMenu();
                    break;
                }
            }
        }
    }
}