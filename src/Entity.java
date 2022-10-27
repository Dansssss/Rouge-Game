/**
 * Hold and set position of entities.
 * @author : Danlan Chen, danlan.chen@student.unimelb.edu.au, and 1288528.
 *
 */
public abstract class Entity {

    private int x;
    private int y;

    public Entity(int x, int y, String s) {
        setX(x);
        setY(y);
    }

    public Entity(int x, int y) {
        setX(x);
        setY(y);
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

}



