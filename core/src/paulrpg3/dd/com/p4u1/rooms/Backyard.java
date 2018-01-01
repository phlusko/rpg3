package paulrpg3.dd.com.p4u1.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Paul on 12/30/2017.
 */

public class Backyard extends Room{

    public Backyard(){
        super();
        ground = Room.blankGround();
        map = new Map(ground);
        map.origin = map.spots[0][0];
    }
    @Override
    public boolean[][] getGround() {
        return new boolean[0][];
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public void drawMe(SpriteBatch batch) {

    }

    @Override
    public void dispose() {

    }
}
