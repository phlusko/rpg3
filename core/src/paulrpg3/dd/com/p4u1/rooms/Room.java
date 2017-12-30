package paulrpg3.dd.com.p4u1.rooms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

import paulrpg3.dd.com.p4u1.things.Thing;
import paulrpg3.dd.com.p4u1.utils.Duple;

/**
 * Created by Paul on 8/28/2017.
 */

public abstract class Room {
    public ArrayList<Thing> things;
    public boolean[][] ground;
    public Map map;
    public Room(){
        things = new ArrayList<Thing>();
    }

    public void removeThingGround() {
        for (Iterator<Thing> iter = things.iterator(); iter.hasNext();) {
            Thing curr = iter.next();
            if (curr.clip) {
                for (int x = 0; x < curr.width; x++) {
                    for (int y = 0; y < curr.height; y++) {
                        ground[x + curr.location.x][y + curr.location.y] = false;
                    }
                }
            }
        }
    }

    public boolean isDupleInThing(Duple arg0) {
        for (Iterator<Thing> iter = things.iterator(); iter.hasNext();) {
            if (iter.next().isDupleInThing(arg0)) {
                return true;
            }
        }
        return false;
    }

    public Thing getThingAtDuple(Duple arg0) {
        for (Iterator<Thing> iter = things.iterator(); iter.hasNext();) {
            Thing curr = iter.next();
            if (curr.isDupleInThing(arg0)) {
                return curr;
            }
        }
        return null;
    }

    public static boolean[][] blankGround() {
        boolean[][] ground = new boolean[20][10];
        for(int x = 0; x < ground.length; x++) {
            for (int y = 0; y < ground[x].length; y++) {
                ground[x][y] = true;
            }
        }
        return ground;
    }
    abstract public boolean[][] getGround();
    abstract public Map getMap();
    abstract public void drawMe(SpriteBatch batch);
    abstract public void dispose();
}
