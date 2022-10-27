/**
 * Handle the error message.
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */
import java.io.FileNotFoundException;

public class GameLevelNotFoundException extends FileNotFoundException{

    public GameLevelNotFoundException() {
        super("Game level not found exception");
    }

    public GameLevelNotFoundException(String s) {
        super(s);
    }

}
