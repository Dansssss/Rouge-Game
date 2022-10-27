/**
 * This is for COMP90041 Assignment 2, RogueExpanded
 * @author: Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 */

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import static java.lang.System.*;
import java.io.IOException;

public class GameEngine {

    static Scanner scanner = new Scanner(System.in);

    final static int DEPTH = 4;
    final static int WIDTH = 6;

    final static int P_INITIAL_POSITION_X = 1;
    final static int P_INITIAL_POSITION_Y = 1;
    final static int M_INITIAL_POSITION_X = 4;
    final static int M_INITIAL_POSITION_Y = 2;
    final static int INITIAL_HEALTH = 17;
    final static int TIMES = 3;

    final static int ZERO = 0;
    final static int ONE = 1;
    final static int TWO = 2;
    final static int THREE = 3;
    final static int FOUR = 4;


    public static void main(String[] args) throws GameLevelNotFoundException {

        Player player = new Player();
        Monster monster = new Monster();
        World world = new World();
        Map map = new Map(WIDTH, DEPTH);

        // Creates an instance of the game engine.
        GameEngine gameEngine = new GameEngine();

        // Runs the main game loop.
        gameEngine.runGameLoop(player, monster, world, map, gameEngine, scanner);

        scanner.close();

    }

    /*
     *  Logic for running the main game loop.
     */
    private void runGameLoop(Player player, Monster monster, World world, Map map, GameEngine gameEngine, Scanner scanner) throws GameLevelNotFoundException {

        while (true) {
            displayTitleText(player, monster);
            String inputCommand = "";
            boolean skip = false;

            while (!skip) {
                skip = doInputCommand(player,monster,world,map,gameEngine,scanner);
            }
        }

    }

    /*
     *  Displays the title text.
     */
    private void displayTitleText(Player player, Monster monster) {

        String titleText = " ____                        \n" +
                "|  _ \\ ___   __ _ _   _  ___ \n" +
                "| |_) / _ \\ / _` | | | |/ _ \\\n" +
                "|  _ < (_) | (_| | |_| |  __/\n" +
                "|_| \\_\\___/ \\__, |\\__,_|\\___|\n" +
                "COMP90041   |___/ Assignment ";

        System.out.println(titleText);

        System.out.println();

        displayPlayer(player);
        displayMonster(monster);

        System.out.println();
        System.out.println();

        displayContent();

    }

    /*
     *  Displays the player's information below title
     */
    private void displayPlayer(Player player) {

        //if there is no player, print Player: [None]  |
        if (player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            System.out.print("Player: [None]  |");
        } else System.out.printf("Player: %s %d/%d  |", player.getName(), player.getHealth(), player.getMaxHealth());
    }

    /*
     *  Displays the monster's information below title
     */
    private void displayMonster(Monster monster) {

        //Monster: [None]
        if (monster.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            System.out.print(" Monster: [None]");
        }
        //if there is one, print monster information
        else {
            System.out.printf(" Monster: %s %d/%d", monster.getName(), monster.getHealth(), monster.getMaxHealth());
        }
    }

    /*
     *  Displays hint content below player and monster
     */
    private void displayContent() {

        System.out.println("Please enter a command to continue.");
        System.out.println("Type 'help' to learn how to get started.");

    }

    /*
     *  Do every input command
     */
    private boolean doInputCommand(Player player, Monster monster, World world, Map map, GameEngine gameEngine, Scanner scanner) throws GameLevelNotFoundException {
        System.out.print("\n> ");
        String inputCommand = scanner.nextLine();
        boolean skip = false;

        if (inputCommand.contains("start ")) {
            readFile(inputCommand, player, monster, world, map, gameEngine,scanner);
        } else {
            switch (inputCommand) {
                case "help":
                    displayHelpContent();
                    break;
                case "commands":
                    displayCommandsContent();
                    break;
                case "player":
                    if (player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
                        playerCreation(player, monster);
                    } else {
                        displayPlayerInfo(player);
                    }
                    skip = true;
                    break;
                case "monster":
                    monsterCreation(player, monster, map);
                    skip = true;
                    break;
                case "start":
                    startGameWithNoArgument(player, monster, world, map);
                    skip = true;
                    break;
                case "exit":
                    exitGame();
                    skip = true;
                    break;
                case "save":
                    savePlayer(player);
                    break;
                case "load":
                    loadPlayer(player);
                    break;
            }
        }
        return skip;
    }

    /*
     *  Displays help information
     */
    private void displayHelpContent() {
        String helpContent = """
                Type 'commands' to list all available commands
                Type 'start' to start a new game
                Create a character, battle monsters, and find treasure!""";
        System.out.print(helpContent);
        System.out.println();
    }

    /*
     *  Displays commands
     */
    private void displayCommandsContent() {

        String CommandsContent = """
                help
                player
                monster
                start
                exit
                save
                load
                """;
        System.out.print(CommandsContent);
        //System.out.println();
    }

    /*
     *  Displays player information
     */
    private void displayPlayerInfo(Player player) {
        System.out.println(player.getName() + " (Lv. " + player.getLevel() + ")");
        System.out.println("Damage: " + player.getDamage());
        System.out.println("Health: " + player.getMaxHealth() + "/" + player.getMaxHealth());
        returnMainMenu();
    }

    /*
     *  Check if the first character of input is a letter
     */
    private boolean checkLetter(String args) {
        char x = args.charAt(ZERO);
        return (x>='a' && x<='z') || (x>='A' && x<='Z');
    }

    /*
     *  Check if there is an input
     */
    private boolean checkInputLength(String input) {
        return input.length() != ZERO;
    }

    /*
     *  Check if input is all integer
     */
    private boolean checkInputInt(String inputInt) {

        checkInputLength(inputInt);
        for (int i = 0; i < inputInt.length(); i++) {
            if (!Character.isDigit(inputInt.charAt(i)))
                return false;
        }
        return true;
    }

    /*
     *  Return to the main menu
     */
    public void returnMainMenu() {
        System.out.println();
        System.out.println("(Press enter key to return to main menu)");
        if (scanner.hasNextLine())
            scanner.nextLine();
    }

    /*
     *  Create a player if there is none
     */
    public Player playerCreation(Player player, Monster monster) {
        if (player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            System.out.println("What is your character's name?");

            String name = scanner.nextLine().strip();
            player.setName(name);
            // if the player name input is valid
            if (checkInputLength(name) && checkLetter(name)) {
                player.setX(P_INITIAL_POSITION_X);
                player.setY(P_INITIAL_POSITION_Y);
                System.out.println("Player '" + name + "' created.");
                returnMainMenu();
            }

        } else System.out.println(player);

        return player;

    }

    /*
     *  Create a monster if there is none
     */
    private Monster monsterCreation(Player player, Monster monster, Map map) {

        System.out.print("Monster name: ");
        String name = scanner.nextLine().strip();

        checkInputLength(name);

        System.out.print("Monster health: ");
        String healthString = scanner.nextLine().strip();

        checkInputInt(healthString);
        int health = Integer.parseInt(healthString);

        System.out.print("Monster damage: ");
        String damageString = scanner.nextLine().strip();

        checkInputInt(damageString);
        int damage = Integer.parseInt(damageString);
        if (health >= ZERO && damage >= ZERO) {

            //all the information is valid, store it
            monster.setName(name);
            monster.setMaxHealth(health);
            monster.setHealth(health);
            monster.setDamage(damage);
            monster.setX(M_INITIAL_POSITION_X);
            monster.setY(M_INITIAL_POSITION_Y);
            map.addMonster(new Monster(M_INITIAL_POSITION_X, M_INITIAL_POSITION_Y, name, health, damage));

            System.out.println("Monster '" + name + "' created.");
            returnMainMenu();
        }
        return monster;
    }

    /*
     *  To parse the file and form the world
     */
    private void readFile(String inputCommand, Player player, Monster monster, World world, Map map, GameEngine gameEngine,Scanner scanner) throws GameLevelNotFoundException {
        // If no player is created, ask user to create one
        if (player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            System.out.println("No player found, please create a player with 'player' first.");
            System.out.println();
            System.out.println("(Press enter key to return to main menu)");
            if (scanner.hasNextLine()){
                scanner.nextLine();
                displayTitleText(player,monster);
                doInputCommand(player,monster,world,map,gameEngine,scanner);
            }
        } else {
            String gameFile = inputCommand.substring(6) + ".dat";

            //initialize the input stream
            Scanner inputStream;
            try {
                File file = new File(gameFile);

                //if the gameFile.dat is not a valid file
                if (!file.isFile()) {
                    throw new GameLevelNotFoundException("Map not found.");
                }

                //if the gameFile.dat is valid
                inputStream = new Scanner(new FileInputStream(gameFile));

                int lineNum = ZERO;
                //use while loop to read each line in the game file
                while (inputStream.hasNextLine()) {
                    String line = inputStream.nextLine();

                    //<map width><map height>
                    if (lineNum == ZERO) {
                        String[] mapWh = line.split(" ");

                        int width = Integer.parseInt(mapWh[ZERO]);
                        int depth = Integer.parseInt(mapWh[ONE]);

                        map.setArraySize(depth, width);

                        map.setWidth(width);
                        map.setDepth(depth);
                    }
                    // map data
                    if (lineNum >= ONE && lineNum <= map.getDepth()) {
                        String[] mapRow = line.split("");
                        for (int i = 0; i < mapRow.length; i++) {
                            map.setMap(i,lineNum - ONE, mapRow[i]);
                        }
                    }
                    else if (lineNum == map.getDepth()+ONE) {
                        String[] playerInfo = line.split(" ");
                        int x = Integer.parseInt(playerInfo[ONE]);
                        player.setX(x);
                        int y = Integer.parseInt(playerInfo[TWO]);
                        player.setY(y);
                        player.putPlayer(map);
                    } else {
                        String[] entityInfoLine = line.split(" ");

                        if (entityInfoLine[ZERO].equals("monster")) {
                            int x = Integer.parseInt(entityInfoLine[ONE]);
                            int y = Integer.parseInt(entityInfoLine[TWO]);
                            String monName = entityInfoLine[THREE];
                            int monHealth = Integer.parseInt(entityInfoLine[FOUR]);
                            int monDamage = Integer.parseInt(entityInfoLine[5]);
                            map.addMonster(new Monster(x, y, monName, monHealth, monDamage));
                            putAllMonsters(map);

                        } else if (entityInfoLine[ZERO].equals("item")) {
                            int x = Integer.parseInt(entityInfoLine[ONE]);
                            int y = Integer.parseInt(entityInfoLine[TWO]);
                            String i = entityInfoLine[THREE];
                            map.addItem(new Item(x, y, i));
                            map.map[y][x] = i;
                            // ArrayList<Item> items = map.getItemArray();
                        }
                    }
                    lineNum++;
                }
                inputStream.close();
            } catch (GameLevelNotFoundException e) {
                out.println(e.getMessage());
                System.out.println();
                System.out.println("(Press enter key to return to main menu)");
                if (scanner.hasNextLine()){
                    scanner.nextLine();
                    displayTitleText(player,monster);
                    doInputCommand(player,monster,world,map,gameEngine,scanner);
                }
            } catch (FileNotFoundException e) {
                out.println(e.getMessage());
            }

            //the game file is all loaded
            map.map();
            runFileGame(player,monster,world,map,gameEngine,scanner);

        }
    }

    private void runFileGame(Player player,Monster monster,World world,Map map,GameEngine gameEngine,Scanner scanner) {

        do {

            System.out.print("> ");
            String move = scanner.nextLine();
            if (checkHome(move)) {
                break;
            }

            if(player.getHealth()>=0){
                world.movementFile(move,player,monster,map,gameEngine);
            } else break;


        } while (map.ifWarpStone() && player.getHealth()>=0);
        if (!map.ifWarpStone()) {
            player.setLevel(player.getLevel()+ONE);
            System.out.println("World complete! (You leveled up!)");
        }

        returnMainMenu();
        displayTitleText(player,monster);
    }

    /*
     *  Save the created player into player.dat
     */
    private void savePlayer(Player player) throws GameLevelNotFoundException {
        if (!player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            String playerInfo = String.format("%s %s", player.getName(), player.getLevel());
            PrintWriter outputStream = null;
            try {
                outputStream = new PrintWriter(new FileOutputStream("player.dat"));
            } catch (FileNotFoundException e) {
                System.out.println("No player data to save.");
                //System.out.println();
            } finally {
                assert outputStream != null;
                outputStream.println(playerInfo);
                outputStream.close();
                System.out.println("Player data saved.");
                //System.out.println();
            }
        } else {
            //no player is created.
            System.out.println("No player data to save.");
        }
    }

    /*
     *  Load player.dat into the game
     */
    public void loadPlayer(Player player) throws GameLevelNotFoundException {

        Scanner inputStream;
        String playerFileContent;
        String playerName;
        int playerLevel;

        try {
            inputStream = new Scanner(new FileInputStream("player.dat"));
            if (inputStream.hasNextLine()) {
                playerFileContent = inputStream.nextLine();
                String[] strs = playerFileContent.split(" ");
                playerName = strs[ZERO];
                player.setName(playerName);
                playerLevel = Integer.parseInt(strs[ONE]);
                player.setLevel(playerLevel);
                player.setMaxHealth(INITIAL_HEALTH + playerLevel * TIMES);
                System.out.println("Player data loaded.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No player data found.");

        }

    }

    /*
     *  Put all monsters down to the map
     */
    public void putAllMonsters(Map map) {
        ArrayList<Monster>monsters = map.getMonsterArray();
        for (int i = 0;i<monsters.size();i++){
            Monster monster = monsters.get(i);
            monster.putMonster(map);
        }
    }

    /*
     *  Start game with no argument.
     *  This function will behave as Rogue 1
     */
    public void startGameWithNoArgument(Player player, Monster monster, World world, Map map) {
        int monsArraySize = map.getMonsterArray().size();
        if (player.getName().equals("0-inCaseSomeoneNameLikeThis")) {
            System.out.println("No player found, please create a player with 'player' first.");
            returnMainMenu();
        } else if (monsArraySize == ZERO) {
            System.out.println("No monster found, please create a monster with 'monster' first.");
            returnMainMenu();
        } else if ((player.getHealth() != player.getMaxHealth() || monster.getHealth() != monster.getMaxHealth())) {

            player.setHealth(player.getMaxHealth());
            monster.setHealth(monster.getMaxHealth());

            player.setX(P_INITIAL_POSITION_X);
            player.setY(P_INITIAL_POSITION_Y);

            monster.setX(M_INITIAL_POSITION_X);
            monster.setY(M_INITIAL_POSITION_Y);

            //map.map();
            startRogue(player, monster, world, map);
        } else {
            map.setArraySize(DEPTH, WIDTH);
            map.fillBackground(DEPTH, WIDTH);
            ArrayList<Monster> monsters = map.getMonsterArray();
            player.setX(P_INITIAL_POSITION_X);
            player.setY(P_INITIAL_POSITION_Y);
            player.putPlayer(map);
            monster = monsters.get(ZERO);
            monster.putMonster(map);
            startRogue(player,monster,world,map);
        }
    }

    public void startRogue(Player player,Monster monster,World world,Map map) {
        map.map();
        while (!world.ifTheyMeet(player, monster)) {
            System.out.print("> ");
            String move = scanner.nextLine();
            if (checkHome(move)) {
                break;
            }
            world.movement(move, player, monster, map);
        }
        if (world.ifTheyMeet(player, monster))    //player encounters monster
            world.battleRound(player, monster);
        returnMainMenu();

    }

    /*
     *  Return to the main menu if user inputs home command
     */
    public boolean checkHome(String inputCommand) {
        if (inputCommand.equals("home")) {
            System.out.println("Returning home...");
            return true;

        }
        return false;
    }

    /*
     *  Terminate the application instead of using System.exit(0)
     */
    private void exitGame() {
        System.out.println("Thank you for playing Rogue!");
        exit(0);
    }

}